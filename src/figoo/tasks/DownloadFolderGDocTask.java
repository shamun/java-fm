package figoo.tasks;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.util.ServiceException;
import figoo.DownloadSingleGDocDialog;
import figoo.ErrorDialog;
import figoo.fileManager.FigooDocsClient;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class DownloadFolderGDocTask extends SwingWorker<Void, Void> {

    /**
     *
     */
    public String resourceID;
    /**
     *
     */
    public DocsService docs;
    /**
     *
     */
    public String to;
    /**
     *
     */
    public String format;
    /**
     *
     */
    public DownloadSingleGDocDialog dp;
    /**
     *
     */
    public String docType;
    /**
     *
     */
    public SpreadsheetService spread;

    /**
     *
     * @param resID
     * @param docs
     * @param format
     * @param to
     * @param dp
     * @param docType
     * @param spread
     */
    public DownloadFolderGDocTask(String resID, DocsService docs, String format, String to, DownloadSingleGDocDialog dp, String docType, SpreadsheetService spread) {
        this.resourceID = resID;
        this.docs = docs;
        this.spread = spread;
        this.to = to;
        this.dp = dp;
        this.docType = docType;
        this.format = format;
    }

    @Override
    public Void doInBackground() {
        try {
            downloadFile();
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "uploadFileFolder", ex.getMessage());
            ed.setVisible(true);
        }
        return null;
    }

    /**
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws ServiceException
     */
    public void downloadFile() throws IOException, MalformedURLException, ServiceException {
        FigooDocsClient.recursiveDownload(resourceID, docs, spread, to, format, docType, dp);
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        dp.setVisible(false);
    }
}
