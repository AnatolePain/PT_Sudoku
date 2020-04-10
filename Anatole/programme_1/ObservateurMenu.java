import java.awt.event.*;
import javax.swing.JButton;
/**
 * La classe <code>ObservateurMenu</code> est un observateur de type ActionListener 
 * qui permet de gérer le click les differents bouton du menu, LOAD FILE et SAVE
 * 
 * @version 0.1
 * @author Anatole Pain
 */
public class ObservateurMenu implements ActionListener {

    /**
     * Window win permet d'indiquer la fenêtre principale a la class, utile pour les methodes 
     * loadGridModele() et solveGridModel().
     */
    private Window win;

    /**
     * Fait le liens entre la fenêtre principale et la variable.
     * @param w Fenêtre principale
     */
    public ObservateurMenu(Window w){
        this.win = w;
    }

    /**
     *Indique quelle action faire:
     *Si le bouton LOAD FILE est clické alors faire la methode loadGridModel().
     *Si le bouton SAVE  est clické alors faire la methode solveGridModel().
     */
    public void actionPerformed(ActionEvent evenement){

        String bouton = evenement.getActionCommand();

        if(bouton.equals("LOAD FILE")){
            this.win.loadGridModel();
        }else if(bouton.equals("SAVE")){
            this.win.saveGridModel();
		}else if(bouton.equals("HELP")){
            HelpVue help = new HelpVue();
        }else{
            System.out.println("erreur methode actionPerformed , class ObservateurMenu ");
        }

    }

}