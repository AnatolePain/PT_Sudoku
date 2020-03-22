import java.awt.*;
import javax.swing.*;

public class PanelMenu extends JPanel {

    private Window window;

    public PanelMenu(Window w){

        window = w;
        FlowLayout layoutMenu = new FlowLayout();
        this.setLayout(layoutMenu);
        JButton loadFileButton = new JButton("LOAD FILE");
        JButton autoButton = new JButton("AUTO");
        ObservateurMenu obs = new ObservateurMenu(window, loadFileButton,autoButton);
        loadFileButton.addActionListener(obs);
        autoButton.addActionListener(obs);
        this.add(loadFileButton);
        this.add(autoButton);

    }

}