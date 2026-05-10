package model;

import java.sql.SQLException;
import javax.swing.JTable;

import Control.datacontrol.ControlData;
import Control.inputcontrol.VerifInput;
import Control.Exceptions.EmptyFieldException;
import Control.Exceptions.GenderInvalidException;
import Control.Exceptions.IDInvalidException;
import dao.*;

public class GestionnaireDonneesAdmin {

    // ═══════════════════════════════════════════════════
    // TABLES
    // ═══════════════════════════════════════════════════
    private JTable tableEtudiants;
    private JTable tableEnseignants;
    private JTable tableMatieres;
    private JTable tableAffectations;
    private JTable tableInscriptions;

    // ═══════════════════════════════════════════════════
    // CHAMPS ETUDIANT
    // ═══════════════════════════════════════════════════
    private String etudiantId;
    private String etudiantNom;
    private String etudiantGenre;
    private String etudiantPassword; // mot de passe = id par défaut

    // ═══════════════════════════════════════════════════
    // CHAMPS ENSEIGNANT
    // ═══════════════════════════════════════════════════
    private String enseignantId;
    private String enseignantNom;
    private String enseignantGenre;
    private String enseignantSpecialite; // colonne specialite dans BDD
    private String enseignantGrade;      // colonne grade dans BDD

    // ═══════════════════════════════════════════════════
    // CHAMPS MATIERE
    // ═══════════════════════════════════════════════════
    private String matiereId;
    private String matiereNom;
    private String matiereDesc;
    private String matiereVol;
    private String matiereCoeff;

    // ═══════════════════════════════════════════════════
    // CHAMPS RELATIONS
    // ═══════════════════════════════════════════════════
    private String idEnseignantLiaison;
    private String idMatiereLiaison;
    private String idEtudiantInscription;
    private String idMatiereInscription;

    // ═══════════════════════════════════════════════════
    // RAFRAICHIR TOUTES LES TABLES
    // ═══════════════════════════════════════════════════
    public void rafraichirToutesLesDonnees() {
        ControlData.serveData(tableEtudiants,
                new EtudiantDAOimpl().getAllEtudiant());
        ControlData.serveData(tableEnseignants,
                new EnseignantDAOimpl().getAllEnseignant());
        ControlData.serveData(tableMatieres,
                new MatiereDAOimpl().getAllMatiere());
        ControlData.serveData(tableAffectations,
                new EnseignantCoursDAOimpl().getTousLesCoursEtEnseignants());
        ControlData.serveData(tableInscriptions,
                new EtudiantCoursDAOimpl().getAllEtudCours());
    }

    // ═══════════════════════════════════════════════════
    // VALIDATION
    // ═══════════════════════════════════════════════════
    public void validerChamps(String... champs) throws EmptyFieldException {
        for (String c : champs) VerifInput.verifFIELDS(c);
    }

    public void validerID(String id) throws IDInvalidException {
        VerifInput.verifID(id);
    }

    public void validerGenre(String genre) throws GenderInvalidException {
        VerifInput.verifGender(genre);
    }

    // ═══════════════════════════════════════════════════
    // ETUDIANT — CRUD
    // ═══════════════════════════════════════════════════
    public void insererEtudiant() throws SQLException {
        // insertDataETD attend : id, nom, genre, password
        ControlData.insertDataETD(
                etudiantId,
                etudiantNom,
                etudiantGenre,
                etudiantPassword
        );
        rafraichirToutesLesDonnees();
    }

    public void modifierEtudiant() {
        ControlData.updateDataETD(etudiantId, etudiantNom);
        rafraichirToutesLesDonnees();
    }

    public void supprimerEtudiant() {
        ControlData.removeDataETD(etudiantId);
        rafraichirToutesLesDonnees();
    }

    // ═══════════════════════════════════════════════════
    // ENSEIGNANT — CRUD
    // ═══════════════════════════════════════════════════
    public void insererEnseignant() throws SQLException {
        // insertDataENS attend : id, nom, genre, specialite, grade
        ControlData.insertDataENS(
                enseignantId,
                enseignantNom,
                enseignantGenre,
                enseignantSpecialite,
                enseignantGrade
        );
        rafraichirToutesLesDonnees();
    }

    public void modifierEnseignant() {
        ControlData.updateDataENS(enseignantId, enseignantNom);
        rafraichirToutesLesDonnees();
    }

    public void supprimerEnseignant() {
        ControlData.removeDataENS(enseignantId);
        rafraichirToutesLesDonnees();
    }

    // ═══════════════════════════════════════════════════
    // MATIERE — CRUD
    // ═══════════════════════════════════════════════════
    public void insererMatiere() throws SQLException {
        // insertDataMatiere attend : id, nom, desc, vol, coeff
        ControlData.insertDataMatiere(
                matiereId,
                matiereNom,
                matiereDesc,
                matiereVol,
                matiereCoeff
        );
        rafraichirToutesLesDonnees();
    }

    public void modifierMatiere() {
        // updateDataMatiere attend : id, nom, desc, vol, coeff
        ControlData.updateDataMatiere(
                matiereId,
                matiereNom,
                matiereDesc,
                matiereVol,
                matiereCoeff
        );
        rafraichirToutesLesDonnees();
    }

    public void supprimerMatiere() {
        // removeDataMatiere attend String... → on passe juste l'id
        ControlData.removeDataMatiere(matiereId);
        rafraichirToutesLesDonnees();
    }

    // ═══════════════════════════════════════════════════
    // RELATIONS — Enseignant ↔ Matière
    // ═══════════════════════════════════════════════════
    public void affecterEnseignantAMatiere() {
        ControlData.insertDataENSCOURS(idEnseignantLiaison, idMatiereLiaison);
        rafraichirToutesLesDonnees();
    }

    public void supprimerAffectation() {
        ControlData.removeDataENSCOURS(idEnseignantLiaison, idMatiereLiaison);
        rafraichirToutesLesDonnees();
    }

    // ═══════════════════════════════════════════════════
    // RELATIONS — Etudiant ↔ Matière
    // ═══════════════════════════════════════════════════
    public void inscrireEtudiantAMatiere() {
        ControlData.insertDataETUDCOURS(idEtudiantInscription, idMatiereInscription);
        rafraichirToutesLesDonnees();
    }

    public void supprimerInscription() {
        ControlData.removeDataETUDCOURS(idEtudiantInscription, idMatiereInscription);
        rafraichirToutesLesDonnees();
    }

    // ═══════════════════════════════════════════════════
    // GETTERS / SETTERS TABLES
    // ═══════════════════════════════════════════════════
    public void setTableEtudiants(JTable t)    { this.tableEtudiants = t; }
    public void setTableEnseignants(JTable t)  { this.tableEnseignants = t; }
    public void setTableMatieres(JTable t)     { this.tableMatieres = t; }
    public void setTableAffectations(JTable t) { this.tableAffectations = t; }
    public void setTableInscriptions(JTable t) { this.tableInscriptions = t; }

    public JTable getTableEtudiants()    { return tableEtudiants; }
    public JTable getTableEnseignants()  { return tableEnseignants; }
    public JTable getTableMatieres()     { return tableMatieres; }
    public JTable getTableAffectations() { return tableAffectations; }
    public JTable getTableInscriptions() { return tableInscriptions; }

    // ═══════════════════════════════════════════════════
    // GETTERS / SETTERS ETUDIANT
    // ═══════════════════════════════════════════════════
    public void setEtudiantId(String v)       { this.etudiantId = v; }
    public void setEtudiantNom(String v)      { this.etudiantNom = v; }
    public void setEtudiantGenre(String v)    { this.etudiantGenre = v; }
    public void setEtudiantPassword(String v) { this.etudiantPassword = v; }

    public String getEtudiantId()       { return etudiantId; }
    public String getEtudiantNom()      { return etudiantNom; }
    public String getEtudiantGenre()    { return etudiantGenre; }
    public String getEtudiantPassword() { return etudiantPassword; }

    // ═══════════════════════════════════════════════════
    // GETTERS / SETTERS ENSEIGNANT
    // ═══════════════════════════════════════════════════
    public void setEnseignantId(String v)         { this.enseignantId = v; }
    public void setEnseignantNom(String v)        { this.enseignantNom = v; }
    public void setEnseignantGenre(String v)      { this.enseignantGenre = v; }
    public void setEnseignantSpecialite(String v) { this.enseignantSpecialite = v; }
    public void setEnseignantGrade(String v)      { this.enseignantGrade = v; }

    public String getEnseignantId()         { return enseignantId; }
    public String getEnseignantNom()        { return enseignantNom; }
    public String getEnseignantGenre()      { return enseignantGenre; }
    public String getEnseignantSpecialite() { return enseignantSpecialite; }
    public String getEnseignantGrade()      { return enseignantGrade; }

    // ═══════════════════════════════════════════════════
    // GETTERS / SETTERS MATIERE
    // ═══════════════════════════════════════════════════
    public void setMatiereId(String v)    { this.matiereId = v; }
    public void setMatiereNom(String v)   { this.matiereNom = v; }
    public void setMatiereDesc(String v)  { this.matiereDesc = v; }
    public void setMatiereVol(String v)   { this.matiereVol = v; }
    public void setMatiereCoeff(String v) { this.matiereCoeff = v; }

    public String getMatiereId()    { return matiereId; }
    public String getMatiereNom()   { return matiereNom; }
    public String getMatiereDesc()  { return matiereDesc; }
    public String getMatiereVol()   { return matiereVol; }
    public String getMatiereCoeff() { return matiereCoeff; }

    // ═══════════════════════════════════════════════════
    // GETTERS / SETTERS RELATIONS
    // ═══════════════════════════════════════════════════
    public void setIdEnseignantLiaison(String v)   { this.idEnseignantLiaison = v; }
    public void setIdMatiereLiaison(String v)       { this.idMatiereLiaison = v; }
    public void setIdEtudiantInscription(String v)  { this.idEtudiantInscription = v; }
    public void setIdMatiereInscription(String v)   { this.idMatiereInscription = v; }

    public String getIdEnseignantLiaison()  { return idEnseignantLiaison; }
    public String getIdMatiereLiaison()     { return idMatiereLiaison; }
    public String getIdEtudiantInscription(){ return idEtudiantInscription; }
    public String getIdMatiereInscription() { return idMatiereInscription; }
}