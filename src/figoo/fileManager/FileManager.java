/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figoo.fileManager;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import com.mortennobel.imagescaling.ResampleOp;
import figoo.CombineFileDialog;
import figoo.ErrorDialog;
import figoo.FigooView;
import figoo.SplitFileDialog;
import figoo.classes.FileInfo;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.DefaultListModel;

/**
 *
 * @author Lada Riha
 */
public class FileManager {

    private static int height;
    private static int width;
    private static int folders = 0;
    private static int files = 0;
    private static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * Renames file
     * @param name old name (absolute path)
     * @param newname new name
     * @throws Exception
     */
    public static void rename(String name, String newname) throws Exception {
        File old = new File(name);
        String sub = name.substring(0, name.lastIndexOf(System.getProperty("file.separator")) + 1);
        newname = sub + newname;
        File newFile = new File(newname);
        boolean test = old.renameTo(newFile);
        if (!test) {
            throw new Exception("Unable to rename file or folder");
        }
    }

    /**
     * Makes new directory
     * @param path path where to make new directory
     * @param name name of new directory
     * @throws IOException
     * @throws Exception
     */
    public static void makeDir(String path, String name) throws IOException, Exception {
        File f = new File(path + System.getProperty("file.separator") + name);
        boolean created = f.mkdirs();
        if (!created) {
            throw new Exception("Unable to create new dir");
        }
    }

    /**
     * Gets file info
     * @param path file to get info about
     * @param fw
     * @return
     */
    public static FileInfo getFileInfo(String path, FigooView fw) {
        folders = 0;
        files = 0;

        FileInfo fi = new FileInfo();
        fi.setPath(path);
        File f = new File(path);

        fi.setName(f.getName());
        long modi = f.lastModified();
        Date d = new Date(modi);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String day = c.get(Calendar.DAY_OF_MONTH) + "";
        String year = c.get(Calendar.YEAR) + "";
        String hour = c.get(Calendar.HOUR_OF_DAY) + "";
        String minute = c.get(Calendar.MINUTE) + "";
        String date = day + " " + month + " " + year + ", " + hour + ":" + minute;
        fi.setLastModif(date);
        if (f.isDirectory()) {
            fi.setIsDir(true);
            fi.setOpenWith("");
            long size = getFileSize(f);
            fi.setHash("Not available for folders");
            String mb = (float) ((float) size / (float) 1000000) + "000";
            mb = mb.substring(0, mb.indexOf(".") + 3);
            fi.setSize(size / 1000 + "  kB (" + mb + " MB) in " + files + " files and " + folders + " folders");
        } else {
            fi.setHash(getHashFromFile(f, "MD5"));
            fi.setHash2(getHashFromFile(f, "SHA1"));
            String mb = (float) ((float) f.length() / (float) 1000000) + "000";
            mb = mb.substring(0, mb.indexOf(".") + 3);
            fi.setSize(f.length() / 1000 + "  kB (" + mb + " MB)");
            fi.setIsDir(false);
        }
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
        String filetype = fc.getTypeDescription(f);
        fi.setIcon(fw.getIcon(f));
        fi.setCanRead(f.canRead());
        fi.setCanWrite(f.canWrite());
        fi.setCanExecute(f.canExecute());

        String ext = "";
        try {
            ext = " (*" + f.getName().substring(f.getName().lastIndexOf(".")) + ")";
        } catch (Exception s) {
        }
        fi.setType(filetype + ext);

        String mime = new MimetypesFileTypeMap().getContentType(f);
        mime = mime.trim();
        fi.setMimeType(mime);
        return fi;
    }

    /**
     * Returns file's size in bytes
     * @param folder
     * @return
     */
    public static long getFileSize(File folder) {
        folders++;
        long foldersize = 0;
        File[] fl = folder.listFiles();
        if (fl != null) {
            for (int i = 0; i < fl.length; i++) {
                if (fl[i].isDirectory()) {
                    foldersize += getFileSize(fl[i]);
                } else {
                    files++;
                    foldersize += fl[i].length();
                }
            }
        }
        return foldersize;
    }

    /**
     * Gets file's MD5 hash
     * @param file
     * @return
     */
    private static String getHashFromFile(File file, String hashType) {
        try {
            MessageDigest digest = MessageDigest.getInstance(hashType);
            FileInputStream fis = new FileInputStream(file);
            if (file.length() <= Integer.MAX_VALUE) {
                int velikost = (int) file.length();
                byte[] pole = new byte[velikost];
                byte[] pole2;
                fis.read(pole);
                digest.update(pole);
                pole2 = digest.digest();
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < pole2.length; i++) {
                    if (Integer.toHexString(0xFF & pole2[i]).length() == 1) {
                        buffer.append("0" + Integer.toHexString(0xFF & pole2[i]));
                    } else {
                        buffer.append(Integer.toHexString(0xFF & pole2[i]));
                    }
                }
                return buffer.toString();
            }
        } catch (Exception a) {
            a.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error getting hash", a.getMessage());
            ed.setVisible(true);
        }
        return "";
    }

    /**
     * Downloads single photo from URL
     * @param urlAddress URL address
     * @param photoName name of photo
     * @param dir directory where to download
     * @param size which size download (on error downloads original size)
     */
    public static void downloadFromPicasaURL(String urlAddress, String photoName, String dir, String size) {
        InputStream is = null;
        String tmp = urlAddress;
        OutputStream outStream = null;

        try {

            String end = urlAddress.substring(urlAddress.lastIndexOf("/"));
            urlAddress = urlAddress.substring(0, urlAddress.lastIndexOf("/"));
            urlAddress = urlAddress.substring(0, urlAddress.lastIndexOf("/") + 1) + size + end;
            URL url = new URL(urlAddress);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            outStream = new BufferedOutputStream(new FileOutputStream(dir + System.getProperty("file.separator") + photoName));
            byte[] buf = new byte[10240];
            int byteRead, byteWritten = 0;
            while ((byteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, byteRead);
                byteWritten += byteRead;
            }
        } catch (Exception ex) {// try original url

            try {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                URL url = new URL(tmp);
                URLConnection con = url.openConnection();
                is = con.getInputStream();
                outStream = new BufferedOutputStream(new FileOutputStream(dir + System.getProperty("file.separator") + photoName));

                byte[] buf = new byte[10240];
                int byteRead, byteWritten = 0;
                while ((byteRead = is.read(buf)) != -1) {
                    outStream.write(buf, 0, byteRead);
                    byteWritten += byteRead;
                }

            } catch (Exception a) {
                a.printStackTrace();
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "downloadFromPicasaURL error", a.getMessage());
                ed.setVisible(true);
            }
        } finally {
            try {
                is.close();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "downloadFromPicasaURL error", e.getMessage());
                ed.setVisible(true);
            }
        }
    }

    /**
     * Downloads low quality photo to temporary directory, file is deleted on exit. If it fails to download low quality, it tries to download original photo
     * @param urlAddress photo's url address
     * @param photoName name of photo
     * @return
     */
    public static File downloadTmpFromPicasaURL(String urlAddress, String photoName) {
        InputStream is = null;
        String tmp = urlAddress;
        String size = "s640";
        OutputStream outStream = null;
        File tmpFile = null;
        try {
            String end = urlAddress.substring(urlAddress.lastIndexOf("/"));
            urlAddress = urlAddress.substring(0, urlAddress.lastIndexOf("/"));
            urlAddress = urlAddress.substring(0, urlAddress.lastIndexOf("/") + 1) + size + end;
            URL url = new URL(urlAddress);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            tmpFile = File.createTempFile("tmpPicasa", photoName.substring(photoName.lastIndexOf(".")));
            tmpFile.deleteOnExit();
            outStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
            byte[] buf = new byte[10240];
            int byteRead, byteWritten = 0;
            while ((byteRead = is.read(buf)) != -1) {
                outStream.write(buf, 0, byteRead);
                byteWritten += byteRead;
            }
        } catch (Exception ex) {// try original url

            try {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                URL url = new URL(tmp);
                URLConnection con = url.openConnection();
                is = con.getInputStream();
                tmpFile = File.createTempFile("tmpPicasa", photoName.substring(photoName.lastIndexOf(".")));
                tmpFile.deleteOnExit();
                outStream = new BufferedOutputStream(new FileOutputStream(tmpFile));
                byte[] buf = new byte[10240];
                int byteRead, byteWritten = 0;
                while ((byteRead = is.read(buf)) != -1) {
                    outStream.write(buf, 0, byteRead);
                    byteWritten += byteRead;
                }
            } catch (Exception a) {
                a.printStackTrace();
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "downloadFromPicasaURL error", a.getMessage());
                ed.setVisible(true);
            }
        } finally {
            try {
                is.close();
                outStream.close();

            } catch (IOException e) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "downloadFromPicasaURL error", e.getMessage());
                ed.setVisible(true);
                e.printStackTrace();
            }
        }
        return tmpFile;
    }

    /**
     *  Downloads file from Picasa
     * @param album album to download
     * @param id photo's id
     * @param picasa
     * @param username
     * @param to target directory
     * @param size selected size to download
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void downloadFileFromPicasa(AlbumFeed album, String id, PicasawebService picasa, String username, String to, String size) throws MalformedURLException, IOException, ServiceException {

        List<PhotoEntry> photos = album.getPhotoEntries();
        PhotoEntry photo;
        String url;
        String name;
        for (int i = 0; i < photos.size(); i++) {
            photo = photos.get(i);
            if (photo.getId().equalsIgnoreCase(id)) {
                url = (photo.getMediaThumbnails().get(0).getUrl());
                name = photo.getTitle().getPlainText();
                downloadFromPicasaURL(url, name, to, size);
            }
        }
    }

    /**
     * Returns list of all Picasa supported files in selected directory, so far works only for photos, not videos
     * @param dir selected directory to be searched
     * @return
     */
    public static ArrayList<File> getPicasaSupportedFiles(String dir) {
        File folder = new File(dir);

        String[] files = folder.list();
        File f;
        ArrayList<File> filesToUpload = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            f = new File(dir + System.getProperty("file.separator") + files[i]);
            String mime = new MimetypesFileTypeMap().getContentType(f);
            mime = mime.trim();
            if (mime.endsWith("bmp") || mime.endsWith("gif") || mime.endsWith("jpeg") || mime.endsWith("png")) {
                filesToUpload.add(f);
            }
        }
        return filesToUpload;
    }

    /**
     * Returns scaled image
     * @param file image to be scaled
     * @param size desired size
     * @return
     * @throws IOException
     * @throws Exception
     */
    public synchronized static File getScaledImage(File file, String size) throws IOException, Exception {

        int newSize = Integer.valueOf(size).intValue();
        if (isResizeNeccessery(file, newSize)) {
            int newHeight;
            int newWidth;
            newWidth = newSize;
            float change = change = (float) ((float) width / (float) newWidth);
            newHeight = (int) (height / change);
            if (height >= width) {
                newHeight = newSize;
                change = (float) ((float) height / (float) newHeight);
                newWidth = (int) (width / change);
            }
            BufferedImage originalImage = ImageIO.read(file);
            ResampleOp resampleOp = new ResampleOp(newWidth, newHeight);
            BufferedImage resizedImage = resampleOp.filter(originalImage, null);
            String name = (file.getCanonicalFile().getName());
            name = name.substring(0, name.lastIndexOf("."));
            File newImage = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
            newImage.deleteOnExit();
            ImageIO.write(resizedImage, file.getName().substring(file.getName().lastIndexOf(".") + 1), newImage);
            return newImage;
        }
        return file;
    }

    private static boolean isResizeNeccessery(File newImage, int size) throws IOException, Exception {

        ImageInputStream imageStream = ImageIO.createImageInputStream(newImage);
        Iterator<ImageReader> readers = ImageIO.getImageReaders(imageStream);
        ImageReader reader = null;
        if (!readers.hasNext()) {
            throw new Exception("Cannot read image file");
        } else {
            reader = readers.next();
        }
        reader.setInput(imageStream, true, true);

        width = reader.getWidth(0);
        height = reader.getHeight(0);
        reader.dispose();
        imageStream.close();

        if (width == size) {
            return false;
        }
        return true;
    }

    /**
     * Downloads file from given url address
     * @param exportUrl
     * @param filepath target folder
     * @param client
     * @throws IOException
     * @throws MalformedURLException
     * @throws ServiceException
     */
    public static void downloadFile(String exportUrl, String filepath, DocsService client) throws IOException, MalformedURLException, ServiceException {
        MediaContent mc = new MediaContent();
        mc.setUri(exportUrl);

        MediaSource ms = client.getMedia(mc);

        InputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = ms.getInputStream();
            outStream = new FileOutputStream(filepath);

            int c;
            while ((c = inStream.read()) != -1) {
                outStream.write(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "uploadFileFolder", ex.getMessage());
            ed.setVisible(true);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.flush();
                outStream.close();
            }
        }
    }

    /**
     * Downloads file from given url address
     * @param exportUrl
     * @param filepath target folder
     * @param client
     * @param spread
     * @throws IOException
     * @throws MalformedURLException
     * @throws ServiceException
     */
    public static void downloadFile(String exportUrl, String filepath, DocsService client, SpreadsheetService spread) throws IOException, MalformedURLException, ServiceException {
        MediaContent mc = new MediaContent();
        mc.setUri(exportUrl);

        MediaSource ms = client.getMedia(mc);

        InputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = ms.getInputStream();
            outStream = new FileOutputStream(filepath);

            int c;
            while ((c = inStream.read()) != -1) {
                outStream.write(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "uploadFileFolder", ex.getMessage());
            ed.setVisible(true);
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.flush();
                outStream.close();
            }
        }
    }

    public static void makeZip(String pathFrom, String pathTo, String archiveName, int compression) throws FileNotFoundException, IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(pathTo + System.getProperty("file.separator") + archiveName + ".zip"));
        zos.setLevel(compression);
        zipChildrenFiles(zos, pathFrom, "");
        zos.close();
    }

    private static void zipChildrenFiles(ZipOutputStream zos, String pathFrom, String prefix) throws IOException {

        File f = new File(pathFrom);
        File[] files = f.listFiles();
        File tmp;
        ZipEntry archiveEntry;
        if (f.isDirectory()) {
            archiveEntry = new ZipEntry(prefix + f.getName() + System.getProperty("file.separator"));
            prefix = prefix + f.getName() + System.getProperty("file.separator");
            zos.putNextEntry(archiveEntry);
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    tmp = files[i];
                    if (tmp.isDirectory()) {
                        zipChildrenFiles(zos, tmp.getAbsolutePath(), prefix);
                    } else if (tmp.isFile()) {
                        byte[] buffer = new byte[1024];
                        int added = 0;
                        archiveEntry = new ZipEntry(prefix + tmp.getName());
                        zos.putNextEntry(archiveEntry);
                        FileInputStream fis = new FileInputStream(tmp);
                        while ((added = fis.read(buffer)) != -1) {
                            zos.write(buffer, 0, added);
                        }
                        fis.close();
                    }
                }
            }

        } else if (f.isFile()) {
            byte[] buffer = new byte[1024];
            int added = 0;
            archiveEntry = new ZipEntry(prefix + f.getName());
            zos.putNextEntry(archiveEntry);
            FileInputStream fis = new FileInputStream(f);
            while ((added = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, added);
            }
            fis.close();
        }
    }

    /**
     * Returns structure of files and directories
     * @param dir where to start
     * @return
     */
    public static String structureToString(String dir) {
        StringBuffer sb = new StringBuffer();
        File directory = new File(dir);
        File[] files = directory.listFiles();
        sb.append(directory.getAbsolutePath() + System.getProperty("line.separator"));
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isHidden()) {
                if (files[i].isDirectory()) {
                    sb.append("   <" + files[i].getName() + ">");
                } else {
                    sb.append("    " + files[i].getName() + "");
                }
                sb.append(System.getProperty("line.separator"));
            }
        }
        return sb.toString();
    }

    /**
     * Returns structure of files and directories with recursive search.
     * @param dir where to start searching
     * @param depth depth of search
     * @param indention indention in txt file
     * @param include whether include text header
     * @return
     */
    public static String structureToString(String dir, int depth, String indention, boolean include) {
        StringBuffer sb = new StringBuffer();

        if (depth >= 0) {
            File directory = new File(dir);
            File[] files = directory.listFiles();
            if (include) {
                sb.append(indention + "<" + directory.getAbsolutePath() + ">" + System.getProperty("line.separator"));
            }
            indention = indention + "  ";
            int newDepth = depth - 1;
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isHidden()) {
                    if (files[i].isDirectory()) {
                        sb.append(indention + "<" + files[i].getName() + ">");
                        sb.append(System.getProperty("line.separator"));
                        String s = FileManager.structureToString(files[i].getAbsolutePath(), newDepth, indention, false);
                        sb.append(s);
                    } else {
                        sb.append(indention + files[i].getName());
                        sb.append(System.getProperty("line.separator"));
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Splits file into smaller files
     * @param splitSize part size in bytes
     * @param sourceFile file to split
     * @param targetDirectory where to save new parts
     * @param cd 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void splitFile(int splitSize, File sourceFile, String targetDirectory, SplitFileDialog cd) throws FileNotFoundException, IOException {

        InputStream inputStream = new FileInputStream(sourceFile);
        long fileSize = sourceFile.length();
        int actu = 1;
        long left = fileSize;
        while (left > 0) {
            String att = "";
            if (actu < 10) {
                att = "00" + actu;
            } else if (actu > 9 && actu < 100) {
                att = "0" + actu;
            } else if (actu > 99) {
                att = actu + "";
            }

            File ff = new File(targetDirectory + System.getProperty("file.separator") + sourceFile.getName() + "." + att);
            cd.getjLabel5().setText(ff.getName());
            if (!ff.exists()) {
                ff.createNewFile();
            }
            OutputStream out = new FileOutputStream(ff);
            byte[] buf = new byte[10000000];
            if (splitSize < 10000000) {
                buf = new byte[splitSize];
            }
            int len;
            long smallleft = splitSize;
            while (smallleft > 0 && (len = inputStream.read(buf)) != -1) {
                smallleft = smallleft - buf.length;
                out.write(buf, 0, len);
            }
            out.flush();
            out.close();
            actu = actu + 1;
            left = left - splitSize;
        }
        inputStream.close();
    }

    /**
     * Combines parts into one file
     * @param firstPart first part to combine (i.e. 001])
     * @param targetDirectory where to save combined file
     * @param cd
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void combineFile(String firstPart, String targetDirectory, CombineFileDialog cd) throws FileNotFoundException, IOException {
        File part = new File(firstPart);
        String nextFileName = firstPart;
        File[] files = part.getParentFile().listFiles();
        String dir = part.getParent();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                list.add(files[i].getAbsolutePath());
            }
        }
        boolean nextPart = true;
        String name2 = part.getName();
        String name = name2.substring(0, name2.lastIndexOf("."));
        File combinedFile = new File(targetDirectory + System.getProperty("file.separator") + name);
        combinedFile.createNewFile();
        OutputStream out = new FileOutputStream(combinedFile);
        InputStream inputStream;
        int len = 0;
        int nextPartNumber = 1;
        while (nextPart) {
            part = new File(nextFileName);
            cd.getjLabel2().setText(part.getName());
            inputStream = new FileInputStream(part);
            byte[] buf = new byte[5000000];
            while ((len = inputStream.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.flush();
            inputStream.close();

            String att = "";
            nextPartNumber = nextPartNumber + 1;
            if (nextPartNumber < 10) {
                att = "00" + nextPartNumber;
            } else if (nextPartNumber > 9 && nextPartNumber < 100) {
                att = "0" + nextPartNumber;
            } else if (nextPartNumber > 99) {
                att = nextPartNumber + "";
            }
            nextFileName = dir + System.getProperty("file.separator") + name + "." + att;
            if (list.contains(nextFileName)) {
                nextPart = true;
                list.remove(nextFileName);
            } else {
                nextPart = false;
            }
        }
        out.close();
    }

        /**
     * Rename and copy multiple files
     * @param dir where to copy new named files
     * @param model list of files to be renamed
     * @param pattern name pattern
     * @return report info
     */
    public static String batchRenameKeepSuffix(File dir, DefaultListModel model, String pattern) {
        int alphabetCounter = 0;
        String newname = "";
        String name = "";
        String suffix = "";
        StringBuffer sb = new StringBuffer();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        sb.append("BATCH RENAME INFO");
        sb.append(System.getProperty("line.separator"));
        int errors = 0;
        for (int i = 0; i < model.size(); i++) {
            File old = new File((String) model.get(i));

            if (alphabetCounter > alphabet.length) {
                sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because number of files (" + model.size() + ") is greater than number of letters ( " + ALPHABET.length + "}");
                errors++;
                sb.append(System.getProperty("line.separator"));
            } else {
                try {
                    name = old.getName();
                    suffix = name.substring(name.lastIndexOf("."));
                    name  =  name.substring(0, name.lastIndexOf("."));

                    newname = pattern.replaceAll("&N", name);
                    if (pattern.contains(">") || pattern.contains("<")) {
                        newname = newname.replaceAll("<", alphabet[alphabetCounter]);
                        newname = newname.replaceAll(">", ALPHABET[alphabetCounter]);
                        alphabetCounter++;
                    }

                    newname = newname.replaceAll("#", i + "");
                    File newFile = new File(dir+System.getProperty("file.separator")+newname+suffix);
                    if (newFile.exists()) {
                        throw new Exception("Target file already exists ");
                    } else {
                        newFile.createNewFile();
                    }

                    FileChannel source = null;
                    FileChannel destination = null;
                    try {
                        source = new FileInputStream(old).getChannel();
                        destination = new FileOutputStream(newFile).getChannel();
                        destination.transferFrom(source, 0, source.size());
                    } finally {
                        if (source != null) {
                            source.close();
                        }
                        if (destination != null) {
                            destination.close();
                        }
                        sb.append((i + 1) + ") File " + old.getAbsolutePath() + " was renamed to " + newFile.getAbsolutePath());
                        sb.append(System.getProperty("line.separator"));
                    }
                } catch (Exception a) {
                    sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because: " + a.getMessage() + " >> " + a.toString());
                    errors++;
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("SUMMARY ");
        sb.append(System.getProperty("line.separator"));
        sb.append("Files renamed: " + (model.size() - errors));
        sb.append(System.getProperty("line.separator"));
        sb.append("Files NOT renamed: " + errors);
        return sb.toString();
    }

    /**
     * Rename and copy multiple files
     * @param dir where to copy new named files
     * @param model list of files to be renamed
     * @param pattern name pattern 
     * @return report info
     */
    public static String batchRenameRemoveSuffix(File dir, DefaultListModel model, String pattern) {
        int alphabetCounter = 0;
        String newname = "";
        String name = "";
        StringBuffer sb = new StringBuffer();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        sb.append("BATCH RENAME INFO");
        sb.append(System.getProperty("line.separator"));
        int errors = 0;
        for (int i = 0; i < model.size(); i++) {
            File old = new File((String) model.get(i));

            if (alphabetCounter > alphabet.length) {
                sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because number of files (" + model.size() + ") is greater than number of letters ( " + ALPHABET.length + "}");
                errors++;
                sb.append(System.getProperty("line.separator"));
            } else {
                try {
                    name = old.getName();
                    name  =  name.substring(0, name.lastIndexOf("."));

                    newname = pattern.replaceAll("&N", name);
                    if (pattern.contains(">") || pattern.contains("<")) {
                        newname = newname.replaceAll("<", alphabet[alphabetCounter]);
                        newname = newname.replaceAll(">", ALPHABET[alphabetCounter]);
                        alphabetCounter++;
                    }

                    newname = newname.replaceAll("#", i + "");
                    File newFile = new File(dir+System.getProperty("file.separator")+newname);
                    if (newFile.exists()) {
                        throw new Exception("Target file already exists ");
                    } else {
                        newFile.createNewFile();
                    }

                    FileChannel source = null;
                    FileChannel destination = null;
                    try {
                        source = new FileInputStream(old).getChannel();
                        destination = new FileOutputStream(newFile).getChannel();
                        destination.transferFrom(source, 0, source.size());
                    } finally {
                        if (source != null) {
                            source.close();
                        }
                        if (destination != null) {
                            destination.close();
                        }
                        sb.append((i + 1) + ") File " + old.getAbsolutePath() + " was renamed to " + newFile.getAbsolutePath());
                        sb.append(System.getProperty("line.separator"));
                    }
                } catch (Exception a) {
                    sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because: " + a.getMessage() + " >> " + a.toString());
                    errors++;
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("SUMMARY ");
        sb.append(System.getProperty("line.separator"));
        sb.append("Files renamed: " + (model.size() - errors));
        sb.append(System.getProperty("line.separator"));
        sb.append("Files NOT renamed: " + errors);
        return sb.toString();
    }

    /**
     * Rename and copy multiple files
     * @param dir where to copy new named files
     * @param model list of files to be renamed
     * @param pattern name pattern
     * @param suffix change original suffix to
     * @return report info
     */
        public static String batchRenameChangeSuffix(File dir, DefaultListModel model, String pattern, String suffix) {
        int alphabetCounter = 0;
        String newname = "";
        String name = "";

        StringBuffer sb = new StringBuffer();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        sb.append("BATCH RENAME INFO");
        sb.append(System.getProperty("line.separator"));
        int errors = 0;
        for (int i = 0; i < model.size(); i++) {
            File old = new File((String) model.get(i));

            if (alphabetCounter > alphabet.length) {
                sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because number of files (" + model.size() + ") is greater than number of letters ( " + ALPHABET.length + "}");
                errors++;
                sb.append(System.getProperty("line.separator"));
            } else {
                try {
                    name = old.getName();
                    name  =  name.substring(0, name.lastIndexOf("."));

                    newname = pattern.replaceAll("&N", name);
                    if (pattern.contains(">") || pattern.contains("<")) {
                        newname = newname.replaceAll("<", alphabet[alphabetCounter]);
                        newname = newname.replaceAll(">", ALPHABET[alphabetCounter]);
                        alphabetCounter++;
                    }

                    newname = newname.replaceAll("#", i + "");
                    File newFile = new File(dir+System.getProperty("file.separator")+newname+"."+suffix);
                    if (newFile.exists()) {
                        throw new Exception("Target file already exists ");
                    } else {
                        newFile.createNewFile();
                    }

                    FileChannel source = null;
                    FileChannel destination = null;
                    try {
                        source = new FileInputStream(old).getChannel();
                        destination = new FileOutputStream(newFile).getChannel();
                        destination.transferFrom(source, 0, source.size());
                    } finally {
                        if (source != null) {
                            source.close();
                        }
                        if (destination != null) {
                            destination.close();
                        }
                        sb.append((i + 1) + ") File " + old.getAbsolutePath() + " was renamed to " + newFile.getAbsolutePath());
                        sb.append(System.getProperty("line.separator"));
                    }
                } catch (Exception a) {
                    sb.append((i + 1) + ") ==ERROR== File " + old.getAbsolutePath() + " was not renamed because: " + a.getMessage() + " >> " + a.toString());
                    errors++;
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }
        sb.append(System.getProperty("line.separator"));
        sb.append("SUMMARY ");
        sb.append(System.getProperty("line.separator"));
        sb.append("Files renamed: " + (model.size() - errors));
        sb.append(System.getProperty("line.separator"));
        sb.append("Files NOT renamed: " + errors);
        return sb.toString();
    }

        /**
         * Saves string to file
         * @param dir directory of the target file
         * @param name file name
         * @param textToSave text to save
         * @throws IOException
         */
    public static void saveStringToFile(File dir, String name, String textToSave) throws IOException {
        
        File f = new File(dir+System.getProperty("file.separator")+name);
        FileWriter fw = new FileWriter(f);
        fw.write(textToSave);
        fw.flush();
        fw.close();
    }
}


