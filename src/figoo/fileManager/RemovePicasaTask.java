//package figoo.fileManager;
//
//import com.google.gdata.client.photos.PicasawebService;
//import com.google.gdata.util.ServiceException;
//import figoo.ErrorDialog;
//import figoo.RemovePicasaDialog;
//import figoo.google.FigooPicasaClient;
//import java.awt.Toolkit;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import javax.swing.SwingWorker;
//
///**
// *
// * @author Lada Riha
// */
//public class RemovePicasaTask extends SwingWorker<Void, Void> {
//
//    /**
//     *
//     */
//    public String id;
//    /**
//     *
//     */
//    public RemovePicasaDialog cd;
//    private boolean album;
//    private PicasawebService picasa;
//    String username;
//
//    /**
//     *
//     * @param alumID
//     * @param d
//     * @param album
//     * @param picasa
//     * @param username
//     */
//    public RemovePicasaTask(String alumID, RemovePicasaDialog d, boolean album, PicasawebService picasa, String username) {
//        this.id = alumID;
//        this.cd = d;
//        this.username = username;
//        this.picasa = picasa;
//        this.album = album;
//    }
//
//    @Override
//    public Void doInBackground() {
//        try {
//            removePicasaFile();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @throws IOException
//     */
//    public void removePicasaFile() throws IOException {
//        if (album) {
//            try {
//                FigooPicasaClient.deleteAlbum(username, picasa, id);
//            } catch (MalformedURLException ex) {
//                ex.printStackTrace();
//                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaTask", ex.getMessage());
//                ed.setVisible(true);
//            } catch (ServiceException ex) {
//                ex.printStackTrace();
//                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaTask", ex.getMessage());
//                ed.setVisible(true);
//            }
//        }
//    }
//
//
//    /*
//     * Executed in event dispatching thread
//     */
//    @Override
//    public void done() {
//        Toolkit.getDefaultToolkit().beep();
//        cd.setVisible(false);
////        cd.getF().refresh();
//    }
//}
