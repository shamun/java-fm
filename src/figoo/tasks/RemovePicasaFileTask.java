package figoo.tasks;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.util.ServiceException;
import figoo.ErrorDialog;
import figoo.RemovePicasaFileDialog;
import figoo.fileManager.FigooPicasaClient;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class RemovePicasaFileTask extends SwingWorker<Void, Void> {

    /**
     *
     */
    public RemovePicasaFileDialog cd;
    private Object[] photos;
    private PicasawebService picasa;
    String username;

    /**
     *
     * @param d
     * @param f
     * @param picasa
     * @param username
     */
    public RemovePicasaFileTask(RemovePicasaFileDialog d, Object[] f, PicasawebService picasa, String username) {
        this.cd = d;
        this.username = username;
        this.picasa = picasa;
        this.photos = f;
    }

    @Override
    public Void doInBackground() {
        try {
            removePicasaFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @throws IOException
     */
    public void removePicasaFile() throws IOException {
        try {
            JPanel q;
            q = (JPanel) this.photos[0];
            String tt = q.getName();
            int progress = 0;

            int i = tt.indexOf("mid");
            String p = tt.substring(i + 4);
            p = p.substring(0, p.indexOf("/"));// idAlbum

            AlbumFeed album = FigooPicasaClient.getAlbumID(picasa, username, p);
            List<PhotoEntry> images = album.getPhotoEntries();
            PhotoEntry image;
            String id;

            for (int j = 0; j < images.size(); j++) {
                image = images.get(j);
                id = image.getId();
                for (int g = 0; g < this.photos.length; g++) {
                    q = (JPanel) this.photos[g];
                    if (id.equalsIgnoreCase(q.getName())) {
                        cd.getjLabel2().setText(image.getTitle().getPlainText());
                        image.delete();
                        break;
                    }
                }

                progress = progress + 1;
                setProgress(progress);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaFileTask", ex.getMessage());
            ed.setVisible(true);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaFileTask", ex.getMessage());
            ed.setVisible(true);
        }
    }


    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        cd.setVisible(false);
        //  cd.getF().refresh();
    }
}
