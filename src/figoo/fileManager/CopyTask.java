package figoo.fileManager;

import figoo.CopyFileDialog;
import figoo.ErrorDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.SwingWorker;

/**
 *
 * @author Lada Riha
 */
public class CopyTask extends SwingWorker<Void, Void> {

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
    public CopyFileDialog cd;

    /**
     *
     * @param a file to copy
     * @param b where to copy
     * @param d
     */
    public CopyTask(String a, String b, CopyFileDialog d) {
        this.from = a;
        this.to = b;
        this.cd = d;

    }

    @Override
    public Void doInBackground() {
        try {
            copyFile(this.from, this.to);
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
    public void copyFile(String from, String to) throws IOException {
        File in = new File(from);
        cd.setTitle(to);
        if (in.isFile()) {
            cd.getjLabel2().setText(in.getName());
            String dest = "";
            if (to.endsWith(System.getProperty("file.separator"))) {
                dest = to + from.substring(from.lastIndexOf(System.getProperty("file.separator")) + 1);
            } else {
                dest = to + from.substring(from.lastIndexOf(System.getProperty("file.separator")));
            }
            File out = new File(dest);
            long l = in.length();

            FileChannel inChannel = new FileInputStream(in).getChannel();
            FileChannel outChannel = new FileOutputStream(out).getChannel();
            try {
                int maxCount = (52 * 1024 * 1024);
                int iteration = (int) l / maxCount;
                if (iteration == 0) {
                    iteration = 1;
                }
                int step = 100 / iteration;
                int progress = 0;
                long size = inChannel.size();
                long position = 0;
                while (position < size) {
                    progress = progress + step;
                    position += inChannel.transferTo(position, maxCount, outChannel);
                    try {
                        setProgress(progress);
                    } catch (Exception a) {
                        a.printStackTrace();
                             ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Copy file error", a.getMessage());
                            ed.setVisible(true);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            }

        } else {
            String dest = "";

            if (to.endsWith( System.getProperty("file.separator"))) {
                dest = to + in.getName() + System.getProperty("file.separator");
            } else {
                dest = to + System.getProperty("file.separator") + in.getName() + System.getProperty("file.separator");
            }
            File dir = new File(dest);
            dir.mkdirs();
            File[] files = in.listFiles();
            for (int i = 0; i < files.length; i++) {
                copyFile(files[i].getAbsolutePath(), dest);
            }
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
