import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;
import javax.swing.event.*;

/**
 * La classe <code>Direction</code> est utilisée pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class PanelSudoku extends JPanel {

    
	private JTextField[][] tabTextField = new JTextField[9][9];
    private JPanel[] tabJPanel = new JPanel[9];
    private GridModel gridModel;
    private String stringFocus;
    private Color bleuClaire;

    public PanelSudoku() {


        /*-------PLACEMENT-----------*/
        this.setPreferredSize(new Dimension(716, 716));
        GridLayout grille = new GridLayout(3,3);
        this.setLayout(grille);

        /*-----------STYLE-------------------------*/
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 4);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(border1);
        Font font = new Font("Courier", Font.BOLD,25);
        bleuClaire = new Color(100,149,237);

        for(int i = 0; i < 9; i++){
            tabJPanel[i] = new JPanel();
            tabJPanel[i].setLayout(grille);
            tabJPanel[i].setBorder(border1);
            this.add(tabJPanel[i]);
        }

        int indic = 0;
        
        for(int i = 0; i < 9; i++){

            for(int j = 0, l = 0; j < 9; j+=3, l++){

                if(i == 3 && j == 0){
                    indic = 3;
                }else if(i == 6  && j == 0){
                    indic = 6;
                }


                for(int k = 0; k < 3; k++){

                    int c = j+k;

                    tabTextField[i][c] = new JTextField();
                    tabTextField[i][c].setEditable(false);
                    tabTextField[i][c].setHorizontalAlignment(JTextField.CENTER);
                    tabTextField[i][c].setFont(font);
                    tabTextField[i][c].setBorder(border);
                    tabTextField[i][c].setBackground(Color.WHITE);

                    ObservateurSudoku obs1 = new ObservateurSudoku(this,tabTextField[i][c],i,c);
                    tabTextField[i][c].addActionListener(obs1);
                    tabTextField[i][c].addFocusListener(obs1);
                    tabTextField[i][c].getDocument().addDocumentListener(obs1);

                    PlainDocument doc = (PlainDocument) tabTextField[i][c].getDocument();
                    doc.setDocumentFilter(obs1);

                    tabJPanel[l+indic].add(tabTextField[i][c]);
                }
            }
        }

    }


    /*---------------------------------------------------------------------*/

    public void setScreenGridModele(GridModel gm){
        
        gridModel = gm;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(gridModel.getCaseFirstNum(i, j) == 0){
                    tabTextField[i][j].setText("");
                    tabTextField[i][j].setEditable(true);
                }else{
                    tabTextField[i][j].setText(gridModel.getCaseFirstNum(i, j)+"");
                    tabTextField[i][j].setFocusable(false);
                }
            }
        }
    }

    public void setScreenCase(int x , int y){
        tabTextField[x][y].setText(gridModel.getCaseFirstNum(x, y)+"");
        tabTextField[x][y].setForeground(Color.BLUE);
    }

    public void CaseaEnter(JTextField j,int x, int y){

        String text = j.getText();
        boolean changeFocus = true;

            if(text.length() == 0){
                gridModel.setCaseFirstNum(x, y, (byte)0);
            }else if(text.length() == 1){
                j.setForeground(Color.BLUE);

                byte b = Byte.parseByte(text,10);

                if(gridModel.isPossible(b, x , y)){

                    gridModel.setCaseFirstNum(x, y, b);
                    
                    //remet a zero les valeurs dans subNum s'il y'en a
                    if(stringFocus.length() > 1){
                        for(int i = 0 ; i < stringFocus.length(); i++){
                            gridModel.setCaseSubNum(x, y, i, (byte)0);
                        }
                    }
                }else{
                    j.setForeground(Color.RED);
                    System.out.println("TEST 2 ");
                    changeFocus = false;
                }
            }else{
                j.setForeground(bleuClaire);
                gridModel.setCaseFirstNum(x, y, (byte)0);
                int numbDigit =  text.length();
                for(int i = 0; i < numbDigit ; i++){
                    byte b = Byte.parseByte(text.substring(i,i+1),10);
                    gridModel.setCaseSubNum(x, y, i, b);
                }

                System.out.println("numDigit = " + numbDigit);
                System.out.println("(4 - numDigit = " + (4 - numbDigit) );
                
                for(int i = numbDigit;  i < 4 ; i++){
                    System.out.println("i = "+i);
                    gridModel.setCaseSubNum(x, y, i, (byte)0);
                }

            }

        if(changeFocus){
            System.out.println("TEST 1 ");
            stringFocus = j.getText();
            j.setFocusable(false);
            j.setFocusable(true);
        }else{  
            changeFocus = false;
        }
    }

    public void caseFocusGained(JTextField j){
        stringFocus = j.getText();
    }
       
    public void caseFocusLost(JTextField j){
        if(!j.getText().equals(stringFocus)){
            j.setText(stringFocus);

            if(stringFocus.length() > 1){
                j.setForeground(bleuClaire);
            }else{
                j.setForeground(Color.BLUE);
            }
        }
        stringFocus = "";
    }

    public void setColor(JTextField j){
        j.setForeground(Color.BLACK);
    }

}