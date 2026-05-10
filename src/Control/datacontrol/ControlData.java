package Control.datacontrol; // Le package mis à jour selon votre projet
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// DAO
import dao.*;

// ENTITES
import entites.*;

public class ControlData {

    // =========================================================
    // TABLE MOYENNE
    // =========================================================
    public static void serveMoy(JTable table, HashMap<String, String> mp) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Map.Entry<String, String> entry : mp.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }

    // =========================================================
    // TABLE GENERIQUE
    // =========================================================
    public static void serveData(JTable table, ResultSet rs) {
        if (rs == null) return;

        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            int col = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Object[] row = new Object[col];
                for (int i = 1; i <= col; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur chargement données");
            ex.printStackTrace();
        } finally {
            try { rs.close(); } catch (Exception e) {}
        }
    }

    // =========================================================
    // SECURE TABLE (si utilisée dans ton projet)
    // =========================================================
    public static void secureServeData(JTable table, ResultSet rs, int id) {
        serveData(table, rs); // tu peux personnaliser si besoin
    }

    // =========================================================
    // ETUDIANT
    // =========================================================
    public static void insertDataETD(String... i) throws SQLException {

        Etudiant etd = new Etudiant(
                Integer.parseInt(i[0]),
                i[1],
                i[2].charAt(0)
        );

        new EtudiantDAOimpl().ajoutEtudiant(etd);

        InsertAccount.creationAccount(
                Integer.parseInt(i[0]),
                i[3],
                1
        );
    }

    public static void updateDataETD(String... i) {
        Etudiant etd = new Etudiant(Integer.parseInt(i[0]));
        new EtudiantDAOimpl().updateEtudiant(etd, i[1]);
    }

    public static void removeDataETD(String id) {
        new EtudiantDAOimpl().supprimerEtudiant(
                new Etudiant(Integer.parseInt(id))
        );
    }

    // =========================================================
    // ENSEIGNANT
    // =========================================================
    public static void insertDataENS(String... i) throws SQLException {

        Enseignant ens = new Enseignant(
                Integer.parseInt(i[0]),
                i[1],
                i[2].charAt(0),
                i[3],
                i[4]
        );

        InsertAccount.creationOnInsert(ens, 0);
        new EnseignantDAOimpl().ajoutEnseignant(ens);
    }

    public static void updateDataENS(String... i) {
        Enseignant ens = new Enseignant(Integer.parseInt(i[0]));
        new EnseignantDAOimpl().updateEnseignant(ens, i[1]);
    }

    public static void removeDataENS(String id) {
        new EnseignantDAOimpl().supprimerEnseignant(
                new Enseignant(Integer.parseInt(id))
        );
    }

    // =========================================================
    // MATIERE
    // =========================================================
    public static void updateDataMatiere(String... i) {

        Matiere mat = new Matiere(
                Integer.parseInt(i[0]),
                i[1],
                i[2],
                Integer.parseInt(i[3]),
                Double.parseDouble(i[4])
        );

        new MatiereDAOimpl().updateMatiere(mat);
    }

    public static void removeDataMatiere(String... i) {
        new MatiereDAOimpl().supprimerMatiere(
                new Matiere(Integer.parseInt(i[0]))
        );
    }

    // =========================================================
    // ENSEIGNANT COURS
    // =========================================================
    public static void insertDataENSCOURS(String... i) {

        try {
            Enseignant ens = new Enseignant(Integer.parseInt(i[0]));
            Matiere mat = new Matiere(Integer.parseInt(i[1]));

            new EnseignantCoursDAOimpl().affecterEnseignantACours(ens, mat);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeDataENSCOURS(String... i) {
        new EnseignantCoursDAOimpl().supprimerAffectation(
                new Enseignant(Integer.parseInt(i[0])),
                new Matiere(Integer.parseInt(i[1]))
        );
    }

    // =========================================================
    // ETUDIANT COURS
    // =========================================================
    public static void insertDataETUDCOURS(String... i) {

        new EtudiantCoursDAOimpl().ajoutEtudCours(
                new Etudiant(Integer.parseInt(i[0])),
                new Matiere(Integer.parseInt(i[1]))
        );
    }

    public static void removeDataETUDCOURS(String... i) {

        new EtudiantCoursDAOimpl().supprimerCours(
                new Etudiant(Integer.parseInt(i[0])),
                new Matiere(Integer.parseInt(i[1]))
        );
    }

    // =========================================================
    // TACHES (CORRIGÉ IMPORTANT)
    // =========================================================
    public static void insertDataTache(String... i) throws SQLException {

        Tache t = new Tache(
                null,
                Integer.parseInt(i[1]),
                i[2],
                i[3]
        );

        new TacheDAOimpl().ajouterTache(t); // ✅ correction ici
    }

    public static void removeDataTache(String... i) throws SQLException {

        new TacheDAOimpl().supprimerTache(
                new Tache(Integer.parseInt(i[0]))
        );
    }

    // =========================================================
    // MESSAGERIE
    // =========================================================
    public static void sendMessage(String... i) throws SQLException {

        Mail ml = new Mail(i[0], i[1], i[2], i[3]);

        new MessagerieDAOimpl().envoyerMessage(ml);
    }
    public static void insertDataMatiere(String... i) throws SQLException {

        Matiere mat = new Matiere(
                Integer.parseInt(i[0]),
                i[1],
                i[2],
                Integer.parseInt(i[3]),
                Double.parseDouble(i[4])
        );

        new MatiereDAOimpl().ajoutMatiere(mat);
    }
}