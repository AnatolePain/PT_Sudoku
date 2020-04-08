import javax.swing.*;
/**
 * La classe <code>WinnerVue</code> affiche une fêtre popup de type JOptionPane , pour 
 * annoncé la victoire et afficher le temps 
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class WinnerVue extends JOptionPane {

    TimerModel timerModel;

    /**
     * Constructeur affichant le message informatif  
     */
    public WinnerVue(TimerModel tm) {
        
        timerModel = tm;
        this.setLocation(10,10);
        showMessageDialog(null, "Temps:  "+tm.getString(), "  Victoire ! ", INFORMATION_MESSAGE);

    }

}