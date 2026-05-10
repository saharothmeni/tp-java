package Control.tools; // Mis à jour pour correspondre à src/Control/tools

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette classe fournit des méthodes pour vérifier la force d'un mot de passe.
 * @author SGI
 */
public class PasswordStrength {

    // Listes de caractères pour la vérification
    private static final String[] A = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z".split(",");
    private static final String[] N = "0,1,2,3,4,5,6,7,8,9".split(",");
    private static final String[] S = "/,*,+,?,.,;,:,!".split(",");
    
    private static final ArrayList<String> ALPHABETS = new ArrayList<>(Arrays.asList(A));
    private static final ArrayList<String> NUMERICS = new ArrayList<>(Arrays.asList(N));
    private static final ArrayList<String> SYMBOLS = new ArrayList<>(Arrays.asList(S));

    /**
     * Évalue la force d'un mot de passe sur une échelle de 0 à 9.
     * @param pwd Le mot de passe à tester.
     * @return Un score de force (strength * 3).
     */
    public static int passwordChecker(String pwd) {
        int sum_alpha = 0;
        int sum_nums = 0;
        int sum_symbols = 0;
        int strength = 0;

        if (pwd == null) return 0;

        for (int i = 0; i < pwd.length(); i++) {
            String character = String.valueOf(pwd.charAt(i));
            if (ALPHABETS.contains(character)) {
                sum_alpha++;
            } else if (NUMERICS.contains(character)) {
                sum_nums++;
            } else if (SYMBOLS.contains(character)) {
                sum_symbols++;
            }
        }

        if (sum_alpha > 5) {
            strength++;
        }
        if (sum_nums > 4) {
            strength++;
        }
        if (sum_symbols > 3) {
            strength++;
        }

        return (strength * 3);
    }
}