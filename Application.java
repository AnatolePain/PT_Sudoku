import java.io.File;

public class Application {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        File f = new File("exemple.gri");
        GridModel gm = new GridModel();
        Window fenetre = new Window();

        fenetre.setVisible(true);
    }
}