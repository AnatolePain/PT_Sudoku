import java.awt.*;
import javax.swing.*;
/**
 * La classe <code>PanelMenu</code> JPanel gérant l'affichage du menu
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class PanelMenu extends JPanel {

    private Window window;

    /**
     * Creation du JPanel: mise en page de type flowLayout contenant deux JButton
     * 
     * @param w Fait le liens entre le panneau et la fenetre: 
     * Window w est necessaire aux arguments de la class ObservateurMenu pour qu'elle puisse appeler
     * LoadGridModel ulterieurement 
     */
    public PanelMenu(Window w){

        window = w;
        FlowLayout layoutMenu = new FlowLayout();
        this.setLayout(layoutMenu);
        JButton loadFileButton = new JButton("LOAD FILE");
        JButton autoButton = new JButton("AUTO");
        ObservateurMenu obs = new ObservateurMenu(window, loadFileButton,autoButton);
        loadFileButton.addActionListener(obs);
        autoButton.addActionListener(obs);
        this.add(loadFileButton);
        this.add(autoButton);

        TimerVue chronometre = new TimerVue();
        this.add(chronometre);

    }

}