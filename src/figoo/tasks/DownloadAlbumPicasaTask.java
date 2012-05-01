package figoo.tasks;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.util.ServiceException;
import figoo.DownloadPicasaDialog;
import figoo.ErrorDialog;
import figoo.fileManager.FileManager;
import figoo.fileManager.FigooPicasaClient;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class DownloadAlbumPicasaTask extends SwingWorker<Void, Void> {

    /**
     *
     */
    public String username;
    /**
     *
     */
    public String albumID;
    /**
     *
     */
    public PicasawebService picasa;
    /**
     *
     */
    public String size;
    /**
     *
     */
    public String to;
    /**
     *
     */
    public boolean alb;
    /**
     *
     */
    public DownloadPicasaDialog dp;
    /**
     *
     */
    public ArrayList<String> todo;

    /**
     * Creates new instance of DownloadPicasaTask
     * @param username username in form of "user@gmail.com"
     * @param albumID album ID (full url id, not just number)
     * @param picasa instance of PicasawebService
     * @param size amount of photos to download
     * @param to target directory
     * @param dp
     */
    public DownloadAlbumPicasaTask(String username, String albumID, PicasawebService picasa, String size, String to, DownloadPicasaDialog dp) {
        this.username = username;
        this.albumID = albumID;
        this.picasa = picasa;
        this.size = size;
        alb = true;
        this.to = to;
        this.dp = dp;
    }

    /**
     *
     * @param username username in form of "user@gmail.com"
     * @param picasa instance of PicasawebService
     * @param size amount of photos to download
     * @param to target directory
     * @param dp
     * @param todo list of ID of photos to download
     */
    public DownloadAlbumPicasaTask(String username, PicasawebService picasa, String size, String to, DownloadPicasaDialog dp, ArrayList<String> todo) {
        this.username = username;
        this.picasa = picasa;
        this.todo = todo;
        alb = false;
        this.size = size;
        this.to = to;
        this.dp = dp;
    }

    @Override
    public Void doInBackground() {
        try {
            if (alb) {
                downloadAlbum();
            } else {
                downloadPhotos();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws ServiceException
     */
    public void downloadPhotos() throws IOException, MalformedURLException, ServiceException {
        int progress = 0;
        int i = todo.get(0).indexOf("mid/");
        String p = todo.get(0).substring(i + 4);
        p = p.substring(0, p.indexOf("/"));// idAlbum

        AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, p);
        for (int j = 0; j < todo.size(); j++) {
            progress = progress + 1;
            dp.getjLabel3().setText(progress + " / " + todo.size());
            FileManager.downloadFileFromPicasa(album, todo.get(j), picasa, username, to, size);
            setProgress(progress);
        }


    }

    /**
     *
     * @throws IOException
     */
    public void downloadAlbum() throws IOException {

        try {
            ArrayList<String> urls = FigooPicasaClient.getURLOfAllPhotos(picasa, albumID, username);
            ArrayList<String> names = FigooPicasaClient.listPicasaAlbumTitle(picasa, albumID, username);
            String name = FigooPicasaClient.getAlbumNameByID(picasa, albumID, username);
            int progress = 0;
            FileManager.makeDir(to, name);
            String dir = to + System.getProperty("file.separator") + name;
            for (int i = 0; i < urls.size(); i++) {
                dp.getjLabel3().setText(names.get(i));
                FileManager.downloadFromPicasaURL(urls.get(i), names.get(i), dir, size);
                progress = progress + 1;
                setProgress(progress);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "google init ", ex.getMessage());
            ed.setVisible(true);
        }
    }


    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        dp.setVisible(false);
        dp.getF().refresh();
    }
}
