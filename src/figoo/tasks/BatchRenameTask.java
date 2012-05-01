package figoo.tasks;

import figoo.BatchInfoDialog;
import figoo.BatchRenameDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class BatchRenameTask extends SwingWorker<Void, Void> {

   
    public String pattern;
    public String to;
    public File dir;
    public DefaultListModel model;
    public BatchRenameDialog cd;
    public String report;
    
    public BatchRenameTask(File dir, DefaultListModel model, String pattern, BatchRenameDialog d) {
        this.pattern = pattern;
        this.model  = model;
        this.dir = dir;
        this.cd = d;
    }

    @Override
    public Void doInBackground() {
        try {
            rename();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param from
     * @param to
     * @throws IOException
     */
    public void rename() throws Exception {
        System.err.println("ERROR");
    }

    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        cd.setVisible(false);
        BatchInfoDialog id = new BatchInfoDialog(null, false, report);
        id.setVisible(true);
        cd.getF().refresh();
    }
}
