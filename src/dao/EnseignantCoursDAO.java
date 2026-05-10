package dao; // Nouveau package propre

import entites.Enseignant;
import entites.Matiere;
import java.sql.ResultSet;

/**
 * Interface EnseignantCoursDAO : Définit les opérations de gestion 
 * des affectations entre enseignants et cours.
 */
public interface EnseignantCoursDAO {
    
    // --- Lecture des données ---
    
    // Récupère la liste globale des cours avec les noms des enseignants
    ResultSet getTousLesCoursEtEnseignants();
    
    // Récupère les matières spécifiques enseignées par un professeur (via son ID)
    ResultSet getMoncours(int idEnseignant);
    
    // Récupère la liste des étudiants inscrits aux cours d'un enseignant spécifique
    ResultSet getEtudiantsMemeCours(int idEnseignant);
    
    // Récupère les affectations pour une matière précise
    ResultSet getAffectationsParMatiere(Matiere mat);

    // --- Actions de modification ---
    
    // Lie un enseignant à une matière dans la base de données
    void affecterEnseignantACours(Enseignant ens, Matiere mat);
    
    // Supprime le lien entre un enseignant et une matière
    void supprimerAffectation(Enseignant ens, Matiere mat);
}