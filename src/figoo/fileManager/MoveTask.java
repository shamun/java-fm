package figoo.fileManager;

import figoo.ErrorDialog;
import figoo.MoveFileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class MoveTask extends SwingWorker<Void, Void> {

    /**
     *
     */
    public String from;
    /**
     *
     */
    public String to;
    /**
     *
     */
    public MoveFileDialog cd;

    /**
     *
     * @param a
     * @param b
     * @param d
     */
    public MoveTask(String a, String b, MoveFileDialog d) {
        this.from = a;
        this.to = b;
        this.cd = d;

    }

    @Override
    public Void doInBackground() {
        try {
            moveFile(this.from, this.to);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "MoveTask error", ex.getMessage());
            ed.setVisible(true);
        }
        return null;
    }

    /**
     * Moves file
     * @param from
     * @param to
     * @throws IOException
     */
    public void moveFile(String from, String to) throws IOException {
        File in = new File(from);
        cd.setTitle(in.getName());
        if (in.isFile()) {
            cd.getjLabel2().setText(in.getName());
            String dest = "";
            String folder = "";
            if (to.endsWith(System.getProperty("file.separator"))) {
                folder = to;
                //dest = to + from.substring(from.lastIndexOf(System.getProperty("file.separator")) + 1);
            } else {
                folder = to + System.getProperty("file.separator");
                //dest = to + from.substring(from.lastIndexOf(System.getProperty("file.separator")));
            }

            File out = new File(folder, in.getName());
            File tFolder = new File(folder);
            in.renameTo(out);


        } else {
            String dest = "";
            String folder = "";
            if (to.endsWith(System.getProperty("file.separator"))) {
                folder = to + System.getProperty("file.separator") + in.getName();
            } else {
                folder = to + System.getProperty("file.separator") + in.getName() + System.getProperty("file.separator");
            }
            File out = new File(folder, in.getName());
            File tFolder = new File(folder);
            tFolder.mkdirs();
            File[] files = in.listFiles();
            for (int i = 0; i < files.length; i++) {
                moveFile(files[i].getAbsolutePath(), folder);
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
        cd.getF().refresh();
    }
}
