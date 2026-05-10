package view.frame;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Enumeration;
import javax.swing.AbstractButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import Control.tools.Generator;
import Control.tools.PasswordStrength;
import view.loading_screen.AnimatedWavesPanel;

public class SignupFrame extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private int x = 0;
    
    // Timer pour l'effet d'apparition du titre (Fade-in)
    private Timer t = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            jLabel1.setForeground(new Color(102, 102, 102, x));
            x++;
            if (x == 255) {
                t.stop();
            }
        }
    });

    public SignupFrame() {
        initComponents();
        t.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
       
        
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new AnimatedWavesPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign-up - Sahar OTHMENI Edition");
        
        // --- CHARGEMENT DE L'IMAGE UNIQUE ---
        String path = "/miniprojet/img/icon.png";
        URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
        } else {
            System.err.println("Erreur : Image introuvable à " + path);
        }

        // --- CONFIGURATION DES PANNEAUX ---
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        // Panneau latéral avec vagues animées
        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);
        
        // Logo
        if (imgURL != null) {
            jLabel14.setIcon(new ImageIcon(imgURL));
        }
        jLabel14.setBounds(110, 50, 150, 150);
        jPanel2.add(jLabel14);
        
        // Titre du panneau latéral
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setText("Bienvenue");
        jLabel2.setBounds(130, 220, 200, 40);
        jPanel2.add(jLabel2);
        
        // Boutons de changement de langue
        jButton4.setText("FR");
        jButton4.setBounds(30, 450, 60, 30);
        jButton4.addActionListener(evt -> languageActionPerformed(evt));
        jPanel2.add(jButton4);
        
        jButton5.setText("EN");
        jButton5.setBounds(100, 450, 60, 30);
        jButton5.addActionListener(evt -> languageActionPerformed(evt));
        jPanel2.add(jButton5);
        
        jButton6.setText("AR");
        jButton6.setBounds(170, 450, 60, 30);
        jButton6.addActionListener(evt -> languageActionPerformed(evt));
        jPanel2.add(jButton6);
        
        jPanel2.setBounds(0, 0, 300, 600);
        jPanel1.add(jPanel2);

        // Titre principal
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setText("SIGN-UP");
        jLabel1.setBounds(420, 30, 260, 60);
        jPanel1.add(jLabel1);
        
        // Section informations utilisateur
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel4.setText("Informations utilisateur:");
        jLabel4.setBounds(420, 100, 250, 30);
        jPanel1.add(jLabel4);
        
        // Identifiant
        jLabel10.setText("Identifiant:");
        jLabel10.setBounds(420, 140, 100, 25);
        jPanel1.add(jLabel10);
        
        jTextField3.setBounds(530, 140, 200, 25);
        jPanel1.add(jTextField3);
        
        // Nom
        jLabel8.setText("Nom:");
        jLabel8.setBounds(420, 175, 100, 25);
        jPanel1.add(jLabel8);
        
        jTextField2.setBounds(530, 175, 200, 25);
        jPanel1.add(jTextField2);
        
        // Sexe
        jLabel9.setText("Sexe:");
        jLabel9.setBounds(420, 210, 100, 25);
        jPanel1.add(jLabel9);
        
        jRadioButton1.setText("Homme");
        jRadioButton1.setBounds(530, 210, 80, 25);
        jRadioButton2.setText("Femme");
        jRadioButton2.setBounds(620, 210, 80, 25);
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        jPanel1.add(jRadioButton1);
        jPanel1.add(jRadioButton2);
        
        // Mot de passe
        jLabel7.setText("Mot de passe:");
        jLabel7.setBounds(420, 245, 100, 25);
        jPanel1.add(jLabel7);
        
        jPasswordField2.setBounds(530, 245, 200, 25);
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                updatePasswordStrength();
            }
        });
        jPanel1.add(jPasswordField2);
        
        // Indicateur force mot de passe
        jLabel13.setText("+-- très faible");
        jLabel13.setForeground(Color.RED);
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jLabel13.setBounds(530, 270, 200, 15);
        jPanel1.add(jLabel13);
        
        // Confirmation mot de passe
        jLabel3.setText("Confirmer:");
        jLabel3.setBounds(420, 290, 100, 25);
        jPanel1.add(jLabel3);
        
        jPasswordField1.setBounds(530, 290, 200, 25);
        jPanel1.add(jPasswordField1);
        
        // Captcha
        jLabel12.setIcon(new ImageIcon(getClass().getResource("/miniprojet/img/captcha.png")));
        jLabel12.setBounds(420, 330, 120, 40);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12);
        
        jLabel11.setText(Generator.randStr());
        jLabel11.setBounds(550, 340, 100, 25);
        jPanel1.add(jLabel11);
        
        jTextField1.setBounds(420, 380, 200, 25);
        jPanel1.add(jTextField1);
        
        jLabel11.setFont(new java.awt.Font("Monospaced", 1, 14));
        jLabel11.setForeground(Color.BLUE);
        
        // Checkbox enseignant
        jCheckBox1.setText("Créer en tant qu'enseignant");
        jCheckBox1.setBounds(420, 420, 250, 25);
        jPanel1.add(jCheckBox1);
        
        // Bouton créer
        jButton1.setText("Créer");
        jButton1.setBackground(new Color(52, 152, 219));
        jButton1.setForeground(Color.WHITE);
        jButton1.setBounds(420, 460, 150, 35);
        jPanel1.add(jButton1);
        
        // Lien connexion
        jLabel5.setText("Déjà inscrit ?");
        jLabel5.setBounds(420, 510, 120, 25);
        jPanel1.add(jLabel5);
        
        jButton2.setText("Connectez-vous");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setForeground(new Color(52, 152, 219));
        jButton2.setBounds(540, 510, 120, 25);
        jPanel1.add(jButton2);
        
        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        
        pack();
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }// </editor-fold>

    /**
     * Méthodes de capture des données pour le contrôleur
     */
    public String getID() { 
        return jTextField3.getText(); 
    }
    
    public String getName() { 
        return jTextField2.getText(); 
    }
    
    public String getGender() {
        String gender = "";
        for (Enumeration<AbstractButton> buttons = buttonGroup1.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                gender = button.getText();
                break;
            }
        }
        return gender;
    }
    
    public char[] getPassword() { 
        return jPasswordField2.getPassword(); 
    }
    
    public char[] getRPassword() { 
        return jPasswordField1.getPassword(); 
    }
    
    public boolean getCheck() { 
        return jCheckBox1.isSelected(); 
    }
    
    public String getOrCapt() { 
        return jLabel11.getText(); 
    }
    
    public String getCapt() { 
        return jTextField1.getText(); 
    }
    
    public JButton getButton() { 
        return jButton1; 
    }
    
    public JButton getButton2() { 
        return jButton2; 
    }

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {                                     
        jLabel11.setText(Generator.randStr());
    }                                    

    private void updatePasswordStrength() {
        String pass = new String(jPasswordField2.getPassword());
        Integer strength = PasswordStrength.passwordChecker(pass);
        if (strength <= 3) {
            jLabel13.setText("+-- très faible");
            jLabel13.setForeground(Color.RED);
        } else if (strength <= 6) {
            jLabel13.setText("++- moyenne");
            jLabel13.setForeground(Color.ORANGE);
        } else {
            jLabel13.setText("+++ forte");
            jLabel13.setForeground(Color.GREEN);
        }
    }

    // Gestion multilingue
    private void languageActionPerformed(java.awt.event.ActionEvent evt) {
        JButton source = (JButton) evt.getSource();
        
        switch(source.getText()) {
            case "FR":
                jLabel1.setText("INSCRIPTION");
                jLabel4.setText("Informations utilisateur:");
                jLabel10.setText("Identifiant:");
                jLabel8.setText("Nom:");
                jLabel9.setText("Sexe:");
                jLabel7.setText("Mot de passe:");
                jLabel3.setText("Confirmer:");
                jCheckBox1.setText("Créer en tant qu'enseignant");
                jButton1.setText("Créer");
                jLabel5.setText("Déjà inscrit ?");
                jButton2.setText("Connectez-vous");
                jLabel2.setText("Bienvenue");
                break;
            case "EN":
                jLabel1.setText("SIGN-UP");
                jLabel4.setText("User Information:");
                jLabel10.setText("ID:");
                jLabel8.setText("Name:");
                jLabel9.setText("Gender:");
                jLabel7.setText("Password:");
                jLabel3.setText("Confirm:");
                jCheckBox1.setText("Create as teacher");
                jButton1.setText("Create");
                jLabel5.setText("Already registered?");
                jButton2.setText("Login");
                jLabel2.setText("Welcome");
                break;
            case "AR":
                jLabel1.setText("تسجيل");
                jLabel4.setText("معلومات المستخدم:");
                jLabel10.setText("المعرف:");
                jLabel8.setText("الاسم:");
                jLabel9.setText("الجنس:");
                jLabel7.setText("كلمة المرور:");
                jLabel3.setText("تأكيد:");
                jCheckBox1.setText("إنشاء كمدرس");
                jButton1.setText("إنشاء");
                jLabel5.setText("مسجل بالفعل؟");
                jButton2.setText("تسجيل الدخول");
                jLabel2.setText("مرحباً");
                break;
        }
    }

    // Variables declaration
    private javax.swing.ButtonGroup buttonGroup1;
   
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
   
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
}