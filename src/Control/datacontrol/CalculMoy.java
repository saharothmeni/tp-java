package Control.datacontrol;

import java.sql.*;
import java.text.DecimalFormat;

import java.util.LinkedHashMap;
import java.util.Map;
import entites.Etudiant;
//Methode pour parcourir a un etudiant donnée ces notes et faire calculer son moyenne//
public class CalculMoy {
    public static Map<String,String> calcMoy(Etudiant etd) throws SQLException{
    Connection con=Singleton_Dbconnect.getInstance();
    PreparedStatement pstmt1=con.prepareStatement("select mat.mat_name,note,coeff from etudcours ec,matiere mat where ec.mat_id=mat.mat_id and ec.id=?");
    pstmt1.setInt(1,etd.getID());
    ResultSet rs=pstmt1.executeQuery();
    Map<String,String> mp=new LinkedHashMap<>();
    Double sum=0.0;
    Double sum_coeff=0.0;
    while(rs.next()){
    sum+=rs.getDouble("note")*rs.getDouble("coeff");
    sum_coeff+=rs.getDouble("coeff");
    mp.put(rs.getString("mat_name"),String.valueOf(rs.getDouble("note")));
    }
    Double moy=sum/sum_coeff;
    String mention="";
    DecimalFormat df=new DecimalFormat("#.##");
    String formatted=df.format(moy);
    if(moy<10){
    mention="Don't give up <3";
    }
    else if (moy>=10 && moy<12){
    mention="Passable";
    }
    else if (moy>=12 && moy<14){
    mention="Assez bien";
    }
    else if (moy>=14 && moy<16){
    mention="Bien";
    }
    else{
    mention="Trés bien";
    }
    PreparedStatement pstmt2=con.prepareStatement("insert into evaluation values(?,?,?)");
    pstmt2.setInt(1,etd.getID());
    pstmt2.setDouble(2,moy);
    pstmt2.setString(3,mention);
    pstmt2.executeUpdate();
    mp.put("Moyenne",formatted);
    mp.put("Mention", mention);
    return mp;
    }
    
}

