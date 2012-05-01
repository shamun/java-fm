package figoo.tasks;

import figoo.ArchiveFileDialog;
import figoo.fileManager.FileManager;
import java.awt.Toolkit;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class ArchiveTask extends SwingWorker<Void, Void> {

    /**
     * File to copy
     */
    public String from;
    /**
     * Target directory to copy file
     */
    public String to;
    /**
     *
     */
    public ArchiveFileDialog cd;
    public String name;
    public int compression;

    /**
     *
     * @param a file to copy
     * @param b where to copy
     * @param d
     */
    public ArchiveTask(String a, String b, ArchiveFileDialog d, String name, int compression) {
        this.from = a;
        this.to = b;
        this.cd = d;
        this.name = name;
        this.compression = compression;
    }

    @Override
    public Void doInBackground() {
        try {
            FileManager.makeZip(this.from, this.to, this.name, this.compression);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        cd.setVisible(false);
        cd.getF().refresh();
    }
}
