package entites;


public class Enseignant {
    private int id;
    private String nom;
    private char genre;
    private String specialite; // Ajout pour personnaliser (ex: Informatique, Math)
    private String grade;      // Ajout (ex: PES, PA, Maitre de conférences)

    // Constructeur par ID
    public Enseignant(int id) {
        this.id = id;
    }

    // Constructeur complet
    public Enseignant(int id, String nom, char genre, String specialite, String grade) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
        this.specialite = specialite;
        this.grade = grade;
    }
   


	// Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public char getGenre() { return genre; }
    public void setGenre(char genre) { this.genre = genre; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("Enseignant [ID: %d] : %s (%s) - Grade: %s", 
                             id, nom, specialite, grade);
    }
}