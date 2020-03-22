import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

    private PanelMenu menu;
    private PanelSudoku sudoku;

    private FileManager fm;
    private GridModel gm;

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


    public void loadGridModele(){

        fm = new FileManager();
        gm = new GridModel();

        fm.askForLoadFile();
        if (fm.getSelectFile() != null) {
            gm = fm.loadGridFromFile();
            sudoku.setScreenGridModele(gm);
        }

    }

    public void afficher(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gm.getCaseFirstNum(i, j) + " ,");
            }
            System.out.println();
        }
    }

}