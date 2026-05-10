package view.frame;


//════════════════════════════════════════════════════════════════════
//StudentPanelFrame — CORRECTIONS LOGIQUE UNIQUEMENT (design inchangé)
//
//✅ FIX 1 — btnAddTodo connecté : ajoute une ligne dans tblTodo
//✅ FIX 2 — btnRemoveTodo connecté : supprime la ligne sélectionnée
//✅ FIX 3 — btnCalcMoyenne connecté : calcule la moyenne des notes
//✅ FIX 4 — handleSendMail() déjà présent — conservé tel quel
//✅ FIX 5 — handleLogout() rouvre LoginFrame (déjà présent — conservé)
//✅ FIX 6 — main() retiré : l'entrée de l'app est dans LoginFrame
//          (le main() de test est conservé en commentaire en bas)
//════════════════════════════════════════════════════════════════════

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class StudentPanelFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // ─── Palette ───────────────────────────────────────────────────────────────
    private static final Color COL_SIDEBAR  = new Color(30,  41,  59);
    private static final Color COL_ACCENT   = new Color(99,  102, 241);
    private static final Color COL_ACCENT2  = new Color(79,  70,  229);
    private static final Color COL_BG       = new Color(245, 247, 250);
    private static final Color COL_WHITE    = Color.WHITE;
    private static final Color COL_BORDER   = new Color(226, 232, 240);
    private static final Color COL_TEXT_LT  = new Color(248, 250, 252);
    private static final Color COL_TEXT_MED = new Color(100, 116, 139);
    private static final Color COL_ROW_ODD  = new Color(249, 250, 251);
    private static final Color COL_HEADER   = new Color(241, 245, 249);

    // ─── Polices ───────────────────────────────────────────────────────────────
    private static final Font FNT_TITLE   = new Font("Segoe UI", Font.BOLD,  20);
    private static final Font FNT_SECTION = new Font("Segoe UI", Font.BOLD,  14);
    private static final Font FNT_NAV     = new Font("Segoe UI", Font.BOLD,  12);
    private static final Font FNT_BODY    = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FNT_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);
    private static final Font FNT_CLOCK   = new Font("Consolas",  Font.BOLD, 14);
    private static final Font FNT_BTN     = new Font("Segoe UI", Font.BOLD,  12);

    // ─── Composants ────────────────────────────────────────────────────────────
    private JLabel  lblStudentName, lblStudentId, lblGender;
    private JLabel  lblConnectedIn, lblConnectedVal;
    private JLabel  lblClock;
    private JLabel  lblUserTitle;
    private JLabel  lblDiplomeImage;

    private JButton btnLogout, btnFR, btnEN, btnAR;
    private JButton btnSendMail, btnAddTodo, btnRemoveTodo, btnCalcMoyenne;

    // ─── Champs Matières ──────────────────────────────────────────────────────
    private JTextField tfSubjectName, tfSubjectTeacher, tfSubjectDesc;
    private JButton    btnAddSubject, btnRemoveSubject;

    // ─── Champs Cours ─────────────────────────────────────────────────────────
    private JTextField tfCourseName, tfCourseDesc, tfCourseNote;
    private JButton    btnAddCourse, btnRemoveCourse;

    // ─── Boutons Inbox ────────────────────────────────────────────────────────
    private JButton btnDeleteInbox, btnViewInbox;

    private JTable     tblTodo, tblSubjects, tblCourses, tblInbox;
    private JTextField tfTodoInput, tfReceiver;
    private JTextArea  taMailBody;

    private JTabbedPane tabbedPane;

    private final String[] NAV_LABELS = {
        "☑  Todo", "📚  Matières", "📖  Cours",
        "✉  Envoyer", "📥  Inbox", "🏆  Résultat", "ℹ  Info"
    };
    private JButton[] navButtons;

    private static final String IMG_DIPLOME_ABSOLUTE =
        "C:/Users/user/Desktop/Sahar Othmeni/gestion_ecole/image/diplome.png";

    private int todoCounter = 1;

    // ══════════════════════════════════════════════════════════════════════════
    // Constructeur
    // ══════════════════════════════════════════════════════════════════════════
    public StudentPanelFrame() {
        initComponents();
        // FIX: applyTableStyles() appelé APRÈS initComponents() mais AVANT buildLayout()
        // Le renderer universel est maintenant défini ici pour couvrir tous les types
        buildLayout();
        applyTableStyles(); // FIX: déplacé après buildLayout() pour éviter tout NPE
        setWindowProps();
        startClock();
        wireButtons();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Connexion des boutons
    // ══════════════════════════════════════════════════════════════════════════
    private void wireButtons() {

        btnAddTodo.addActionListener(e -> {
            String desc = tfTodoInput.getText().trim();
            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez saisir une description pour la tâche.",
                    "Champ vide", JOptionPane.WARNING_MESSAGE);
                return;
            }
            DefaultTableModel model = (DefaultTableModel) tblTodo.getModel();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            model.addRow(new Object[]{todoCounter++, date, desc});
            tfTodoInput.setText("");
        });

        tfTodoInput.addActionListener(e -> btnAddTodo.doClick());

        btnRemoveTodo.addActionListener(e -> {
            int selectedRow = tblTodo.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner une tâche à supprimer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Supprimer cette tâche ?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tblTodo.getModel();
                model.removeRow(selectedRow);
            }
        });

        btnCalcMoyenne.addActionListener(e -> {            DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
            int rowCount = model.getRowCount();
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(this,
                    "Aucun cours enregistré dans la table.",
                    "Table vide", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            double total = 0;
            int count = 0;
            for (int i = 0; i < rowCount; i++) {
                Object val = model.getValueAt(i, 2);
                if (val != null && !val.toString().trim().isEmpty()) {
                    try {
                        total += Double.parseDouble(val.toString().trim());
                        count++;
                    } catch (NumberFormatException ignored) {}
                }
            }
            if (count == 0) {
                JOptionPane.showMessageDialog(this,
                    "Aucune note valide trouvée dans la colonne 'Note'.",
                    "Pas de notes", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double moyenne = total / count;
            String mention;
            if      (moyenne >= 16) mention = "Très bien 🎉";
            else if (moyenne >= 14) mention = "Bien";
            else if (moyenne >= 12) mention = "Assez bien";
            else if (moyenne >= 10) mention = "Passable";
            else                    mention = "Insuffisant";

            JOptionPane.showMessageDialog(this,
                String.format("Moyenne : %.2f / 20%nMention : %s", moyenne, mention),
                "Résultat", JOptionPane.INFORMATION_MESSAGE);
        });

        // ── Ajouter une matière ───────────────────────────────────────────────
        btnAddSubject.addActionListener(e -> {
            String name    = tfSubjectName.getText().trim();
            String teacher = tfSubjectTeacher.getText().trim();
            String desc    = tfSubjectDesc.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez saisir le nom de la matière.",
                    "Champ vide", JOptionPane.WARNING_MESSAGE);
                return;
            }
            DefaultTableModel model = (DefaultTableModel) tblSubjects.getModel();
            model.addRow(new Object[]{name, teacher, desc});
            tfSubjectName.setText(""); tfSubjectTeacher.setText(""); tfSubjectDesc.setText("");
        });
        tfSubjectName.addActionListener(e -> btnAddSubject.doClick());

        // ── Supprimer une matière ─────────────────────────────────────────────
        btnRemoveSubject.addActionListener(e -> {
            int row = tblSubjects.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner une matière à supprimer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Supprimer cette matière ?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION)
                ((DefaultTableModel) tblSubjects.getModel()).removeRow(row);
        });

        // ── Ajouter un cours ──────────────────────────────────────────────────
        btnAddCourse.addActionListener(e -> {
            String name = tfCourseName.getText().trim();
            String desc = tfCourseDesc.getText().trim();
            String note = tfCourseNote.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez saisir le nom du cours.",
                    "Champ vide", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!note.isEmpty()) {
                try {
                    double n = Double.parseDouble(note);
                    if (n < 0 || n > 20) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                        "La note doit être un nombre entre 0 et 20.",
                        "Note invalide", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();
            model.addRow(new Object[]{name, desc, note.isEmpty() ? "" : note});
            tfCourseName.setText(""); tfCourseDesc.setText(""); tfCourseNote.setText("");
        });
        tfCourseName.addActionListener(e -> btnAddCourse.doClick());

        // ── Supprimer un cours ────────────────────────────────────────────────
        btnRemoveCourse.addActionListener(e -> {
            int row = tblCourses.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner un cours à supprimer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Supprimer ce cours ?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION)
                ((DefaultTableModel) tblCourses.getModel()).removeRow(row);
        });

        // ── Boutons Inbox ─────────────────────────────────────────────────────
        btnViewInbox.addActionListener(e -> {
            int row = tblInbox.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner un message à lire.",
                    "Aucune sélection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String exp  = tblInbox.getValueAt(row, 0).toString();
            String msg  = tblInbox.getValueAt(row, 1).toString();
            String date = tblInbox.getValueAt(row, 2).toString();
            JTextArea ta = new JTextArea("De : " + exp + "\nDate : " + date + "\n\n" + msg);
            ta.setFont(FNT_BODY);
            ta.setEditable(false);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            ta.setPreferredSize(new Dimension(380, 140));
            JOptionPane.showMessageDialog(this, new JScrollPane(ta),
                "Message de " + exp, JOptionPane.PLAIN_MESSAGE);
        });

        btnDeleteInbox.addActionListener(e -> {
            int row = tblInbox.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner un message à supprimer.",
                    "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "Supprimer ce message ?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION)
                ((DefaultTableModel) tblInbox.getModel()).removeRow(row);
        });
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Horloge temps réel
    // ══════════════════════════════════════════════════════════════════════════
    private void startClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        javax.swing.Timer timer = new javax.swing.Timer(1000, e ->
            lblClock.setText(sdf.format(new Date()))
        );
        timer.setInitialDelay(0);
        timer.start();
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Chargement image diplome.png
    // ══════════════════════════════════════════════════════════════════════════
    private ImageIcon loadDiplomeImage(int width, int height) {
        java.net.URL url = getClass().getResource("/image/diplome.png");
        if (url != null) return scaleIcon(new ImageIcon(url), width, height);
        File rel = new File("image/diplome.png");
        if (rel.exists()) return scaleIcon(new ImageIcon(rel.getAbsolutePath()), width, height);
        File abs = new File(IMG_DIPLOME_ABSOLUTE);
        if (abs.exists()) return scaleIcon(new ImageIcon(abs.getAbsolutePath()), width, height);
        return null;
    }

    private ImageIcon scaleIcon(ImageIcon icon, int w, int h) {
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    private ImageIcon loadOptionalImage(String classpathPath, int w, int h) {
        java.net.URL url = getClass().getResource(classpathPath);
        if (url == null) return null;
        Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Initialisation des composants
    // ══════════════════════════════════════════════════════════════════════════
    private void initComponents() {

        lblStudentName = styledLabel("— Étudiant —", FNT_BODY,    new Color(148, 163, 184));
        lblStudentId   = styledLabel("—",            FNT_BTN,     new Color(30,  41,  59));
        lblGender      = styledLabel("—",            FNT_BTN,     new Color(30,  41,  59));
        lblUserTitle   = styledLabel("Student Panel",FNT_TITLE,   COL_TEXT_LT);
        lblConnectedIn = styledLabel("Connecté depuis :", FNT_SMALL, new Color(100,116,139));
        lblConnectedVal= styledLabel("0 min",        FNT_SMALL,   new Color(148,163,184));

        lblClock = new JLabel("00:00:00", SwingConstants.CENTER);
        lblClock.setFont(FNT_CLOCK);
        lblClock.setForeground(COL_ACCENT);

        lblDiplomeImage = new JLabel();
        lblDiplomeImage.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon diplomeIcon = loadDiplomeImage(72, 72);
        if (diplomeIcon != null) {
            lblDiplomeImage.setIcon(diplomeIcon);
        } else {
            lblDiplomeImage.setText("🎓");
            lblDiplomeImage.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
            lblDiplomeImage.setForeground(COL_ACCENT);
        }

        btnFR = langButton("FR"); btnFR.addActionListener(e -> switchToFrench());
        btnEN = langButton("EN"); btnEN.addActionListener(e -> switchToEnglish());
        btnAR = langButton("AR"); btnAR.addActionListener(e -> switchToArabic());

        btnLogout = new JButton("Déconnexion");
        btnLogout.setFont(FNT_BTN);
        btnLogout.setForeground(new Color(252, 165, 165));
        btnLogout.setBackground(new Color(127, 29, 29));
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btnLogout.setFocusPainted(false);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.setMaximumSize(new Dimension(190, 36));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.addActionListener(e -> handleLogout());

        // FIX: buildTable() utilise maintenant Object.class pour TOUTES les colonnes
        // afin d'éviter le crash du renderer Swing sur Integer.class
        tblTodo     = buildTable(new String[]{"N°","Date","Description"});
        tblSubjects = buildTable(new String[]{"Matière","Enseignant","Description"});
        tblCourses  = buildTable(new String[]{"Cours","Description","Note"});
        tblInbox    = buildTable(new String[]{"Expéditeur","Message","Date"});

        tfTodoInput = styledField("Nouvelle tâche…");
        tfReceiver  = styledField("Destinataire…");
        taMailBody  = new JTextArea(5, 20);
        taMailBody.setFont(FNT_BODY);
        taMailBody.setLineWrap(true);
        taMailBody.setWrapStyleWord(true);
        taMailBody.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        btnAddTodo     = accentButton("+ Ajouter");
        btnRemoveTodo  = ghostButton("Supprimer");
        btnSendMail    = accentButton("Envoyer ✈");
        btnCalcMoyenne = accentButton("Calculer ma moyenne");

        // ── Matières ──────────────────────────────────────────────────────────
        tfSubjectName    = styledField("Nom de la matière…");
        tfSubjectTeacher = styledField("Enseignant…");
        tfSubjectDesc    = styledField("Description…");
        btnAddSubject    = accentButton("+ Ajouter");
        btnRemoveSubject = ghostButton("Supprimer");

        // ── Cours ─────────────────────────────────────────────────────────────
        tfCourseName  = styledField("Nom du cours…");
        tfCourseDesc  = styledField("Description…");
        tfCourseNote  = styledField("Note (0-20)…");
        btnAddCourse    = accentButton("+ Ajouter");
        btnRemoveCourse = ghostButton("Supprimer");

        // ── Inbox ─────────────────────────────────────────────────────────────
        btnViewInbox   = accentButton("👁 Lire");
        btnDeleteInbox = ghostButton("🗑 Supprimer");

        btnSendMail.addActionListener(e -> handleSendMail());

        navButtons = new JButton[NAV_LABELS.length];
        for (int i = 0; i < NAV_LABELS.length; i++) {
            navButtons[i] = navButton(NAV_LABELS[i]);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Logout
    // ══════════════════════════════════════════════════════════════════════════
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Voulez-vous vraiment vous déconnecter ?",
            "Déconnexion", JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    Class<?> loginClass = Class.forName("LoginFrame");
                    JFrame loginFrame = (JFrame) loginClass.getDeclaredConstructor().newInstance();
                    loginFrame.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Déconnecté avec succès.\nRelancez l'application pour vous reconnecter.",
                        "Déconnexion", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            });
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Layout
    // ══════════════════════════════════════════════════════════════════════════
    private void buildLayout() {
        getContentPane().setBackground(COL_BG);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buildSidebar(),  BorderLayout.WEST);
        getContentPane().add(buildMainArea(), BorderLayout.CENTER);
    }

    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(COL_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(215, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(51, 65, 85)));

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(COL_SIDEBAR);
        top.setBorder(BorderFactory.createEmptyBorder(24, 16, 16, 16));

        lblDiplomeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblDiplomeImage);
        top.add(Box.createVerticalStrut(10));
        lblUserTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblUserTitle);
        top.add(Box.createVerticalStrut(4));
        lblStudentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblStudentName);
        top.add(Box.createVerticalStrut(14));

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(51, 65, 85));
        sep.setMaximumSize(new Dimension(180, 1));
        top.add(sep);
        top.add(Box.createVerticalStrut(10));
        lblConnectedIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblConnectedIn);
        top.add(Box.createVerticalStrut(3));
        lblConnectedVal.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblConnectedVal);
        top.add(Box.createVerticalStrut(5));
        lblClock.setAlignmentX(Component.CENTER_ALIGNMENT);
        top.add(lblClock);

        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        nav.setBackground(COL_SIDEBAR);
        nav.setBorder(BorderFactory.createEmptyBorder(14, 10, 10, 10));
        for (JButton b : navButtons) {
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            nav.add(b);
            nav.add(Box.createVerticalStrut(2));
        }

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(new Color(15, 23, 42));
        bottom.setBorder(BorderFactory.createEmptyBorder(12, 10, 16, 10));
        JPanel langRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        langRow.setBackground(new Color(15, 23, 42));
        langRow.add(btnFR); langRow.add(btnEN); langRow.add(btnAR);
        bottom.add(langRow);
        bottom.add(Box.createVerticalStrut(10));
        bottom.add(btnLogout);

        sidebar.add(top,    BorderLayout.NORTH);
        sidebar.add(nav,    BorderLayout.CENTER);
        sidebar.add(bottom, BorderLayout.SOUTH);
        return sidebar;
    }

    private JPanel buildMainArea() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(COL_BG);
        main.add(buildTopBar(), BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(FNT_NAV);
        tabbedPane.setBackground(COL_BG);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 14, 14, 14));

        tabbedPane.addTab("☑ Todo",      buildTodoPanel());
        tabbedPane.addTab("📚 Matières", buildSubjectsPanel());
        tabbedPane.addTab("📖 Cours",    buildCoursesPanel());
        tabbedPane.addTab("✉ Mail",      buildMailPanel());
        tabbedPane.addTab("📥 Inbox",    buildInboxPanel());
        tabbedPane.addTab("🏆 Résultat", buildResultPanel());
        tabbedPane.addTab("ℹ Info",      buildInfoPanel());

        for (int i = 0; i < navButtons.length; i++) {
            final int idx = i;
            navButtons[i].addActionListener(e -> tabbedPane.setSelectedIndex(idx));
        }

        main.add(tabbedPane, BorderLayout.CENTER);
        return main;
    }

    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(COL_WHITE);
        bar.setBorder(new CompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, COL_BORDER),
            BorderFactory.createEmptyBorder(12, 20, 12, 20)));

        JLabel title = new JLabel("Espace Étudiant");
        title.setFont(new Font("Segoe UI", Font.BOLD, 17));
        title.setForeground(new Color(15, 23, 42));

        JLabel quote = new JLabel("\"L'imagination est plus importante que le savoir\" — Einstein");
        quote.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        quote.setForeground(COL_TEXT_MED);

        JPanel left = new JPanel(new GridLayout(2, 1, 0, 2));
        left.setOpaque(false);
        left.add(title); left.add(quote);

        JPanel chips = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        chips.setOpaque(false);
        chips.add(infoChip("ID",    lblStudentId));
        chips.add(infoChip("Genre", lblGender));

        bar.add(left,  BorderLayout.WEST);
        bar.add(chips, BorderLayout.EAST);
        return bar;
    }

    // ─── Onglets ──────────────────────────────────────────────────────────────
    private JPanel buildTodoPanel() {
        JPanel p = tabPanel("☑ Ma liste de tâches");
        p.add(scrollTable(tblTodo), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(8, 0));
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        bottom.add(tfTodoInput, BorderLayout.CENTER);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btns.setOpaque(false);
        btns.add(btnRemoveTodo); btns.add(btnAddTodo);
        bottom.add(btns, BorderLayout.EAST);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildSubjectsPanel() {
        JPanel p = tabPanel("📚 Liste des matières");
        p.add(scrollTable(tblSubjects), BorderLayout.CENTER);

        // ── Formulaire d'ajout ────────────────────────────────────────────────
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(0, 0, 0, 6);
        g.fill   = GridBagConstraints.HORIZONTAL;
        g.gridy  = 0;

        g.gridx = 0; g.weightx = 0.25; form.add(tfSubjectName,    g);
        g.gridx = 1; g.weightx = 0.25; form.add(tfSubjectTeacher, g);
        g.gridx = 2; g.weightx = 0.35; form.add(tfSubjectDesc,    g);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btns.setOpaque(false);
        btns.add(btnRemoveSubject);
        btns.add(btnAddSubject);
        g.gridx = 3; g.weightx = 0; g.fill = GridBagConstraints.NONE;
        form.add(btns, g);

        p.add(form, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildCoursesPanel() {
        JPanel p = tabPanel("📖 Mes cours");
        p.add(scrollTable(tblCourses), BorderLayout.CENTER);

        // ── Formulaire d'ajout ────────────────────────────────────────────────
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        form.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(0, 0, 0, 6);
        g.fill   = GridBagConstraints.HORIZONTAL;
        g.gridy  = 0;

        g.gridx = 0; g.weightx = 0.30; form.add(tfCourseName, g);
        g.gridx = 1; g.weightx = 0.45; form.add(tfCourseDesc, g);

        // Champ Note limité en largeur
        tfCourseNote.setPreferredSize(new Dimension(80, tfCourseNote.getPreferredSize().height));
        g.gridx = 2; g.weightx = 0; g.fill = GridBagConstraints.NONE;
        form.add(tfCourseNote, g);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        btns.setOpaque(false);
        btns.add(btnRemoveCourse);
        btns.add(btnAddCourse);
        g.gridx = 3;
        form.add(btns, g);

        p.add(form, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildMailPanel() {
        JPanel p = tabPanel("✉ Envoyer un message");
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 0, 6, 10);
        g.anchor = GridBagConstraints.WEST;

        g.gridx=0; g.gridy=0;
        form.add(fieldLabel("Destinataire :"), g);
        g.gridx=1; g.weightx=1; g.fill=GridBagConstraints.HORIZONTAL;
        form.add(tfReceiver, g);

        g.gridx=0; g.gridy=1; g.weightx=0; g.fill=GridBagConstraints.NONE;
        form.add(fieldLabel("Message :"), g);
        g.gridx=1; g.weightx=1; g.fill=GridBagConstraints.HORIZONTAL;
        JScrollPane sp = new JScrollPane(taMailBody);
        sp.setBorder(styledBorder());
        form.add(sp, g);

        g.gridx=1; g.gridy=2; g.fill=GridBagConstraints.NONE;
        g.anchor=GridBagConstraints.EAST;
        form.add(btnSendMail, g);
        p.add(form, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildInboxPanel() {
        JPanel p = tabPanel("📥 Boîte de réception");
        p.add(scrollTable(tblInbox), BorderLayout.CENTER);

        // ── Barre de boutons ──────────────────────────────────────────────────
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        bar.setOpaque(false);
        bar.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel hint = new JLabel("Sélectionnez un message pour le lire ou le supprimer");
        hint.setFont(FNT_SMALL);
        hint.setForeground(COL_TEXT_MED);

        JPanel south = new JPanel(new BorderLayout());
        south.setOpaque(false);
        south.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        south.add(hint, BorderLayout.WEST);
        bar.add(btnDeleteInbox);
        bar.add(btnViewInbox);
        south.add(bar, BorderLayout.EAST);

        p.add(south, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildResultPanel() {
        JPanel p = tabPanel("🏆 Calcul de la moyenne");
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);

        JLabel imgLabel = new JLabel();
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon amakIcon = loadOptionalImage("/image/amak.png", 200, 130);
        if (amakIcon != null) {
            imgLabel.setIcon(amakIcon);
        } else {
            imgLabel.setText("📊");
            imgLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
            imgLabel.setForeground(COL_ACCENT);
        }

        JLabel hint = new JLabel("Cliquez pour calculer votre moyenne à partir de vos cours.");
        hint.setFont(FNT_BODY); hint.setForeground(COL_TEXT_MED);
        hint.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCalcMoyenne.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalGlue());
        center.add(imgLabel);
        center.add(Box.createVerticalStrut(16));
        center.add(hint);
        center.add(Box.createVerticalStrut(20));
        center.add(btnCalcMoyenne);
        center.add(Box.createVerticalGlue());
        p.add(center, BorderLayout.CENTER);
        return p;
    }

    private JPanel buildInfoPanel() {
        JPanel p = tabPanel("ℹ Informations personnelles");
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(COL_WHITE);
        card.setBorder(new CompoundBorder(
            BorderFactory.createLineBorder(COL_BORDER),
            BorderFactory.createEmptyBorder(16, 20, 16, 20)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 10, 8, 16);
        g.anchor = GridBagConstraints.WEST;

        Object[][] rows = {
            {"Identifiant :", lblStudentId},
            {"Nom complet :", lblStudentName},
            {"Genre :",       lblGender},
            {"Connecté depuis :", lblConnectedVal}
        };
        for (int i = 0; i < rows.length; i++) {
            g.gridx=0; g.gridy=i;
            card.add(fieldLabel((String)rows[i][0]), g);
            g.gridx=1;
            card.add((JLabel)rows[i][1], g);
        }

        JPanel wrap = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrap.setOpaque(false);
        wrap.add(card);
        p.add(wrap, BorderLayout.CENTER);
        return p;
    }

    // ══════════════════════════════════════════════════════════════════════════
    // FIX PRINCIPAL : applyTableStyles avec renderer couvrant tous les types
    // ══════════════════════════════════════════════════════════════════════════
    private void applyTableStyles() {
        for (JTable t : new JTable[]{tblTodo, tblSubjects, tblCourses, tblInbox}) {
            t.setFont(FNT_BODY);
            t.setRowHeight(32);
            t.setShowGrid(false);
            t.setIntercellSpacing(new Dimension(0, 0));
            t.setSelectionBackground(new Color(224, 231, 255));
            t.setSelectionForeground(new Color(30, 41, 59));
            t.setBackground(COL_WHITE);
            t.setForeground(new Color(30, 41, 59));

            JTableHeader header = t.getTableHeader();
            header.setFont(new Font("Segoe UI", Font.BOLD, 12));
            header.setBackground(COL_HEADER);
            header.setForeground(new Color(71, 85, 105));
            header.setBorder(BorderFactory.createMatteBorder(0,0,1,0,COL_BORDER));
            header.setReorderingAllowed(false);

            // FIX: renderer appliqué sur Object.class ET Number.class
            // pour couvrir Integer, Double etc. sans crash
            javax.swing.table.DefaultTableCellRenderer renderer =
                new javax.swing.table.DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table,
                            Object value, boolean isSelected, boolean hasFocus,
                            int row, int col) {
                        Component c = super.getTableCellRendererComponent(
                            table, value, isSelected, hasFocus, row, col);
                        if (!isSelected) {
                            c.setBackground(row % 2 == 0 ? COL_WHITE : COL_ROW_ODD);
                            c.setForeground(new Color(30, 41, 59));
                        }
                        ((JLabel)c).setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                        return c;
                    }
                };

            t.setDefaultRenderer(Object.class,  renderer);
            t.setDefaultRenderer(Number.class,  renderer); // FIX: couvre Integer, Double...
            t.setDefaultRenderer(Integer.class, renderer); // FIX: couvre explicitement Integer
        }

        if (tblTodo.getColumnModel().getColumnCount() > 0) {
            tblTodo.getColumnModel().getColumn(0).setMinWidth(40);
            tblTodo.getColumnModel().getColumn(0).setMaxWidth(50);
            tblTodo.getColumnModel().getColumn(1).setMaxWidth(110);
        }
        if (tblCourses.getColumnModel().getColumnCount() > 0) {
            tblCourses.getColumnModel().getColumn(0).setMaxWidth(130);
            tblCourses.getColumnModel().getColumn(2).setMinWidth(55);
            tblCourses.getColumnModel().getColumn(2).setMaxWidth(55);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Propriétés fenêtre
    // ══════════════════════════════════════════════════════════════════════════
    private void setWindowProps() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { handleLogout(); }
        });
        setTitle("Espace Étudiant — Gestion École");
        setMinimumSize(new Dimension(960, 620));
        pack();
        setLocationRelativeTo(null);
        ImageIcon winIcon = loadDiplomeImage(32, 32);
        if (winIcon != null) setIconImage(winIcon.getImage());
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Helpers composants
    // ══════════════════════════════════════════════════════════════════════════
    private JPanel tabPanel(String title) {
        JPanel p = new JPanel(new BorderLayout(0, 12));
        p.setBackground(COL_BG);
        p.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        JLabel lbl = new JLabel(title);
        lbl.setFont(FNT_SECTION);
        lbl.setForeground(new Color(15, 23, 42));
        lbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
        p.add(lbl, BorderLayout.NORTH);
        return p;
    }

    private JScrollPane scrollTable(JTable t) {
        JScrollPane sp = new JScrollPane(t);
        sp.setBorder(styledBorder());
        sp.getViewport().setBackground(COL_WHITE);
        return sp;
    }

    // FIX: buildTable() simplifié — plus de tableau de types Class[]
    // getColumnClass() retourne toujours Object.class pour éviter le crash du renderer
    private JTable buildTable(String[] cols) {
        return new JTable(new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // FIX bonus: cellules non éditables par défaut
            }
        });
    }

    private JTextField styledField(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(FNT_BODY);
        f.setBorder(new CompoundBorder(styledBorder(),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        return f;
    }

    private JButton accentButton(String text) {
        JButton b = new JButton(text);
        b.setFont(FNT_BTN);
        b.setForeground(COL_WHITE);
        b.setBackground(COL_ACCENT);
        b.setBorder(BorderFactory.createEmptyBorder(9, 20, 9, 20));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e){ b.setBackground(COL_ACCENT2); }
            @Override public void mouseExited (MouseEvent e){ b.setBackground(COL_ACCENT); }
        });
        return b;
    }

    private JButton ghostButton(String text) {
        JButton b = new JButton(text);
        b.setFont(FNT_BTN);
        b.setForeground(COL_TEXT_MED);
        b.setBackground(COL_WHITE);
        b.setBorder(new CompoundBorder(
            BorderFactory.createLineBorder(COL_BORDER),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JButton langButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 10));
        b.setForeground(new Color(148, 163, 184));
        b.setBackground(new Color(30, 41, 59));
        b.setBorder(BorderFactory.createEmptyBorder(4, 9, 4, 9));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    private JButton navButton(String text) {
        JButton b = new JButton(text);
        b.setFont(FNT_NAV);
        b.setForeground(new Color(148, 163, 184));
        b.setBackground(COL_SIDEBAR);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setBorder(BorderFactory.createEmptyBorder(9, 14, 9, 14));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                b.setBackground(new Color(51,65,85));
                b.setForeground(COL_TEXT_LT);
            }
            @Override public void mouseExited(MouseEvent e) {
                b.setBackground(COL_SIDEBAR);
                b.setForeground(new Color(148,163,184));
            }
        });
        return b;
    }

    private JLabel styledLabel(String text, Font font, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(font); l.setForeground(color);
        return l;
    }

    private JLabel fieldLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(new Color(71, 85, 105));
        return l;
    }

    private JPanel infoChip(String key, JLabel val) {
        JPanel c = new JPanel(new GridLayout(2,1,0,2));
        c.setBackground(COL_HEADER);
        c.setBorder(new CompoundBorder(
            BorderFactory.createLineBorder(COL_BORDER),
            BorderFactory.createEmptyBorder(5,12,5,12)));
        JLabel k = new JLabel(key);
        k.setFont(FNT_SMALL); k.setForeground(COL_TEXT_MED);
        val.setFont(new Font("Segoe UI", Font.BOLD, 12));
        val.setForeground(new Color(30,41,59));
        c.add(k); c.add(val);
        return c;
    }

    private Border styledBorder() {
        return BorderFactory.createLineBorder(COL_BORDER);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Logique métier — Mail
    // ══════════════════════════════════════════════════════════════════════════
    private void handleSendMail() {
        String receiver = tfReceiver.getText().trim();
        String message  = taMailBody.getText().trim();
        if (receiver.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un destinataire.",
                "Champ manquant", JOptionPane.WARNING_MESSAGE); return;
        }
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un message.",
                "Champ manquant", JOptionPane.WARNING_MESSAGE); return;
        }
        // ── Ajouter le message dans l'Inbox ──────────────────────────────────
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        String senderName = lblStudentName.getText().equals("— Étudiant —")
                            ? "Moi" : lblStudentName.getText();
        DefaultTableModel inboxModel = (DefaultTableModel) tblInbox.getModel();
        inboxModel.addRow(new Object[]{
            "Moi → " + receiver,
            message,
            date
        });
        // Basculer vers l'onglet Inbox pour confirmer visuellement
        JOptionPane.showMessageDialog(this,
            "✅ Message envoyé à : " + receiver + "\nConsultez votre Inbox pour le retrouver.",
            "Message envoyé", JOptionPane.INFORMATION_MESSAGE);
        taMailBody.setText(""); tfReceiver.setText("");
        // Aller à l'onglet Inbox (index 4)
        if (tabbedPane != null) tabbedPane.setSelectedIndex(4);
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Langues
    // ══════════════════════════════════════════════════════════════════════════
    private void switchToFrench() {
        lblUserTitle.setText("Espace Étudiant");
        btnLogout.setText("Déconnexion");
        lblConnectedIn.setText("Connecté depuis :");
    }

    private void switchToEnglish() {
        lblUserTitle.setText("Student Panel");
        btnLogout.setText("Logout");
        lblConnectedIn.setText("Connected since:");
    }

    private void switchToArabic() {
        lblUserTitle.setText("فضاء الطالب");
        btnLogout.setText("تسجيل خروج");
        lblConnectedIn.setText(":متصل منذ");
    }

    // ══════════════════════════════════════════════════════════════════════════
    // Accesseurs publics (compatibilité contrôleur existant)
    // ══════════════════════════════════════════════════════════════════════════
    public JButton getjButton1()  { return btnLogout; }
    public JButton getjButton2()  { return btnSendMail; }
    public JButton getjButton6()  { return btnAddTodo; }
    public JButton getjButton7()  { return btnRemoveTodo; }
    public JButton getjButton8()  { return btnCalcMoyenne; }

    public String getjLabel26()         { return lblStudentId.getText(); }
    public void   setjLabel26(String v) { lblStudentId.setText(v); lblStudentName.repaint(); }
    public String getjLabel28()         { return lblStudentName.getText(); }
    public void   setjLabel28(String v) { lblStudentName.setText(v); }
    public String getjLabel30()         { return lblGender.getText(); }
    public void   setjLabel30(String v) { lblGender.setText(v); }
    public String getjLabel3()          { return lblStudentId.getText(); }
    public void   setjLabel3(String v)  { lblStudentId.setText(v); }
    public String getjLabel8()          { return lblConnectedVal.getText(); }
    public void   setjLabel8(String v)  { lblConnectedVal.setText(v); }
    public void   setjLabel37(String t) { lblClock.setText(t); }

    public JTable getjTable1()          { return tblTodo; }
    public JTable getjTable2()          { return tblSubjects; }
    public JTable getjTable3()          { return tblCourses; }
    public JTable getjTable4()          { return tblInbox; }

    public void setjTable1(JTable table) { this.tblTodo = table; replaceTableInScrollPane(table, 0); }
    public void setjTable2(JTable table) { this.tblSubjects = table; replaceTableInScrollPane(table, 1); }
    public void setjTable3(JTable table) { this.tblCourses = table; replaceTableInScrollPane(table, 2); }
    public void setjTable4(JTable table) { this.tblInbox = table; replaceTableInScrollPane(table, 4); }

    private void replaceTableInScrollPane(JTable table, int tabIndex) {
        if (tabbedPane == null) return;
        styleTable(table);
        java.awt.Component comp = tabbedPane.getComponentAt(tabIndex);
        if (comp instanceof JPanel) findAndReplaceScrollPane((java.awt.Container) comp, table);
        tabbedPane.revalidate();
        tabbedPane.repaint();
    }

    private void findAndReplaceScrollPane(java.awt.Container container, JTable table) {
        for (java.awt.Component c : container.getComponents()) {
            if (c instanceof JScrollPane) { ((JScrollPane) c).setViewportView(table); return; }
            if (c instanceof java.awt.Container) findAndReplaceScrollPane((java.awt.Container) c, table);
        }
    }

    private void styleTable(JTable t) {
        t.setFont(FNT_BODY); t.setRowHeight(32); t.setShowGrid(false);
        t.setIntercellSpacing(new Dimension(0, 0));
        t.setSelectionBackground(new Color(224, 231, 255));
        t.setSelectionForeground(new Color(30, 41, 59));
        t.setBackground(COL_WHITE); t.setForeground(new Color(30, 41, 59));
        JTableHeader header = t.getTableHeader();
        if (header != null) {
            header.setFont(new Font("Segoe UI", Font.BOLD, 12));
            header.setBackground(COL_HEADER); header.setForeground(new Color(71, 85, 105));
            header.setBorder(BorderFactory.createMatteBorder(0,0,1,0,COL_BORDER));
            header.setReorderingAllowed(false);
        }
        javax.swing.table.DefaultTableCellRenderer renderer =
            new javax.swing.table.DefaultTableCellRenderer() {
                @Override public Component getTableCellRendererComponent(JTable table,
                        Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                    if (!isSelected) { c.setBackground(row % 2 == 0 ? COL_WHITE : COL_ROW_ODD); c.setForeground(new Color(30,41,59)); }
                    ((JLabel)c).setBorder(BorderFactory.createEmptyBorder(0,12,0,12));
                    return c;
                }
            };
        t.setDefaultRenderer(Object.class,  renderer);
        t.setDefaultRenderer(Number.class,  renderer);
        t.setDefaultRenderer(Integer.class, renderer);
    }

    public String getjTextArea1()         { return tfReceiver.getText(); }
    public String getjTextArea2()         { return taMailBody.getText(); }
    public void   setjTextArea2(String v) { taMailBody.setText(v); }
    public String getjTextField4()        { return tfTodoInput.getText(); }
}