package entites;


public class Etudiant {
    private int id;
    private String name;
    private char gender;
public Etudiant(int id){this.id=id;}
public Etudiant(int id,String name,char gender){
this.id=id;
this.name=name;
this.gender=gender;
}
public int getID(){return id;}
public void setID(int id){this.id=id;}
public String getName(){return name;}
public void setName(String name){this.name=name;}
public char getGender(){return gender;}
public void setGender(char gender){this.gender=gender;}
@Override
public String toString(){
return "EtudiantID:"+id+"EtudiantName:"+name+"EtudiantGender"+gender;
    }
}

