package views;

import view.frame.LoginFrame;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author SGI
 */
public class LoginView {
private LoginFrame lf;
public LoginView(){
lf=new LoginFrame();
lf.setVisible(true);
lf.pack();
lf.setResizable(false);
lf.setLocation(280,120);
}
public void Close(){ lf.dispose();}
public String GetID(){return lf.getID();}
public char[] GetPass(){return lf.getPassword();}
public String GetCapt(){return lf.getCapt();}
public String GetOrCapt(){return lf.getPOrCapt();}
public void addButtonListener(ActionListener e){
lf.getButton().addActionListener(e);
}
public void addButtonListener2(ActionListener e){
lf.getButton2().addActionListener(e);
}
public void displayMessgae(String m){
JOptionPane.showMessageDialog(lf, m);
}
}
