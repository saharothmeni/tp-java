package entites;


public class Tache {
    private Integer id;
    private int idUtilisateur; // Identifiant du propriétaire de la tâche
    private String dateEcheance;
    private String description;

    // Constructeur complet
    public Tache(Integer id, int idUtilisateur, String dateEcheance, String description) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateEcheance = dateEcheance;
        this.description = description;
    }

    // Constructeur par défaut / ID
    public Tache(int id) {
        this.id = id;
    }

    // --- Getters et Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne une vue textuelle de la tâche pour l'interface utilisateur.
     */
    @Override
    public String toString() {
        return "À faire pour le " + dateEcheance + " : " + description;
    }
}
