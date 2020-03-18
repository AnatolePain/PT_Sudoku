import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

    private JFileChooser jfc;

    public Window() {
        this.setSize(800, 600);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jfc = new JFileChooser("./save");


        









        
        this.add(jfc);
    }
}