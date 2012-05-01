package figoo.tasks;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.photos.PicasawebService;
import figoo.SaveStructureDialog;
import figoo.fileManager.FigooDocsClient;
import figoo.fileManager.FigooPicasaClient;
import figoo.fileManager.FileManager;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class SaveStructureTask extends SwingWorker<Void, Void> {

    private int type; // 0 = file system, 1 = GDocs, 2 = Picasa
    private String username;
    private DocsService docs;
    private PicasawebService picasa;
    private String target;
    private SaveStructureDialog cd;
    private String from;
    private int depth;
/**
 * Saves structure to txt file
 * @param cd dialog with progress bar
 * @param docs instance of DocsService
 * @param picasa instance of PicasawebService
 * @param type origin of files (0 = file system, 1 = GDocs, 2 = Picasa)
 * @param from where to start with searching
 * @param recursion depth of search
 * @param target where to save txt file
 * @param username Username for Picasa
 */
    public SaveStructureTask(SaveStructureDialog cd, DocsService docs, PicasawebService picasa, int type, String from, int recursion, String target, String username) {
        this.type = type;
        this.username = username;
        this.from = from;
        this.target = target;
        this.picasa = picasa;
        this.depth = recursion;
        this.cd = cd;
        this.docs = docs;
    }

    @Override
    public Void doInBackground() {
        try {
            saveStructure();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Saves structure to txt file
     */
    public void saveStructure() throws Exception {

        this.cd.getjProgressBar1().setIndeterminate(true);
        this.cd.getjFileChooser1().setVisible(false);
        String struct = "";
        if (this.type == 0) {
            struct = FileManager.structureToString(this.from, this.depth, "", true);
        } else if (this.type == 1) {
            struct = FigooDocsClient.structureToString(this.from, this.depth, "", true, this.docs);
        } else if (this.type == 2) {
            struct = FigooPicasaClient.structureToString(this.from, this.depth, "", this.picasa, this.username);
        }
        
        File file  = new File(this.target+System.getProperty("file.separator")+"structure.txt");
        System.out.println("File  "+file.getAbsolutePath());
        FileWriter fw = new FileWriter(file);
        fw.write(struct);
        fw.close();

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
