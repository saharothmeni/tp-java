package dao; // Nouveau package propre

import java.sql.ResultSet;
import java.sql.SQLException;
import entites.Mail; // Import de l'entité Mail (ou Message)

/**
 * Interface MessagerieDAO : Définit les services de communication 
 * interne entre les utilisateurs (Étudiants, Enseignants, Admin).
 */
public interface MessagerieDAO {

    /**
     * Récupère la boîte de réception d'un utilisateur.
     * @param idDestinataire L'identifiant de l'utilisateur connecté.
     * @return Un ResultSet contenant les messages reçus.
     */
    ResultSet getMesMessages(int idDestinataire);

    /**
     * Enregistre un nouveau message dans la base de données.
     * @param m L'objet Mail contenant l'expéditeur, le destinataire, le contenu et la date.
     * @throws SQLException En cas d'erreur lors de l'insertion.
     */
    void envoyerMessage(Mail m) throws SQLException;
}
