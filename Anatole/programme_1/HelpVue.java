import javax.swing.*;
/**
 * La classe <code>HelpVue</code> affiche une fêtre popup de type JOptionPane , pour expliquer 
 * les contrôle à l'utilisateur.
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class HelpVue extends JOptionPane {


    /**
     * Constructeur affichant le message  
     */
    public HelpVue() {
        
        String message = "Bienvenue sur sudoku developper !\n"
        				+"Cliquer sur une case pour la selectionner puis entrer un numero entre 1 et 9.\n"
        				+"Enfin, appuyer sur entrer pour valider votre reponse.\n"
						+"Le nombre valide aparait en rouge s'il n'est pas possible de le placer.";
        this.showMessageDialog(null, message, "  Help", INFORMATION_MESSAGE);

    }

}