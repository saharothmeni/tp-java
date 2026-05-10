package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Control.datacontrol.Singleton_Dbconnect;
import entites.Mail;

public class MessagerieDAOimpl implements MessagerieDAO {

    // =========================
    // AFFICHER MESSAGES
    // =========================
    @Override
    public ResultSet getMesMessages(int idDestinataire) {

        try {
            String sql = "SELECT sender_id, message, date_msg FROM mail WHERE reciever_id = ?";

            PreparedStatement ps =
                    Singleton_Dbconnect.getInstance().prepareStatement(sql);

            ps.setInt(1, idDestinataire);

            return ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // =========================
    // ENVOYER MESSAGE (AES)
    // =========================
    @Override
    public void envoyerMessage(Mail m) throws SQLException {

        String sql = "INSERT INTO mail (sender_id, reciever_id, message, date_msg) VALUES (?, ?, ?, ?)";

        PreparedStatement ps =
                Singleton_Dbconnect.getInstance().prepareStatement(sql);

        ps.setInt(1, Integer.parseInt(m.getSender_id()));
        ps.setInt(2, Integer.parseInt(m.getReciever_id()));

        try {
            // 🔐 CHIFFREMENT AES
            String msgCrypt =
                    Control.security.AES128.encrypt(m.getSender_id(), m.getMessage());

            String dateCrypt =
                    Control.security.AES128.encrypt(m.getSender_id(), m.getDate_msg());

            ps.setString(3, msgCrypt);
            ps.setString(4, dateCrypt);

            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(MessagerieDAOimpl.class.getName())
                    .log(Level.SEVERE, "Erreur chiffrement AES", ex);
        }
    }
}