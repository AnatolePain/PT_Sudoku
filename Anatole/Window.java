import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*; 

import javax.swing.text.PlainDocument;

public class Window extends JFrame {

	private JTextField[][] tabTextField = new JTextField[9][9];
    private JPanel[] tabJPanel = new JPanel[9];
    private int indic;

    public Window() {
        this.setSize(600, 600);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*------------PLACEMENT--------------------*/
        JPanel pan = new JPanel();
        GridLayout grille = new GridLayout(3,3);
	    this.setLayout(grille);


        /*-----------STYLE-------------------------*/
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 4);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        pan.setBorder(border1);
        Font font = new Font("Courier", Font.BOLD,25);
 
        indic = 0;
        int indice = 0;

        for(int i = 0; i < 9; i++){
            tabJPanel[i] = new JPanel();
            tabJPanel[i].setLayout(grille);
            tabJPanel[i].setBorder(border1);
            this.add(tabJPanel[i]);
        }


        for(int i = 0; i < 9; i++){

            for(int j = 0, l = 0; j < 9; j+=3, l++){

                if(i == 3 && j == 0){
                    indic = 3;
                }else if(i == 6  && j == 0){
                    indic = 6;
                }


                for(int k = 0; k < 3; k++){

                    tabTextField[i][j+k] = new JTextField(4);
                    tabTextField[i][j+k].setHorizontalAlignment(JTextField.CENTER);
                    tabTextField[i][j+k].setFont(font);
                    tabTextField[i][j+k].setBorder(border);

                    Observateur02 obs1 = new Observateur02(this,tabTextField[i][j+k],i,j+k);
                    tabTextField[i][j+k].getDocument().addDocumentListener(obs1);

                    PlainDocument doc = (PlainDocument) tabTextField[i][j+k].getDocument();
                    doc.setDocumentFilter(obs1);

                    tabJPanel[l+indic].add(tabTextField[i][j+k]);
                }
            }
        }

        //this.add(pan, BorderLayout.CENTER);
    }


    public void returnJTextField(JTextField j, int x,int y){
    	String text = j.getText();
    	System.out.println("JTextField["+y+"]["+x+"] = "+text);
        System.out.println("( "+x+" ; "+y+" )");
        System.out.println();
    }

}