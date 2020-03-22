import java.awt.*;
import javax.swing.*;
/**
 * La class <code>Window</code> JFrame gèrant l'affichage dans son ensemble, notament en invoquant les
 * les class PanelSudoku et PanelMenu
 * 
 * @version 0.1
 * @author Anatole Pain
 */
public class Window extends JFrame {

    private PanelMenu menu;
    private PanelSudoku sudoku;

    private FileManager fm;
    private GridModel gm;

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
    }

    /**
     * Charge le GridModele et passe par la methode setScreenGridModele pour l'afficher
     */
    public void loadGridModel(){

        fm = new FileManager();
        gm = new GridModel();

        fm.askForLoadFile();
        if (fm.getSelectFile() != null) {
            gm = fm.loadGridFromFile();
            sudoku.setScreenGridModele(gm);
        }

    }

    //A SUPPRIMER: affiche le GridModele sur le terminal
    public void afficher(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gm.getCaseFirstNum(i, j) + " ,");
            }
            System.out.println();
        }
    }

}