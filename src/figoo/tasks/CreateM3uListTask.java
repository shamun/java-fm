package figoo.tasks;

import figoo.DoneDialog;
import figoo.M3uDialog;
import figoo.fileManager.FileManager;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

public class CreateM3uListTask extends SwingWorker<Void, Void> {

    private String name;
    private File dir;
    private DefaultListModel model;
    private M3uDialog d;

    public CreateM3uListTask(File dir, DefaultListModel model, M3uDialog dialog, String name) {
        this.dir = dir;
        this.model = model;
        this.d = dialog;
        this.name = name;
    }

    @Override
    protected Void doInBackground() throws Exception {
        this.d.getjProgressBar1().setIndeterminate(true);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.model.size(); i++) {
            sb.append((String) this.model.get(i));
            sb.append(System.getProperty("line.separator"));
        }
        if (!name.endsWith(".m3u")) {
            name = name + ".m3u";
        }
        FileManager.saveStringToFile(this.dir, this.name, sb.toString());
        return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        DoneDialog dd = new DoneDialog(null, true);
        dd.setVisible(true);
        this.d.setVisible(false);
    }
}
