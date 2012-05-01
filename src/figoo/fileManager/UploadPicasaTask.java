//package figoo.fileManager;
//
//import com.google.gdata.client.photos.PicasawebService;
//import com.google.gdata.util.ServiceException;
//import figoo.ErrorDialog;
//import figoo.UploadPicasaProgressDialog;
//import figoo.google.FigooPicasaClient;
//import java.awt.Toolkit;
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import javax.swing.SwingWorker;
//
///**
// *
// * @author Lada Riha
// */
//public class UploadPicasaTask extends SwingWorker<Void, Void> {
//
//    /**
//     *
//     */
//    public UploadPicasaProgressDialog cd;
//    private String name;
//    private String desc;
//    private String size;
//    private PicasawebService picasa;
//    private String username;
//    private String access;
//    private ArrayList<File> files;
//    private boolean newAlbum;
//    private String location;
//
//    /**
//     *
//     * @param album
//     * @param desc album description
//     * @param size
//     * @param access
//     * @param d
//     * @param files
//     * @param newAlbum
//     * @param picasa
//     * @param username
//     * @param location
//     */
//    public UploadPicasaTask(String album, String desc, String size, String access, UploadPicasaProgressDialog d, ArrayList<File> files, boolean newAlbum, PicasawebService picasa, String username, String location) {
//        this.cd = d;
//        this.location = location;
//        this.name = album;
//        this.access = access;
//        this.files = files;
//        this.picasa = picasa;
//        this.username = username;
//        this.size = size;
//        this.newAlbum = newAlbum;
//    }
//
//    /**
//     *
//     * @param album
//     * @param desc
//     * @param size
//     * @param d
//     * @param files
//     * @param newAlbum
//     * @param picasa
//     * @param username
//     */
//    public UploadPicasaTask(String album, String desc, String size, UploadPicasaProgressDialog d, ArrayList<File> files, boolean newAlbum, PicasawebService picasa, String username) {
//        this.cd = d;
//        this.name = album;
//        this.files = files;
//        this.picasa = picasa;
//        this.username = username;
//        this.size = size;
//        this.newAlbum = newAlbum;
//    }
//
//    @Override
//    public Void doInBackground() {
//        try {
//            if (newAlbum) {
//                uploadFileToNewAlbum();
//            } else {
//                uploadFileToExistingAlbum();
//            }
//            this.cd.setVisible(false);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error", ex.getMessage());
//            ed.setVisible(true);
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @throws IOException
//     */
//    public void uploadFileToNewAlbum() throws IOException {
//        try {
//            // create album
//            String id = FigooPicasaClient.addAlbum(this.name, this.desc, this.access, this.picasa, this.username, this.location);
//            int progress = 0;
//            File image;
//            for (int i = 0; i < files.size(); i++) {
//                synchronized (this) {
//                    image = files.get(i);
//                    this.cd.getjLabel2().setText("Uploading " + image.getName());
//                    if (!this.size.equals("o")) {
//                        try {
//                            image = FileManager.getScaledImage(files.get(i), this.size);
//                            FigooPicasaClient.addPhoto(id, picasa, username, image);
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                            try {
//                                FigooPicasaClient.addPhoto(id, picasa, username, files.get(i));
//                            } catch (Exception a) {
//                                a.printStackTrace();
//                                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error: " + image.getName(), ex.getMessage());
//                                ed.setVisible(true);
//                            }
//                        }
//                    } else {
//                        FigooPicasaClient.addPhoto(id, picasa, username, files.get(i));
//                    }
//
//                    progress = getProgress();
//                    setProgress(progress + 1);
//                }
//                // create sized image in tmp
//
//            }
//        } catch (MalformedURLException ex) {
//            ex.printStackTrace();
//            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error", ex.getMessage());
//            ed.setVisible(true);
//        } catch (ServiceException ex) {
//            ex.printStackTrace();
//            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error", ex.getMessage());
//            ed.setVisible(true);
//        }
//    }
//
//    /**
//     *
//     * @throws IOException
//     */
//    public void uploadFileToExistingAlbum() throws IOException {
//        try {
//            // create album
//            String id = FigooPicasaClient.getAlbumIdByName(picasa, username, name);
//            int t = id.indexOf("mid/");
//            String p = id.substring(t + 4);
//
//            id = p;
//            int progress = 0;
//            File image;
//            for (int i = 0; i < files.size(); i++) {
//                // create sized image in tmp
//                image = files.get(i);
//                this.cd.getjLabel2().setText("Uploading " + image.getName());
//                if (!this.size.equals("o")) {
//                    try {
//                        image = FileManager.getScaledImage(files.get(i), this.size);
//                        FigooPicasaClient.addPhoto(id, picasa, username, image);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                        try {
//                            FigooPicasaClient.addPhoto(id, picasa, username, files.get(i));
//                        } catch (Exception a) {
//                            a.printStackTrace();
//                            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error: " + image.getName(), ex.getMessage());
//                            ed.setVisible(true);
//                        }
//                    }
//                } else {
//                    FigooPicasaClient.addPhoto(id, picasa, username, files.get(i));
//                }
//
//                progress = getProgress();
//                setProgress(progress + 1);
//            }
//        } catch (MalformedURLException ex) {
//            ex.printStackTrace();
//            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error", ex.getMessage());
//            ed.setVisible(true);
//        } catch (ServiceException ex) {
//            ex.printStackTrace();
//            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), false, "Uploading error", ex.getMessage());
//            ed.setVisible(true);
//        }
//    }
//    /*
//     * Executed in event dispatching thread
//     */
//
//    @Override
//    public void done() {
//        Toolkit.getDefaultToolkit().beep();
//        cd.setVisible(false);
//        cd.getF().refresh();
//    }
//}
