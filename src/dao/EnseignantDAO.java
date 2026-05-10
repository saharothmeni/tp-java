package dao; // Package simplifié

import java.sql.ResultSet;
import java.sql.SQLException;
import entites.Enseignant; // Import de l'entité propre

/**
 * Interface EnseignantDAO : Définit le contrat pour la gestion 
 * des profils enseignants dans la base de données.
 */
public interface EnseignantDAO {

    // --- Lecture ---
    
    // Récupère la liste de tous les enseignants inscrits
    ResultSet getAllEnseignant();
    
    // Récupère un enseignant spécifique via son objet entité
    ResultSet getEnseignant(Enseignant ens);
    
    // Récupère un enseignant spécifique via son identifiant unique (ID)
    ResultSet getEnseignant(int id);

    // --- Écriture ---
    
    // Ajoute un nouveau profil enseignant
    void ajoutEnseignant(Enseignant ens) throws SQLException;

    // --- Suppression & Modification ---
    
    // Supprime un enseignant de la base de données
    void supprimerEnseignant(Enseignant ens);
    
    // Modifie le nom d'un enseignant existant
    void updateEnseignant(Enseignant ens, String nouveauNom);
}