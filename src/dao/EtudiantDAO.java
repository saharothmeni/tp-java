package dao; // Package simplifié pour plus de clarté

import java.sql.ResultSet;
import java.sql.SQLException;
import entites.Etudiant; // Import de l'entité Etudiant

/**
 * Interface EtudiantDAO : Définit le contrat pour la gestion 
 * des profils étudiants dans la base de données.
 */
public interface EtudiantDAO {

    // --- Lecture des données ---
    
    // Récupère la liste complète de tous les étudiants
    ResultSet getAllEtudiant();
    
    // Récupère les informations d'un étudiant spécifique (objet complet)
    ResultSet getEtudiant(Etudiant etd);
    
    // Récupère les informations d'un étudiant spécifique (via son ID unique)
    ResultSet getEtudiant(int id);

    // --- Actions de modification ---
    
    // Insère un nouvel étudiant dans la base de données
    void ajoutEtudiant(Etudiant etd) throws SQLException;

    // Supprime le profil d'un étudiant
    void supprimerEtudiant(Etudiant etd);
    
    // Met à jour le nom d'un étudiant existant
    void updateEtudiant(Etudiant etd, String nouveauNom);
}