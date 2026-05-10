package view.frame;




import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TeacherPanelFrame extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;

    // ── Labels infos enseignant ──────────────────────────────────────────────
    private JLabel jLabel3;    // username
    private JLabel jLabel8;    // date login
    private JLabel jLabel26;   // ID
    private JLabel jLabel28;   // nom
    private JLabel jLabel30;   // genre
    private JLabel labelTime;  // horloge
    private JLabel labelNom;   // nom étudiant sélectionné
    private JLabel labelAff;   // label "Note :"
    private JLabel labelMatiere; // label "Matière :"  ← NOUVEAU
    private JLabel imageLabel; // photo

    // ── Tables ───────────────────────────────────────────────────────────────
    private JTable jTable2;    // matières
    private JTable jTable3;    // étudiants
    private JTable jTable4;    // messages

    // ── Saisie note ──────────────────────────────────────────────────────────
    private JTextField    fieldNote;
    private JComboBox<String> comboMatiere; // ← NOUVEAU
    private JButton       buttonValiderNote;

    // ── Messagerie ───────────────────────────────────────────────────────────
    private JTextArea jTextArea1;  // destinataire
    private JTextArea jTextArea2;  // message

    // ── Boutons principaux ───────────────────────────────────────────────────
    private JButton jButton1;  // Déconnexion
    private JButton jButton2;  // Envoyer message

    // ── Boutons CRUD Étudiants ───────────────────────────────────────────────
    private JButton btnAddStudent, btnEditStudent, btnDeleteStudent, btnRefreshStudent;

    // ── Boutons CRUD Matières ────────────────────────────────────────────────
    private JButton btnAddSubject, btnEditSubject, btnDeleteSubject, btnRefreshSubject;

    // ── TabbedPane ───────────────────────────────────────────────────────────
    private JTabbedPane tabs;

    // ════════════════════════════════════════════════════════════════════════
    public TeacherPanelFrame() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // ════════════════════════════════════════════════════════════════════════
    public void setName(String name) {
        labelNom.setText("Étudiant : " + name);
    }

    public void setNameNt(String name) { setName(name); }

    public String getLabelNomv() {
        return labelNom != null ? labelNom.getText() : "";
    }

    public void setjLabel8(String date)  { jLabel8.setText("Connexion : " + date); }
    public void setLabelTime(String time) { labelTime.setText(time); }

    // ════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Espace Enseignant - Gestion Scolaire");
        setSize(1100, 750);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // ── HEADER ───────────────────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(70, 130, 180));
        header.setPreferredSize(new Dimension(1100, 60));
        header.setBorder(new EmptyBorder(8, 20, 8, 20));

        JLabel titleLabel = new JLabel("Espace Enseignant");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);

        JPanel headerRight = new JPanel(new GridLayout(2, 1));
        headerRight.setOpaque(false);
        jLabel8   = new JLabel("Connexion : —");
        labelTime = new JLabel("00:00:00");
        for (JLabel l : new JLabel[]{jLabel8, labelTime}) {
            l.setForeground(Color.WHITE);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            l.setHorizontalAlignment(SwingConstants.RIGHT);
            headerRight.add(l);
        }
        header.add(titleLabel,  BorderLayout.WEST);
        header.add(headerRight, BorderLayout.EAST);

        // ── SIDEBAR ───────────────────────────────────────────────────────────
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(new Color(52, 73, 94));
        sidebar.setPreferredSize(new Dimension(220, 690));

        JPanel photoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        photoPanel.setBackground(new Color(52, 73, 94));
        photoPanel.setBorder(new EmptyBorder(15, 0, 10, 0));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(120, 120));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 3));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String imagePath = "C:/Users/user/Desktop/Sahar Othmeni/gestion_ecole/image/69493-OCRJ54-447.jpg";
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        } else {
            imageLabel.setText("👤");
            imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
            imageLabel.setForeground(Color.WHITE);
        }
        photoPanel.add(imageLabel);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        infoPanel.setBackground(new Color(52, 73, 94));
        infoPanel.setBorder(new EmptyBorder(5, 15, 10, 10));
        jLabel26 = new JLabel("ID : —");
        jLabel3  = new JLabel("Utilisateur : —");
        jLabel28 = new JLabel("Nom : —");
        jLabel30 = new JLabel("Genre : —");
        for (JLabel l : new JLabel[]{jLabel26, jLabel3, jLabel28, jLabel30}) {
            l.setForeground(new Color(189, 195, 199));
            l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            infoPanel.add(l);
        }

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.setBackground(new Color(52, 73, 94));
        logoutPanel.setBorder(new EmptyBorder(0, 10, 20, 10));

        jButton1 = new JButton("🚪  Déconnexion");
        jButton1.setPreferredSize(new Dimension(190, 40));
        jButton1.setForeground(Color.WHITE);
        jButton1.setBackground(new Color(192, 57, 43));
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jButton1.setFocusPainted(false);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { jButton1.setBackground(new Color(231, 76, 60)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { jButton1.setBackground(new Color(192, 57, 43)); }
        });
        jButton1.addActionListener(e -> {
            int c = JOptionPane.showConfirmDialog(this,
                "Voulez-vous vraiment vous déconnecter ?",
                "Déconnexion", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) {
                new LoginFrame().setVisible(true);
                this.dispose();
            }
        });
        logoutPanel.add(jButton1);

        JPanel sideTop = new JPanel(new BorderLayout());
        sideTop.setBackground(new Color(52, 73, 94));
        sideTop.add(photoPanel, BorderLayout.NORTH);
        sideTop.add(infoPanel,  BorderLayout.CENTER);
        sidebar.add(sideTop,     BorderLayout.NORTH);
        sidebar.add(logoutPanel, BorderLayout.SOUTH);

        // ── TABS ──────────────────────────────────────────────────────────────
        tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tabs.addTab("□ Étudiants",   buildStudentsTab());
        tabs.addTab("□ Matières",    buildSubjectsTab());
        tabs.addTab("□ Saisie note", buildNoteTab());
        tabs.addTab("□ Messagerie",  buildMessagerieTab());

        mainPanel.add(header,  BorderLayout.NORTH);
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(tabs,    BorderLayout.CENTER);
        this.add(mainPanel);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB ÉTUDIANTS
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildStudentsTab() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setBackground(Color.WHITE);

        JPanel crudBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        crudBar.setBackground(Color.WHITE);

        btnAddStudent     = makeCrudBtn("➕ Ajouter",    new Color(46, 139, 87));
        btnEditStudent    = makeCrudBtn("✏️ Modifier",   new Color(70, 130, 180));
        btnDeleteStudent  = makeCrudBtn("🗑 Supprimer",  new Color(192, 57, 43));
        btnRefreshStudent = makeCrudBtn("🔄 Actualiser", new Color(108, 117, 125));

        crudBar.add(btnAddStudent);
        crudBar.add(btnEditStudent);
        crudBar.add(btnDeleteStudent);
        crudBar.add(btnRefreshStudent);

        jTable3 = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nom", "Prénom", "Classe"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        styleTable(jTable3);

        btnAddStudent.addActionListener(e -> {
            JTextField fId = new JTextField(), fNom = new JTextField(),
                       fPrenom = new JTextField(), fClasse = new JTextField();
            Object[] fields = {"ID :", fId, "Nom :", fNom, "Prénom :", fPrenom, "Classe :", fClasse};
            if (JOptionPane.showConfirmDialog(this, fields,
                    "Ajouter un étudiant", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                ((DefaultTableModel) jTable3.getModel()).addRow(new Object[]{
                    fId.getText(), fNom.getText(), fPrenom.getText(), fClasse.getText()});
        });

        btnEditStudent.addActionListener(e -> {
            int row = jTable3.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Sélectionnez un étudiant.", "Aucune sélection", JOptionPane.WARNING_MESSAGE); return; }
            DefaultTableModel m = (DefaultTableModel) jTable3.getModel();
            JTextField fId = new JTextField((String) m.getValueAt(row, 0)),
                       fNom = new JTextField((String) m.getValueAt(row, 1)),
                       fPrenom = new JTextField((String) m.getValueAt(row, 2)),
                       fClasse = new JTextField((String) m.getValueAt(row, 3));
            Object[] fields = {"ID :", fId, "Nom :", fNom, "Prénom :", fPrenom, "Classe :", fClasse};
            if (JOptionPane.showConfirmDialog(this, fields, "Modifier l'étudiant", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                m.setValueAt(fId.getText(), row, 0); m.setValueAt(fNom.getText(), row, 1);
                m.setValueAt(fPrenom.getText(), row, 2); m.setValueAt(fClasse.getText(), row, 3);
            }
        });

        btnDeleteStudent.addActionListener(e -> {
            int row = jTable3.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Sélectionnez un étudiant.", "Aucune sélection", JOptionPane.WARNING_MESSAGE); return; }
            if (JOptionPane.showConfirmDialog(this, "Supprimer cet étudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                ((DefaultTableModel) jTable3.getModel()).removeRow(row);
        });

        btnRefreshStudent.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Rechargement depuis la base de données...\n(à connecter au contrôleur)", "Actualiser", JOptionPane.INFORMATION_MESSAGE));

        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = jTable3.getSelectedRow();
                if (row >= 0) {
                    String nom = jTable3.getValueAt(row, 1) + " " + jTable3.getValueAt(row, 2);
                    setName(nom);
                    setNoteTabVisible(true);
                    tabs.setSelectedIndex(2);
                }
            }
        });

        p.add(crudBar, BorderLayout.NORTH);
        p.add(new JScrollPane(jTable3), BorderLayout.CENTER);
        return p;
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB MATIÈRES
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildSubjectsTab() {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setBackground(Color.WHITE);

        JPanel crudBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        crudBar.setBackground(Color.WHITE);

        btnAddSubject     = makeCrudBtn("➕ Ajouter",    new Color(46, 139, 87));
        btnEditSubject    = makeCrudBtn("✏️ Modifier",   new Color(70, 130, 180));
        btnDeleteSubject  = makeCrudBtn("🗑 Supprimer",  new Color(192, 57, 43));
        btnRefreshSubject = makeCrudBtn("🔄 Actualiser", new Color(108, 117, 125));

        crudBar.add(btnAddSubject); crudBar.add(btnEditSubject);
        crudBar.add(btnDeleteSubject); crudBar.add(btnRefreshSubject);

        jTable2 = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Code", "Matière", "Coefficient", "Classe"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        styleTable(jTable2);

        btnAddSubject.addActionListener(e -> {
            JTextField fCode = new JTextField(), fMat = new JTextField(),
                       fCoef = new JTextField(), fClasse = new JTextField();
            Object[] fields = {"Code :", fCode, "Matière :", fMat, "Coefficient :", fCoef, "Classe :", fClasse};
            if (JOptionPane.showConfirmDialog(this, fields, "Ajouter une matière", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                ((DefaultTableModel) jTable2.getModel()).addRow(new Object[]{
                    fCode.getText(), fMat.getText(), fCoef.getText(), fClasse.getText()});
        });

        btnEditSubject.addActionListener(e -> {
            int row = jTable2.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Sélectionnez une matière.", "Aucune sélection", JOptionPane.WARNING_MESSAGE); return; }
            DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
            JTextField fCode = new JTextField(m.getValueAt(row, 0).toString()),
                       fMat  = new JTextField(m.getValueAt(row, 1).toString()),
                       fCoef = new JTextField(m.getValueAt(row, 2).toString()),
                       fClasse = new JTextField(m.getValueAt(row, 3).toString());
            Object[] fields = {"Code :", fCode, "Matière :", fMat, "Coefficient :", fCoef, "Classe :", fClasse};
            if (JOptionPane.showConfirmDialog(this, fields, "Modifier la matière", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                m.setValueAt(fCode.getText(), row, 0); m.setValueAt(fMat.getText(), row, 1);
                m.setValueAt(fCoef.getText(), row, 2); m.setValueAt(fClasse.getText(), row, 3);
            }
        });

        btnDeleteSubject.addActionListener(e -> {
            int row = jTable2.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Sélectionnez une matière.", "Aucune sélection", JOptionPane.WARNING_MESSAGE); return; }
            if (JOptionPane.showConfirmDialog(this, "Supprimer cette matière ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                ((DefaultTableModel) jTable2.getModel()).removeRow(row);
        });

        btnRefreshSubject.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Rechargement depuis la base de données...\n(à connecter au contrôleur)", "Actualiser", JOptionPane.INFORMATION_MESSAGE));

        p.add(crudBar, BorderLayout.NORTH);
        p.add(new JScrollPane(jTable2), BorderLayout.CENTER);
        return p;
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB SAISIE NOTE  ← CORRIGÉ : ajout champ Matière + layout revu
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildNoteTab() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);

        // ── Titre ─────────────────────────────────────────────────────────────
        JLabel titre = new JLabel("Saisie de note");
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setForeground(new Color(70, 130, 180));
        titre.setBounds(30, 20, 300, 35);
        p.add(titre);

        JSeparator sep = new JSeparator();
        sep.setBounds(30, 60, 600, 2);
        sep.setForeground(new Color(70, 130, 180));
        p.add(sep);

        // ── Nom étudiant ──────────────────────────────────────────────────────
        labelNom = new JLabel("Étudiant : —");
        labelNom.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelNom.setForeground(new Color(52, 73, 94));
        labelNom.setBounds(30, 75, 400, 30);
        labelNom.setVisible(false);
        p.add(labelNom);

        // ── Matière ← NOUVEAU ─────────────────────────────────────────────────
        labelMatiere = new JLabel("Matière :");
        labelMatiere.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelMatiere.setBounds(30, 118, 160, 25);
        labelMatiere.setVisible(false);
        p.add(labelMatiere);

        comboMatiere = new JComboBox<>(new String[]{
            "Mathématiques", "Physique", "Informatique",
            "Français", "Anglais", "Histoire-Géographie",
            "Sciences", "Éducation Physique"
        });
        comboMatiere.setBounds(30, 143, 220, 32);
        comboMatiere.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboMatiere.setBackground(Color.WHITE);
        comboMatiere.setVisible(false);
        p.add(comboMatiere);

        // ── Note ──────────────────────────────────────────────────────────────
        labelAff = new JLabel("Note (0 – 20) :");
        labelAff.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelAff.setBounds(30, 190, 160, 25);
        labelAff.setVisible(false);
        p.add(labelAff);

        fieldNote = new JTextField();
        fieldNote.setBounds(30, 215, 160, 40);
        fieldNote.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        fieldNote.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        fieldNote.setVisible(false);
        p.add(fieldNote);

        // ── Bouton Valider ────────────────────────────────────────────────────
        buttonValiderNote = new JButton("✔  Valider la note");
        buttonValiderNote.setBounds(30, 270, 180, 42);
        buttonValiderNote.setBackground(new Color(46, 139, 87));
        buttonValiderNote.setForeground(Color.WHITE);
        buttonValiderNote.setFont(new Font("Segoe UI", Font.BOLD, 13));
        buttonValiderNote.setFocusPainted(false);
        buttonValiderNote.setBorderPainted(false);
        buttonValiderNote.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonValiderNote.setVisible(false);
        buttonValiderNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { buttonValiderNote.setBackground(new Color(39, 174, 96)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { buttonValiderNote.setBackground(new Color(46, 139, 87)); }
        });

        buttonValiderNote.addActionListener(e -> {
            String noteStr = fieldNote.getText().trim();
            try {
                double note = Double.parseDouble(noteStr);
                if (note < 0 || note > 20) throw new NumberFormatException();
                String matiere = (String) comboMatiere.getSelectedItem();
                JOptionPane.showMessageDialog(this,
                    "Note " + note + "/20 enregistrée pour " + labelNom.getText()
                    + "\nMatière : " + matiere,
                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                fieldNote.setText("");
                setNoteTabVisible(false);
                tabs.setSelectedIndex(0);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Entrez une note valide entre 0 et 20.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        p.add(buttonValiderNote);

        // ── Message d'aide (visible par défaut) ───────────────────────────────
        JLabel helpLabel = new JLabel("← Sélectionnez un étudiant dans l'onglet Étudiants pour saisir sa note.");
        helpLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        helpLabel.setForeground(new Color(150, 150, 150));
        helpLabel.setBounds(30, 75, 700, 30);
        p.add(helpLabel);

        // Cacher le helpLabel quand un étudiant est sélectionné
        // (géré via setNoteTabVisible : quand visible=true, labelNom couvre cette zone)

        return p;
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB MESSAGERIE  ← CORRIGÉ : bouton Envoyer ajoute le message dans jTable4
    // ════════════════════════════════════════════════════════════════════════
    private JPanel buildMessagerieTab() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(new EmptyBorder(10, 10, 10, 10));
        p.setBackground(Color.WHITE);

        // ── Table messages reçus ──────────────────────────────────────────────
        jTable4 = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"De / À", "Message", "Date"}
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        styleTable(jTable4);

        // ── Panneau d'envoi ───────────────────────────────────────────────────
        JPanel sendPanel = new JPanel(null);
        sendPanel.setPreferredSize(new Dimension(1100, 175));
        sendPanel.setBackground(new Color(245, 245, 245));
        sendPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            " Nouveau message "
        ));

        JLabel lDest = new JLabel("Destinataire (ID) :");
        lDest.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lDest.setBounds(15, 28, 150, 25);
        sendPanel.add(lDest);

        jTextArea1 = new JTextArea();
        jTextArea1.setFont(new Font("Monospaced", Font.PLAIN, 13));
        jTextArea1.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        JScrollPane spDest = new JScrollPane(jTextArea1);
        spDest.setBounds(170, 28, 260, 28);
        sendPanel.add(spDest);

        JLabel lMsg = new JLabel("Message :");
        lMsg.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lMsg.setBounds(15, 68, 100, 25);
        sendPanel.add(lMsg);

        jTextArea2 = new JTextArea();
        jTextArea2.setLineWrap(true);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane spMsg = new JScrollPane(jTextArea2);
        spMsg.setBounds(15, 98, 700, 60);
        sendPanel.add(spMsg);

        jButton2 = new JButton("📨  Envoyer");
        jButton2.setBounds(725, 98, 140, 60);
        jButton2.setBackground(new Color(70, 130, 180));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        jButton2.setFocusPainted(false);
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { jButton2.setBackground(new Color(52, 110, 160)); }
            public void mouseExited(java.awt.event.MouseEvent e)  { jButton2.setBackground(new Color(70, 130, 180)); }
        });

        // ── CORRECTION : action Envoyer ────────────────────────────────────────
        jButton2.addActionListener(e -> {
            String dest = jTextArea1.getText().trim();
            String msg  = jTextArea2.getText().trim();

            // Validation champs
            if (dest.isEmpty() || msg.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Remplissez le destinataire et le message.",
                    "Champs manquants", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ajouter dans la table
            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm")
                              .format(new java.util.Date());
            model.addRow(new Object[]{"Moi → " + dest, msg, date});

            // Vider les champs
            jTextArea1.setText("");
            jTextArea2.setText("");

            JOptionPane.showMessageDialog(this,
                "Message envoyé à : " + dest,
                "Succès ✔", JOptionPane.INFORMATION_MESSAGE);
        });

        sendPanel.add(jButton2);

        p.add(new JScrollPane(jTable4), BorderLayout.CENTER);
        p.add(sendPanel,                BorderLayout.SOUTH);
        return p;
    }

    // ════════════════════════════════════════════════════════════════════════
    // HELPERS
    // ════════════════════════════════════════════════════════════════════════
    private JButton makeCrudBtn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(130, 32));
        return b;
    }

    private void styleTable(JTable t) {
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        t.setRowHeight(26);
        t.setGridColor(new Color(220, 220, 220));
        t.setSelectionBackground(new Color(70, 130, 180, 80));
        t.getTableHeader().setBackground(new Color(70, 130, 180));
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        t.getTableHeader().setReorderingAllowed(false);
    }

    // ── CORRECTION : setNoteTabVisible inclut maintenant comboMatiere et labelMatiere
    public void setNoteTabVisible(boolean b) {
        if (labelNom != null)          labelNom.setVisible(b);
        if (labelAff != null)          labelAff.setVisible(b);
        if (fieldNote != null)         fieldNote.setVisible(b);
        if (buttonValiderNote != null) buttonValiderNote.setVisible(b);
        if (labelMatiere != null)      labelMatiere.setVisible(b);   // ← NOUVEAU
        if (comboMatiere != null)      comboMatiere.setVisible(b);   // ← NOUVEAU
    }

    // ════════════════════════════════════════════════════════════════════════
    // GETTERS / SETTERS pour TeacherPanelView
    // ════════════════════════════════════════════════════════════════════════
    public JButton    getButton()            { return buttonValiderNote; }
    public JButton    getjButton1()          { return jButton1; }
    public JButton    getjButton2()          { return jButton2; }
    public JButton    getBtnAddStudent()     { return btnAddStudent; }
    public JButton    getBtnEditStudent()    { return btnEditStudent; }
    public JButton    getBtnDeleteStudent()  { return btnDeleteStudent; }
    public JButton    getBtnRefreshStudent() { return btnRefreshStudent; }
    public JButton    getBtnAddSubject()     { return btnAddSubject; }
    public JButton    getBtnEditSubject()    { return btnEditSubject; }
    public JButton    getBtnDeleteSubject()  { return btnDeleteSubject; }
    public JButton    getBtnRefreshSubject() { return btnRefreshSubject; }
    public JLabel     getLabelAff()          { return labelAff; }
    public JLabel     getLabelNom()          { return labelNom; }
    public JTextField getFieldNote()         { return fieldNote; }
    public String     getNote()              { return fieldNote.getText().trim(); }
    public JComboBox<String> getComboMatiere() { return comboMatiere; }          // ← NOUVEAU
    public String     getSelectedMatiere()   { return (String) comboMatiere.getSelectedItem(); } // ← NOUVEAU
    public JTable     getjTable2()           { return jTable2; }
    public JTable     getjTable3()           { return jTable3; }
    public JTable     getjTable4()           { return jTable4; }
    public void setjTable2(JTable t)         { jTable2.setModel(t.getModel()); }
    public void setjTable3(JTable t)         { jTable3.setModel(t.getModel()); }
    public void setjTable4(JTable t)         { jTable4.setModel(t.getModel()); }
    public String getjTextArea1()            { return jTextArea1.getText().trim(); }
    public String getjTextArea2()            { return jTextArea2.getText().trim(); }
    public void   setjTextArea2(String s)    { jTextArea2.setText(s); }
    public void setTeacherID(String id)      { jLabel26.setText("ID : "    + id); }
    public void setjLabel26(String id)       { jLabel26.setText("ID : "    + id); }
    public void setjLabel28(String name)     { jLabel28.setText("Nom : "   + name); }
    public void setjLabel30(String genre)    { jLabel30.setText("Genre : " + genre); }
    public void setjLabel3(String user)      { jLabel3.setText("Utilisateur : " + user); }
}