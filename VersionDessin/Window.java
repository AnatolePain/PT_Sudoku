import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*; 

import javax.swing.text.PlainDocument;

public class Window extends JFrame {

	private JTextField[] tabTextField = new JTextField[81];
    private JPanel[] tabJPanel = new JPanel[9];
    private int indic;

    public Window() {
        this.setSize(600, 627);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CaseView dessin = new CaseView();

        this.add(dessin);
    }
}