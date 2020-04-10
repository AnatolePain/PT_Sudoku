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

    /**
     * Creation de la fenettre: mise en page de type BorderLayout.avec un le PannelSudoku
     * en CENTER et le PanelMenu en NORTH
     */
    public Window(){
        
		this.setTitle("Sudoku developer");
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.menu = new PanelMenu(this);
        this.add(this.menu, BorderLayout.NORTH);

        this.gridModel = new GridModel();
        this.sudoku = new PanelSudoku(gridModel);
        this.add(sudoku, BorderLayout.CENTER);

        this.pack();
        this.fileManager = new FileManager();
    }

    /**
     * Charge le GridModele et passe par la methode setScreenGridModele pour l'afficher
     */
    public void loadGridModel(){

        this.fileManager.askForLoadFile();
        if (this.fileManager.getSelectFile() != null) {
            this.gridModel = this.fileManager.loadGridFromFile();
            this.sudoku.setScreenGridModel(this.gridModel);
        }
    }

    /**
    * Sauvegarde le GridModele chargé
    */
    public void saveGridModel(){
        this.fileManager.askForSaveFile();
		if (this.fileManager.getSelectFile() != null) {
            this.fileManager.saveFileFromGrid(this.gridModel);
        }
    }

}