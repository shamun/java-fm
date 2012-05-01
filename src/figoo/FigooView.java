/*
 * FigooView.java
 */
package figoo;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import figoo.classes.AlbumInfo;
import figoo.classes.PhotoInfo;
import figoo.classes.Session;
import figoo.fileManager.FileManager;
import figoo.fileManager.FigooDocsClient;
import figoo.fileManager.FigooPicasaClient;
import figoo.guiClasses.ListRenderer;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.filechooser.FileSystemView;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * The application's main frame.
 */
public class FigooView extends FrameView {

  /**
   *
   * @param app
   */
  public FigooView(SingleFrameApplication app) {
    super(app);
    
    //initComponents();
    initComponentsa();
    
    //googleInit();
    icons = new HashMap<String, Icon>();
    docsStructure = new HashMap<String, String>();
    desktop = null;
    if (Desktop.isDesktopSupported()) {
      desktop = Desktop.getDesktop();
    }

    getFrame().setFocusable(true);
    getFrame().addKeyListener(new MyKeyListener());

    mainPanel.addKeyListener(new MyKeyListener());
    menuBar.addKeyListener(new MyKeyListener());
    fileChooser = new JFileChooser();
    session = new Session();
    
    //String path = System.getProperty("user.home");
    String path = "/var/tmp/usb";
    listDirectory(path, jList1, 1);    
    //listDirectory(path, jList2, 0);    
    findRoots(path, 0);
    //findRoots(path, 1);
  }
  
  private void initComponentsa() {

      mainPanel = new javax.swing.JPanel();
      jPanel1 = new javax.swing.JPanel();
      jPanel4 = new javax.swing.JPanel();
      jButton1 = new javax.swing.JButton();
      jButton2 = new javax.swing.JButton();
      jButton3 = new javax.swing.JButton();
      jButton4 = new javax.swing.JButton();
      jButton5 = new javax.swing.JButton();
      jButton6 = new javax.swing.JButton();
      jButton7 = new javax.swing.JButton();
      jButton8 = new javax.swing.JButton();
      jButton14 = new javax.swing.JButton();
      jPanel2 = new javax.swing.JPanel();
      jPanel8 = new javax.swing.JPanel();
      jButton9 = new javax.swing.JButton();
      jButton10 = new javax.swing.JButton();
      jButton11 = new javax.swing.JButton();
      jButton12 = new javax.swing.JButton();
      jButton13 = new javax.swing.JButton();
      jButton15 = new javax.swing.JButton();
      jButton16 = new javax.swing.JButton();
      jButton17 = new javax.swing.JButton();
      jButton18 = new javax.swing.JButton();
      jButton19 = new javax.swing.JButton();
      jButton20 = new javax.swing.JButton();
      jButton21 = new javax.swing.JButton();
      jPanel7 = new javax.swing.JPanel();
      jPanel9 = new javax.swing.JPanel();
      jPanel17 = new javax.swing.JPanel();
      jPanel13 = new javax.swing.JPanel();
      jPanel12 = new javax.swing.JPanel();
      jPanel11 = new javax.swing.JPanel();
      jPanel10 = new javax.swing.JPanel();
      jPanel14 = new javax.swing.JPanel();
      jPanel15 = new javax.swing.JPanel();
      jPanel16 = new javax.swing.JPanel();
      jPanel18 = new javax.swing.JPanel();
      jLabel1 = new javax.swing.JLabel();
      jLabel2 = new javax.swing.JLabel();
      jPanel3 = new javax.swing.JPanel();
      jPanel6 = new javax.swing.JPanel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();
      jPanel5 = new javax.swing.JPanel();
      jScrollPane2 = new javax.swing.JScrollPane();
      jList2 = new javax.swing.JList();
      menuBar = new javax.swing.JMenuBar();
      javax.swing.JMenu fileMenu = new javax.swing.JMenu();
      jMenuItem2 = new javax.swing.JMenuItem();
      jMenuItem3 = new javax.swing.JMenuItem();
      jMenuItem4 = new javax.swing.JMenuItem();
      jMenuItem1 = new javax.swing.JMenuItem();
      jMenuItem5 = new javax.swing.JMenuItem();
      jSeparator1 = new javax.swing.JPopupMenu.Separator();
      javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
      javax.swing.JMenu helpMenu = new javax.swing.JMenu();
      javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
      statusPanel = new javax.swing.JPanel();
      javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();

      mainPanel.setName("mainPanel"); // NOI18N

      jPanel1.setName("jPanel1"); // NOI18N
      jPanel1.setLayout(new java.awt.BorderLayout());

      jPanel4.setName("jPanel4"); // NOI18N
      jPanel4.setPreferredSize(new java.awt.Dimension(801, 50));

      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getResourceMap(FigooView.class);
      jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
      jButton1.setMargin(new java.awt.Insets(2, 5, 2, 5));
      jButton1.setName("jButton1"); // NOI18N
      jButton1.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton1.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              fileInfo(evt);
          }
      });
      jButton1.setVisible(false);
      
      jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
      jButton2.setMargin(new java.awt.Insets(2, 5, 2, 5));
      jButton2.setName("jButton2"); // NOI18N
      jButton2.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton2.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              renameButton(evt);
          }
      });

      jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
      jButton3.setMargin(new java.awt.Insets(2, 5, 2, 5));
      jButton3.setName("jButton3"); // NOI18N
      jButton3.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton3.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              makeDirectory(evt);
          }
      });
      jButton3.setVisible(false);

      jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
      jButton4.setMargin(new java.awt.Insets(2, 5, 2, 5));
      jButton4.setName("jButton4"); // NOI18N
      jButton4.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton4.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              archiveAction(evt);
          }
      });
      jButton4.setVisible(false);
      
      jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
      jButton5.setMargin(new java.awt.Insets(2, 5, 2, 5));
      jButton5.setName("jButton5"); // NOI18N
      jButton5.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton5.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              copyFiles(evt);
          }
      });
      jButton5.setVisible(false);

      jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
      jButton6.setName("jButton6"); // NOI18N
      jButton6.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton6.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              moveFiles(evt);
          }
      });
      jButton6.setVisible(false);
      
      jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
      jButton7.setName("jButton7"); // NOI18N
      jButton7.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton7.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              deleteFile(evt);
          }
      });

      jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
      jButton8.setEnabled(false);
      jButton8.setName("jButton8"); // NOI18N
      jButton8.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton8.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              uploadDialog(evt);
          }
      });
      jButton8.setVisible(false);

      jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
      jButton14.setName("jButton14"); // NOI18N
      jButton14.setPreferredSize(new java.awt.Dimension(100, 23));
      jButton14.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              downloadFile(evt);
          }
      });
      jButton14.setVisible(false);

      javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
      jPanel4.setLayout(jPanel4Layout);
      jPanel4Layout.setHorizontalGroup(
          jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(
              jPanel4Layout.createSequentialGroup()
              .addContainerGap()
              .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          ));

      jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton5});

      jPanel4Layout.setVerticalGroup(
          jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel4Layout.createSequentialGroup()
              .addContainerGap()
              .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              )
              .addContainerGap(16, Short.MAX_VALUE))
      );

      jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

      jPanel2.setName("jPanel2"); // NOI18N
      jPanel2.setPreferredSize(new java.awt.Dimension(801, 80));
      jPanel2.setLayout(new java.awt.GridLayout(2, 0));

      jPanel8.setName("jPanel8"); // NOI18N
      jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 5));

      jButton9.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
      jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
      jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
      jButton9.setAlignmentY(0.0F);
      jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
      jButton9.setIconTextGap(1);
      jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
      jButton9.setMaximumSize(new java.awt.Dimension(34, 34));
      jButton9.setMinimumSize(new java.awt.Dimension(34, 34));
      jButton9.setName("jButton9"); // NOI18N
      jButton9.setPreferredSize(new java.awt.Dimension(36, 36));
      jButton9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
      jButton9.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              refreshView(evt);
          }
      });
      jPanel8.add(jButton9);
//
//      jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
//      jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
//      jButton10.setToolTipText(resourceMap.getString("jButton10.toolTipText")); // NOI18N
//      jButton10.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton10.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton10.setName("jButton10"); // NOI18N
//      jButton10.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton10.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              duplicateLeftView(evt);
//          }
//      });
//      jPanel8.add(jButton10);
//
//      jButton11.setIcon(resourceMap.getIcon("jButton11.icon")); // NOI18N
//      jButton11.setToolTipText(resourceMap.getString("jButton11.toolTipText")); // NOI18N
//      jButton11.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton11.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton11.setName("jButton11"); // NOI18N
//      jButton11.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton11.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              duplicateRightView(evt);
//          }
//      });
//      jPanel8.add(jButton11);
//
//      jButton12.setIcon(resourceMap.getIcon("jButton12.icon")); // NOI18N
//      jButton12.setToolTipText(resourceMap.getString("jButton12.toolTipText")); // NOI18N
//      jButton12.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton12.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton12.setName("jButton12"); // NOI18N
//      jButton12.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton12.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              loginPicasaDialog(evt);
//          }
//      });
//      jPanel8.add(jButton12);
//
//      jButton13.setIcon(resourceMap.getIcon("jButton13.icon")); // NOI18N
//      jButton13.setToolTipText(resourceMap.getString("jButton13.toolTipText")); // NOI18N
//      jButton13.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton13.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton13.setName("jButton13"); // NOI18N
//      jButton13.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton13.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              logoutPicasa(evt);
//          }
//      });
//      jPanel8.add(jButton13);
//
//      jButton15.setIcon(resourceMap.getIcon("jButton15.icon")); // NOI18N
//      jButton15.setToolTipText(resourceMap.getString("jButton15.toolTipText")); // NOI18N
//      jButton15.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton15.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton15.setName("jButton15"); // NOI18N
//      jButton15.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton15.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              jButton15loginPicasaDialog(evt);
//          }
//      });
//      jPanel8.add(jButton15);
//
//      jButton16.setIcon(resourceMap.getIcon("jButton16.icon")); // NOI18N
//      jButton16.setToolTipText(resourceMap.getString("jButton16.toolTipText")); // NOI18N
//      jButton16.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton16.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton16.setName("jButton16"); // NOI18N
//      jButton16.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton16.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              jButton16logoutPicasa(evt);
//          }
//      });
//      jPanel8.add(jButton16);
//
//      jButton17.setIcon(resourceMap.getIcon("jButton17.icon")); // NOI18N
//      jButton17.setToolTipText(resourceMap.getString("jButton17.toolTipText")); // NOI18N
//      jButton17.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton17.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton17.setName("jButton17"); // NOI18N
//      jButton17.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton17.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              copyListToClipboard(evt);
//          }
//      });
//      jPanel8.add(jButton17);
//
//      jButton18.setIcon(resourceMap.getIcon("jButton18.icon")); // NOI18N
//      jButton18.setToolTipText(resourceMap.getString("jButton18.toolTipText")); // NOI18N
//      jButton18.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton18.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton18.setName("jButton18"); // NOI18N
//      jButton18.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton18.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              copyListToFile(evt);
//          }
//      });
//      jPanel8.add(jButton18);
//
//      jButton19.setIcon(resourceMap.getIcon("jButton19.icon")); // NOI18N
//      jButton19.setToolTipText(resourceMap.getString("jButton19.toolTipText")); // NOI18N
//      jButton19.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton19.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton19.setName("jButton19"); // NOI18N
//      jButton19.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton19.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              splitFile(evt);
//          }
//      });
//      jPanel8.add(jButton19);
//
//      jButton20.setIcon(resourceMap.getIcon("jButton20.icon")); // NOI18N
//      jButton20.setToolTipText(resourceMap.getString("jButton20.toolTipText")); // NOI18N
//      jButton20.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton20.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton20.setName("jButton20"); // NOI18N
//      jButton20.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton20.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              jButton20splitFile(evt);
//          }
//      });
//      jPanel8.add(jButton20);
//
//      jButton21.setIcon(resourceMap.getIcon("jButton21.icon")); // NOI18N
//      jButton21.setToolTipText(resourceMap.getString("jButton21.toolTipText")); // NOI18N
//      jButton21.setMaximumSize(new java.awt.Dimension(34, 34));
//      jButton21.setMinimumSize(new java.awt.Dimension(34, 34));
//      jButton21.setName("jButton21"); // NOI18N
//      jButton21.setPreferredSize(new java.awt.Dimension(36, 36));
//      jButton21.addActionListener(new java.awt.event.ActionListener() {
//          public void actionPerformed(java.awt.event.ActionEvent evt) {
//              jButton21splitFile(evt);
//          }
//      });
//      jPanel8.add(jButton21);

      //jPanel2.add(jPanel8);

      jPanel7.setName("jPanel7"); // NOI18N
      jPanel7.setPreferredSize(new java.awt.Dimension(801, 20));
      jPanel7.setLayout(new java.awt.GridLayout(2, 2));

      jPanel9.setName("jPanel9"); // NOI18N
      jPanel9.setLayout(new java.awt.GridLayout(1, 4));

      jPanel17.setName("jPanel17"); // NOI18N

      javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
      jPanel17.setLayout(jPanel17Layout);
      jPanel17Layout.setHorizontalGroup(
          jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel17Layout.setVerticalGroup(
          jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel9.add(jPanel17);

      jPanel13.setName("jPanel13"); // NOI18N

      javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
      jPanel13.setLayout(jPanel13Layout);
      jPanel13Layout.setHorizontalGroup(
          jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel13Layout.setVerticalGroup(
          jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel9.add(jPanel13);

      jPanel12.setName("jPanel12"); // NOI18N

      javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
      jPanel12.setLayout(jPanel12Layout);
      jPanel12Layout.setHorizontalGroup(
          jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel12Layout.setVerticalGroup(
          jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel9.add(jPanel12);

      jPanel11.setName("jPanel11"); // NOI18N
      jPanel11.setLayout(new java.awt.GridLayout(1, 1));
      jPanel9.add(jPanel11);

      jPanel7.add(jPanel9);

      jPanel10.setName("jPanel10"); // NOI18N
      jPanel10.setLayout(new java.awt.GridLayout(1, 4));

      jPanel14.setName("jPanel14"); // NOI18N

      javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
      jPanel14.setLayout(jPanel14Layout);
      jPanel14Layout.setHorizontalGroup(
          jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel14Layout.setVerticalGroup(
          jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel10.add(jPanel14);

      jPanel15.setName("jPanel15"); // NOI18N

      javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
      jPanel15.setLayout(jPanel15Layout);
      jPanel15Layout.setHorizontalGroup(
          jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel15Layout.setVerticalGroup(
          jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel10.add(jPanel15);

      jPanel16.setName("jPanel16"); // NOI18N

      javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
      jPanel16.setLayout(jPanel16Layout);
      jPanel16Layout.setHorizontalGroup(
          jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 121, Short.MAX_VALUE)
      );
      jPanel16Layout.setVerticalGroup(
          jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGap(0, 20, Short.MAX_VALUE)
      );

      jPanel10.add(jPanel16);

      jPanel18.setName("jPanel18"); // NOI18N
      jPanel18.setLayout(new java.awt.GridLayout(1, 1));
      jPanel10.add(jPanel18);

      jPanel7.add(jPanel10);

      jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
      jLabel1.setName("jLabel1"); // NOI18N
      jPanel7.add(jLabel1);

      jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
      jLabel2.setName("jLabel2"); // NOI18N
      jPanel7.add(jLabel2);
      jPanel2.add(jPanel7);
      jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

      // Main - explorer 
      jPanel3.setName("jPanel3"); // NOI18N
      jPanel3.setLayout(new java.awt.GridLayout(1, 2));

      jPanel6.setName("jPanel6"); // NOI18N
      jScrollPane1.setName("jScrollPane1"); // NOI18N
      jList1.setName("jList1"); // NOI18N
      jScrollPane1.setViewportView(jList1);
      javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
      jPanel6.setLayout(jPanel6Layout);
      jPanel6Layout.setHorizontalGroup(
          jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
      );
      jPanel6Layout.setVerticalGroup(
          jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
      );
      jPanel3.add(jPanel6);
      
      // Right - explorer
//      jPanel5.setName("jPanel5"); // NOI18N
//      jScrollPane2.setName("jScrollPane2"); // NOI18N
//      jList2.setName("jList2"); // NOI18N
//      jScrollPane2.setViewportView(jList2);
//      javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
//      jPanel5.setLayout(jPanel5Layout);
//      jPanel5Layout.setHorizontalGroup(
//          jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
//      );
//      jPanel5Layout.setVerticalGroup(
//          jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
//      );
      // Right scrollpane 
      //jPanel3.add(jPanel5);

      
      jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

      javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
      mainPanel.setLayout(mainPanelLayout);
      mainPanelLayout.setHorizontalGroup(
          mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
      );
      mainPanelLayout.setVerticalGroup(
          mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );

      menuBar.setName("menuBar"); // NOI18N

      fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
      fileMenu.setName("fileMenu"); // NOI18N

      jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
      jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
      jMenuItem2.setName("jMenuItem2"); // NOI18N
      jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              menuCopyClipboard(evt);
          }
      });
      fileMenu.add(jMenuItem2);

      jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
      jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
      jMenuItem3.setName("jMenuItem3"); // NOI18N
      jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              saveListFile(evt);
          }
      });
      fileMenu.add(jMenuItem3);

      jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
      jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
      jMenuItem4.setName("jMenuItem4"); // NOI18N
      fileMenu.add(jMenuItem4);

      jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
      jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
      jMenuItem1.setName("jMenuItem1"); // NOI18N
      jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              splitFile(evt);
          }
      });
      fileMenu.add(jMenuItem1);

      jMenuItem5.setIcon(resourceMap.getIcon("jMenuItem5.icon")); // NOI18N
      jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
      jMenuItem5.setName("jMenuItem5"); // NOI18N
      jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              showBatchDialog(evt);
          }
      });
      fileMenu.add(jMenuItem5);

      jSeparator1.setName("jSeparator1"); // NOI18N
      fileMenu.add(jSeparator1);

      javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getActionMap(FigooView.class, this);
      exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
      exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
      exitMenuItem.setName("exitMenuItem"); // NOI18N
      fileMenu.add(exitMenuItem);

      //menuBar.add(fileMenu);

      helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
      helpMenu.setName("helpMenu"); // NOI18N

      aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
      aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
      aboutMenuItem.setName("aboutMenuItem"); // NOI18N
      helpMenu.add(aboutMenuItem);

      menuBar.add(helpMenu);

      statusPanel.setName("statusPanel"); // NOI18N

      statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

      javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
      statusPanel.setLayout(statusPanelLayout);
      statusPanelLayout.setHorizontalGroup(
          statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
      );
      statusPanelLayout.setVerticalGroup(
          statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(statusPanelLayout.createSequentialGroup()
              .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addContainerGap(23, Short.MAX_VALUE))
      );

            
      // Header - menu 
      //setMenuBar(menuBar);
      
      // Body -  This is the main application container       
      setComponent(mainPanel);  
      
      // Staus 
      setStatusBar(statusPanel);
      
  }// </editor-fold>                        


  private void listDirectory(String path, JList list, int left) {
    list = new JList();

    String field = "";
    if (rootBox != null) {
      field = (String) rootBox2.getModel().getSelectedItem();
      if (field.equalsIgnoreCase("/picasa")) {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      }
    }
    list.setName(left + "");

    list.addKeyListener(new MyKeyListener());
    if (left == 1) {
      jScrollPane1.setViewportView(list);
    } else {
      jScrollPane2.setViewportView(list);
    }
    DefaultListModel listModel = new DefaultListModel();
    list.setCellRenderer(new ListRenderer());
    File folder = new File(path);
    if (left == 1) {
      if (path.length() > 60) {
        int diff = path.length() - 60;
        path = "..." + path.substring(diff);
        jLabel1.setText(path);
      } else {
        jLabel1.setText(path);
      }
    } else {
      jButton11.setEnabled(true);
      if (path.length() > 60) {
        int diff = path.length() - 60;
        path = "..." + path.substring(diff);
        jLabel2.setText(path);
      } else {
        jLabel2.setText(path);
      }
    }

    if (jLabel1.getText().equalsIgnoreCase(jLabel2.getText())) {
      jButton6.setEnabled(false);
    } else {
      jButton6.setEnabled(true);
    }

    String parent = folder.getParent();
    final JList list2 = list;

    File[] files = folder.listFiles();
    if (files != null) {
      Arrays.sort(files);
      Vector pole = new Vector();
      Vector pole2 = new Vector();

      JPanel p = new JPanel();
      p.setName(parent);
      p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
      JLabel name = new JLabel("..");
      p.add(name);
      pole2.add(p);
      JLabel icon;
      
      // Right click 
      JPopupMenu popup;
      JMenuItem menuItem;
      popup = new JPopupMenu();
      menuItem = new JMenuItem("Open");
      Font font = menuItem.getFont();
      menuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
      menuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          openFile(e, list2);
        }
      });
      popup.add(menuItem);

      menuItem = new JMenuItem("Rename");
      menuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          renameDialog();
        }
      });
      popup.add(menuItem);

      menuItem = new JMenuItem("Delete");
      menuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          removeDialog();
        }
      });
      popup.add(menuItem);
//
//      menuItem = new JMenuItem("Copy");
//      menuItem.addActionListener(new java.awt.event.ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          copyDialog();
//        }
//      });
//      popup.add(menuItem);
//
//      menuItem = new JMenuItem("Move");
//      menuItem.addActionListener(new java.awt.event.ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          moveDialog();
//        }
//      });
//      popup.add(menuItem);
//      
//      popup.addSeparator();
//      menuItem = new JMenuItem("Properties");
//      menuItem.addActionListener(new java.awt.event.ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//          propertiesDialog();
//        }
//      });
//      popup.add(menuItem);

      // List
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile() && !files[i].isHidden()) {
          p = new JPanel();
          p.setName(files[i].getAbsolutePath());
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(files[i].getName());
          pole.add(p);

          Icon image = getIcon(files[i]);
          icon = new JLabel(image);
          p.add(icon);
          p.add(name);
        } else if (files[i].isDirectory() && !files[i].isHidden()) {
          p = new JPanel();
          p.setName(files[i].getAbsolutePath());
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(files[i].getName());
          Icon image = getIcon(files[i]);
          icon = new JLabel(image);
          p.add(icon);
          p.add(name);
          pole2.add(p);
        }
      }

      final JPopupMenu popup2 = popup;

      list.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent me) {
          // if right mouse button clicked (or me.isPopupTrigger())
          if (SwingUtilities.isRightMouseButton(me)
                  && !list2.isSelectionEmpty()
                  && list2.locationToIndex(me.getPoint())
                  == list2.getSelectedIndex()) {
            popup2.show(list2, me.getX(), me.getY());
          }
        }
      });
      pole2.addAll(pole);
      list.setListData(pole2);

      list.addMouseListener(new DirectoryActionJList(list, left));
      list.addKeyListener(null);
    }
  }

  /**
   * Open file
   * - only allow jpeg, pdf, powerpoint
   * 
   * @param e
   * @param list 
   */
  private void openFile(ActionEvent e, JList list) {
    String filename = null;
    
    int[] indices = list.getSelectedIndices();
    if (indices.length == 1) {
      ListModel lm = list.getModel();
      JPanel dir = (JPanel) lm.getElementAt(indices[0]);

      list.ensureIndexIsVisible(indices[0]);
      File f = new File(dir.getName());
      if (f.isDirectory()) {
        if (list.getName().equalsIgnoreCase("1")) {
          System.out.println("list 1 " + f.getPath());
          if (f.getPath().toLowerCase().startsWith("/var/tmp/usb")) {
            listDirectory(dir.getName(), list, 1);
          } else {
            System.out.println("we do not permit");
          }
        } else {
          System.out.println("list 0 "  + f.getPath());
          if (f.getPath().toLowerCase().startsWith("/var/tmp/usb")) {
            listDirectory(dir.getName(), list, 1);
          } else {
            System.out.println("we do not permit");
          }          
          listDirectory(dir.getName(), list, 0);
        }
      } else if (f.isFile() && desktop != null) {
        try {
          filename = f.getName().toLowerCase();
          if (filename.endsWith(".pdf") || filename.endsWith(".jpeg") || filename.endsWith(".jpg")  || filename.endsWith(".ppt")) {
            System.out.println("valid file " + filename.compareTo(".pdf"));
            desktop.open(f);
            
          } else {
            System.out.println("invalid file");
          }
          
        } catch (IOException ex) {
          ex.printStackTrace();
          ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Open file error", ex.getMessage());
          ed.setVisible(true);
        }
      }
    } else {
      ListModel lm = list.getModel();
      for (int i = 0; i < indices.length; i++) {
        JPanel dir = (JPanel) lm.getElementAt(indices[i]);
        list.ensureIndexIsVisible(indices[i]);
        File f = new File(dir.getName());
        if (f.isFile() && desktop != null) {
          try {
            filename = f.getName().toLowerCase();
            if (filename.endsWith(".pdf") || filename.endsWith(".jpeg") || filename.endsWith(".jpg") || filename.endsWith(".ppt")) {
              System.out.println("valid file " + filename.compareTo(".pdf"));
              desktop.open(f);

            } else {
              System.out.println("invalid file");
            }
            //desktop.edit(f);
            //desktop.open(f);
          } catch (IOException ex) {
            ex.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Open file error", ex.getMessage());
            ed.setVisible(true);
          }
        }
      }
    }
  }

  private void findRoots(String path, int left) {
    File[] f = File.listRoots();
    String[] roots = new String[f.length];
    String t;
    int index = 0;
    session.setRootsL(new String[roots.length]);
    session.setRootsR(new String[roots.length]);
    for (int i = 0; i < f.length; i++) {
      t = f[i].getAbsolutePath();
      roots[i] = t;
    }

    Arrays.sort(roots);

    for (int i = 0; i < f.length; i++) {
      if (path.startsWith(roots[i])) {
        index = i;
      }
    }
    if (left == 1) {
      jPanel11.removeAll();
      ArrayList al = new ArrayList();
      for (int i = 0; i < roots.length; i++) {
        al.add(roots[i]);
      }

      rootBox = new JComboBox(al.toArray());
      rootBox.setSelectedIndex(index);
      rootBox.setName("1");//left one
      rootBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          changeDrive(evt);
        }
      });

      jPanel11.add(rootBox);
    }
    // right
    if (left == 0) {
      jPanel18.removeAll();
      ArrayList al = new ArrayList();
      for (int i = 0; i < roots.length; i++) {
        al.add(roots[i]);
      }
      if (logPicasa) {
        al.add("/picasa");
      }
      if (logDoc) {
        al.add("GDocs");
      }

      rootBox2 = new JComboBox(al.toArray());
      rootBox2.setName("0");//right one
      rootBox2.setSelectedIndex(index);
      rootBox2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          changeDrive(evt);
        }
      });

      jPanel18.add(rootBox2);
    }
  }

  private void changeDrive(ActionEvent evt) {

    JComboBox cb = (JComboBox) evt.getSource();
    if (cb.getName().equals("1")) {
      String field = (String) cb.getModel().getSelectedItem();
      for (int i = 0; i < cb.getItemCount(); i++) {
        if (jLabel1.getText().startsWith((String) cb.getItemAt(i))) {
          session.setRootL(jLabel1.getText(), i);
        }
      }

      if (session.getRootL(cb.getSelectedIndex()) != null && session.getRootL(cb.getSelectedIndex()).length() > 1) {
        listDirectory(session.getRootL(cb.getSelectedIndex()), jList1, 1);
      } else {
        listDirectory((String) cb.getSelectedItem(), jList1, 1);
      }
    } else {
      for (int i = 0; i < cb.getItemCount(); i++) {
        if (!jLabel2.getText().equalsIgnoreCase("/picasa") && jLabel2.getText().startsWith((String) cb.getItemAt(i))) {
          session.setRootR(jLabel2.getText(), i);
        }
      }
      String field = (String) cb.getModel().getSelectedItem();

      if (field.equalsIgnoreCase("/picasa")) {
        listDirectory(jLabel1.getText(), jList1, 1);
        listPicasa(jList2, 0);
      } else if (field.equalsIgnoreCase("/gdocs")) {
      } else {
        if (session.getRootR(cb.getSelectedIndex()) != null && session.getRootR(cb.getSelectedIndex()).length() > 1) {
          listDirectory(session.getRootR(cb.getSelectedIndex()), jList2, 0);
        } else {
          listDirectory((String) cb.getSelectedItem(), jList2, 0);
        }
      }
    }
  }

  /**
   *
   */
  @Action
  public void showAboutBox() {
    if (aboutBox == null) {
      JFrame mainFrame = FigooApp.getApplication().getMainFrame();
      aboutBox = new FigooAboutBox(mainFrame);
      aboutBox.setLocationRelativeTo(mainFrame);
    }
    FigooApp.getApplication().show(aboutBox);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(801, 50));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getResourceMap(FigooView.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileInfo(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton2.setName("jButton2"); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameButton(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeDirectory(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archiveAction(evt);
            }
        });

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setMargin(new java.awt.Insets(2, 5, 2, 5));
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyFiles(evt);
            }
        });

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveFiles(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFile(evt);
            }
        });

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setEnabled(false);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadDialog(evt);
            }
        });

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.setPreferredSize(new java.awt.Dimension(100, 23));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadFile(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton5});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(801, 80));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 5));

        jButton9.setFont(resourceMap.getFont("jButton9.font")); // NOI18N
        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setAlignmentY(0.0F);
        jButton9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton9.setIconTextGap(1);
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton9.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton9.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshView(evt);
            }
        });
        jPanel8.add(jButton9);

        jButton10.setIcon(resourceMap.getIcon("jButton10.icon")); // NOI18N
        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setToolTipText(resourceMap.getString("jButton10.toolTipText")); // NOI18N
        jButton10.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton10.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton10.setName("jButton10"); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicateLeftView(evt);
            }
        });
        jPanel8.add(jButton10);

        jButton11.setIcon(resourceMap.getIcon("jButton11.icon")); // NOI18N
        jButton11.setToolTipText(resourceMap.getString("jButton11.toolTipText")); // NOI18N
        jButton11.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton11.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton11.setName("jButton11"); // NOI18N
        jButton11.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicateRightView(evt);
            }
        });
        jPanel8.add(jButton11);

        jButton12.setIcon(resourceMap.getIcon("jButton12.icon")); // NOI18N
        jButton12.setToolTipText(resourceMap.getString("jButton12.toolTipText")); // NOI18N
        jButton12.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton12.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton12.setName("jButton12"); // NOI18N
        jButton12.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginPicasaDialog(evt);
            }
        });
        jPanel8.add(jButton12);

        jButton13.setIcon(resourceMap.getIcon("jButton13.icon")); // NOI18N
        jButton13.setToolTipText(resourceMap.getString("jButton13.toolTipText")); // NOI18N
        jButton13.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton13.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton13.setName("jButton13"); // NOI18N
        jButton13.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutPicasa(evt);
            }
        });
        jPanel8.add(jButton13);

        jButton15.setIcon(resourceMap.getIcon("jButton15.icon")); // NOI18N
        jButton15.setToolTipText(resourceMap.getString("jButton15.toolTipText")); // NOI18N
        jButton15.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton15.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton15.setName("jButton15"); // NOI18N
        jButton15.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15loginPicasaDialog(evt);
            }
        });
        jPanel8.add(jButton15);

        jButton16.setIcon(resourceMap.getIcon("jButton16.icon")); // NOI18N
        jButton16.setToolTipText(resourceMap.getString("jButton16.toolTipText")); // NOI18N
        jButton16.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton16.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton16.setName("jButton16"); // NOI18N
        jButton16.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16logoutPicasa(evt);
            }
        });
        jPanel8.add(jButton16);

        jButton17.setIcon(resourceMap.getIcon("jButton17.icon")); // NOI18N
        jButton17.setToolTipText(resourceMap.getString("jButton17.toolTipText")); // NOI18N
        jButton17.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton17.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton17.setName("jButton17"); // NOI18N
        jButton17.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyListToClipboard(evt);
            }
        });
        jPanel8.add(jButton17);

        jButton18.setIcon(resourceMap.getIcon("jButton18.icon")); // NOI18N
        jButton18.setToolTipText(resourceMap.getString("jButton18.toolTipText")); // NOI18N
        jButton18.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton18.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton18.setName("jButton18"); // NOI18N
        jButton18.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyListToFile(evt);
            }
        });
        jPanel8.add(jButton18);

        jButton19.setIcon(resourceMap.getIcon("jButton19.icon")); // NOI18N
        jButton19.setToolTipText(resourceMap.getString("jButton19.toolTipText")); // NOI18N
        jButton19.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton19.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton19.setName("jButton19"); // NOI18N
        jButton19.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitFile(evt);
            }
        });
        jPanel8.add(jButton19);

        jButton20.setIcon(resourceMap.getIcon("jButton20.icon")); // NOI18N
        jButton20.setToolTipText(resourceMap.getString("jButton20.toolTipText")); // NOI18N
        jButton20.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton20.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton20.setName("jButton20"); // NOI18N
        jButton20.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20splitFile(evt);
            }
        });
        jPanel8.add(jButton20);

        jButton21.setIcon(resourceMap.getIcon("jButton21.icon")); // NOI18N
        jButton21.setToolTipText(resourceMap.getString("jButton21.toolTipText")); // NOI18N
        jButton21.setMaximumSize(new java.awt.Dimension(34, 34));
        jButton21.setMinimumSize(new java.awt.Dimension(34, 34));
        jButton21.setName("jButton21"); // NOI18N
        jButton21.setPreferredSize(new java.awt.Dimension(36, 36));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21splitFile(evt);
            }
        });
        jPanel8.add(jButton21);

        jPanel2.add(jPanel8);

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(801, 20));
        jPanel7.setLayout(new java.awt.GridLayout(2, 2));

        jPanel9.setName("jPanel9"); // NOI18N
        jPanel9.setLayout(new java.awt.GridLayout(1, 4));

        jPanel17.setName("jPanel17"); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel17);

        jPanel13.setName("jPanel13"); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel13);

        jPanel12.setName("jPanel12"); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel12);

        jPanel11.setName("jPanel11"); // NOI18N
        jPanel11.setLayout(new java.awt.GridLayout(1, 1));
        jPanel9.add(jPanel11);

        jPanel7.add(jPanel9);

        jPanel10.setName("jPanel10"); // NOI18N
        jPanel10.setLayout(new java.awt.GridLayout(1, 4));

        jPanel14.setName("jPanel14"); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel14);

        jPanel15.setName("jPanel15"); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel15);

        jPanel16.setName("jPanel16"); // NOI18N

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 121, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel16);

        jPanel18.setName("jPanel18"); // NOI18N
        jPanel18.setLayout(new java.awt.GridLayout(1, 1));
        jPanel10.add(jPanel18);

        jPanel7.add(jPanel10);

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel7.add(jLabel1);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        jPanel7.add(jLabel2);

        jPanel2.add(jPanel7);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel6.setName("jPanel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setName("jList1"); // NOI18N
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6);

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jList2.setName("jList2"); // NOI18N
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCopyClipboard(evt);
            }
        });
        fileMenu.add(jMenuItem2);

        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveListFile(evt);
            }
        });
        fileMenu.add(jMenuItem3);

        jMenuItem4.setIcon(resourceMap.getIcon("jMenuItem4.icon")); // NOI18N
        jMenuItem4.setText(resourceMap.getString("jMenuItem4.text")); // NOI18N
        jMenuItem4.setName("jMenuItem4"); // NOI18N
        fileMenu.add(jMenuItem4);

        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splitFile(evt);
            }
        });
        fileMenu.add(jMenuItem1);

        jMenuItem5.setIcon(resourceMap.getIcon("jMenuItem5.icon")); // NOI18N
        jMenuItem5.setText(resourceMap.getString("jMenuItem5.text")); // NOI18N
        jMenuItem5.setName("jMenuItem5"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBatchDialog(evt);
            }
        });
        fileMenu.add(jMenuItem5);

        jSeparator1.setName("jSeparator1"); // NOI18N
        fileMenu.add(jSeparator1);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getActionMap(FigooView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setIcon(resourceMap.getIcon("aboutMenuItem.icon")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshView(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshView
      refresh();
    }//GEN-LAST:event_refreshView

    private void renameButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameButton
      renameDialog();
    }//GEN-LAST:event_renameButton

    private void makeDirectory(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeDirectory
      makeDirDialog();        // TODO add your handling code here:
    }//GEN-LAST:event_makeDirectory

    private void copyFiles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyFiles
      copyDialog();
    }//GEN-LAST:event_copyFiles

    private void moveFiles(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveFiles
      moveDialog();
    }//GEN-LAST:event_moveFiles

    private void deleteFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFile
      if (jLabel2.getText().startsWith("/docs")) {
        removeGDocsDialog();
      } else {
        removeDialog();
      }
    }//GEN-LAST:event_deleteFile

    private void fileInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileInfo
      propertiesDialog();
    }//GEN-LAST:event_fileInfo

    private void loginPicasaDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginPicasaDialog
      if (!logPicasa) {
        try {
          PicasaLogin pl = new PicasaLogin(getFrame(), true, this);
          pl.setVisible(true);
        } catch (Exception ex) {
          ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Picasa dialog", ex.getMessage());
          ed.setVisible(true);
        }
      }
    }//GEN-LAST:event_loginPicasaDialog

  /**
   *
   * @param u
   * @param p
   */
  public void loginPicasa(String u, String p) {
    try {
      this.usernamePicasa = u;
      picasa = FigooPicasaClient.logPicasa(u, p);
      this.logPicasa = true;
      ComboBoxModel cm = rootBox2.getModel();
      int index = cm.getSize();
      rootBox2.addItem(new String("/picasa"));
      rootBox2.setSelectedIndex(index);
      jButton8.setEnabled(true);
      listPicasa(jList2, 0);
    } catch (AuthenticationException ex) {
      this.logPicasa = false;
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Picasa Login", ex.getMessage());
      ex.printStackTrace();
      ed.setVisible(true);
    }
  }

    private void duplicateLeftView(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicateLeftView
      listDirectory(jLabel1.getText(), jList2, 0);
    }//GEN-LAST:event_duplicateLeftView

    private void duplicateRightView(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duplicateRightView
      listDirectory(jLabel2.getText(), jList1, 1);
    }//GEN-LAST:event_duplicateRightView

    private void logoutPicasa(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutPicasa
      logPicasa = false;
      picasa = null;
      jButton8.setEnabled(false);
      duplicateLeftView(evt);
      refresh();
    }//GEN-LAST:event_logoutPicasa

    private void downloadFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadFile
      if (active != null && active.getName().equals("0") && jLabel2.getText().startsWith("/picasa")) {
        downloadPicasa(active);
      }
    }//GEN-LAST:event_downloadFile

    private void uploadDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadDialog
      // TODO add your handling code here:
      showUploadDialog();
    }//GEN-LAST:event_uploadDialog

    private void jButton15loginPicasaDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15loginPicasaDialog
      if (!logDoc) {
        try {
          DocLogin pl = new DocLogin(getFrame(), true, this);
          pl.setVisible(true);
        } catch (Exception ex) {
          ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Picasa dialog", ex.getMessage());
          ed.setVisible(true);
        }
      }
    }//GEN-LAST:event_jButton15loginPicasaDialog

    private void jButton16logoutPicasa(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16logoutPicasa
      logDoc = false;
      docs = null;
      jButton8.setEnabled(false);
      duplicateLeftView(evt);
      refresh();
    }//GEN-LAST:event_jButton16logoutPicasa

    private void archiveAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archiveAction
      archiveDialog();
    }//GEN-LAST:event_archiveAction

    private void copyListToClipboard(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyListToClipboard
      String dir = "";
      String list = "";
      if (active != null && active.getName().equals("1")) {
        dir = jLabel1.getText();
        list = FileManager.structureToString(dir);
      } else {
        if (jLabel2.getText().startsWith("/picasa")) {
          try {
            if (jLabel2.getText().equals("/picasa")) {
              list = FigooPicasaClient.structureToString("/picasa", this.picasa, this.usernamePicasa);
            } else {
              list = FigooPicasaClient.structureToString(this.lastPicasaId, this.picasa, this.usernamePicasa);
            }
          } catch (Exception ex) {
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Picasa to clipboard", ex.getMessage());
            ed.setVisible(true);
          }
        } else if (jLabel2.getText().startsWith("/docs")) {
          try {
            if (jLabel2.getText().equals("/docs")) {
              list = FigooDocsClient.structureToString("/docs", this.docs);
            } else {
              list = FigooDocsClient.structureToString(this.currentGFolder, this.docs);
            }
          } catch (Exception ex) {
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on GDocs to clipboard", ex.getMessage());
            ed.setVisible(true);
          }
        } else {
          dir = jLabel2.getText();
          list = FileManager.structureToString(dir);
        }
      }
      StringSelection ss = new StringSelection(list);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

    }//GEN-LAST:event_copyListToClipboard

    private void copyListToFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyListToFile

      String dir = "";

      if (active != null && active.getName().equals("1")) {
        dir = jLabel1.getText();
        SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 0, docs, picasa, this.usernamePicasa, dir);
        sd.setVisible(true);
      } else {
        if (jLabel2.getText().startsWith("/picasa")) {
          try {
            if (jLabel2.getText().equals("/picasa")) {
              SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 2, docs, picasa, this.usernamePicasa, "picasa");
              sd.setVisible(true);
            } else {
              SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 2, docs, picasa, this.usernamePicasa, this.lastPicasaId);
              sd.setVisible(true);
            }
          } catch (Exception ex) {
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Picasa to file", ex.getMessage());
            ed.setVisible(true);
          }
        } else if (jLabel2.getText().startsWith("/docs")) {
          try {
            if (jLabel2.getText().equals("/docs")) {
              SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 1, docs, picasa, this.usernamePicasa, "docs");
              sd.setVisible(true);
            } else {
              SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 1, docs, picasa, this.usernamePicasa, this.currentGFolder);
              sd.setVisible(true);
            }
          } catch (Exception ex) {
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on GDocs to file", ex.getMessage());
            ed.setVisible(true);
          }
        } else {
          dir = jLabel2.getText();
          SaveStructureDialog sd = new SaveStructureDialog(getFrame(), false, 0, docs, picasa, this.usernamePicasa, dir);
          sd.setVisible(true);
        }
      }
    }//GEN-LAST:event_copyListToFile

    private void menuCopyClipboard(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCopyClipboard
      copyListToClipboard(null);
    }//GEN-LAST:event_menuCopyClipboard

    private void saveListFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveListFile
      copyListToFile(null);        // TODO add your handling code here:
    }//GEN-LAST:event_saveListFile

    private void splitFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splitFile
      if (active != null) {
        if (!jLabel2.getText().startsWith("/docs") && !jLabel2.getText().startsWith("/picasa")) {
          JPanel panel = (JPanel) active.getSelectedValue();
          File file = new File(panel.getName());
          if (file.isFile()) {
            if (active.getName().equals("1")) {
              SplitFileDialog sd = new SplitFileDialog(this.getFrame(), false, file, jLabel2.getText(), this);
              sd.setVisible(true);
            } else {
              SplitFileDialog sd = new SplitFileDialog(this.getFrame(), false, file, jLabel1.getText(), this);
              sd.setVisible(true);
            }
          }
        }
      }
    }//GEN-LAST:event_splitFile

    private void jButton20splitFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20splitFile

      if (active != null) {
        if (!jLabel2.getText().startsWith("/docs") && !jLabel2.getText().startsWith("/picasa")) {
          JPanel panel = (JPanel) active.getSelectedValue();
          File file = new File(panel.getName());
          if (file.isFile()) {
            if (file.getName().endsWith(".001")) {
              if (active.getName().equals("1")) {
                CombineFileDialog sd = new CombineFileDialog(this.getFrame(), false, file.getAbsolutePath(), jLabel2.getText(), this);
                sd.setVisible(true);
              } else {
                CombineFileDialog sd = new CombineFileDialog(this.getFrame(), false, file.getAbsolutePath(), jLabel1.getText(), this);
                sd.setVisible(true);
              }
            } else {
              Toolkit.getDefaultToolkit().beep();
            }
          }
        }
      }
    }//GEN-LAST:event_jButton20splitFile

    private void showBatchDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBatchDialog

      BatchRenameDialog bd = new BatchRenameDialog(this.getFrame(), false, this);
      bd.setVisible(true);
    }//GEN-LAST:event_showBatchDialog

    private void jButton21splitFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21splitFile

      M3uDialog mm = new M3uDialog(this.getFrame(), false, this);
      mm.setVisible(true);
    }//GEN-LAST:event_jButton21splitFile

  public void googleInit() {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(new File(""));
      InputStreamReader in = new InputStreamReader(fis);
      BufferedReader b = new BufferedReader(in);
      String line = "";

      while (b.ready()) {
        line = b.readLine();
      }
      this.usernameDoc = "";
      String pass = line;
      loginDocs(this.usernameDoc, pass);

    } catch (Exception ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "google init ", ex.getMessage());
      ed.setVisible(true);
    } finally {
      try {
        fis.close();
      } catch (IOException ex) {
        ex.printStackTrace();
        ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "google init ", ex.getMessage());
        ed.setVisible(true);
      }
    }
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
  private JDialog aboutBox;
  private String currentGFolder;
  private JList active;
  private String lastPicasaId;
  /**
   *
   */
  public Session session;
  private JFileChooser fileChooser;
  private Map<String, Icon> icons = null;
  private Map<String, String> docsStructure = null;
  private Desktop desktop;
  private JComboBox rootBox2;
  private JComboBox rootBox;
  public boolean cont = false;
  private String usernamePicasa;
  private String usernameDoc;
  private boolean logPicasa = false;
  private boolean logDoc = false;
  private PicasawebService picasa;
  private DocsService docs;
  private SpreadsheetService spreads;
  public boolean trash = true;

  /**
   *
   * @param file
   * @return
   */
  public Icon getIcon(File file) {
    try {
      String type = fileChooser.getTypeDescription(file);
      if (icons.containsKey(type)) {
        return icons.get(type);
      } else {
      }

      File tmp = File.createTempFile("icon", type);
      FileSystemView view = FileSystemView.getFileSystemView();
      Icon icon = view.getSystemIcon(file);
      tmp.delete();
      icons.put(type, icon);
      return icon;
    } catch (IOException ex) {
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Icon error", ex.getMessage());
      ed.setVisible(true);
    }
    return null;
  }

  private void archiveDialog() {
    if (!jLabel2.getText().startsWith("/docs") && !jLabel2.getText().startsWith("/picasa")) {
      JPanel p = (JPanel) active.getSelectedValue();
      String from = p.getName();
      String to = "";
      if (active.getName().equalsIgnoreCase("1")) {
        to = jLabel2.getText();
      } else {
        to = jLabel1.getText();
      }
      ArchiveFileDialog dd = new ArchiveFileDialog(this, this.getFrame(), true, from, to);
      dd.setVisible(true);
    }
  }

  private void renameDialog() {
    if (active.getName().equals("0") && jLabel2.getText().startsWith("/picasa")) {
      if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
        Object[] f = active.getSelectedValues();
        int[] indices = active.getSelectedIndices();
        if (indices.length > 0) {
          JPanel p;
          for (int i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String id = p.getName();
              RenamePicasaDialog rd = new RenamePicasaDialog(this, this.getFrame(), true, id, picasa, usernamePicasa);
              rd.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RenameDialog error", e.getMessage());
              ed.setVisible(true);
            }
          }
          refresh();
        }
      }
    } else if (active.getName().equals("0") && jLabel2.getText().startsWith("/docs")) {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        JPanel p;
        for (int i = 0; i < f.length; i++) {
          try {
            p = (JPanel) f[i];
            String id = p.getName();
            RenameGFileDialog rd = new RenameGFileDialog(this.getFrame(), true, id, docs);
            rd.setVisible(true);
          } catch (Exception e) {
            e.printStackTrace();
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RenameDialog error", e.getMessage());
            ed.setVisible(true);
          }
        }
      }
      refresh();
    } else {
      try {
        Object[] f = active.getSelectedValues();
        int[] indices = active.getSelectedIndices();
        if (indices.length > 0) {
          JPanel p;
          int i = 0;
          if (indices[0] == 0) {
            for (i = 1; i < f.length; i++) {
              try {
                p = (JPanel) f[i];
                String file = p.getName();
                RenameDialog rd = new RenameDialog(this, this.getFrame(), true, file);
                rd.setVisible(true);
              } catch (Exception e) {
                e.printStackTrace();
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "RenameDialog error", e.getMessage());
                ed.setVisible(true);
              }
            }
          } else {
            for (i = 0; i < f.length; i++) {
              try {
                p = (JPanel) f[i];
                String file = p.getName();
                RenameDialog rd = new RenameDialog(this, this.getFrame(), true, file);
                rd.setVisible(true);
              } catch (Exception e) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error rename", e.getMessage());
                ed.setVisible(true);
              }
            }
          }
        }
        refresh();
      } catch (java.lang.NullPointerException n) {
        ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error rename", n.getMessage());
        ed.setVisible(true);
      }
    }
  }

  private void downloadDocs() {
    Object[] f = active.getSelectedValues();
    int[] indices = active.getSelectedIndices();
    if (indices.length > 1) {
      JPanel p;
      for (int i = 0; i < f.length; i++) {
        try {
          p = (JPanel) f[i];
          String resourceID = p.getName();
          DownloadSingleGDocDialog dd = new DownloadSingleGDocDialog(this.getFrame(), true, resourceID, this.docs, jLabel1.getText(), spreads);
          dd.setVisible(true);

        } catch (Exception e) {
          e.printStackTrace();
          ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "DownloadDocs error", e.getMessage());
          ed.setVisible(true);
        }
      }
    }
    if (indices.length == 1) {
      JPanel p = (JPanel) active.getSelectedValue();
      String resourceID = p.getName();
      DownloadSingleGDocDialog dd = new DownloadSingleGDocDialog(this.getFrame(), true, resourceID, this.docs, jLabel1.getText(), spreads);
      dd.setVisible(true);

    }
    listDirectory(jLabel1.getText(), jList1, 1);
  }

  private void copyDialog() {
    if (!jLabel2.getText().startsWith("/picasa") && !jLabel2.getText().startsWith("/docs")) {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        JPanel p;
        int i = 0;
        if (indices[0] == 0) {
          for (i = 1; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String from = "";
              String to = "";
              if (active.getName().equalsIgnoreCase("1")) {
                from = jLabel1.getText();
                to = jLabel2.getText();
              } else {
                to = jLabel1.getText();
                from = jLabel2.getText();
              }
              try {
                CopyFileDialog cd = new CopyFileDialog(this, this.getFrame(), true, to, file);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Copy dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Copy dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        } else {
          for (i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String from = "";
              String to = "";
              if (active.getName().equalsIgnoreCase("1")) {
                from = jLabel1.getText();
                to = jLabel2.getText();
              } else {
                to = jLabel1.getText();
                from = jLabel2.getText();
              }
              try {
                CopyFileDialog cd = new CopyFileDialog(this, this.getFrame(), true, to, file);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Copy dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Copy dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
    }
  }

  private void moveDialog() {
    if (!jLabel2.getText().startsWith("/picasa") && !jLabel2.getText().startsWith("/docs")) {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        JPanel p;
        int i = 0;
        if (indices[0] == 0) {
          for (i = 1; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String from = "";
              String to = "";
              if (active.getName().equalsIgnoreCase("1")) {
                from = jLabel1.getText();
                to = jLabel2.getText();
              } else {
                to = jLabel1.getText();
                from = jLabel2.getText();
              }
              try {
                MoveFileDialog cd = new MoveFileDialog(this, this.getFrame(), true, to, file);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Move dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Move dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        } else {
          for (i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String from = "";
              String to = "";
              if (active.getName().equalsIgnoreCase("1")) {
                from = jLabel1.getText();
                to = jLabel2.getText();
              } else {
                to = jLabel1.getText();
                from = jLabel2.getText();
              }
              try {
                MoveFileDialog cd = new MoveFileDialog(this, this.getFrame(), true, to, file);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Move dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Move dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
    }
  }

  private void makeDirDialog() {
    if (active.getName().equalsIgnoreCase("1")) {
      MakeDirDialog md = new MakeDirDialog(this, this.getFrame(), true, jLabel1.getText());
      md.setVisible(true);
    } else {
      if (!jLabel2.getText().startsWith("/picasa")) {
        if (jLabel2.getText().startsWith("/docs")) {
          if (jLabel2.getText().equals("/docs")) {
            MakeGFolderDialog md = new MakeGFolderDialog(this.getFrame(), true, docs, "root");
            md.setVisible(true);
          } else {
            MakeGFolderDialog md = new MakeGFolderDialog(this.getFrame(), true, docs, currentGFolder);
            md.setVisible(true);
          }
          refresh();
        } else {
          MakeDirDialog md = new MakeDirDialog(this, this.getFrame(), true, jLabel2.getText());
          md.setVisible(true);
        }
      }
    }
  }

  private void showUploadDialog() {
    if (active != null & active.getName().equalsIgnoreCase("1") && jLabel2.getText().startsWith("/picasa")) {
      int index = (active.getSelectedIndex());
      JPanel p = (JPanel) active.getSelectedValue();
      UploadPicasaDialog d = new UploadPicasaDialog(this.getFrame(), true, picasa, usernamePicasa, p.getName(), this);
      d.setVisible(true);
    }

    if (active != null & active.getName().equalsIgnoreCase("1") && jLabel2.getText().startsWith("/docs")) {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {

        JPanel p;
        int i = 0;
        if (indices[0] == 0) {
          for (i = 1; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String toRes = "";
              if (jLabel2.getText().equals("/docs")) {
                toRes = "root";
              } else {
                toRes = currentGFolder;
              }

              try {
                UploadGDocsProgressDialog cd = new UploadGDocsProgressDialog(this.getFrame(), true, file, toRes, docs);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "showUploadDialog dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "showUploadDialog dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        } else {
          for (i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              String toRes = "";
              if (jLabel2.getText().equals("/docs")) {
                toRes = "root";
              } else {
                toRes = currentGFolder;
              }
              try {
                UploadGDocsProgressDialog cd = new UploadGDocsProgressDialog(this.getFrame(), true, file, toRes, docs);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "showUploadDialog dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "showUploadDialog dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
      refresh();
    }
  }

  private void removeDialog() {
    YesNoDialog y = new YesNoDialog(this.getFrame(), true, this);
    y.setVisible(true);
    if (cont) {
      cont = false;
      if (active.getName().equals("0") && jLabel2.getText().startsWith("/picasa")) {
        if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
          Object[] f = active.getSelectedValues();
          int[] indices = active.getSelectedIndices();
          if (indices.length > 0) {
            JPanel p;
            for (int i = 0; i < f.length; i++) {
              p = (JPanel) f[i];
              String file = p.getName();
              RemovePicasaDialog cd = new RemovePicasaDialog(this, this.getFrame(), true, file, true, usernamePicasa, picasa);
              cd.setVisible(true);
            }
          }
          refresh();
        } else {
          Object[] f = active.getSelectedValues();
          int[] indices = active.getSelectedIndices();
          if (indices.length > 0) {

            RemovePicasaFileDialog cd = new RemovePicasaFileDialog(this, this.getFrame(), true, usernamePicasa, picasa, f);
            cd.setVisible(true);
          }
          refresh();
        }
      } else {
        Object[] f = active.getSelectedValues();
        int[] indices = active.getSelectedIndices();
        if (indices.length > 0) {
          JPanel p;
          int i = 0;
          if (indices[0] == 0) {
            for (i = 1; i < f.length; i++) {
              try {
                p = (JPanel) f[i];
                String file = p.getName();
                try {
                  RemoveFileDialog cd = new RemoveFileDialog(this, this.getFrame(), true, file);
                  cd.setVisible(true);
                } catch (Exception n) {
                  ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Remove dialog", n.getMessage());
                  ed.setVisible(true);
                }
              } catch (Exception e) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Remove dialog", e.getMessage());
                ed.setVisible(true);
              }
            }
          } else {
            for (i = 0; i < f.length; i++) {
              try {
                p = (JPanel) f[i];
                String file = p.getName();
                try {
                  RemoveFileDialog cd = new RemoveFileDialog(this, this.getFrame(), true, file);
                  cd.setVisible(true);
                } catch (Exception n) {
                  ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Remove dialog", n.getMessage());
                  ed.setVisible(true);
                }
              } catch (Exception e) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Remove dialog", e.getMessage());
                ed.setVisible(true);
              }
            }
            refresh();
          }
        }
      }
    }
  }

  private void removeGDocsDialog() {
    YesNoGDocsDialog y = new YesNoGDocsDialog(this.getFrame(), true, this);
    y.setVisible(true);
    if (cont) {
      cont = false;
      if (active.getName().equals("0") && jLabel2.getText().startsWith("/docs")) {
        Object[] f = active.getSelectedValues();
        int[] indices = active.getSelectedIndices();
        if (indices.length > 0) {
          JPanel p;
          for (int i = 0; i < f.length; i++) {
            p = (JPanel) f[i];
            String resourceId = p.getName();
            RemoveGDocsFileDialog cd = new RemoveGDocsFileDialog(this.getFrame(), true, resourceId, trash, docs);
            cd.setVisible(true);
          }
        }
        refresh();
      }
    }
    trash = true;
  }

  private void propertiesDialog() {
    if (logPicasa && jLabel2.getText().startsWith("/picasa") && active.getName().equalsIgnoreCase("0")) {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
          for (int i = 0; i < f.length; i++) {

            String albumID = ((JPanel) f[i]).getName();
            try {
              AlbumInfo a = FigooPicasaClient.getAlbumInfo(albumID, usernamePicasa, picasa);
              AlbumInfoDialog ad = new AlbumInfoDialog(getFrame(), false, a);
              ad.setVisible(true);
            } catch (MalformedURLException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            } catch (IOException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            } catch (ServiceException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            }
          }
        } else {
          for (int i = 0; i < f.length; i++) {
            String albumID = ((JPanel) f[i]).getName();
            try {
              PhotoInfoDialog id = new PhotoInfoDialog(getFrame(), false, FigooPicasaClient.getPhotoInfo(albumID, picasa, usernamePicasa));
              id.setVisible(true);
            } catch (MalformedURLException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            } catch (IOException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            } catch (ServiceException ex) {
              ex.printStackTrace();
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "propertiesDialog error", ex.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
    } else if (logPicasa && jLabel2.getText().startsWith("/docs") && active.getName().equalsIgnoreCase("0")) {
      // doc
    } else {
      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        JPanel p;
        int i = 0;
        if (indices[0] == 0) {
          for (i = 1; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              try {
                PropertiesDialog cd = new PropertiesDialog(this.getFrame(), false, file, this);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        } else {
          for (i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              try {
                PropertiesDialog cd = new PropertiesDialog(this.getFrame(), false, file, this);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
    }
  }

  /**
   *
   */
  public void refresh() {
    listDirectory(jLabel1.getText(), jList1, 1);
    if (!jLabel2.getText().startsWith("/picasa") && !jLabel2.getText().startsWith("/docs")) {
      listDirectory(jLabel2.getText(), jList2, 0);
      findRoots(jLabel2.getText(), 0);
    } else {
      if (jLabel2.getText().startsWith("/picasa")) {
        listPicasa(jList2, 0);
      } else {
        try {
          docsStructure = FigooDocsClient.getStructure(docs);
          listDocs(jList2, 0);
        } catch (MalformedURLException ex) {
          Logger.getLogger(FigooView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(FigooView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServiceException ex) {
          Logger.getLogger(FigooView.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    findRoots(jLabel1.getText(), 1);
  }

  private void listPicasa(JList list, int left) {
    try {
      jButton11.setEnabled(false);
      ArrayList<String> titles = FigooPicasaClient.getAllPicasaAlbumTitle(picasa, this.usernamePicasa);
      ArrayList<String> ids = FigooPicasaClient.getAllPicasaAlbumID(picasa, this.usernamePicasa);
      list = new JList();
      list.setName(left + "");
      list.addKeyListener(new MyKeyListener());

      if (left == 1) {
        jScrollPane1.setViewportView(list);
      } else {
        jScrollPane2.setViewportView(list);
      }
      DefaultListModel listModel = new DefaultListModel();
      list.setCellRenderer(new ListRenderer());
      jLabel2.setText("/picasa");
      //list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      final JList list2 = list;

      if (titles != null && ids != null) {

        Vector pole = new Vector();
        Vector pole2 = new Vector();

        JLabel icon;
        JPopupMenu popup;
        JMenuItem menuItem;
        popup = new JPopupMenu();
        menuItem = new JMenuItem("Open");
        Font font = menuItem.getFont();
        menuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            openAlbumPicasa(list2);
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Modify");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            modifyPicasa();
          }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Download");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            downloadPicasa(list2);
          }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            deletePicasa();
          }
        });
        popup.add(menuItem);
        popup.addSeparator();
        menuItem = new JMenuItem("Properties");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            propertiesDialog();
          }
        });
        popup.add(menuItem);
        JPanel p;
        JLabel name;
        File dir = new File(System.getProperty("user.home"));

        for (int i = 0; i < titles.size(); i++) {
          p = new JPanel();
          p.setName(ids.get(i));
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(titles.get(i));
          pole.add(p);

          Icon image = getIcon(dir);
          icon = new JLabel(image);
          p.add(icon);
          p.add(name);
        }

        final JPopupMenu popup2 = popup;

        list.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
            // if right mouse button clicked (or me.isPopupTrigger())
            if (SwingUtilities.isRightMouseButton(me)
                    && !list2.isSelectionEmpty()
                    && list2.locationToIndex(me.getPoint())
                    == list2.getSelectedIndex()) {
              popup2.show(list2, me.getX(), me.getY());
            }
          }
        });
        pole2.addAll(pole);
        list.setListData(pole2);

        list.addMouseListener(new DirectoryActionJList(list, left));
        list.addKeyListener(null);
      }
    } catch (MalformedURLException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List picasa", ex.getMessage());
      ed.setVisible(true);
    } catch (IOException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List picasa", ex.getMessage());
      ed.setVisible(true);
    } catch (ServiceException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List picasa", ex.getMessage());
      ed.setVisible(true);
    }
  }

  private void deletePicasa() {
    removeDialog();
  }

  private void downloadPicasa(JList lis) {
    PicasaSizeDialog pd = new PicasaSizeDialog(getFrame(), true, lis, this);
    pd.setVisible(true);
  }

  /**
   *
   * @param size
   * @param lis
   */
  public void downloadPicasaStep2(String size, JList lis) {
    int[] indices = lis.getSelectedIndices();
    if (indices != null && indices.length > 0) {
      ListModel lm = lis.getModel();

      if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
        try {
          for (int i = 0; i < indices.length; i++) {
            JPanel dir = (JPanel) lm.getElementAt(indices[i]);
            lis.ensureIndexIsVisible(indices[i]);
            String p = dir.getName();
            DownloadPicasaDialog dp = new DownloadPicasaDialog(this, getFrame(), true, jLabel1.getText(), size, picasa, usernamePicasa, p, true);
            dp.setVisible(true);
          }
        } catch (Exception ex) {
          ex.printStackTrace();
          ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "downloadPicasaStep2 error", ex.getMessage());
          ed.setVisible(true);
        }
      } else {
        ArrayList<String> todo = new ArrayList<String>();
        for (int i = 0; i < indices.length; i++) {
          JPanel dir = (JPanel) lm.getElementAt(indices[i]);
          lis.ensureIndexIsVisible(indices[i]);
          todo.add(dir.getName());
        }
        DownloadPicasaDialog dp = new DownloadPicasaDialog(this, getFrame(), true, jLabel1.getText(), size, picasa, usernamePicasa, todo, false);
        dp.setVisible(true);
      }
    }
  }

  private void modifyPicasa() {
    if (jLabel2.getText().equalsIgnoreCase("/picasa")) {

      Object[] f = active.getSelectedValues();
      int[] indices = active.getSelectedIndices();
      if (indices.length > 0) {
        JPanel p;
        int i = 0;
        AlbumInfo al;
        for (i = 0; i < f.length; i++) {
          try {
            p = (JPanel) f[i];
            String file = p.getName();
            try {
              al = FigooPicasaClient.getAlbumInfo(file, usernamePicasa, picasa);
              ModifyAlbumPicasaDialog cd = new ModifyAlbumPicasaDialog(this.getFrame(), false, al, picasa, usernamePicasa, this);
              cd.setVisible(true);
            } catch (Exception n) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", n.getMessage());
              ed.setVisible(true);
            }
          } catch (Exception e) {
            ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", e.getMessage());
            ed.setVisible(true);
          }
        }
      }
    } else {
      if (jLabel2.getText().startsWith("/picasa")) {

        Object[] f = active.getSelectedValues();
        int[] indices = active.getSelectedIndices();
        if (indices.length > 0) {
          JPanel p;
          int i = 0;
          PhotoInfo al;
          for (i = 0; i < f.length; i++) {
            try {
              p = (JPanel) f[i];
              String file = p.getName();
              try {
                al = FigooPicasaClient.getPhotoInfo(file, picasa, usernamePicasa);
                ModifyPhotoPicasaDialog cd = new ModifyPhotoPicasaDialog(this.getFrame(), false, al, picasa, usernamePicasa, this);
                cd.setVisible(true);
              } catch (Exception n) {
                ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", n.getMessage());
                ed.setVisible(true);
              }
            } catch (Exception e) {
              ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "properties dialog", e.getMessage());
              ed.setVisible(true);
            }
          }
        }
      }
    }
  }

  private void openTempPicasaFile(String idPicture) {
    String size = "s640";
    try {
      FigooPicasaClient.downloadTempPhoto(desktop, size, idPicture, picasa, usernamePicasa);
    } catch (MalformedURLException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "openTempPicasaFile error", ex.getMessage());
      ed.setVisible(true);
    } catch (IOException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "openTempPicasaFile error", ex.getMessage());
      ed.setVisible(true);
    } catch (ServiceException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "openTempPicasaFile error", ex.getMessage());
      ed.setVisible(true);
    }
  }

  private void openAlbumPicasa(JList lis) {

    int[] indices = lis.getSelectedIndices();
    if (indices.length == 1) {
      ListModel lm = lis.getModel();
      JPanel dir = (JPanel) lm.getElementAt(indices[0]);
      lis.ensureIndexIsVisible(indices[0]);
      String p = dir.getName();
      if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
        openAlbum(p);
      }
    }
  }

  private void openAlbum(String id) {
    try {
      ArrayList<String> titles = FigooPicasaClient.listPicasaAlbumTitle(picasa, id, this.usernamePicasa);
      ArrayList<String> ids = FigooPicasaClient.listPicasaAlbumID(picasa, id, this.usernamePicasa);
      String albumName = FigooPicasaClient.getAlbumNameByID(picasa, id, this.usernamePicasa);

      this.lastPicasaId = id;
      jButton11.setEnabled(false);
      jList2 = new JList();
      jList2.setName("0");
      jList2.addKeyListener(new MyKeyListener());
      jScrollPane2.setViewportView(jList2);
      DefaultListModel listModel = new DefaultListModel();
      jList2.setCellRenderer(new ListRenderer());
      jLabel2.setText("/picasa/" + albumName);
      final JList list2 = jList2;

      if (titles != null && ids != null) {

        Vector pole = new Vector();
        Vector pole2 = new Vector();

        JLabel icon;
        JPopupMenu popup;
        JMenuItem menuItem;
        popup = new JPopupMenu();
        menuItem = new JMenuItem("Open");
        Font font = menuItem.getFont();
        menuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {

            ListModel lm = list2.getModel();
            JPanel dir = (JPanel) lm.getElementAt(list2.getSelectedIndex());
            openTempPicasaFile(dir.getName());
          }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Modify");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            modifyPicasa();
          }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Download");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            downloadPicasa(list2);
          }
        });
        popup.add(menuItem);

        menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            deletePicasa();
          }
        });
        popup.add(menuItem);
        popup.addSeparator();
        menuItem = new JMenuItem("Properties");

        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            propertiesDialog();
          }
        });
        popup.add(menuItem);

        JPanel p = new JPanel();
        p.setName("..");
        p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
        JLabel name = new JLabel("..");
        p.add(name);
        pole2.add(p);
        File tmp;
        for (int i = 0; i < titles.size(); i++) {

          p = new JPanel();
          p.setName(ids.get(i));
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(titles.get(i));
          pole.add(p);

          tmp = File.createTempFile("aaaa", titles.get(i).substring(titles.get(i).lastIndexOf(".")));

          Icon image = getIcon(tmp);
          icon = new JLabel(image);
          p.add(icon);
          p.add(name);
        }

        final JPopupMenu popup2 = popup;

        jList2.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
            if (SwingUtilities.isRightMouseButton(me)
                    && !list2.isSelectionEmpty()
                    && list2.locationToIndex(me.getPoint())
                    == list2.getSelectedIndex()) {
              popup2.show(list2, me.getX(), me.getY());
            }
          }
        });
        pole2.addAll(pole);
        jList2.setListData(pole2);

        jList2.addMouseListener(new DirectoryActionJList(jList2, 0));
        jList2.addKeyListener(null);
      }
    } catch (MalformedURLException ex) {
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Open album", ex.getMessage());
      ed.setVisible(true);
    } catch (IOException ex) {
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Open album", ex.getMessage());
      ed.setVisible(true);
    } catch (ServiceException ex) {
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Open album", ex.getMessage());
      ed.setVisible(true);
      ex.printStackTrace();
    }
  }

  public void loginDocs(String u, String p) {
    try {
      this.usernameDoc = u;
      docs = FigooDocsClient.logDocs(u, p);
      spreads = FigooDocsClient.logSpreads(u, p);
      this.logDoc = true;
      ComboBoxModel cm = rootBox2.getModel();
      int index = cm.getSize();
      rootBox2.addItem(new String("/gdocs"));
      rootBox2.setSelectedIndex(index);
      jButton8.setEnabled(true);
      docsStructure = FigooDocsClient.getStructure(docs);
      listDocs(jList2, 0);
    } catch (Exception ex) {
      this.logDoc = false;
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "Error on Docs Login", ex.getMessage());
      ex.printStackTrace();
      ed.setVisible(true);
    }
  }

  private void listDocs(JList list, int left) {
    try {
      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getResourceMap(FigooView.class);
      jButton11.setEnabled(false);
      ArrayList<DocumentListEntry> files = FigooDocsClient.getRootContent(docs);
      list = new JList();
      list.setName(left + "");
      list.addKeyListener(new MyKeyListener());

      if (left == 1) {
        jScrollPane1.setViewportView(list);
      } else {
        jScrollPane2.setViewportView(list);
      }
      DefaultListModel listModel = new DefaultListModel();
      list.setCellRenderer(new ListRenderer());
      jLabel2.setText("/docs");
      //list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      final JList list2 = list;

      if (files != null) {

        Vector pole = new Vector();
        Vector pole2 = new Vector();

        JLabel icon;
        JPopupMenu popup;
        JMenuItem menuItem;
        popup = new JPopupMenu();
        menuItem = new JMenuItem("Open");
        menuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Download");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            downloadDocs();
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Delete");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            removeGDocsDialog();
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Rename");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            renameDialog();
          }
        });
        popup.add(menuItem);

        JPanel p;
        JLabel name;
        File dir = new File(System.getProperty("user.home"));
        DocumentListEntry entry;
        for (int i = 0; i < files.size(); i++) {
          p = new JPanel();
          entry = files.get(i);
          p.setName(entry.getResourceId());
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(entry.getTitle().getPlainText());
          String type = entry.getType();
          if (type.equals("folder")) {

            File tmp = new File(System.getProperty("java.io.tmpdir"));
            Icon image = getIcon(tmp);
            icon = new JLabel(image);
            p.add(icon);

          } else if (type.equals("document")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("doc.icon")));
            p.add(icon);
          } else if (type.equals("presentation")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("ppt.icon")));
            p.add(icon);
          } else if (type.equals("spreadsheet")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("xls.icon")));
            p.add(icon);
          } else if (type.equals("file") || type.equals("pdf")) {
            File tmp = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + files.get(i).getTitle().getPlainText());
            if (!tmp.exists()) {
              tmp.createNewFile();
            }
            tmp.deleteOnExit();
            Icon image = getIcon(tmp);
            icon = new JLabel(image);
            p.add(icon);
          }
          pole.add(p);
          p.add(name);
        }

        final JPopupMenu popup2 = popup;

        list.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
            // if right mouse button clicked (or me.isPopupTrigger())
            if (SwingUtilities.isRightMouseButton(me)
                    && !list2.isSelectionEmpty()
                    && list2.locationToIndex(me.getPoint())
                    == list2.getSelectedIndex()) {
              popup2.show(list2, me.getX(), me.getY());
            }
          }
        });
        pole2.addAll(pole);
        list.setListData(pole2);

        list.addMouseListener(new DirectoryActionJList(list, left));
        list.addKeyListener(null);
      }
    } catch (MalformedURLException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs1", ex.getMessage());
      ed.setVisible(true);
    } catch (IOException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs2", ex.getMessage());
      ed.setVisible(true);
    } catch (ServiceException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs3", ex.getMessage());
      ed.setVisible(true);
    }
  }

  private void listDocs(JList list, int left, String resourceID) {
    try {
      org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(figoo.FigooApp.class).getContext().getResourceMap(FigooView.class);
      jButton11.setEnabled(false);
      this.currentGFolder = resourceID;
      ArrayList<DocumentListEntry> files = FigooDocsClient.getFolderContent(resourceID, docs);
      list = new JList();
      list.setName(left + "");
      list.addKeyListener(new MyKeyListener());

      if (left == 1) {
        jScrollPane1.setViewportView(list);
      } else {
        jScrollPane2.setViewportView(list);
      }
      list.setCellRenderer(new ListRenderer());
      final JList list2 = list;

      if (files != null) {

        Vector pole = new Vector();
        Vector pole2 = new Vector();

        JLabel icon;
        JPopupMenu popup;
        JMenuItem menuItem;
        popup = new JPopupMenu();
        menuItem = new JMenuItem("Open");
        menuItem.setFont(new Font("Tahoma", Font.BOLD, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Download");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            downloadDocs();
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Delete");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            removeGDocsDialog();
          }
        });
        popup.add(menuItem);


        menuItem = new JMenuItem("Rename");
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 11));
        menuItem.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(ActionEvent e) {
            renameDialog();
          }
        });
        popup.add(menuItem);

        JPanel p = new JPanel();
        String tt = this.docsStructure.get(resourceID);
        if (tt.startsWith("folder")) {
          p.setName(this.docsStructure.get(resourceID));
        } else {
          p.setName("folder:" + this.docsStructure.get(resourceID));
        }
        p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
        JLabel name = new JLabel("..");
        p.add(name);
        pole2.add(p);
        File dir = new File(System.getProperty("user.home"));
        DocumentListEntry entry;
        for (int i = 0; i < files.size(); i++) {
          p = new JPanel();
          entry = files.get(i);
          p.setName(entry.getResourceId());
          p.setLayout(new java.awt.FlowLayout(FlowLayout.LEFT));
          name = new JLabel(entry.getTitle().getPlainText());
          String type = entry.getType();
          if (type.equals("folder")) {

            File tmp = new File(System.getProperty("java.io.tmpdir"));
            Icon image = getIcon(tmp);
            icon = new JLabel(image);
            p.add(icon);

          } else if (type.equals("document")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("doc.icon")));
            p.add(icon);
          } else if (type.equals("presentation")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("ppt.icon")));
            p.add(icon);
          } else if (type.equals("spreadsheet")) {
            icon = new JLabel();
            icon.setIcon((resourceMap.getIcon("xls.icon")));
            p.add(icon);
          } else if (type.equals("file") || type.equals("pdf")) {
            File tmp = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + files.get(i).getTitle().getPlainText());
            if (type.equals("pdf") && !files.get(i).getTitle().getPlainText().endsWith("pdf")) {
              tmp = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "tempp" + ".pdf");
            }

            if (!tmp.exists()) {
              tmp.createNewFile();
            }
            tmp.deleteOnExit();
            Icon image = getIcon(tmp);
            icon = new JLabel(image);
            p.add(icon);
          }
          pole.add(p);
          p.add(name);
        }

        final JPopupMenu popup2 = popup;

        list.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
            // if right mouse button clicked (or me.isPopupTrigger())
            if (SwingUtilities.isRightMouseButton(me)
                    && !list2.isSelectionEmpty()
                    && list2.locationToIndex(me.getPoint())
                    == list2.getSelectedIndex()) {
              popup2.show(list2, me.getX(), me.getY());
            }
          }
        });
        pole2.addAll(pole);
        list.setListData(pole2);
        list.addMouseListener(new DirectoryActionJList(list, left));
        list.addKeyListener(null);
      }
    } catch (MalformedURLException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs1", ex.getMessage());
      ed.setVisible(true);
    } catch (IOException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs2", ex.getMessage());
      ed.setVisible(true);
    } catch (ServiceException ex) {
      ex.printStackTrace();
      ErrorDialog ed = new ErrorDialog(new javax.swing.JFrame(), true, "List docs3", ex.getMessage());
      ed.setVisible(true);
    }
  }

  class MyKeyListener implements KeyListener {

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
      @SuppressWarnings("static-access")
      String key = KeyEvent.getKeyText(e.getKeyCode());
      if (key.equalsIgnoreCase("F1")) {
        propertiesDialog();
      } else if (key.equalsIgnoreCase("F2")) {
        renameDialog();
      } else if (key.equalsIgnoreCase("F3")) {
        makeDirDialog();
      } else if (key.equalsIgnoreCase("F4")) {
        archiveDialog();
      } else if (key.equalsIgnoreCase("F5")) {
        copyDialog();
      } else if (key.equalsIgnoreCase("F6")) {
        moveDialog();
      } else if (key.equalsIgnoreCase("F7")) {

        if (jLabel2.getText().startsWith("/docs")) {
          removeGDocsDialog();
        } else {
          removeDialog();
        }
      } else if (key.equalsIgnoreCase("F8")) {
        showUploadDialog();
      } else if (key.equalsIgnoreCase("F9")) {
        if (active != null && active.getName().equals("0") && jLabel2.getText().startsWith("/picasa")) {
          downloadPicasa(active);
        }
        if (active != null && active.getName().equals("0") && jLabel2.getText().startsWith("/docs")) {
          downloadDocs();
        }
      }
    }

    public void keyReleased(KeyEvent e) {
    }
  }

  // Double click on the JList
  class DirectoryActionJList extends MouseAdapter {

    protected JList list;
    protected int left;

    public DirectoryActionJList(JList l, int left) {
      list = l;
      this.left = left;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

      if (e.getClickCount() == 2 && e.getButton() == 1) {
        if (logPicasa && list.getName().equalsIgnoreCase("0") && jLabel2.getText().startsWith("/picasa")) {
          int index = list.locationToIndex(e.getPoint());
          ListModel lm = list.getModel();
          JPanel dir = (JPanel) lm.getElementAt(index);
          list.ensureIndexIsVisible(index);
          if (jLabel2.getText().equalsIgnoreCase("/picasa")) {
            openAlbum(dir.getName());
          } else {
            if (dir.getName().equals("..")) {
              listPicasa(list, 0);
            } else {
              openTempPicasaFile(dir.getName());
            }
          }
        } else if (logDoc && list.getName().equalsIgnoreCase("0") && jLabel2.getText().startsWith("/docs")) {

          int index = list.locationToIndex(e.getPoint());
          ListModel lm = list.getModel();
          JPanel dir = (JPanel) lm.getElementAt(index);
          list.ensureIndexIsVisible(index);
          Component[] cmp = dir.getComponents();
          JLabel lab;
          try {
            lab = (JLabel) cmp[1];
          } catch (Exception ee) {
            lab = (JLabel) cmp[0];
          }


          if (lab.getText().equals("..")) {
            jLabel2.setText(jLabel2.getText().substring(0, jLabel2.getText().lastIndexOf("/")));
          } else {
            jLabel2.setText(jLabel2.getText() + "/" + lab.getText());
            //parentDocsID = dir.getName();
          }

          if (dir.getName().contains("root")) {
            listDocs(list, 0);
            jLabel2.setText("/docs");
          } else {
            listDocs(list, 0, dir.getName());
          }


        } else {
          int index = list.locationToIndex(e.getPoint());
          ListModel lm = list.getModel();
          JPanel dir = (JPanel) lm.getElementAt(index);
          list.ensureIndexIsVisible(index);
          File f = new File(dir.getName());
          
          
//          if (f.isDirectory()) {
//            listDirectory(dir.getName(), list, left);
//          } else if (f.isFile() && desktop != null) {
//            try {
//              //desktop.edit(f);
//              desktop.open(f);
//            } catch (IOException ex) {
//              ex.printStackTrace();
//            }
//          }
          if (f.isDirectory()) {
            //listDirectory(dir.getName(), list, left);
            if (f.getPath().toLowerCase().startsWith("/var/tmp/usb")) {
              listDirectory(dir.getName(), list, left);
            } else {
              System.out.println("we do not permit");
            }

          } else if (f.isFile() && desktop != null) {
            try {
              String filename = f.getName().toLowerCase();
              if (filename.endsWith(".pdf") || filename.endsWith(".jpeg") || filename.endsWith(".jpg")  || filename.endsWith(".ppt")) {
                System.out.println("valid file " + filename.compareTo(".pdf"));
                desktop.open(f);

              } else {
                System.out.println("invalid file");
              }

              //desktop.open(f);

            } catch (IOException ex) {
              ex.printStackTrace();
            }
          }
          
        }
      } else {
        active = list;
        if (active.getName().equals("1") || (active.getName().equals("0") && !jLabel2.getText().startsWith("/docs") && !jLabel2.getText().startsWith("/picasa"))) {
          if (e.getButton() == 2) {
            int index = list.locationToIndex(e.getPoint());
            ListModel lm = list.getModel();
            JPanel dir = (JPanel) lm.getElementAt(index);
            showThumbmail(dir.getName(), list, e);
          }
        }
      }
    }

    public void mouseClickedOpen(MouseEvent e) {
      int index = list.locationToIndex(e.getPoint());
      ListModel lm = list.getModel();
      JPanel dir = (JPanel) lm.getElementAt(index);

      list.ensureIndexIsVisible(index);
      File f = new File(dir.getName());
      if (f.isDirectory()) {
        //listDirectory(dir.getName(), list, left);
        if (f.getPath().toLowerCase().startsWith("/var/tmp/usb")) {
          listDirectory(dir.getName(), list, left);
        } else {
          System.out.println("we do not permit");
        }
    
      } else if (f.isFile() && desktop != null) {
        try {
          String filename = f.getName().toLowerCase();
          if (filename.endsWith(".pdf") || filename.endsWith(".jpeg") || filename.endsWith(".jpg")  || filename.endsWith(".ppt")) {
            System.out.println("valid file " + filename.compareTo(".pdf"));
            desktop.open(f);

          } else {
            System.out.println("invalid file");
          }
    
          //desktop.open(f);
          
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      
      
    }
  }

  private void showThumbmail(String name, JList list, MouseEvent e) {
    File file = new File(name);
    try {
      String type = " (*" + file.getName().substring(file.getName().lastIndexOf(".")) + ")";
      type = type.toLowerCase();
      if (type.contains(".pdf")) {
        ThumbmailDialog td = new ThumbmailDialog(getFrame(), true);
        td.doPdfThumbmail(file);
        td.setVisible(true);
      } else if (type.contains(".jpg") || type.contains(".jpeg") || type.contains(".gif") || type.contains(".png")) {
        ThumbmailDialog td = new ThumbmailDialog(getFrame(), true);
        td.doImageThumbmail(file);
        td.setVisible(true);
      } else {
        propertiesDialog();
      }
    } catch (Exception ex) {
      propertiesDialog();
    }
  }
}
