package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import entites.Tache; // Importation de votre nouvelle classe

/**
 * Interface TacheDAO : Définit les services de gestion de la To-Do List.
 */
public interface TacheDAO {

    /**
     * Récupère la liste des tâches d'un utilisateur spécifique.
     */
    ResultSet getMesTaches(int idUtilisateur);

    /**
     * Ajoute une nouvelle tâche dans la base de données.
     * Correction : On utilise l'objet Tache.
     */
    void ajouterTache(Tache t) throws SQLException;

    /**
     * Supprime une tâche de la base de données.
     * Correction : On utilise l'objet Tache.
     */
    void supprimerTache(Tache t) throws SQLException;
}