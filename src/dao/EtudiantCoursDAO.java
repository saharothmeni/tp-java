package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import entites.Etudiant;
import entites.Matiere;

public interface EtudiantCoursDAO {

    // Lecture
    ResultSet getAllEtudCours();
    ResultSet getEtudCours(Etudiant etd);
    ResultSet getEtudCours(int idEtudiant);

    // Ecriture
    void ajoutEtudCours(Etudiant etd, Matiere mat);
    void supprimerCours(Etudiant etd, Matiere mat);

    // Modification
    void updateEtudiantC(int idEtudiant, Etudiant etd);
    void updateEtudiantC(int idEtudiant, Matiere mat);

    // ✅ Ajout obligatoire
    void updateNote(int idMat, int idEtudiant, double note) throws SQLException;
}