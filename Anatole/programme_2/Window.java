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
        
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        menu = new PanelMenu(this);
        this.add(menu, BorderLayout.NORTH);

        sudoku = new PanelSudoku();
        this.add(sudoku, BorderLayout.CENTER);

        this.pack();

        fileManager = new FileManager();
        gridModel = new GridModel();
        chronometre = new TimerModel();

    }

    /**
     * Charge le GridModele et passe par la methode setScreenGridModele pour l'afficher
     */
    public void loadGridModel(){

        fileManager.askForLoadFile();
        if (fileManager.getSelectFile() != null) {
            gridModel = fileManager.loadGridFromFile();
            sudoku.setScreenGridModele(gridModel);
        }
    }

    /**
     * permet de de resoudre la grille et utilise les class TimerModel et GridComplete
     * pour gérer le temps et l'afficher 
     */
    public void solveGridModel(){

        chronometre.startTime();
        ModeAuto auto = new ModeAuto(gridModel, sudoku);
        auto.resolution((byte)0,(byte)0);
        this.gridComplete();
        //A SUPPRIMER: affiche le GridModele sur le terminal 
        this.afficher();

    }

    /**
     * Stop le timer et affiche le temps et le message de victoire a l'aide de 
     * la class WinnerVue
     */
    public void gridComplete(){
        chronometre.setTime();
        WinnerVue wv = new WinnerVue(chronometre);
    }

    //A SUPPRIMER: affiche le GridModele sur le terminal
    public void afficher(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gridModel.getCaseFirstNum(i, j) + "(");
                for (int k = 0; k < 4; k++) {
                    System.out.print(gridModel.getCaseSubNum(i, j, k) + ",");
                }
                System.out.print("), ");
            }
            System.out.println();
        }

        /*for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gm.getCaseFirstNum(i, j) + " ,");
            }
            System.out.println();
        }*/
        System.out.println();
        System.out.println();
    }

}