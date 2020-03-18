import java.io.File;

public class Application {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        File f = new File("exemple.gri");
        GridModel gm = new GridModel();
        gm = fm.loadGridFromFile(f);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(gm.getCaseFirstNum(i, j)+" ,");
            }
            System.out.println();
        }

        Window fenetre = new Window();
        fenetre.setVisible(true);
    }
}