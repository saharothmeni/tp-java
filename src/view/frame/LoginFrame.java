package view.frame;



import Control.tools.Generator;
import Control.datacontrol.VerifLogin;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import javax.swing.*;
import view.loading_screen.AnimatedWavesPanel;

public class LoginFrame extends javax.swing.JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new LoginFrame().setVisible(true);
        });
    }

    private static final long serialVersionUID = 1L;

    private JPanel jPanelMain, jPanelLeft, jPanelRight;
    private JButton jButton1, jButton2;
    private JLabel jLabel1, jLabel3, jLabel4, jLabel5, jLabel7, jLabel8, jLabel10;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1, jTextField2;

    private int alpha = 0;

    private Timer t = new Timer(15, e -> {
        if (alpha < 255) {
            alpha += 5;
            if (jLabel1 != null)
                jLabel1.setForeground(new Color(102, 102, 102, alpha));
        } else {
            ((Timer) e.getSource()).stop();
        }
    });

    public LoginFrame() {
        initComponents();
        t.start();
        setLocationRelativeTo(null);
    }

    // ─── Getters pour le Contrôleur ──────────────────────────────────────────
    public String getID()       { return jTextField2.getText(); }
    public char[] getPassword() { return jPasswordField1.getPassword(); }
    public String getPOrCapt()  { return jLabel7.getText(); }
    public String getCapt()     { return jTextField1.getText(); }
    public JButton getButton()  { return jButton1; }
    public JButton getButton2() { return jButton2; }

    private void initComponents() {
        jPanelMain  = new JPanel(new GridLayout(1, 2));
        jPanelLeft  = new AnimatedWavesPanel();
        jPanelRight = new JPanel();

        jLabel1  = new JLabel("SIGN-IN");
        jLabel3  = new JLabel("Mot de passe :");
        jLabel4  = new JLabel("Identifiant :");
        jLabel5  = new JLabel("Pas encore de compte ?");
        jLabel7  = new JLabel(Generator.randStr());
        jLabel8  = new JLabel("🔄");
        jLabel10 = new JLabel();

        jTextField1     = new JTextField();
        jTextField2     = new JTextField();
        jPasswordField1 = new JPasswordField();

        jButton1 = new JButton("Se connecter");
        jButton2 = new JButton("S'inscrire");

        // ─── Configuration fenêtre ───────────────────────────────────────────
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connexion - Gestion Scolaire");
        setResizable(false);
        setSize(900, 550);

        // ─── Panneau gauche ──────────────────────────────────────────────────
        jPanelLeft.setLayout(new BorderLayout());
        String imagePath = "C:/Users/user/Desktop/Sahar Othmeni/gestion_ecole/image/computer-security-with-login-password-padlock.jpg";
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(450, 550, Image.SCALE_SMOOTH);
            jLabel10.setIcon(new ImageIcon(img));
        } else {
            jLabel10.setText("Image non trouvée");
            jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
        }
        jPanelLeft.add(jLabel10, BorderLayout.CENTER);

        // ─── Panneau droit ───────────────────────────────────────────────────
        jPanelRight.setBackground(Color.WHITE);
        jPanelRight.setLayout(null);

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        jLabel1.setForeground(new Color(102, 102, 102, 0));
        jLabel1.setBounds(50, 40, 300, 40);
        jPanelRight.add(jLabel1);

        jLabel4.setBounds(50, 110, 300, 20);
        jPanelRight.add(jLabel4);
        jTextField2.setBounds(50, 130, 320, 35);
        jPanelRight.add(jTextField2);

        jLabel3.setBounds(50, 180, 300, 20);
        jPanelRight.add(jLabel3);
        jPasswordField1.setBounds(50, 205, 320, 35);
        jPanelRight.add(jPasswordField1);

        jLabel7.setFont(new Font("Monospaced", Font.BOLD, 18));
        jLabel7.setBounds(50, 255, 100, 30);
        jPanelRight.add(jLabel7);

        jLabel8.setBounds(160, 255, 30, 30);
        jLabel8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7.setText(Generator.randStr());
            }
        });
        jPanelRight.add(jLabel8);

        jTextField1.setBounds(50, 290, 150, 35);
        jPanelRight.add(jTextField1);

        jButton1.setBounds(50, 345, 180, 40);
        jButton1.setBackground(new Color(70, 130, 180));
        jButton1.setForeground(Color.WHITE);
        jPanelRight.add(jButton1);

        jLabel5.setBounds(50, 405, 160, 20);
        jPanelRight.add(jLabel5);

        jButton2.setBounds(200, 405, 100, 20);
        jButton2.setForeground(Color.RED);
        jButton2.setContentAreaFilled(false);
        jButton2.setBorderPainted(false);
        jPanelRight.add(jButton2);

        addLanguageButtons();

        // ─── Action listeners ────────────────────────────────────────────────
        jButton1.addActionListener(e -> handleLogin());
        jPasswordField1.addActionListener(e -> handleLogin());

        jPanelMain.add(jPanelLeft);
        jPanelMain.add(jPanelRight);
        this.add(jPanelMain);
    }

    // ════════════════════════════════════════════════════════════════════════
    // LOGIQUE DE CONNEXION — basée sur VerifLogin.verifcon()
    // Retour :  1 → Étudiant | 2 → Enseignant | 3 → Admin | -1 → Refus
    // ════════════════════════════════════════════════════════════════════════
    private void handleLogin() {
        String idText  = jTextField2.getText().trim();
        String password = new String(jPasswordField1.getPassword()).trim();
        String captchaExpected = jLabel7.getText().trim();
        String captchaEntered  = jTextField1.getText().trim();

        // ── 1. Validation champs vides ───────────────────────────────────────
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez saisir votre identifiant.",
                "Champ manquant", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez saisir votre mot de passe.",
                "Champ manquant", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ── 2. Validation format ID (doit être un entier) ────────────────────
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "L'identifiant doit être un nombre entier.",
                "Format invalide", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── 3. Validation captcha ─────────────────────────────────────────────
        if (!captchaEntered.equalsIgnoreCase(captchaExpected)) {
            JOptionPane.showMessageDialog(this,
                "Code de vérification incorrect. Veuillez réessayer.",
                "Captcha invalide", JOptionPane.ERROR_MESSAGE);
            jLabel7.setText(Generator.randStr());
            jTextField1.setText("");
            return;
        }

        // ── 4. Vérification en base via VerifLogin ───────────────────────────
        try {
            Control.datacontrol.VerifLogin verifLogin = new Control.datacontrol.VerifLogin();
            int role = verifLogin.verifcon(id, password);

            switch (role) {

                case 1: {
                    // ── Étudiant ─────────────────────────────────────────────
                    StudentPanelFrame studentPanel = new StudentPanelFrame();
                    studentPanel.setjLabel26(idText);
                    studentPanel.setjLabel3(idText);
                    studentPanel.setVisible(true);
                    this.dispose();
                    break;
                }

                case 2: {
                    // ── Enseignant ───────────────────────────────────────────
                    TeacherPanelFrame teacherPanel = new TeacherPanelFrame();
                    teacherPanel.setTeacherID(idText);
                    teacherPanel.setVisible(true);
                    this.dispose();
                    break;
                }

                case 3: {
                    // ── Admin ────────────────────────────────────────────────
                    // AdminPanelFrame adminPanel = new AdminPanelFrame();
                    // adminPanel.setVisible(true);
                    // this.dispose();
                    JOptionPane.showMessageDialog(this,
                        "Panneau Admin non encore implémenté.",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                case -1:
                default:
                    // ── Identifiant ou mot de passe incorrect ────────────────
                    JOptionPane.showMessageDialog(this,
                        "Identifiant ou mot de passe incorrect.",
                        "Accès refusé", JOptionPane.ERROR_MESSAGE);
                    // Sécurité : regénère le captcha après échec
                    jLabel7.setText(Generator.randStr());
                    jTextField1.setText("");
                    break;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Erreur de connexion à la base de données :\n" + ex.getMessage(),
                "Erreur BDD", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ════════════════════════════════════════════════════════════════════════
    // Boutons de langue AR / FR / EN
    // ════════════════════════════════════════════════════════════════════════
    private void addLanguageButtons() {
        JButton b1 = new JButton("AR");
        JButton b2 = new JButton("FR");
        JButton b3 = new JButton("EN");

        b1.setBounds(50,  460, 55, 25);
        b2.setBounds(115, 460, 55, 25);
        b3.setBounds(180, 460, 55, 25);

        b1.addActionListener(e -> {
            jLabel1.setText("تسجيل الدخول");
            jLabel4.setText(": المعرف");
            jLabel3.setText(": كلمة المرور");
            jButton1.setText("دخول");
        });

        b2.addActionListener(e -> {
            jLabel1.setText("SIGN-IN");
            jLabel4.setText("Identifiant :");
            jLabel3.setText("Mot de passe :");
            jButton1.setText("Se connecter");
        });

        b3.addActionListener(e -> {
            jLabel1.setText("SIGN-IN");
            jLabel4.setText("Username:");
            jLabel3.setText("Password:");
            jButton1.setText("Log in");
        });

        jPanelRight.add(b1);
        jPanelRight.add(b2);
        jPanelRight.add(b3);
    }
}