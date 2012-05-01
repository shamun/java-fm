package figoo.tasks;

import figoo.BatchRenameDialog;
import figoo.fileManager.FileManager;
import java.io.File;
import javax.swing.DefaultListModel;


public class BatchRemoveSuffixTask extends BatchRenameTask {

    public BatchRemoveSuffixTask(File dir, DefaultListModel model, String pattern, BatchRenameDialog d) {
        super(dir, model, pattern, d);
    }

    public void rename() throws Exception {
        this.cd.getjProgressBar1().setIndeterminate(true);
        this.report = FileManager.batchRenameRemoveSuffix(this.dir, this.model, this.pattern);
    }
}
