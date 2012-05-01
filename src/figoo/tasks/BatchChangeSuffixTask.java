package figoo.tasks;

import figoo.BatchRenameDialog;
import figoo.fileManager.FileManager;
import java.io.File;
import javax.swing.DefaultListModel;


public class BatchChangeSuffixTask extends BatchRenameTask {
    private String suffix;
    
    public BatchChangeSuffixTask(File dir, DefaultListModel model, String pattern, BatchRenameDialog d, String suffix) {
        super(dir, model, pattern, d);
        this.suffix = suffix;
    }

    
    public void rename() throws Exception {
        this.cd.getjProgressBar1().setIndeterminate(true);
        this.report = FileManager.batchRenameChangeSuffix(this.dir, this.model, this.pattern, this.suffix);
    }
}
