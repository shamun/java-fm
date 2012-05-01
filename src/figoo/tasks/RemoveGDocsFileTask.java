package figoo.tasks;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.util.ServiceException;
import figoo.ErrorDialog;
import figoo.RemoveGDocsFileDialog;
import figoo.fileManager.FigooDocsClient;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class RemoveGDocsFileTask extends SwingWorker<Void, Void> {

    private String resourceId;
    private boolean trash;
    private DocsService docs;
    private RemoveGDocsFileDialog cd;

    /**
     *
     * @param a
     * @param resourceId
     * @param docs
     * @param trash
     */
    public RemoveGDocsFileTask(RemoveGDocsFileDialog a, String resourceId, DocsService docs, boolean trash) {
        this.docs = docs;
        this.trash = trash;
        this.resourceId = resourceId;
        this.cd = a;
    }

    @Override
    public Void doInBackground() {
        try {
            removeFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @throws IOException
     */
    public void removeFile() throws IOException {
        try {
            FigooDocsClient.deleteFile(docs, resourceId, trash);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaTask", ex.getMessage());
            ed.setVisible(true);
        } catch (ServiceException ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemovePicasaTask", ex.getMessage());
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
    }
}
