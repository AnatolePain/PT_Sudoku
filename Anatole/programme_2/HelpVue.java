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
        
        String message = "Bienvenue dans sudoku !\n"
        				+"Cliquer sur une case pour la selectionner puis entrer un numero entre 1 et 9.\n"
        				+"Enfin, appuyer sur entrer pour valider votre reponse.\n"
						+"Le nombre valide aparait en rouge si vous ne pouvez pas le placer, en bleu sinon.\n"
						+"De plus, pour vous aider, vous pouvez entrer jusqu'a 4 nombre en simultane, ils aparaissent en bleu clair.";
        this.showMessageDialog(null, message, "  Help", INFORMATION_MESSAGE);

    }

}