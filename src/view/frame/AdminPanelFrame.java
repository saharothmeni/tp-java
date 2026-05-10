package view.frame;

import java.awt.Color;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AdminPanelFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    // ─────────────────────────────────────────────
    //  Chemin absolu vers l'image personnalisée
    // ─────────────────────────────────────────────
    private static final String CUSTOM_IMG =
        "C:/Users/user/Desktop/Sahar Othmeni/gestion_ecole/image/freelancer_17985829.png";

    private javax.swing.ImageIcon loadCustomIcon() {
        try {
            java.io.File f = new java.io.File(CUSTOM_IMG);
            if (f.exists()) return new javax.swing.ImageIcon(f.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("Impossible de charger l'image : " + e.getMessage());
        }
        return null;
    }

    private javax.swing.ImageIcon loadCustomIcon(int w, int h) {
        javax.swing.ImageIcon raw = loadCustomIcon();
        if (raw == null) return null;
        java.awt.Image scaled = raw.getImage().getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        return new javax.swing.ImageIcon(scaled);
    }

    // ─────────────────────────────────────────────
    //  Getters exposés pour les contrôleurs (Matières)
    // ─────────────────────────────────────────────
    public javax.swing.JButton getjButtonRemove() { return jButton21; }
    public javax.swing.JButton getjButtonUpdate() { return jButton22; }
    public javax.swing.JButton getjButtonAdd()    { return jButton23; }
    public javax.swing.JButton getjButtonClose()  { return jButton24; }
    public String getjTextField11() { return jTextField11.getText(); }
    public String getjTextField12() { return jTextField12.getText(); }
    public String getjTextField14() { return jTextField14.getText(); }
    public String getjTextField13() { return jTextField13.getText(); }
    public String getjTextField15() { return jTextField15.getText(); }

    // ─────────────────────────────────────────────
    //  ✅ NOUVEAUX getters — Spécialité et Grade enseignant
    // ─────────────────────────────────────────────
    public String getjTextField16() { return jTextField16.getText(); } // Spécialité
    public String getjTextField17() { return jTextField17.getText(); } // Grade

    // ─────────────────────────────────────────────
    //  Constructeur
    // ─────────────────────────────────────────────
    public AdminPanelFrame() {
        initComponents();
    }

    // ─────────────────────────────────────────────
    //  initComponents
    // ─────────────────────────────────────────────
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1  = new javax.swing.JPanel();
        jPanel3  = new javax.swing.JPanel();
        jPanel4  = new javax.swing.JPanel();
        jLabel3  = new javax.swing.JLabel();
        jLabel7  = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1  = new javax.swing.JLabel();
        jLabel4  = new javax.swing.JLabel();
        jLabel5  = new javax.swing.JLabel();
        jLabel8  = new javax.swing.JLabel();
        jLabel9  = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9  = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        // ── Tab Students ──────────────────────────
        jPanel6      = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1      = new javax.swing.JTable();
        jButton9  = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel2  = new javax.swing.JLabel();
        jLabel6  = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();

        // ── Tab Teachers ──────────────────────────
        jPanel7      = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2      = new javax.swing.JTable();
        jTextField4  = new javax.swing.JTextField();   // Genre
        jTextField5  = new javax.swing.JTextField();   // ID
        jTextField6  = new javax.swing.JTextField();   // Nom
        // ✅ NOUVEAUX champs Spécialité et Grade
        jTextField16 = new javax.swing.JTextField();   // Spécialité
        jTextField17 = new javax.swing.JTextField();   // Grade
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();  // "Spécialité"
        jLabel34 = new javax.swing.JLabel();  // "Grade"
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();

        // ── Tab Subjects ──────────────────────────
        jPanel2      = new javax.swing.JPanel();
        jButton21    = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel28     = new javax.swing.JLabel();
        jButton22    = new javax.swing.JButton();
        jLabel29     = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7      = new javax.swing.JTable();
        jButton23    = new javax.swing.JButton();
        jButton24    = new javax.swing.JButton();
        jLabel30     = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel31     = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel32     = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();

        // ── Tab T.subjects (Cours Enseignants) ───
        jPanel8      = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3      = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4      = new javax.swing.JTable();
        jButton2     = new javax.swing.JButton();
        jButton4     = new javax.swing.JButton();
        jTextField7  = new javax.swing.JTextField();
        jTextField8  = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        // ── Tab S.courses (Cours Étudiants) ──────
        jPanel10     = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5      = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6      = new javax.swing.JTable();
        jButton7     = new javax.swing.JButton();
        jButton8     = new javax.swing.JButton();
        jTextField9  = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        // ── Logo + reload panel ───────────────────
        jPanel5   = new javax.swing.JPanel();
        jLabel11  = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();

        // ── Fenêtre principale ────────────────────
        jToggleButton1.setText("jToggleButton1");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrateur");
        javax.swing.ImageIcon winIcon = loadCustomIcon();
        if (winIcon != null) setIconImage(winIcon.getImage());

        // ── jPanel3 (conteneur global) ────────────
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel3.setLayout(null);

        // ── jPanel4 (barre latérale) ──────────────
        jPanel4.setBackground(new java.awt.Color(45, 55, 72));
        jPanel4.setPreferredSize(new java.awt.Dimension(360, 510));

        jLabel1.setIcon(loadCustomIcon(80, 80));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Admin Panel");
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel8.setForeground(new java.awt.Color(180, 190, 210));
        jLabel8.setText("Admin :");
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel4.setText("root");

        jLabel9.setForeground(new java.awt.Color(180, 190, 210));
        jLabel9.setText("Connecté le :");
        Date d = new Date();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(formatter.format(d));

        jButton1.setText("Déconnexion");
        jButton1.setForeground(new java.awt.Color(255, 80, 80));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel16.setText("▶  Étudiants");
        jLabel16.setForeground(new java.awt.Color(200, 210, 230));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jLabel17.setText("▶  Enseignants");
        jLabel17.setForeground(new java.awt.Color(200, 210, 230));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jLabel18.setText("▶  Matières/Cours");
        jLabel18.setForeground(new java.awt.Color(200, 210, 230));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 13));
        jLabel19.setText("▶  Cours Étudiants");
        jLabel19.setForeground(new java.awt.Color(200, 210, 230));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 13));

        jLabel12.setText("");
        jLabel13.setText("");
        jLabel14.setText("");
        jLabel15.setText("");

        // Navigation latérale
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(0); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel16.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel16.setForeground(new Color(200, 210, 230)); }
        });
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(1); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel17.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel17.setForeground(new Color(200, 210, 230)); }
        });
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(2); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel18.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel18.setForeground(new Color(200, 210, 230)); }
        });
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(3); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel19.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel19.setForeground(new Color(200, 210, 230)); }
        });
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(0); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel16.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel16.setForeground(new Color(200, 210, 230)); }
        });
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(1); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel17.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel17.setForeground(new Color(200, 210, 230)); }
        });
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(2); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel18.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel18.setForeground(new Color(200, 210, 230)); }
        });
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) { jTabbedPane1.setSelectedIndex(3); }
            public void mouseEntered(java.awt.event.MouseEvent evt) { jLabel19.setForeground(new Color(100, 200, 255)); }
            public void mouseExited (java.awt.event.MouseEvent evt) { jLabel19.setForeground(new Color(200, 210, 230)); }
        });

        // Layout jPanel4
        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8).addGap(6).addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9).addGap(6).addComponent(jLabel10))
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8).addComponent(jLabel4))
                .addGap(4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9).addComponent(jLabel10))
                .addGap(30)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(16))
        );
        jPanel3.add(jPanel4);
        jPanel4.setBounds(0, 0, 360, 510);

        // ── Tab Students ──────────────────────────
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null},{null,null,null},{null,null,null},{null,null,null}},
            new String[]{"ID","Name","Gender"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane1.setViewportView(jTable1);
        jButton9.setText("Ajouter");
        jButton10.setText("Modifier");
        jButton11.setText("Supprimer");
        jButton12.setText("Fermer");
        jLabel2.setText("ID");
        jLabel6.setText("Nom");
        jLabel20.setText("Genre");
        jButton14.setText("Imprimer");
        jButton14.addActionListener(e -> jButton14ActionPerformed(e));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2).addComponent(jLabel6).addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11).addComponent(jButton10).addComponent(jButton12).addComponent(jButton14))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Étudiants", jPanel6);

        // ── Tab Teachers ──────────────────────────
        // Champs : tf5=ID, tf6=Nom, tf4=Genre, tf16=Spécialité, tf17=Grade
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null}},
            new String[]{"ID","Nom","Genre","Spécialité","Grade"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane2.setViewportView(jTable2);
        jLabel23.setText("ID");
        jLabel22.setText("Nom");
        jLabel21.setText("Genre");
        jLabel33.setText("Spécialité");   // ✅ NOUVEAU
        jLabel34.setText("Grade");         // ✅ NOUVEAU
        jButton17.setText("Fermer");
        jButton18.setText("Supprimer");
        jButton19.setText("Modifier");
        jButton20.setText("Ajouter");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        // ID
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        // Nom
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        // Genre
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        // ✅ Spécialité
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        // ✅ Grade
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23).addComponent(jLabel22).addComponent(jLabel21)
                    .addComponent(jLabel33).addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20))
                .addGap(12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18).addComponent(jButton19).addComponent(jButton17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Enseignants", jPanel7);

        // ── Tab Subjects ──────────────────────────
        jButton21.setText("Supprimer");
        jLabel28.setText("Nom");
        jButton22.setText("Modifier");
        jLabel29.setText("Description");
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null}},
            new String[]{"ID","Name","Description","Volume","Coeff"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane7.setViewportView(jTable7);
        jButton23.setText("Ajouter");
        jButton24.setText("Fermer");
        jLabel30.setText("ID");
        jLabel31.setText("Volume");
        jLabel32.setText("Coefficient");
        jTextField14.addActionListener(e -> {});
        jTextField15.addActionListener(e -> {});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28).addComponent(jLabel32))
                                .addGap(26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField12, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(jTextField13))
                                .addGap(6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29).addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                        .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField12)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28).addComponent(jLabel31)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21).addComponent(jButton22).addComponent(jButton23).addComponent(jButton24))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Matières", jPanel2);

        // ── Tab T.subjects (Cours Enseignants) ───
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null},{null,null,null,null},{null,null,null,null},{null,null,null,null}},
            new String[]{"ID","Name","Description","Volume"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane3.setViewportView(jTable3);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null},{null,null,null,null},{null,null,null,null},{null,null,null,null}},
            new String[]{"Teacher ID","Teacher Name","Subject ID","Subject Name"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton2.setText("Ajouter");
        jButton4.setText("Supprimer");
        jTextField7.addActionListener(e -> {});
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel24.setText("ID Enseignant");
        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel25.setText("ID Matière");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24).addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jTextField7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2).addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Cours Enseignants", jPanel8);

        // ── Tab S.courses (Cours Étudiants) ──────
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null},{null,null,null,null},{null,null,null,null},{null,null,null,null}},
            new String[]{"ID","Name","Description","Volume"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane5.setViewportView(jTable5);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{{null,null,null,null},{null,null,null,null},{null,null,null,null},{null,null,null,null}},
            new String[]{"Student ID","Student Name","Subject ID","Subject Name"}) {
            Class[] types = {java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class};
            public Class getColumnClass(int c){ return types[c]; }
        });
        jScrollPane6.setViewportView(jTable6);

        jButton7.setText("Ajouter");
        jButton8.setText("Supprimer");
        jTextField9.addActionListener(e -> {});
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel26.setText("ID Étudiant");
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel27.setText("ID Matière");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9))
                        .addGap(12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField10)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27).addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(jTextField10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7).addComponent(jButton8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jTabbedPane1.addTab("Cours Étudiants", jPanel10);

        // ── jPanel9 (conteneur tabs) ──────────────
        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3.add(jPanel9);
        jPanel9.setBounds(360, 220, 440, 300);

        // ── jPanel5 (logo + reload) ───────────────
        jLabel11.setIcon(loadCustomIcon(189, 210));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.ImageIcon reloadIcon = loadCustomIcon(32, 32);
        if (reloadIcon != null) {
            jButton13.setIcon(reloadIcon);
            jButton13.setText("");
        } else {
            jButton13.setText("↺ Reload");
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addGap(47)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3.add(jPanel5);
        jPanel5.setBounds(360, 0, 450, 220);

        // ── Layout global ─────────────────────────
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }

    // ─────────────────────────────────────────────
    //  Event handler impression
    // ─────────────────────────────────────────────
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
        java.text.MessageFormat header = new java.text.MessageFormat("Liste des étudiants :");
        java.text.MessageFormat footer = new java.text.MessageFormat("École — Page {0}");
        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.PORTRAIT);
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
            javax.swing.JOptionPane.showMessageDialog(this, "Impression réussie.");
        } catch (PrinterException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Erreur d'impression : " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────
    //  Getters — Boutons
    // ─────────────────────────────────────────────
    public javax.swing.JButton getjButton1()  { return jButton1;  }
    public javax.swing.JButton getjButton2()  { return jButton2;  }
    public javax.swing.JButton getjButton4()  { return jButton4;  }
    public javax.swing.JButton getjButton7()  { return jButton7;  }
    public javax.swing.JButton getjButton8()  { return jButton8;  }
    public javax.swing.JButton getjButton9()  { return jButton9;  }
    public javax.swing.JButton getjButton10() { return jButton10; }
    public javax.swing.JButton getjButton11() { return jButton11; }
    public javax.swing.JButton getjButton12() { return jButton12; }
    public javax.swing.JButton getjButton13() { return jButton13; }
    public javax.swing.JButton getjButton17() { return jButton17; }
    public javax.swing.JButton getjButton18() { return jButton18; }
    public javax.swing.JButton getjButton19() { return jButton19; }
    public javax.swing.JButton getjButton20() { return jButton20; }

    // ─────────────────────────────────────────────
    //  Getters — Tables
    // ─────────────────────────────────────────────
    public javax.swing.JTable getjTable1() { return jTable1; }
    public javax.swing.JTable getjTable2() { return jTable2; }
    public javax.swing.JTable getjTable3() { return jTable3; }
    public javax.swing.JTable getjTable4() { return jTable4; } // affectations ens-matière
    public javax.swing.JTable getjTable5() { return jTable5; } // matières (Cours Étudiants, haut)
    public javax.swing.JTable getjTable6() { return jTable6; } // inscriptions étud-matière
    public javax.swing.JTable getjTable7() { return jTable7; } // matières (onglet Matières)

    public void setjTable1(javax.swing.JTable t) { this.jTable1 = t; }
    public void setjTable2(javax.swing.JTable t) { this.jTable2 = t; }
    public void setjTable3(javax.swing.JTable t) { this.jTable3 = t; }
    public void setjTable4(javax.swing.JTable t) { this.jTable4 = t; }
    public void setjTable5(javax.swing.JTable t) { this.jTable5 = t; }
    public void setjTable6(javax.swing.JTable t) { this.jTable6 = t; }
    public void setjTable7(javax.swing.JTable t) { this.jTable7 = t; }

    // ─────────────────────────────────────────────
    //  Getters — Champs texte
    // ─────────────────────────────────────────────
    public String getjTextField1()  { return jTextField1.getText();  }  // ID étudiant
    public String getjTextField2()  { return jTextField2.getText();  }  // Nom étudiant
    public String getjTextField3()  { return jTextField3.getText();  }  // Genre étudiant
    public String getjTextField4()  { return jTextField4.getText();  }  // Genre enseignant
    public String getjTextField5()  { return jTextField5.getText();  }  // ID enseignant
    public String getjTextField6()  { return jTextField6.getText();  }  // Nom enseignant
    // ✅ tf16 et tf17 déjà déclarés en haut avec leur getter
    public String getjTextField7()  { return jTextField7.getText();  }  // ID ens (affectation)
    public String getjTextField8()  { return jTextField8.getText();  }  // ID mat (affectation)
    public String getjTextField9()  { return jTextField9.getText();  }  // ID étd (inscription)
    public String getjTextField10() { return jTextField10.getText(); }  // ID mat (inscription)

    // ─────────────────────────────────────────────
    //  Déclarations des variables
    // ─────────────────────────────────────────────
    private javax.swing.JButton jButton1, jButton2, jButton3, jButton4, jButton5, jButton6;
    private javax.swing.JButton jButton7, jButton8, jButton9, jButton10, jButton11, jButton12;
    private javax.swing.JButton jButton13, jButton14, jButton17, jButton18, jButton19, jButton20;
    private javax.swing.JButton jButton21, jButton22, jButton23, jButton24;
    private javax.swing.JLabel  jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private javax.swing.JLabel  jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, jLabel13;
    private javax.swing.JLabel  jLabel14, jLabel15, jLabel16, jLabel17, jLabel18, jLabel19;
    private javax.swing.JLabel  jLabel20, jLabel21, jLabel22, jLabel23, jLabel24, jLabel25;
    private javax.swing.JLabel  jLabel26, jLabel27, jLabel28, jLabel29, jLabel30, jLabel31, jLabel32;
    private javax.swing.JLabel  jLabel33, jLabel34;  // ✅ NOUVEAU : Spécialité, Grade
    private javax.swing.JPanel  jPanel1, jPanel2, jPanel3, jPanel4, jPanel5, jPanel6;
    private javax.swing.JPanel  jPanel7, jPanel8, jPanel9, jPanel10;
    private javax.swing.JScrollPane jScrollPane1, jScrollPane2, jScrollPane3, jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5, jScrollPane6, jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable  jTable1, jTable2, jTable3, jTable4, jTable5, jTable6, jTable7;
    private javax.swing.JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5;
    private javax.swing.JTextField jTextField6, jTextField7, jTextField8, jTextField9, jTextField10;
    private javax.swing.JTextField jTextField11, jTextField12, jTextField13, jTextField14, jTextField15;
    private javax.swing.JTextField jTextField16, jTextField17;  // ✅ NOUVEAU : Spécialité, Grade
    private javax.swing.JToggleButton jToggleButton1;
}