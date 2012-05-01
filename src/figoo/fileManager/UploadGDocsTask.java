///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package figoo.fileManager;
//
//import com.google.gdata.client.docs.DocsService;
//import com.google.gdata.util.ServiceException;
//import figoo.ErrorDialog;
//import figoo.UploadGDocsProgressDialog;
//import figoo.google.FigooDocsClient;
//import java.awt.Toolkit;
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.SwingWorker;
//
///**
// *
// * @author Lada Riha
// */
//public class UploadGDocsTask extends SwingWorker<Void, Void> {
//
//    private UploadGDocsProgressDialog cd;
//    private String from;
//    private String toResourceId;
//    private DocsService docs;
//
//    /**
//     *
//     * @param cd
//     * @param docs
//     * @param from
//     * @param toResourceId
//     */
//    public UploadGDocsTask(UploadGDocsProgressDialog cd, DocsService docs, String from, String toResourceId) {
//        this.docs = docs;
//        this.cd = cd;
//        this.from = from;
//        this.toResourceId = toResourceId;
//    }
//
//    @Override
//    protected Void doInBackground() throws Exception {
//        uploadFileFolder(from, toResourceId);
//        return null;
//    }
//
//    @Override
//    public void done() {
//        Toolkit.getDefaultToolkit().beep();
//        cd.setVisible(false);
//
//    }
//
//    private void uploadFileFolder(String from, String toResourceId) {
//        File f = new File(from);
//        if (f.isFile()) {
//            cd.getjLabel2().setText("Uploading " + f.getName());
//            try {
//                FigooDocsClient.uploadFile(f, from, toResourceId, docs);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(UploadGDocsTask.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(UploadGDocsTask.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ServiceException ex) {
//                Logger.getLogger(UploadGDocsTask.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else if (f.isDirectory()) {
//            try {
//                String newResourceId = FigooDocsClient.makeFolderGetResourceId(docs, f.getName(), toResourceId);
//                File[] files = f.listFiles();
//                cd.getjLabel2().setText("Creating folder " + f.getName());
//                for (int i = 0; i < files.length; i++) {
//                    uploadFileFolder(files[i].getAbsolutePath(), newResourceId);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "uploadFileFolder", ex.getMessage());
//                ed.setVisible(true);
//            }
//        }
//    }
//}
