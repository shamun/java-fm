/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DownloadSingleGDocDialog.java
 *
 * Created on 15.7.2010, 20:33:01
 */
package figoo;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import figoo.tasks.DownloadFolderGDocTask;
import figoo.tasks.DownloadSingleGDocTask;
import figoo.fileManager.FigooDocsClient;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Lada Riha
 */
public class DownloadSingleGDocDialog extends javax.swing.JDialog {

    public DownloadSingleGDocDialog(java.awt.Frame parent, boolean modal, String resourceID, DocsService docs, String to, SpreadsheetService spread) {
        super(parent, modal);
        try {
            this.to = to;
            this.resourceID = resourceID;
            this.client = docs;
            initComponents();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            int w = this.getSize().width;
            int h = this.getSize().height;
            int left = (d.width - w) / 2;
            int top = (d.height - h) / 2;
            this.setLocation(left, top);
            this.spread = spread;
            String type = FigooDocsClient.getFileType(docs, resourceID);
            String name = FigooDocsClient.getFileName(resourceID, docs);
            jLabel4.setText(name);

            this.type = type;
            if (type.equals("folder")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(FOLDERS_FORMATS));
            } else if (type.equals("document")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(DOCUMENT_FORMATS));
            } else if (type.equals("presentation")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(PRESENTATION_FORMATS));
            } else if (type.equals("spreadsheet")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(SPREADSHEETS_FORMATS));
            } else if (type.equals("file")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(NATIVE_FORMATS));
            } else if (type.equals("pdf")) {
                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(PDF_FORMAT));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "DownloadSingleGDocDialog ", ex.getMessage());
            ed.setVisible(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getResourceMap(DownloadSingleGDocDialog.class);
        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Error", " " }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jProgressBar1.setName("jProgressBar1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadFile(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAction(evt);
            }
        });

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JProgressBar getjProgressBar1() {
        return jProgressBar1;
    }

    public void setjProgressBar1(javax.swing.JProgressBar jProgressBar1) {
        this.jProgressBar1 = jProgressBar1;
    }

    private void cancelAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAction
        this.setVisible(false);
    }//GEN-LAST:event_cancelAction

    private void downloadFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadFile

        String format = (String) jComboBox1.getSelectedItem();
        if (!type.equals("folder")) {
            getjProgressBar1().setIndeterminate(true);
            task = new DownloadSingleGDocTask(resourceID, this.client, format, to, this, type, this.spread);
            task.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equals(evt.getPropertyName())) {
                        getjProgressBar1().setValue((Integer) evt.getNewValue());
                    }
                }
            });

            task.execute();
        } else if (type.equals("folder")) {
            getjProgressBar1().setIndeterminate(true);
            task2 = new DownloadFolderGDocTask(resourceID, this.client, format, to, this, type, this.spread);
            task2.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equals(evt.getPropertyName())) {
                        getjProgressBar1().setValue((Integer) evt.getNewValue());
                    }
                }
            });
            task2.execute();
        }
    }//GEN-LAST:event_downloadFile
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
    private String resourceID;
    private DocsService client;
    private SpreadsheetService spread;
    private String type;
    private String to;
    private final String[] DOCUMENT_FORMATS = {"txt", "odt", "pdf", "html", "rtf", "doc", "zip"};
    private final String[] PRESENTATION_FORMATS = {"swf", "pdf", "png", "ppt"};
    private final String[] SPREADSHEETS_FORMATS = {"xls", "csv", "pdf", "ods", "tsv", "html"};
    private final String[] FOLDERS_FORMATS = {"doc|xls|ppt", "pdf|pdf|pdf", "odt|ods|ppt"};
    private final String[] NATIVE_FORMATS = {"original"};
    private final String[] PDF_FORMAT = {"pdf"};
    private DownloadSingleGDocTask task;
    private DownloadFolderGDocTask task2;

    /**
     * @return the jLabel3
     */
    public javax.swing.JLabel getjLabel3() {
        return jLabel3;
    }

    /**
     * @param jLabel3 the jLabel3 to set
     */
    public void setjLabel3(javax.swing.JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }
}
