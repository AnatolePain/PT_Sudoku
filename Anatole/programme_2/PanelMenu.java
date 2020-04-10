import java.awt.*;
import javax.swing.*;
/**
 * La classe <code>PanelMenu</code> de type JPanel gère l'affichage de la bar de menu
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class PanelMenu extends JPanel {

    private Window window;
	
	/**
	 *Bouton Auto qui doit être désactivé au démarage.
	 */
	private JButton autoButton;

    /**
     * Creation du JPanel: mise en page de type flowLayout contenant deux JButton
     * 
     * @param w Fait le liens entre le panneau et la fenetre: 
     * Window w est necessaire aux arguments de la class ObservateurMenu pour qu'elle 
     * puisse appeler LoadGridModel ulterieurement 
     */
    public PanelMenu(Window w){

        this.window = w;
        FlowLayout layoutMenu = new FlowLayout();
        this.setLayout(layoutMenu);
		
        JButton loadFileButton = new JButton("LOAD FILE");
        this.autoButton = new JButton("AUTO");
		this.autoButton.setEnabled(false);
		JButton helpButton = new JButton("HELP");
		
        ObservateurMenu obs = new ObservateurMenu(window);
        loadFileButton.addActionListener(obs);
        this.autoButton.addActionListener(obs);
		helpButton.addActionListener(obs);
		
        this.add(loadFileButton);
        this.add(autoButton);
		this.add(helpButton);

    }
	
	/**
	 * Réactiver le bouton Auto (quand on on charge un GridModel).
	 */
	public void enableAutoButton(){
		this.autoButton.setEnabled(true);
	}

}