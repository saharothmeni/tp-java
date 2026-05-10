package dao; // Nouveau package simplifié

import java.sql.ResultSet;
import entites.Matiere;

/**
 * Interface MatiereDAO : Définit le contrat pour la gestion 
 * du catalogue des matières (cours) de l'établissement.
 */
public interface MatiereDAO {

    // --- Consultation ---
    
    // Récupère la liste complète de toutes les matières disponibles
    ResultSet getAllMatiere();
    
    // Récupère les détails d'une matière spécifique (objet complet)
    ResultSet getMatiere(Matiere mat);
    
    /**
     * Récupère l'ID d'une matière en fonction du lien Etudiant-Enseignant.
     * Utile pour identifier un cours partagé.
     */
    Integer getIDMat(int idEtudiant, int idEnseignant);

    // --- Actions de modification ---
    
    // Ajoute une nouvelle matière au catalogue
    void ajoutMatiere(Matiere mat);
    
    // Supprime une matière du catalogue
    void supprimerMatiere(Matiere mat);
    
    // Met à jour les informations d'une matière (Nom, Description, Volume Horaire)
    void updateMatiere(Matiere mat);   
}