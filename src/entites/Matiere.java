package entites;



/**
 * Représente un module d'enseignement au sein de l'établissement.
 * @author [VOTRE_NOM]
 */
public class Matiere {
    private int id;
    private String nom;
    private String description;
    private int chargeHoraire; // Nouveau nom pour volume_hor
    private double coefficient;

    // Constructeur rapide par ID
    public Matiere(int id) {
        this.id = id;
    }

    // Constructeur complet
    public Matiere(int id, String nom, String description, int chargeHoraire, double coefficient) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.chargeHoraire = chargeHoraire;
        this.coefficient = coefficient;
    }

    // --- Getters et Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChargeHoraire() {
        return chargeHoraire;
    }

    public void setChargeHoraire(int chargeHoraire) {
        this.chargeHoraire = chargeHoraire;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return String.format("Matière : %s (ID: %d) | Volume: %dh | Coeff: %.2f", 
                             nom, id, chargeHoraire, coefficient);
    }
}