import java.awt.*;
import javax.swing.*;
/**
 * La class <code>Window</code> est une fenêtre JFrame gèrant l'affichage dans son ensemble, notament en invoquant les
 * les class PanelSudoku et PanelMenu qui sont deux panneaux
 * 
 * @version 0.1
 * @author Anatole Pain
 */
public class Window extends JFrame {

    private PanelMenu menu;
    private PanelSudoku sudoku;

    private FileManager fileManager;
    private GridModel gridModel;

    private TimerModel chronometre;

    /**
     * Creation de la fenettre: mise en page de type BorderLayout.avec un le PannelSudoku
     * en CENTER et le PanelMenu en NORTH
     */
    public Window(){
        
		this.setTitle("Sudoku");
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.menu = new PanelMenu(this);
        this.add(this.menu, BorderLayout.NORTH);

        this.sudoku = new PanelSudoku();
        this.add(this.sudoku, BorderLayout.CENTER);

        this.pack();

        this.fileManager = new FileManager();
        this.gridModel = new GridModel();
        this.chronometre = new TimerModel();

    }

    /**
     * Charge le GridModele et passe par la methode setScreenGridModele pour l'afficher
     */
    public void loadGridModel(){

        this.fileManager.askForLoadFile();
        if (this.fileManager.getSelectFile() != null) {
            this.gridModel = this.fileManager.loadGridFromFile();
            this.sudoku.setScreenGridModele(this.gridModel);
			this.menu.enableAutoButton();
        }
    }

    /**
     * permet de de resoudre la grille et utilise les class TimerModel et GridComplete
     * pour gérer le temps et l'afficher 
     */
    public void solveGridModel(){

        this.chronometre.startTime();
        ModeAuto auto = new ModeAuto(this.gridModel, this.sudoku);
        auto.resolution((byte)0,(byte)0);
        this.gridComplete();

    }

    /**
     * Stop le timer et affiche le temps et le message de victoire a l'aide de 
     * la class WinnerVue
     */
    public void gridComplete(){
        this.chronometre.setTime();
        WinnerVue wv = new WinnerVue(this.chronometre);
    }

}