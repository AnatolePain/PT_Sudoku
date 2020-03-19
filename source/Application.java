import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Application {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        GridModel gm = new GridModel();
        fm.askForLoadFile();
        System.out.println(fm.getSelectFile());
        if (fm.getSelectFile() != null) {
            gm = fm.loadGridFromFile();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gm.getCaseFirstNum(i, j) + " ,");
            }
            System.out.println();
        }
        fm.askForSaveFile();
        if (fm.getSelectFile() != null) {
            fm.saveFileFromGrid(gm);
        }
    }
    
}