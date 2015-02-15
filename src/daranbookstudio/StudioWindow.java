/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daranbookstudio;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author 雅詩
 */
public class StudioWindow extends javax.swing.JFrame {

    public LoadingWindow loadwin;
    private DefaultListModel listModel;
    private DefaultListModel plistModel1;
    private DefaultListModel plistModel2;
    private DefaultListModel plistModel3;
    private DefaultComboBoxModel picModel;
    private String dirAddress;
    private String separator;
    private String nowtxtfile;
    private int listi;
    private Boolean isFirst;

    public String softname = "DaRanBookPackage Studio v1.1 beta";

    /**
     * Creates new form StudioWindow
     */
    public StudioWindow() {
        initComponents();
        Image icon = new ImageIcon(getClass().getResource("logo.png")).getImage();
        this.setIconImage(icon);
        isFirst = true;
        loadwin = new LoadingWindow();
        loadwin.closewindow();
        lbl_stat.setText("请新建或打开一个数据包。使用较短的文件路径更容易编辑。");
        this.setTitle(softname + " - 尚未加载数据包文件夹");
        System.out.println("os.name:" + System.getProperties().getProperty("os.name"));
        separator = System.getProperties().getProperty("file.separator");
        win_filemgr.setTitle(win_filemgr.getTitle() + "：" + System.getProperties().getProperty("os.name") + " 格式");
        win_fileedit.setTitle(win_fileedit.getTitle() + "：" + "没有打开文件，请在左侧列表选择要编辑的txt文件。");
        listModel = new DefaultListModel();
        plistModel1 = new DefaultListModel();
        plistModel2 = new DefaultListModel();
        plistModel3 = new DefaultListModel();
        picModel = new DefaultComboBoxModel();
        filemgr_list_files.setModel(listModel);
        plist_list1.setModel(plistModel1);
        plist_list2.setModel(plistModel2);
        plist_list3.setModel(plistModel3);
        lst_pic1.setModel(picModel);
        lst_pic2.setModel(picModel);
        //filemgr_list_files.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        filemgr_list_files.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1 && filemgr_list_files.getSelectedIndex() >= 0) {
//                    oneClick(filemgr_list_files.getSelectedValue());
//                }
                if (e.getClickCount() == 2 && filemgr_list_files.getSelectedIndex() >= 0) {
                    twoClick(filemgr_list_files.getSelectedValue());
                }
            }
        });
        plist_list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && plist_list1.getSelectedIndex() >= 0) {
                    oneClick(plist_list1.getSelectedIndex());
                }
            }
        });
        plist_list2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && plist_list2.getSelectedIndex() >= 0) {
                    oneClick(plist_list2.getSelectedIndex());
                }
            }
        });
        plist_list3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && plist_list3.getSelectedIndex() >= 0) {
                    oneClick(plist_list3.getSelectedIndex());
                }
            }
        });
    }

    public void oneClick(int index) {
        plist_list1.setSelectedIndex(index);
        plist_list2.setSelectedIndex(index);
        plist_list3.setSelectedIndex(index);
        if (index == 0) {
            plist_btn_delete.setEnabled(false);
        } else {
            plist_btn_delete.setEnabled(true);
        }
    }

    public void twoClick(Object value) {
        loadingOpen("正在打开文件");
        String fileAddress = dirAddress + separator + value.toString();
        win_fileedit.setTitle("文件编辑：" + fileAddress);
        nowtxtfile = fileAddress;
        BufferedReader reader;
        if (typeidtxt(fileAddress)) {
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
                String str = null;
                while ((str = reader.readLine()) != null) {
                    fileedit_txt_edit.setText(fileedit_txt_edit.getText() + str + "\n");
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadingClose();
    }

    private Boolean typeidtxt(String fileAddress) {
        String typename = fileAddress.substring(fileAddress.length() - 4);
        if (typename.equals(".txt") || typename.equals(".TXT")) {
            fileedit_btn_savefile.setEnabled(true);
            fileedit_txt_edit.setEnabled(true);
            fileedit_txt_edit.setText("");
            return true;
        } else {
            fileedit_btn_savefile.setEnabled(false);
            fileedit_txt_edit.setEnabled(false);
            fileedit_txt_edit.setText("不支持的文件格式（" + typename + "），不能编辑。");
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        win_filemgr = new javax.swing.JInternalFrame();
        jToolBar1 = new javax.swing.JToolBar();
        filemgr_btn_addfile = new javax.swing.JButton();
        filemgr_btn_removefile = new javax.swing.JButton();
        filemgr_btn_adddir = new javax.swing.JButton();
        filemgr_btn_clear = new javax.swing.JButton();
        filemgr_btn_reload = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        filemgr_list_files = new javax.swing.JList();
        jToolBar2 = new javax.swing.JToolBar();
        mainwin_btn_opendir = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        lbl_stat = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        win_insert = new javax.swing.JInternalFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        index_txt_zipname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        index_txt_author = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        index_txt_press = new javax.swing.JTextField();
        index_btn_gbookinfo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        index_btn_gfilelist = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chapter_txt_chaptername = new javax.swing.JTextField();
        chapter_com_isone = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        chapter_txt_sectionname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        chapter_txt_chapternum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chapter_txt_sectionnum = new javax.swing.JTextField();
        chapter_btn_g = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lst_pic1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        album_txt_picname = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        album_txt_picinfo = new javax.swing.JTextField();
        album_btn_g = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        keyword_txt_keyword = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        keyword_txt_info = new javax.swing.JTextField();
        keyword_btn_g = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        choice_txt_name = new javax.swing.JTextField();
        choice_btn_name = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        choice_txt_q = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        choice_txt_a = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        choice_txt_b = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        choice_txt_c = new javax.swing.JTextField();
        choice_txt_d = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        choice_com_a = new javax.swing.JCheckBox();
        choice_com_b = new javax.swing.JCheckBox();
        choice_com_c = new javax.swing.JCheckBox();
        choice_com_d = new javax.swing.JCheckBox();
        choice_btn_q = new javax.swing.JButton();
        choice_txt_qid = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        classify_txt_name = new javax.swing.JTextField();
        classify_btn_name = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        classify_txt_typea = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        classify_txt_typeb = new javax.swing.JTextField();
        classify_rad_typea = new javax.swing.JRadioButton();
        classify_rad_typeb = new javax.swing.JRadioButton();
        classify_txt_pic = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        lst_pic2 = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        plist_btn_browser = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        plist_list1 = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        plist_list2 = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        plist_list3 = new javax.swing.JList();
        plist_txt1 = new javax.swing.JTextField();
        plist_txt2 = new javax.swing.JTextField();
        plist_txt3 = new javax.swing.JTextField();
        plist_btn_insert = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        plist_btn_delete = new javax.swing.JButton();
        plist_btn_clear = new javax.swing.JButton();
        plist_btn_gplist = new javax.swing.JButton();
        win_fileedit = new javax.swing.JInternalFrame();
        jToolBar3 = new javax.swing.JToolBar();
        fileedit_btn_savefile = new javax.swing.JButton();
        fileedit_btn_clear = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        fileedit_txt_newfilename = new javax.swing.JTextField();
        fileedit_btn_newfile = new javax.swing.JButton();
        fileedit_btn_savenew = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fileedit_chk_entermode = new javax.swing.JCheckBox();
        jLabel32 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        fileedit_txt_edit = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loading");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        win_filemgr.setTitle("文件管理器");
        win_filemgr.setVisible(true);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        filemgr_btn_addfile.setText("添加");
        filemgr_btn_addfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filemgr_btn_addfile.setFocusable(false);
        filemgr_btn_addfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filemgr_btn_addfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filemgr_btn_addfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filemgr_btn_addfileActionPerformed(evt);
            }
        });
        jToolBar1.add(filemgr_btn_addfile);

        filemgr_btn_removefile.setText("删除");
        filemgr_btn_removefile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filemgr_btn_removefile.setFocusable(false);
        filemgr_btn_removefile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filemgr_btn_removefile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filemgr_btn_removefile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filemgr_btn_removefileActionPerformed(evt);
            }
        });
        jToolBar1.add(filemgr_btn_removefile);

        filemgr_btn_adddir.setText("添加文件夹");
        filemgr_btn_adddir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filemgr_btn_adddir.setFocusable(false);
        filemgr_btn_adddir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filemgr_btn_adddir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filemgr_btn_adddir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filemgr_btn_adddirActionPerformed(evt);
            }
        });
        jToolBar1.add(filemgr_btn_adddir);

        filemgr_btn_clear.setText("清空");
        filemgr_btn_clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filemgr_btn_clear.setFocusable(false);
        filemgr_btn_clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filemgr_btn_clear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filemgr_btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filemgr_btn_clearActionPerformed(evt);
            }
        });
        jToolBar1.add(filemgr_btn_clear);

        filemgr_btn_reload.setText("刷新");
        filemgr_btn_reload.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filemgr_btn_reload.setFocusable(false);
        filemgr_btn_reload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filemgr_btn_reload.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filemgr_btn_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filemgr_btn_reloadActionPerformed(evt);
            }
        });
        jToolBar1.add(filemgr_btn_reload);

        filemgr_list_files.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(filemgr_list_files);

        javax.swing.GroupLayout win_filemgrLayout = new javax.swing.GroupLayout(win_filemgr.getContentPane());
        win_filemgr.getContentPane().setLayout(win_filemgrLayout);
        win_filemgrLayout.setHorizontalGroup(
            win_filemgrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        win_filemgrLayout.setVerticalGroup(
            win_filemgrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(win_filemgrLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToolBar2.setFloatable(false);

        mainwin_btn_opendir.setText("打开数据包文件夹");
        mainwin_btn_opendir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainwin_btn_opendir.setFocusable(false);
        mainwin_btn_opendir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mainwin_btn_opendir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainwin_btn_opendir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainwin_btn_opendirActionPerformed(evt);
            }
        });
        jToolBar2.add(mainwin_btn_opendir);
        jToolBar2.add(filler1);

        lbl_stat.setForeground(java.awt.Color.blue);
        lbl_stat.setText("就绪。");
        jToolBar2.add(lbl_stat);

        jButton2.setText("关于和更新");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/daranbookstudio/cc80x15.png"))); // NOI18N
        jButton1.setToolTipText("");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        win_insert.setTitle("插入条目");
        win_insert.setVisible(true);

        jLabel4.setText("数据包（压缩包）名称（全部为小写英文字母不包含标点）：");

        index_txt_zipname.setText("pingmiangoucheng");

        jLabel7.setText("书籍编者（如果有多个编者请用空格隔开）：");

        index_txt_author.setText("xxx xxx xxx");

        jLabel8.setText("出版社名称：");

        index_txt_press.setText("xxxxxx出版社");

        index_btn_gbookinfo.setText("↓ 插入书籍信息 ↓");
        index_btn_gbookinfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        index_btn_gbookinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                index_btn_gbookinfoActionPerformed(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("<html>\n书籍信息必须在文件的前三行并且不能修改顺序，<p>\n请先插入书籍信息再插入文件列表。\n</html>");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("<html>\n将生成一个文件列表。<p>\n请注意章节的pdf和txt应由小到大排序，错误的章节顺序会导致显示问题。<p>\n生成后请手工核对一下排序，并删除无关文件。<p>\n</html>");
        jLabel10.setToolTipText("");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        index_btn_gfilelist.setText("↓ 插入文件列表 ↓");
        index_btn_gfilelist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        index_btn_gfilelist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                index_btn_gfilelistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(index_txt_zipname)
                    .addComponent(index_txt_author)
                    .addComponent(index_txt_press)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(index_btn_gbookinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                            .addComponent(index_btn_gfilelist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(index_txt_zipname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(index_txt_author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(index_txt_press, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(index_btn_gbookinfo)
                    .addComponent(index_btn_gfilelist))
                .addContainerGap())
        );

        jTabbedPane1.addTab("引导文件生成", jPanel1);

        jLabel1.setText("自定义章名称（如果不是第一小节请不要修改）：");

        chapter_txt_chaptername.setText("第？章 xxxxx");
        chapter_txt_chaptername.setEnabled(false);

        chapter_com_isone.setText("这是本章的第一节");
        chapter_com_isone.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chapter_com_isone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chapter_com_isoneActionPerformed(evt);
            }
        });

        jLabel3.setText("自定义节名称：");

        chapter_txt_sectionname.setText("第？节 xxxxx");

        jLabel5.setText("章号（纯数字）：");

        chapter_txt_chapternum.setText("0");

        jLabel6.setText("节号（纯数字）：");

        chapter_txt_sectionnum.setText("2");

        chapter_btn_g.setText("↓ 插入章节名称信息 ↓");
        chapter_btn_g.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chapter_btn_g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chapter_btn_gActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chapter_btn_g, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chapter_com_isone)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(chapter_txt_chapternum)
                            .addComponent(jLabel6)
                            .addComponent(chapter_txt_sectionnum))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chapter_txt_chaptername)
                            .addComponent(chapter_txt_sectionname)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(0, 572, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chapter_com_isone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chapter_txt_chaptername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chapter_txt_chapternum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chapter_txt_sectionname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chapter_txt_sectionnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chapter_btn_g)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("章节名称生成", jPanel2);

        jLabel11.setText("图片文件名：");

        lst_pic1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setText("图片标题：");

        album_txt_picname.setText("未命名图片");

        jLabel13.setText("图片描述：");

        album_txt_picinfo.setText("这个图片没有介绍");

        album_btn_g.setText("↓ 添加一张图片到相册 ↓");
        album_btn_g.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        album_btn_g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                album_btn_gActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lst_pic1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(album_txt_picname)
                    .addComponent(album_txt_picinfo)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(album_btn_g, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lst_pic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(album_txt_picname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(album_txt_picinfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(album_btn_g)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("相册图片列表生成", jPanel3);

        jLabel14.setText("关键字：");

        keyword_txt_keyword.setText("关键字");

        jLabel15.setText("解释：");

        keyword_txt_info.setText("这是关键字");

        keyword_btn_g.setText("↓ 插入关键字信息 ↓");
        keyword_btn_g.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        keyword_btn_g.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyword_btn_gActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keyword_txt_info)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(keyword_txt_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel15))
                        .addGap(0, 740, Short.MAX_VALUE))
                    .addComponent(keyword_btn_g, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(keyword_txt_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyword_txt_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(keyword_btn_g)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("关键字解释生成", jPanel4);

        jLabel16.setText("选择题配置文件中第一行必须为题目组名称：");

        choice_txt_name.setText("未命名选择题组");

        choice_btn_name.setText("↓ 插入选择题组名称 ↓");
        choice_btn_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_btn_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_btn_nameActionPerformed(evt);
            }
        });

        jLabel17.setText("请在下面逐个添加选择题题目：");

        jLabel18.setText("题目：");

        choice_txt_q.setText("问题？");

        jLabel19.setText("选项A：");

        choice_txt_a.setText("A.");

        jLabel20.setText("选项B：");

        choice_txt_b.setText("B.");

        jLabel21.setText("选项C：");

        choice_txt_c.setText("C.");

        choice_txt_d.setText("D.");

        jLabel22.setText("选项D：");

        jLabel23.setText("正确的选项是：");

        choice_com_a.setSelected(true);
        choice_com_a.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_com_a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_com_aActionPerformed(evt);
            }
        });

        choice_com_b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_com_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_com_bActionPerformed(evt);
            }
        });

        choice_com_c.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_com_c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_com_cActionPerformed(evt);
            }
        });

        choice_com_d.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_com_d.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_com_dActionPerformed(evt);
            }
        });

        choice_btn_q.setText("↓ 插入一个题目 ↓");
        choice_btn_q.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choice_btn_q.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choice_btn_qActionPerformed(evt);
            }
        });

        choice_txt_qid.setText("1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(choice_txt_c, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(choice_txt_d, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(choice_txt_b)
                                    .addComponent(choice_txt_a)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(choice_txt_qid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(choice_txt_q)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(choice_btn_q, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(choice_txt_name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(choice_btn_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(choice_com_c)
                                        .addComponent(choice_com_b)
                                        .addComponent(choice_com_d))
                                    .addComponent(choice_com_a))
                                .addGap(51, 51, 51)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choice_txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choice_btn_name)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(choice_txt_q, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice_txt_qid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(choice_txt_a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(choice_com_a))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(choice_txt_b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(choice_com_b))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(choice_txt_c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21))
                    .addComponent(choice_com_c))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choice_com_d)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(choice_txt_d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(choice_btn_q)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("选择题生成", jPanel5);

        jLabel24.setText("分类题配置文件中第一行必须为题目：");

        classify_txt_name.setText("未命名分类题");

        classify_btn_name.setText("↓ 插入分类题名称 ↓");
        classify_btn_name.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        classify_btn_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classify_btn_nameActionPerformed(evt);
            }
        });

        jLabel25.setText("注意，一共只能添加两个分类，每个分类有四张图片！");

        jLabel26.setText("分类A名称：");

        classify_txt_typea.setText("分类A");

        jLabel27.setText("分类B名称：");

        classify_txt_typeb.setText("分类B");

        classify_rad_typea.setSelected(true);
        classify_rad_typea.setText("分类A");
        classify_rad_typea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        classify_rad_typea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classify_rad_typeaActionPerformed(evt);
            }
        });

        classify_rad_typeb.setText("分类B");
        classify_rad_typeb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        classify_rad_typeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classify_rad_typebActionPerformed(evt);
            }
        });

        classify_txt_pic.setText("未命名图片");

        jLabel28.setText("图片名：");

        lst_pic2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel29.setText("图片文件名：");

        jButton19.setText("↓ 插入一张图片 ↓");
        jButton19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel30.setText("加入图片：");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classify_txt_name)
                    .addComponent(classify_btn_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(classify_txt_typea, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(classify_txt_typeb, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30))
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(classify_rad_typeb)
                            .addComponent(classify_rad_typea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(classify_txt_pic, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lst_pic2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classify_txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(classify_btn_name)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(classify_txt_typea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(classify_txt_typeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classify_rad_typea)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classify_rad_typeb)
                    .addComponent(classify_txt_pic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lst_pic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("分类题生成", jPanel6);

        jLabel31.setText("请把章节图标以章号命名（例如1.png,2.png）后导入。");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(231, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("章节图标", jPanel7);

        jLabel34.setText("该功能用于iOS版软件测试。请从要导入数据包的设备中提取文件“MyBook.plist”并打开它：");

        plist_btn_browser.setText("打开“MyBook.plist”文件...");
        plist_btn_browser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plist_btn_browserActionPerformed(evt);
            }
        });

        jLabel35.setText("书籍名称（bName）");

        plist_list1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(plist_list1);

        plist_list2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(plist_list2);

        plist_list3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(plist_list3);

        plist_txt1.setText("未命名书籍");

        plist_txt2.setText("noname.png");

        plist_txt3.setText("http://127.0.0.1/test/noname.zip");

        plist_btn_insert.setText("插入行");
        plist_btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plist_btn_insertActionPerformed(evt);
            }
        });

        jLabel36.setText("书籍封面图片文件名（bPicture）");

        jLabel37.setText("书籍来源网址（bWeb）");

        plist_btn_delete.setText("删除选择");
        plist_btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plist_btn_deleteActionPerformed(evt);
            }
        });

        plist_btn_clear.setText("清空");
        plist_btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plist_btn_clearActionPerformed(evt);
            }
        });

        plist_btn_gplist.setText("↓ 生成“MyBook.plist” ↓");
        plist_btn_gplist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plist_btn_gplistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plist_btn_gplist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(plist_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plist_txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plist_txt3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(plist_btn_insert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(plist_btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(plist_btn_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plist_btn_browser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(plist_btn_browser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plist_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plist_txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plist_txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plist_btn_insert)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(plist_btn_delete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plist_btn_clear)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plist_btn_gplist)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("plist文件编辑", jPanel8);

        javax.swing.GroupLayout win_insertLayout = new javax.swing.GroupLayout(win_insert.getContentPane());
        win_insert.getContentPane().setLayout(win_insertLayout);
        win_insertLayout.setHorizontalGroup(
            win_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        win_insertLayout.setVerticalGroup(
            win_insertLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );

        win_fileedit.setTitle("文件编辑");
        win_fileedit.setVisible(true);

        jToolBar3.setRollover(true);

        fileedit_btn_savefile.setText("保存修改");
        fileedit_btn_savefile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fileedit_btn_savefile.setEnabled(false);
        fileedit_btn_savefile.setFocusable(false);
        fileedit_btn_savefile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileedit_btn_savefile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileedit_btn_savefile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileedit_btn_savefileActionPerformed(evt);
            }
        });
        jToolBar3.add(fileedit_btn_savefile);

        fileedit_btn_clear.setText("清空");
        fileedit_btn_clear.setFocusable(false);
        fileedit_btn_clear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileedit_btn_clear.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileedit_btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileedit_btn_clearActionPerformed(evt);
            }
        });
        jToolBar3.add(fileedit_btn_clear);

        jLabel33.setText("新建文件名:");
        jToolBar3.add(jLabel33);

        fileedit_txt_newfilename.setText("newfile.txt");
        jToolBar3.add(fileedit_txt_newfilename);

        fileedit_btn_newfile.setText("新建文件");
        fileedit_btn_newfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fileedit_btn_newfile.setFocusable(false);
        fileedit_btn_newfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileedit_btn_newfile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileedit_btn_newfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileedit_btn_newfileActionPerformed(evt);
            }
        });
        jToolBar3.add(fileedit_btn_newfile);

        fileedit_btn_savenew.setText("存为新文件");
        fileedit_btn_savenew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fileedit_btn_savenew.setFocusable(false);
        fileedit_btn_savenew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileedit_btn_savenew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileedit_btn_savenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileedit_btn_savenewActionPerformed(evt);
            }
        });
        jToolBar3.add(fileedit_btn_savenew);
        jToolBar3.add(filler2);

        fileedit_chk_entermode.setSelected(true);
        fileedit_chk_entermode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fileedit_chk_entermode.setFocusable(false);
        fileedit_chk_entermode.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fileedit_chk_entermode.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(fileedit_chk_entermode);

        jLabel32.setText("强制UNIX风格换行符");
        jToolBar3.add(jLabel32);

        jCheckBox7.setSelected(true);
        jCheckBox7.setEnabled(false);
        jCheckBox7.setFocusable(false);
        jCheckBox7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(jCheckBox7);

        jLabel2.setText("强制使用UTF-8读写");
        jToolBar3.add(jLabel2);

        fileedit_txt_edit.setColumns(20);
        fileedit_txt_edit.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        fileedit_txt_edit.setRows(5);
        jScrollPane4.setViewportView(fileedit_txt_edit);

        javax.swing.GroupLayout win_fileeditLayout = new javax.swing.GroupLayout(win_fileedit.getContentPane());
        win_fileedit.getContentPane().setLayout(win_fileeditLayout);
        win_fileeditLayout.setHorizontalGroup(
            win_fileeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
        );
        win_fileeditLayout.setVerticalGroup(
            win_fileeditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(win_fileeditLayout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(win_filemgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(win_insert)
                    .addComponent(win_fileedit)))
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(win_insert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(win_fileedit))
                    .addComponent(win_filemgr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void filemgr_btn_addfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filemgr_btn_addfileActionPerformed
        loadingOpen("正在添加文件");
        JFileChooser fc = new JFileChooser();
        int flag = 0;
        File[] f = null;
        try {
            fc.setMultiSelectionEnabled(true);
            flag = fc.showOpenDialog(null);
        } catch (HeadlessException head) {
            System.out.println("Open File Dialog ERROR!");
        }
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            f = fc.getSelectedFiles();
        }
        if (f != null && f.length > 0) {
            for (int i = 0; i < f.length; i++) {
                File nowF = f[i];
                String fromPath = nowF.getPath();
                String toPath = dirAddress + separator + nowF.getName();
                FileManager fm = new FileManager();
                File file = new File(toPath);
                Boolean isOK = false;
                Boolean showErr = true;
                if (file.exists()) {
                    int option = JOptionPane.showConfirmDialog(null, "导入文件“" + fromPath + "”时发现资料夹中已经存在了一个相同文件名的文件，要覆盖资料夹中已有的“" + nowF.getName() + "”文件吗？\n（建议您记下这些名称重复的文件路径并选择否，之后手工把要导入的名字改名后重新导入。）", "重复的文件名", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    switch (option) {
                        case JOptionPane.YES_NO_OPTION: {
                            isOK = fm.copyFile(fromPath, toPath);
                            break;
                        }
                        case JOptionPane.NO_OPTION: {
                            isOK = false;
                            showErr = false;
                            break;
                        }
                    }
                } else {
                    isOK = fm.copyFile(fromPath, toPath);
                }
                if (isOK) {
                    lbl_stat.setText("已导入" + i + "个文件。");
                } else {
                    if (showErr) {
                        JOptionPane.showMessageDialog(this, "未能从“" + fromPath + "”导入到“" + toPath + "”。请尝试手工复制文件。", "导入文件失败", JOptionPane.ERROR_MESSAGE);
                        lbl_stat.setText("未能导入文件“" + nowF.getName() + "”。");
                    } else {
                        lbl_stat.setText("文件“" + nowF.getName() + "”因为重名而未被导入。");
                    }
                }
            }
            reload();
        } else {
            lbl_stat.setText("已取消选择。");
        }
        loadingClose();
    }//GEN-LAST:event_filemgr_btn_addfileActionPerformed

    private void mainwin_btn_opendirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainwin_btn_opendirActionPerformed
        openDataDir();
    }//GEN-LAST:event_mainwin_btn_opendirActionPerformed

    public void openDataDir() {
        loadingOpen("正在统计文件");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
        String path = null;
        int flag = 0;
        File f = null;
        try {
            flag = fc.showOpenDialog(null);
        } catch (HeadlessException head) {
            System.out.println("Open File Dialog ERROR!");
        }
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            f = fc.getSelectedFile();
            path = f.getPath();
        }
        if (path != null && !path.equals("null")) {
            this.setTitle(softname + " - " + path);
            dirAddress = path;
            listi = 0;
            list(dirAddress, false, false, 1);
            lbl_stat.setText("已挂载文件夹：" + path + "，导入了" + listi + "个文件。");
            isFirst = false;
            win_filemgr.setEnabled(true);
            win_insert.setEnabled(true);
            win_fileedit.setEnabled(true);
        } else {
            lbl_stat.setText("已取消选择。" + path);
            if (isFirst) {
                loadingClose();
                this.setVisible(false);
                System.exit(0);
            }
        }
        loadingClose();
    }

    private void convertEnter() {
        if (fileedit_chk_entermode.isSelected()) {
            fileedit_txt_edit.setText(fileedit_txt_edit.getText().replaceAll("\r\n", "\n"));
        }
    }

    private void saveEditor() {
        BufferedWriter fw = null;
        convertEnter();
        try {
            File file = new File(nowtxtfile);
            fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8")); // 指定编码格式，以免读取时中文字符异常
            fw.append(fileedit_txt_edit.getText());
            //fw.newLine();
            fw.flush(); // 全部写入缓存中的内容
            lbl_stat.setText("已保存文件“" + nowtxtfile + "”。");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "未能存储文件到“" + nowtxtfile + "”。", "存储失败", JOptionPane.ERROR_MESSAGE);
            lbl_stat.setText("文件“" + nowtxtfile + "”保存失败。");
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "文件“" + nowtxtfile + "”已存储，但是可能遇到了一些问题。建议重新开启本程序。", "推出失败", JOptionPane.WARNING_MESSAGE);
                    lbl_stat.setText("文件“" + nowtxtfile + "”解除锁定失败。");
                    e.printStackTrace();
                }
            }
        }
    }

    private Boolean clearEditor() {
        if (!fileedit_txt_edit.getText().equals("")) {
            int leng = fileedit_txt_edit.getText().length();
            int option = JOptionPane.showConfirmDialog(null,
                    "清空下面框中的文字，确定吗？", "清空文本编辑器中的文字", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (option) {
                case JOptionPane.YES_NO_OPTION: {
                    fileedit_txt_edit.setText("");
                    lbl_stat.setText("删除了 " + leng + " 个文字。");
                    return true;
                }
                case JOptionPane.NO_OPTION: {
                    //System.exit(0);
                    return false;
                }
            }
        }
        return true;
    }

    private void filemgr_btn_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filemgr_btn_reloadActionPerformed
        loadingOpen("正在刷新");
        reload();
        loadingClose();
    }//GEN-LAST:event_filemgr_btn_reloadActionPerformed

    private void filemgr_btn_removefileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filemgr_btn_removefileActionPerformed
        loadingOpen("正在删除此文件");
        int seleIndex = filemgr_list_files.getSelectedIndex();
        if (seleIndex > 0) {
            ListModel nowModel = filemgr_list_files.getModel();

            Object nowElement = nowModel.getElementAt(seleIndex);
            String nowFileName = nowElement.toString();
            String fileAddress = dirAddress + separator + nowFileName;
            int option = JOptionPane.showConfirmDialog(null,
                    "确定要永久删除文件“" + fileAddress + "”吗？", "删除文件", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null);
            switch (option) {
                case JOptionPane.YES_NO_OPTION: {
                    FileManager fm = new FileManager();
                    Boolean isOK = fm.deleteFile(fileAddress);
                    if (isOK) {
                        lbl_stat.setText("已永久删除文件“" + nowFileName + "”。");
                    } else {
                        lbl_stat.setText("未能删除文件“" + nowFileName + "”。");
                        JOptionPane.showMessageDialog(this, "未能删除“" + fileAddress + "”。请尝试手工删除。", "删除失败", JOptionPane.ERROR_MESSAGE);
                    }
                    reload();
                    break;
                }
                case JOptionPane.NO_OPTION: {
                    //System.exit(0);
                    break;
                }
            }
        }
        loadingClose();
    }//GEN-LAST:event_filemgr_btn_removefileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        lbl_stat.setText("本作品采用知识共享署名 4.0 国际许可协议进行许可。");
        try {
            URI uri = new URI("https://creativecommons.org/licenses/by/4.0/deed.zh_TW");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filemgr_btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filemgr_btn_clearActionPerformed
        loadingOpen("正在清空资料包");
        if (dirAddress != null && !dirAddress.equals("null")) {
            int option = JOptionPane.showConfirmDialog(null,
                    "确定要永久删除资料包文件夹“" + dirAddress + "”中的所有文件吗？", "清空资料包", JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE, null);
            switch (option) {
                case JOptionPane.YES_NO_OPTION: {
                    FileManager fm = new FileManager();
                    Boolean isOK = fm.deleteDirectory(dirAddress);
                    if (isOK) {
                        lbl_stat.setText("已清空资料夹“" + dirAddress + "”。");
                    } else {
                        lbl_stat.setText("未能删除资料夹“" + dirAddress + "”中一个或多个文件。");
                        JOptionPane.showMessageDialog(this, "未能删除资料夹“" + dirAddress + "中一个或多个文件”。请尝试手工删除。", "删除失败", JOptionPane.ERROR_MESSAGE);
                    }
                    reload();
                    break;
                }
                case JOptionPane.NO_OPTION: {
                    //System.exit(0);
                    break;
                }
            }
        }
        loadingClose();
    }//GEN-LAST:event_filemgr_btn_clearActionPerformed

    private void filemgr_btn_adddirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filemgr_btn_adddirActionPerformed
        loadingOpen("正在添加文件夹");
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
        String path = null;
        int flag = 0;
        File f = null;
        try {
            flag = fc.showOpenDialog(null);
        } catch (HeadlessException head) {
            System.out.println("Open File Dialog ERROR!");
        }
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            f = fc.getSelectedFile();
            path = f.getPath();
        }
        if (path != null && !path.equals("null")) {
            Boolean inChildDir = false;
            int option = JOptionPane.showConfirmDialog(null, "是否需要包含“" + path + "”子文件夹中的所有文件？", "导入文件夹", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            switch (option) {
                case JOptionPane.YES_NO_OPTION: {
                    inChildDir = true;
                    break;
                }
                case JOptionPane.NO_OPTION: {
                    inChildDir = false;
                    break;
                }
            }
            listi = 0;
            list(path, inChildDir, false, 2);
            reload();
        } else {
            lbl_stat.setText("已取消选择。" + path);
        }
        loadingClose();
    }//GEN-LAST:event_filemgr_btn_adddirActionPerformed

    private void fileedit_btn_savenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileedit_btn_savenewActionPerformed
        loadingOpen("正在另存为");
        String fileAddress = dirAddress + separator + fileedit_txt_newfilename.getText();
        win_fileedit.setTitle("文件编辑：" + fileAddress);
        nowtxtfile = fileAddress;
        saveEditor();
        reload();
        fileedit_txt_edit.setEnabled(true);
        fileedit_btn_savefile.setEnabled(true);
        lbl_stat.setText("已另存为文件到“" + fileAddress + "”。");
        loadingClose();
    }//GEN-LAST:event_fileedit_btn_savenewActionPerformed

    private void fileedit_btn_newfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileedit_btn_newfileActionPerformed
        loadingOpen("正在新建文件");
        Boolean isOK = clearEditor();
        if (isOK) {
            String fileAddress = dirAddress + separator + fileedit_txt_newfilename.getText();
            win_fileedit.setTitle("文件编辑：" + fileAddress);
            nowtxtfile = fileAddress;
            saveEditor();
            reload();
            fileedit_txt_edit.setEnabled(true);
            fileedit_btn_savefile.setEnabled(true);
            lbl_stat.setText("已新建文件“" + fileAddress + "”。");
        }
        loadingClose();
    }//GEN-LAST:event_fileedit_btn_newfileActionPerformed

    private void fileedit_btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileedit_btn_clearActionPerformed
        clearEditor();
    }//GEN-LAST:event_fileedit_btn_clearActionPerformed

    private void fileedit_btn_savefileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileedit_btn_savefileActionPerformed
        loadingOpen("正在保存");
        saveEditor();
        loadingClose();
    }//GEN-LAST:event_fileedit_btn_savefileActionPerformed

    private void index_btn_gbookinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_index_btn_gbookinfoActionPerformed
        loadingOpen("插入书籍信息");
        String bookinfo = index_txt_zipname.getText() + "\n" + index_txt_author.getText() + "\n" + index_txt_press.getText();
        fileedit_txt_edit.setText(bookinfo);
        lbl_stat.setText("插入“" + index_txt_zipname.getText() + "”书籍信息完毕。");
        loadingClose();
    }//GEN-LAST:event_index_btn_gbookinfoActionPerformed

    private void index_btn_gfilelistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_index_btn_gfilelistActionPerformed
        loadingOpen("插入文件列表");
        if (dirAddress != null && !dirAddress.equals("")) {
            listi = 0;
            list(dirAddress, false, false, 3);
            lbl_stat.setText("插入文件列表完毕，生成了 " + listi + " 个条目。");
        }
        loadingClose();
    }//GEN-LAST:event_index_btn_gfilelistActionPerformed

    private void chapter_com_isoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chapter_com_isoneActionPerformed
        chapter_txt_chaptername.setEnabled(chapter_com_isone.isSelected());
        chapter_txt_sectionnum.setEnabled(!chapter_com_isone.isSelected());
        if (chapter_com_isone.isSelected()) {
            chapter_txt_sectionnum.setText("1");
        }
    }//GEN-LAST:event_chapter_com_isoneActionPerformed

    private void chapter_btn_gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chapter_btn_gActionPerformed
        loadingOpen("插入章节名称信息");
        String fileName = chapter_txt_chapternum.getText() + "-" + chapter_txt_sectionnum.getText() + ".txt";
        if (chapter_com_isone.isSelected()) {
            fileedit_txt_edit.setText(chapter_txt_chaptername.getText() + "\n" + chapter_txt_sectionname.getText());
        } else {
            fileedit_txt_edit.setText(chapter_txt_sectionname.getText());
        }
        fileedit_txt_newfilename.setText(fileName);
        lbl_stat.setText("插入章节名称信息完毕，请使用“" + fileName + "”文件名保存！");
        loadingClose();
    }//GEN-LAST:event_chapter_btn_gActionPerformed

    private void album_btn_gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_album_btn_gActionPerformed
        loadingOpen("新增图片到相册");
        if (fileedit_txt_edit.getText().length() > 13) {
            fileedit_txt_edit.setText(fileedit_txt_edit.getText() + "\n");
        } else {
            fileedit_txt_edit.setText("");
        }
        fileedit_txt_edit.setText(fileedit_txt_edit.getText() + lst_pic1.getSelectedItem().toString() + "---" + album_txt_picname.getText() + "---" + album_txt_picinfo.getText());
        lbl_stat.setText("新增图片“" + lst_pic1.getSelectedItem().toString() + "”到相册完毕。");
        loadingClose();
    }//GEN-LAST:event_album_btn_gActionPerformed

    private void keyword_btn_gActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyword_btn_gActionPerformed
        loadingOpen("新增关键字解释");
        fileedit_txt_edit.setText(keyword_txt_keyword.getText() + "---" + keyword_txt_info.getText());
        lbl_stat.setText("新增关键字“" + keyword_txt_keyword.getText() + "”解释完毕。");
        loadingClose();
    }//GEN-LAST:event_keyword_btn_gActionPerformed

    private void choice_com_aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_com_aActionPerformed
        choice_com_b.setSelected(false);
        choice_com_c.setSelected(false);
        choice_com_d.setSelected(false);
        choice_com_a.setSelected(true);
    }//GEN-LAST:event_choice_com_aActionPerformed

    private void choice_com_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_com_bActionPerformed
        choice_com_a.setSelected(false);
        choice_com_c.setSelected(false);
        choice_com_d.setSelected(false);
        choice_com_b.setSelected(true);
    }//GEN-LAST:event_choice_com_bActionPerformed

    private void choice_com_cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_com_cActionPerformed
        choice_com_b.setSelected(false);
        choice_com_a.setSelected(false);
        choice_com_d.setSelected(false);
        choice_com_c.setSelected(true);
    }//GEN-LAST:event_choice_com_cActionPerformed

    private void choice_com_dActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_com_dActionPerformed
        choice_com_b.setSelected(false);
        choice_com_c.setSelected(false);
        choice_com_a.setSelected(false);
        choice_com_d.setSelected(true);
    }//GEN-LAST:event_choice_com_dActionPerformed

    private void classify_rad_typeaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classify_rad_typeaActionPerformed
        classify_rad_typea.setSelected(true);
        classify_rad_typeb.setSelected(false);
    }//GEN-LAST:event_classify_rad_typeaActionPerformed

    private void classify_rad_typebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classify_rad_typebActionPerformed
        classify_rad_typea.setSelected(false);
        classify_rad_typeb.setSelected(true);
    }//GEN-LAST:event_classify_rad_typebActionPerformed

    private void choice_btn_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_btn_nameActionPerformed
        loadingOpen("插入选择题名称");
        fileedit_txt_edit.setText(choice_txt_name.getText());
        lbl_stat.setText("插入选择题名称“" + choice_txt_name.getText() + "”完毕。");
        loadingClose();
    }//GEN-LAST:event_choice_btn_nameActionPerformed

    private void choice_btn_qActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choice_btn_qActionPerformed
        loadingOpen("插入选择题");
        String y = "APP_ERR";
        if (choice_com_a.isSelected()) {
            y = "A";
        } else if (choice_com_b.isSelected()) {
            y = "B";
        } else if (choice_com_c.isSelected()) {
            y = "C";
        } else if (choice_com_d.isSelected()) {
            y = "D";
        }
        String q = choice_txt_qid.getText() + "." + choice_txt_q.getText();
        fileedit_txt_edit.setText(fileedit_txt_edit.getText() + "\n" + q + "---" + y + "---" + choice_txt_a.getText() + "---" + choice_txt_b.getText() + "---" + choice_txt_c.getText() + "---" + choice_txt_d.getText());
        int nqid = Integer.parseInt(choice_txt_qid.getText());
        nqid++;
        String nqids = Integer.toString(nqid);
        choice_txt_qid.setText(nqids);
        lbl_stat.setText("插入选择题“" + q + "”（正确答案" + y + "）完毕。下一题的题号大概是 " + nqids + ".");
        loadingClose();
    }//GEN-LAST:event_choice_btn_qActionPerformed

    private void classify_btn_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classify_btn_nameActionPerformed
        loadingOpen("插入分类题名称");
        fileedit_txt_edit.setText(classify_txt_name.getText());
        lbl_stat.setText("插入分类题名称“" + classify_txt_name.getText() + "”完毕。");
        loadingClose();
    }//GEN-LAST:event_classify_btn_nameActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        loadingOpen("插入分类题图片");
        String y = "APP_ERR";
        if (classify_rad_typea.isSelected()) {
            y = classify_txt_typea.getText();
        } else if (classify_rad_typeb.isSelected()) {
            y = classify_txt_typeb.getText();
        }
        fileedit_txt_edit.setText(fileedit_txt_edit.getText() + "\n" + y + "---" + classify_txt_pic.getText() + "---" + lst_pic2.getSelectedItem().toString());
        lbl_stat.setText("插入分类题图片“" + lst_pic2.getSelectedItem().toString() + "”完毕。");
        loadingClose();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void plist_btn_browserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plist_btn_browserActionPerformed
        loadingOpen("正在打开plist");
        JFileChooser fc = new JFileChooser();
        int flag = 0;
        File f = null;
        try {
            fc.setMultiSelectionEnabled(false);
            flag = fc.showOpenDialog(null);
        } catch (HeadlessException head) {
            System.out.println("Open File Dialog ERROR!");
        }
        if (flag == JFileChooser.APPROVE_OPTION) {
            //获得该文件
            f = fc.getSelectedFile();
            if (f != null) {
                if (f.getName().equals("MyBook.plist")) {
                    String fileAddress = f.getPath();
                    win_fileedit.setTitle("文件编辑：" + fileAddress);
                    nowtxtfile = fileAddress;
                    BufferedReader reader;
                    Boolean readOK = false;
                    try {
                        fileedit_txt_edit.setText("");
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress), "UTF-8")); // 指定读取文件的编码格式，要和写入的格式一致，以免出现中文乱码,
                        String str = null;
                        while ((str = reader.readLine()) != null) {
                            fileedit_txt_edit.setText(fileedit_txt_edit.getText() + str + "\n");
                        }
                        reader.close();
                        fileedit_btn_savefile.setEnabled(true);
                        fileedit_txt_edit.setEnabled(true);
                        fileedit_txt_newfilename.setText(f.getName());
                        readOK = true;
                        int keyid = 1; //String[] keyword = {"bName","bPicture","bWeb"};
                        SearchKeyword sh = new SearchKeyword();
                        ArrayList<String> words = sh.schHtmValume(fileedit_txt_edit.getText(), "<string>", "</string>");
                        for (int i = 0; i < words.size(); i++) {
                            String nowWord = words.get(i);
                            if (keyid == 1) {
                                plistModel1.addElement(nowWord);
                                keyid++;
                            } else if (keyid == 2) {
                                plistModel2.addElement(nowWord);
                                keyid++;
                            } else if (keyid == 3) {
                                plistModel3.addElement(nowWord);
                                keyid = 1;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (readOK) {

                    } else {
                        JOptionPane.showMessageDialog(this, "plist文件“" + f.getPath() + "”解析失败。", "文件不能导入", JOptionPane.ERROR_MESSAGE);
                        lbl_stat.setText("plist文件“" + f.getName() + "”解析失败。");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "只能选择“MyBook.plist”文件！", "文件不能导入", JOptionPane.ERROR_MESSAGE);
                    lbl_stat.setText("只能选择“MyBook.plist”文件！");
                }
            }
        }
        loadingClose();
    }//GEN-LAST:event_plist_btn_browserActionPerformed

    private void plist_btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plist_btn_clearActionPerformed
//        for (int i = 1; i < plistModel1.size(); i++) {
//            plistModel1.removeElementAt(i);
//            plistModel2.removeElementAt(i);
//            plistModel3.removeElementAt(i);
//        }
        plistModel1.removeAllElements();
        plistModel2.removeAllElements();
        plistModel3.removeAllElements();
        plistModel1.addElement("book1");
        plistModel2.addElement("demobook.png");
        plistModel3.addElement("http://##");
        //gplist();
    }//GEN-LAST:event_plist_btn_clearActionPerformed

    private void plist_btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plist_btn_insertActionPerformed
        plistModel1.addElement(plist_txt1.getText());
        plistModel2.addElement(plist_txt2.getText());
        plistModel3.addElement(plist_txt3.getText());
        //gplist();
    }//GEN-LAST:event_plist_btn_insertActionPerformed

    private void plist_btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plist_btn_deleteActionPerformed
        plist_btn_delete.setEnabled(false);
        int nowSelect = plist_list1.getSelectedIndex();
        plist_list1.setSelectedIndex(0);
        plist_list2.setSelectedIndex(0);
        plist_list3.setSelectedIndex(0);
        plistModel1.removeElementAt(nowSelect);
        plistModel2.removeElementAt(nowSelect);
        plistModel3.removeElementAt(nowSelect);
        //gplist();
    }//GEN-LAST:event_plist_btn_deleteActionPerformed

    private void plist_btn_gplistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plist_btn_gplistActionPerformed
        loadingOpen("正在生成plist");
        gplist();
        //saveEditor();
        loadingClose();
    }//GEN-LAST:event_plist_btn_gplistActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        lbl_stat.setText("by 神楽坂雅詩（CXC）");
        try {
            URI uri = new URI("https://uuu.moe/?p=1596");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void gplist() {
        String newplist = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n<plist version=\"1.0\">\n<array>";
        for (int i = 0; i < plistModel1.size(); i++) {
            newplist = newplist + "\n	<dict>\n		<key>bName</key>\n		<string>" + plistModel1.getElementAt(i) + "</string>\n		<key>bPicture</key>\n		<string>" + plistModel2.getElementAt(i) + "</string>\n		<key>bWeb</key>\n		<string>" + plistModel3.getElementAt(i) + "</string>\n	</dict>";
        }
        newplist = newplist + "\n</array>\n</plist>";
        fileedit_txt_edit.setText(newplist);
        //return newplist;
    }

    private void reload() {
        if (dirAddress != null && !dirAddress.equals("")) {
            listi = 0;
            list(dirAddress, false, false, 1);
        }
    }

    private ArrayList<String> list(String filePath, boolean b_cdir, boolean b_dirname, int endMode) {
        if (endMode == 1) {
            picModel.removeAllElements();
            listModel.removeAllElements();
        }
        ArrayList<String> filelist = new ArrayList<String>();
        String[] arr = null;
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (b_cdir) {
                    list(file.getAbsolutePath(), b_cdir, b_dirname, endMode);
                }
                if (b_dirname) {
                    filelist.add(file.getName());
                    //mw.setNum(false);
                    addListItem(file, endMode);
                }
            } else {
                filelist.add(file.getName());
                //mw.setNum(false);
                addListItem(file, endMode);
            }
        }
        return filelist;
    }

    private void addListItem(File file, int endMode) {
        listi++;
        switch (endMode) {
            case 1: {
                String nowFileName = file.getName();
                listModel.addElement(nowFileName);
                String typeName = nowFileName.substring(nowFileName.length() - 4);
                if (typeName.equals(".jpg") || typeName.equals(".png")) {
                    picModel.addElement(nowFileName);
                }
                break;
            }
            case 2: {
                String fromPath = file.getPath();
                String toPath = dirAddress + separator + file.getName();
                FileManager fm = new FileManager();
                Boolean isOK = fm.copyFile(fromPath, toPath);
                if (isOK) {
                    lbl_stat.setText("已导入" + listi + "个文件。");
                } else {
                    JOptionPane.showMessageDialog(this, "未能从“" + fromPath + "”导入到“" + toPath + "”。请尝试手工复制文件。", "导入文件失败", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
            case 3: {
                fileedit_txt_edit.setText(fileedit_txt_edit.getText() + "\n" + file.getName());
                break;
            }
        }
    }

    private void loadingOpen(String info) {
        this.setEnabled(false);
        loadwin.openwindow(info);
    }

    private void loadingClose() {
        this.setEnabled(true);
        this.requestFocus();
        loadwin.closewindow();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudioWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudioWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudioWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudioWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudioWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton album_btn_g;
    private javax.swing.JTextField album_txt_picinfo;
    private javax.swing.JTextField album_txt_picname;
    private javax.swing.JButton chapter_btn_g;
    private javax.swing.JCheckBox chapter_com_isone;
    private javax.swing.JTextField chapter_txt_chaptername;
    private javax.swing.JTextField chapter_txt_chapternum;
    private javax.swing.JTextField chapter_txt_sectionname;
    private javax.swing.JTextField chapter_txt_sectionnum;
    private javax.swing.JButton choice_btn_name;
    private javax.swing.JButton choice_btn_q;
    private javax.swing.JCheckBox choice_com_a;
    private javax.swing.JCheckBox choice_com_b;
    private javax.swing.JCheckBox choice_com_c;
    private javax.swing.JCheckBox choice_com_d;
    private javax.swing.JTextField choice_txt_a;
    private javax.swing.JTextField choice_txt_b;
    private javax.swing.JTextField choice_txt_c;
    private javax.swing.JTextField choice_txt_d;
    private javax.swing.JTextField choice_txt_name;
    private javax.swing.JTextField choice_txt_q;
    private javax.swing.JTextField choice_txt_qid;
    private javax.swing.JButton classify_btn_name;
    private javax.swing.JRadioButton classify_rad_typea;
    private javax.swing.JRadioButton classify_rad_typeb;
    private javax.swing.JTextField classify_txt_name;
    private javax.swing.JTextField classify_txt_pic;
    private javax.swing.JTextField classify_txt_typea;
    private javax.swing.JTextField classify_txt_typeb;
    private javax.swing.JButton fileedit_btn_clear;
    private javax.swing.JButton fileedit_btn_newfile;
    private javax.swing.JButton fileedit_btn_savefile;
    private javax.swing.JButton fileedit_btn_savenew;
    private javax.swing.JCheckBox fileedit_chk_entermode;
    private javax.swing.JTextArea fileedit_txt_edit;
    private javax.swing.JTextField fileedit_txt_newfilename;
    private javax.swing.JButton filemgr_btn_adddir;
    private javax.swing.JButton filemgr_btn_addfile;
    private javax.swing.JButton filemgr_btn_clear;
    private javax.swing.JButton filemgr_btn_reload;
    private javax.swing.JButton filemgr_btn_removefile;
    private javax.swing.JList filemgr_list_files;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton index_btn_gbookinfo;
    private javax.swing.JButton index_btn_gfilelist;
    private javax.swing.JTextField index_txt_author;
    private javax.swing.JTextField index_txt_press;
    private javax.swing.JTextField index_txt_zipname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JButton keyword_btn_g;
    private javax.swing.JTextField keyword_txt_info;
    private javax.swing.JTextField keyword_txt_keyword;
    private javax.swing.JLabel lbl_stat;
    private javax.swing.JComboBox lst_pic1;
    private javax.swing.JComboBox lst_pic2;
    private javax.swing.JButton mainwin_btn_opendir;
    private javax.swing.JButton plist_btn_browser;
    private javax.swing.JButton plist_btn_clear;
    private javax.swing.JButton plist_btn_delete;
    private javax.swing.JButton plist_btn_gplist;
    private javax.swing.JButton plist_btn_insert;
    private javax.swing.JList plist_list1;
    private javax.swing.JList plist_list2;
    private javax.swing.JList plist_list3;
    private javax.swing.JTextField plist_txt1;
    private javax.swing.JTextField plist_txt2;
    private javax.swing.JTextField plist_txt3;
    private javax.swing.JInternalFrame win_fileedit;
    private javax.swing.JInternalFrame win_filemgr;
    private javax.swing.JInternalFrame win_insert;
    // End of variables declaration//GEN-END:variables
}
