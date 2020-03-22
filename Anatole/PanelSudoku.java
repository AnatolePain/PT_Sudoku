import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;

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
                    tabTextField[i][j].setEditable(true);
                }else{
                    tabTextField[i][j].setText(gridModel.getCaseFirstNum(i, j)+"");
                }
            }
        }
    }

    public void CaseaEnter(JTextField j,int y, int x){

        String text = j.getText();
        System.out.println("x = "+x+" ; y = "+y);
        
        if(text.length() == 0){
            gridModel.setCaseFirstNum(x, y, (byte)0);
        }else if(text.length() == 1){
            gridModel.setCaseFirstNum(x, y, Byte.parseByte(text,10));
        }else{
            int numbDigit =  text.length();
            for(int i = 0; i < numbDigit ; i++){
                byte b = Byte.parseByte(text.substring(i,i+1),10);
                gridModel.setCaseSubNum(x, y, numbDigit-1 , b);
            }
        }

        System.out.println("value = "+gridModel.getCaseFirstNum(x,y));

        stringFocus = j.getText();
        j.setFocusable(false);
        j.setFocusable(true);
    }

    public void caseFocusGained(JTextField j){
        stringFocus = j.getText();
    }
       
    public void caseFocusLost(JTextField j){
        if(!j.getText().equals(stringFocus)){
            j.setText(stringFocus);
        }
        stringFocus = "";
    }

}