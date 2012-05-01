/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package figoo.fileManager;

import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import figoo.classes.AlbumInfo;
import figoo.classes.PhotoInfo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;

/**
 *
 * @author Lada Riha
 */
public class FigooPicasaClient {

    /**
     * Login to Picasa and returns instance of PicasawebService
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    public static PicasawebService logPicasa(String username, String password) throws AuthenticationException {
        PicasawebService myService = new PicasawebService("figoo");
        myService.setUserCredentials(username, password);
        return myService;
    }

    /**
     * Returns titles of all user's albums in Picasa
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @return all album titles
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<String> getAllPicasaAlbumTitle(PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();

        AlbumEntry myAlbum;
        ArrayList<String> titles = new ArrayList<String>();
        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            titles.add(myAlbum.getTitle().getPlainText());//.put(myAlbum.getId(), myAlbum.getTitle().getPlainText());
        }
        return titles;
    }

    /**
     * Returns ID of all user's albums in Picasa
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @return all album IDs
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<String> getAllPicasaAlbumID(PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;
        ArrayList<String> ids = new ArrayList<String>();

        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            ids.add(myAlbum.getId());
        }
        return ids;
    }

    /**
     * Returns titles of all photos in given album
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album id (full url)
     * @return all photos's titles
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<String> listPicasaAlbumTitle(PicasawebService picasa, String id, String username) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + id);

        AlbumFeed album = picasa.getFeed(feedUrl, AlbumFeed.class);
        List<PhotoEntry> photos = album.getPhotoEntries();
        PhotoEntry photo;
        ArrayList<String> title = new ArrayList<String>();

        for (int i = 0; i < photos.size(); i++) {
            photo = photos.get(i);
            title.add(photo.getTitle().getPlainText());
        }
        return title;
    }

    /**
     * Returns ID of all photos in given album
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @return ID of all photos
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<String> listPicasaAlbumID(PicasawebService picasa, String id, String username) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + id);
        AlbumFeed album = picasa.getFeed(feedUrl, AlbumFeed.class);
        List<PhotoEntry> photos = album.getPhotoEntries();
        PhotoEntry photo;
        ArrayList<String> title = new ArrayList<String>();

        for (int i = 0; i < photos.size(); i++) {
            photo = photos.get(i);
            title.add(photo.getId());
        }
        return title;
    }

    /**
     * Returns album name according to given album ID
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @return album name
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String getAlbumNameByID(PicasawebService picasa, String id, String username) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;
        ArrayList<String> ids = new ArrayList<String>();
        String t;
        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            t = myAlbum.getId();
            t = t.substring(t.lastIndexOf("/") + 1);
            if (t.equalsIgnoreCase(id)) {
                return myAlbum.getTitle().getPlainText();
            }
        }
        throw new IOException("Album not found");
    }

    /**
     * Returns number of photos in album give by album ID
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static int getTotalPhotosByAlbumID(PicasawebService picasa, String username, String id) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + id);
        AlbumFeed album = picasa.getFeed(feedUrl, AlbumFeed.class);
        return Integer.valueOf(album.getPhotosUsed()).intValue();
    }

    /**
     * Returns instance of albumFeed by given album ID
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static AlbumFeed getAlbumID(PicasawebService picasa, String username, String id) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + id);
        AlbumFeed album = picasa.getFeed(feedUrl, AlbumFeed.class);
        return album;
    }

    /**
     * Returns URL of all photos in given album
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static ArrayList<String> getURLOfAllPhotos(PicasawebService picasa, String id, String username) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + id);
        AlbumFeed album = picasa.getFeed(feedUrl, AlbumFeed.class);
        List<PhotoEntry> photos = album.getPhotoEntries();
        PhotoEntry photo;
        ArrayList<String> url = new ArrayList<String>();

        for (int i = 0; i < photos.size(); i++) {
            photo = photos.get(i);
            url.add(photo.getMediaThumbnails().get(0).getUrl());
        }
        return url;
    }

    /**
     * Renames album
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @param newname new name of album
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void renameAlbum(PicasawebService picasa, String username, String id, String newname) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;
        ArrayList<String> ids = new ArrayList<String>();
        String t;
        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            t = myAlbum.getId();
            t = t.substring(t.lastIndexOf("/") + 1);
            if (t.equalsIgnoreCase(id)) {
                TextConstruct tc = TextConstruct.plainText(newname);
                myAlbum.setTitle(tc);
                myAlbum.update();
                return;
            }
        }
    }

    /**
     * Deletes album
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param id album ID (full URL)
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void deleteAlbum(String username, PicasawebService picasa, String id) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;
        ArrayList<String> ids = new ArrayList<String>();
        String t;
        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            t = myAlbum.getId();
            t = t.substring(t.lastIndexOf("/") + 1);
            if (t.equalsIgnoreCase(id)) {
                myAlbum.delete();
            }
        }
    }

    /**
     * Saves small size photo to user's temporary directory and opens it with system default opener
     * @param desktop instance of Desktop
     * @param size photo's size to download
     * @param idPicture ID of photo to be shown
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void downloadTempPhoto(Desktop desktop, String size, String idPicture, PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {
        int i = idPicture.indexOf("mid/");
        String p = idPicture.substring(i + 4);
        p = p.substring(0, p.indexOf("/"));// idAlbum
        AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, p);
        List<PhotoEntry> images = album.getPhotoEntries();
        PhotoEntry image;
        String id;

        for (int j = 0; j < images.size(); j++) {
            image = images.get(j);
            id = image.getId();
            if (id.equalsIgnoreCase(idPicture)) {
                String url = (image.getMediaThumbnails().get(0).getUrl());
                String name = image.getTitle().getPlainText();
                File f = FileManager.downloadTmpFromPicasaURL(url, name);
                f.deleteOnExit();
                desktop.open(f);
                return;
            }
        }
    }

    /**
     * Returns full album info
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @param albumID album ID (full URL)
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static AlbumInfo getAlbumInfo(String albumID, String username, PicasawebService picasa) throws MalformedURLException, IOException, ServiceException {
        String id = albumID.substring(albumID.lastIndexOf("/") + 1);
        AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, id);
        if (album != null) {
            AlbumInfo al = new AlbumInfo();
            al.setAccess(album.getAccess() + "");
            al.setComments(album.getCommentCount());
            al.setDate(album.getDate());
            al.setLocation(album.getLocation());
            al.setName(album.getName());
            al.setPhotos(album.getPhotosUsed());
            al.setSize(album.getBytesUsed());
            al.setId(album.getId());
            al.setTitle(album.getTitle().getPlainText());
            al.setDesc(album.getDescription().getPlainText());
            return al;
        }
        return null;
    }

    /**
     * Returns full photo info
     * @param idPicture photo ID
     * @param picasa instance of PicasawebService
     * @param username user's username (full email)
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static PhotoInfo getPhotoInfo(String idPicture, PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {
        int i = idPicture.indexOf("mid/");
        String p = idPicture.substring(i + 4);
        p = p.substring(0, p.indexOf("/"));// idAlbum

        AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, p);
        List<PhotoEntry> images = album.getPhotoEntries();
        PhotoEntry image;
        String id;

        for (int j = 0; j < images.size(); j++) {
            image = images.get(j);
            id = image.getId();
            if (id.equalsIgnoreCase(idPicture)) {

                PhotoInfo pi = new PhotoInfo();
                pi.setDesc(image.getDescription().getPlainText());
                pi.setId(image.getId());
                pi.setTitle(image.getTitle().getPlainText());
                try {
                    StringBuffer sb = new StringBuffer();
                    MediaKeywords m = image.getMediaKeywords();
                    List<String> s = m.getKeywords();
                    for (int k = 0; k < s.size(); k++) {
                        sb.append(s.get(k) + ",");
                    }
                    pi.setTags(sb.toString());
                } catch (Exception a) {
                    a.printStackTrace();
                }
                pi.setDate(image.getTimestamp());
                pi.setHeight(image.getHeight());
                pi.setWidth(image.getWidth());
                pi.setSize(image.getSize());
                pi.setExifs(image.getExifTags().getExifTags());
                return pi;
            }
        }
        return null;
    }

    /**
     * Updates album
     * @param picasa
     * @param username
     * @param album instance of AlbumInfo class, must contains album ID
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void updateAlbum(PicasawebService picasa, String username, AlbumInfo album) throws MalformedURLException, IOException, ServiceException {

        AlbumEntry albumEntry = FigooPicasaClient.getAlbumEntryID(picasa, username, album.getId());
        if (albumEntry != null) {
            albumEntry.setName(album.getName());
            TextConstruct tc = TextConstruct.plainText(album.getTitle());
            albumEntry.setTitle(tc);
            albumEntry.setAccess(album.getAccess());
            tc = TextConstruct.plainText(album.getDesc());
            albumEntry.setDescription(tc);
            albumEntry.setLocation(album.getLocation());
            albumEntry.update();
        }
    }

    /**
     * Returns album entry
     * @param picasa
     * @param username
     * @param id
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    private static AlbumEntry getAlbumEntryID(PicasawebService picasa, String username, String id) throws MalformedURLException, IOException, ServiceException {
        id = id.substring(id.lastIndexOf("/") + 1);
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;
        String id2;
        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            id2 = myAlbum.getId().substring(myAlbum.getId().lastIndexOf("/") + 1);
            if (id2.equalsIgnoreCase(id)) {
                return myAlbum;
            }
        }
        return null;
    }

    /**
     * Updates photo's information
     * @param picasa
     * @param username
     * @param pi
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static void updatePhoto(PicasawebService picasa, String username, PhotoInfo pi) throws MalformedURLException, IOException, ServiceException {
        int i = pi.getId().indexOf("mid/");
        String p = pi.getId().substring(i + 4);
        p = p.substring(0, p.indexOf("/"));// idAlbum
        AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, p);
        List<PhotoEntry> images = album.getPhotoEntries();
        PhotoEntry image;
        String id;

        for (int j = 0; j < images.size(); j++) {
            image = images.get(j);
            id = image.getId();
            if (id.equalsIgnoreCase(pi.getId())) {

                String photoid2 = pi.getId().substring(pi.getId().lastIndexOf("/") + 1);
                URL feedUrl2 = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + p + "/photoid/" + photoid2 + "?kind=tag");
                AlbumFeed searchResultsFeed = picasa.getFeed(feedUrl2, AlbumFeed.class);

                for (TagEntry tag : searchResultsFeed.getTagEntries()) {
                    tag.delete();
                }

                TextConstruct tc = TextConstruct.plainText(pi.getDesc());
                image.setDescription(tc);
                image.update();
                String[] tags = pi.getTags().split(",");
                String photoid = pi.getId();//.replace("%40", ".");
                photoid = photoid.replace(username.substring(0, username.indexOf("@")), username);
                photoid = photoid.replace("/entry/", "/feed/");
                URL feedUrl = new URL(photoid);
                for (int k = 0; k < tags.length; k++) {
                    TagEntry myTag = new TagEntry();
                    myTag.setTitle(new PlainTextConstruct(tags[k]));
                    picasa.insert(feedUrl, myTag);
                }
            }
        }
    }

    /**
     * Adds new album to picasa
     * @param name
     * @param desc
     * @param access
     * @param picasa
     * @param username
     * @param location
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String addAlbum(String name, String desc, String access, PicasawebService picasa, String username, String location) throws MalformedURLException, IOException, ServiceException {
        AlbumEntry myAlbum = new AlbumEntry();
        URL postUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username);
        myAlbum.setTitle(new PlainTextConstruct(name));
        myAlbum.setLocation(location);
        myAlbum.setDescription(new PlainTextConstruct(desc));
        myAlbum.setAccess(access);
        AlbumEntry insertedEntry = picasa.insert(postUrl, myAlbum);
        return insertedEntry.getId();
    }

    /**
     * Adds new photo to selected picasa album
     * @param id
     * @param picasa
     * @param username
     * @param file
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public synchronized static void addPhoto(String id, PicasawebService picasa, String username, File file) throws MalformedURLException, IOException, ServiceException {
        String albumId = id.substring(id.lastIndexOf("/") + 1);
        URL albumPostUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "/albumid/" + albumId);
        PhotoEntry myPhoto = new PhotoEntry();
        myPhoto.setTitle(new PlainTextConstruct(file.getName()));
        myPhoto.setDescription(new PlainTextConstruct(""));
        myPhoto.setClient("Figoo");
        MediaFileSource myMedia = new MediaFileSource(file, new MimetypesFileTypeMap().getContentType(file));
        myPhoto.setMediaSource(myMedia);
        picasa.insert(albumPostUrl, myPhoto);
    }

    /**
     * Returns album name
     * @param picasa
     * @param username
     * @param name
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String getAlbumIdByName(PicasawebService picasa, String username, String name) throws MalformedURLException, IOException, ServiceException {
        URL feedUrl = new URL("http://picasaweb.google.com/data/feed/api/user/" + username + "?kind=album");
        UserFeed myUserFeed = picasa.getFeed(feedUrl, UserFeed.class);
        List<AlbumEntry> albums = myUserFeed.getAlbumEntries();
        AlbumEntry myAlbum;

        for (int i = 0; i < albums.size(); i++) {
            myAlbum = albums.get(i);
            if (myAlbum.getTitle().getPlainText().equalsIgnoreCase(name)) {
                return myAlbum.getId();
            }
        }
        throw new ServiceException("Selected album not found");
    }

    /**
     * Returns structure in form of String for given folder
     * @param dir folder's id (full URL) or just /picasa
     * @param picasa
     * @param username
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String structureToString(String dir, PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {

        StringBuffer sb = new StringBuffer();
        if (dir.equals("/picasa")) {
            sb.append("Google Picasa" + System.getProperty("line.separator"));
            ArrayList<String> list = FigooPicasaClient.getAllPicasaAlbumTitle(picasa, username);
            for (int i = 0; i < list.size(); i++) {
                sb.append("   " + list.get(i));
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } else {
            String title = FigooPicasaClient.getAlbumNameByID(picasa, dir, username);
            ArrayList<String> list = FigooPicasaClient.listPicasaAlbumTitle(picasa, dir, username);
            sb.append("Google Picasa/" + title + System.getProperty("line.separator"));
            for (int i = 0; i < list.size(); i++) {
                sb.append("   " + list.get(i));
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }
    }

    /**
     * Returns structure of albums and images in picasa
     * @param dir where to start (folder's id (full URL) or just picasa for root folder)
     * @param depth depth of search (more then 1 is useless)
     * @param indention text indention
     * @param picasa instance of PicasawebService
     * @param username Username to Picasa
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws ServiceException
     */
    public static String structureToString(String dir, int depth, String indention, PicasawebService picasa, String username) throws MalformedURLException, IOException, ServiceException {

        StringBuffer sb = new StringBuffer();
        if (depth >= 0) {
            indention = indention + "  ";
            int newDepth = depth - 1;
            String tt = "";
            if (dir.equals("picasa")) {
                sb.append("Google Picasa" + System.getProperty("line.separator"));
                ArrayList<String> list = FigooPicasaClient.getAllPicasaAlbumTitle(picasa, username);
                ArrayList<String> list2 = FigooPicasaClient.getAllPicasaAlbumID(picasa, username);
                for (int i = 0; i < list.size(); i++) {
                    sb.append(indention + list.get(i));
                    sb.append(System.getProperty("line.separator"));
                    if (depth > 0) {
                        ArrayList<String> s = FigooPicasaClient.listPicasaAlbumTitle(picasa, list2.get(i), username);
                        for (int j = 0; j < s.size(); j++) {
                            sb.append("      ");
                            sb.append(s.get(j) + System.getProperty("line.separator"));
                        }
                    }
                }
                return sb.toString();
            } else {
                String title = FigooPicasaClient.getAlbumNameByID(picasa, dir, username);
                ArrayList<String> list = FigooPicasaClient.listPicasaAlbumTitle(picasa, dir, username);
                sb.append("Google Picasa/" + title + System.getProperty("line.separator"));
                for (int i = 0; i < list.size(); i++) {
                    sb.append("   " + list.get(i));
                    sb.append(System.getProperty("line.separator"));
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }
}
