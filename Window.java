import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        this.setSize(800, 600);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Button load = new Button("load file");
        GridModel gm = null;
        load.addActionListener(new ButtonLoadFileObserver(new FileManager(), new JFileChooser("./save"), gm));

        this.add(load, BorderLayout.CENTER);

    }

}