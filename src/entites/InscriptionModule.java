package entites;


public class InscriptionModule {
    
    private Etudiant etudiant;
    private Matiere matiere;
    private double noteControle; // Ajout pour la spécificité du projet
    private double noteExamen;   // Ajout pour la spécificité du projet

    // Constructeur pour l'inscription initiale
    public InscriptionModule(Etudiant etudiant, Matiere matiere) {
        this.etudiant = etudiant;
        this.matiere = matiere;
    }

    // Getters et Setters
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public double getNoteControle() { return noteControle; }
    public void setNoteControle(double note) { this.noteControle = note; }

    public double getNoteExamen() { return noteExamen; }
    public void setNoteExamen(double note) { this.noteExamen = note; }

    public double calculerMoyenne() {
        return (noteControle * 0.4) + (noteExamen * 0.6); // Exemple de pondération
    }

    @Override
    public String toString() {
        return "Inscription : " + etudiant.getName() + " suit le module " + matiere.getNom();
    }
}