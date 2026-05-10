package entites;



public class AffectationEnseignant {
    
    private Enseignant enseignant;
    private Matiere matiere;
    private String semestre; // Pour préciser S1 ou S2
    private int chargeHoraire; // Nombre d'heures prévues pour cette affectation

    // Constructeur complet
    public AffectationEnseignant(Enseignant enseignant, Matiere matiere, String semestre, int chargeHoraire) {
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.semestre = semestre;
        this.chargeHoraire = chargeHoraire;
    }

    // Getters et Setters
    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getChargeHoraire() {
        return chargeHoraire;
    }

    public void setChargeHoraire(int chargeHoraire) {
        this.chargeHoraire = chargeHoraire;
    }

    /**
     * Retourne une description lisible de l'affectation.
     */
    @Override
    public String toString() {
        return "Affectation : M/Mme " + enseignant.getNom() + 
               " | Module : " + matiere.getNom() + 
               " | Semestre : " + semestre + 
               " (" + chargeHoraire + "h)";
    }
}