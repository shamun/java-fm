package figoo.fileManager;

import figoo.ErrorDialog;
import figoo.RemoveFileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class RemoveTask extends SwingWorker<Void, Void> {

    /**
     *
     */
    public String file;
    /**
     *
     */
    public RemoveFileDialog cd;

    /**
     *
     * @param a File to delete (absolute path)
     * @param d
     */
    public RemoveTask(String a, RemoveFileDialog d) {
        this.file = a;
        this.cd = d;

    }

    @Override
    public Void doInBackground() {
        try {
            removeFile(this.file);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RemoveTask", ex.getMessage());
            ed.setVisible(true);
        }
        return null;
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    public void removeFile(String file) throws IOException {
        File in = new File(file);
        cd.setTitle(in.getName());
        cd.getjLabel2().setText(in.getName());
        if (in.isFile()) {
            in.delete();
        } else {
            File[] files = in.listFiles();
            for (int i = 0; i < files.length; i++) {
                removeFile(files[i].getAbsolutePath());
            }
            in.delete();
        }
    }


    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        cd.setVisible(false);
     //   cd.getF().refresh();
    }
}
