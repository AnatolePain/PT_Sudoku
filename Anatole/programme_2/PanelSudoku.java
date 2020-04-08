import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;
import javax.swing.event.*;

/**
 * La classe <code>PanelSudoku</code> est utilisée pour gérer l'affichage de la grille, les
 *différentes couleur, récupérer les differentes information des case et les charger dans
  le gridModel
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class PanelSudoku extends JPanel {

    /**
     * Tableaux des 81 JTextField.
     */
    private JTextField[][] tabTextField = new JTextField[9][9];
    
    /**
     * 9 panneaux faisant les 9 blocs du sodoku.
     */
    private JPanel[] tabJPanel = new JPanel[9];

    private GridModel gridModel;

    /**
     * Contenue de la case ayant le focus: permet de réafficher le contenue
     * de la case s'il n'est pas validé.
     */
    private String stringFocus;

    /**
     * Couleur réutilisé dans plusieur methode.
     */
    private Color bleuClaire;

    /**
     * Constructeur affichant la grille de sudoku vide et non remplissable 
     */
    public PanelSudoku() {

        stringFocus = "NULL";

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

        //remplissage du tablaux de JPanel et ajout au panneau principale
        for(int i = 0; i < 9; i++){
            tabJPanel[i] = new JPanel();
            tabJPanel[i].setLayout(grille);
            tabJPanel[i].setBorder(border1);
            this.add(tabJPanel[i]);
        }

        int indic = 0;
        
        //remplissage du tableaux de JLabel et ajout au tableau de 9 JPanel
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


    /**
     * Permet de charger le GridModel et de l'afficher.
     * @param gm "Importation" du GridMoedel depuis la class Windows
     */
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

    /**
     * Change la valeur d'une case case selon le GridModel ( principalement utilisé
     *  par la methode ModeAuto).
     * @param x coordonné x de la grille de sudoku
     * @param y coordonné y de la grille de sudoku
     */
    public void setScreenCase(int x , int y){
        tabTextField[x][y].setText(gridModel.getCaseFirstNum(x, y)+"");
        tabTextField[x][y].setForeground(Color.BLUE);
    } 


    /**
     * Methode utilié quand on valide une nouvelle valeur dans une case CaseEnter 
     * verifie si cette valeur est valide et dans ce cas la charge dans le GridModel.
     * @param j nouvelle valeur de la case 
     * @param x coordonné x de la grille de sudoku
     * @param y coordonné y de la grille de sudoku
     */
    public void CaseaEnter(JTextField j,int x, int y){

        String text = j.getText();
        boolean changeFocus = true;

            //si la valeur est égale à zéro
            if(text.length() == 0){
                gridModel.setCaseFirstNum(x, y, (byte)0);
            
            //si c'est une valeur seul alors (firstNum)
            }else if(text.length() == 1){
                j.setForeground(Color.BLUE);

                byte b = Byte.parseByte(text,10);

                //vérifie si cette valeur est possile
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
            
            //sinon s'il y'a plusieurs numéro (subNum)
            }else{
                j.setForeground(bleuClaire);
                gridModel.setCaseFirstNum(x, y, (byte)0);
                int numbDigit =  text.length();
                for(int i = 0; i < numbDigit ; i++){
                    byte b = Byte.parseByte(text.substring(i,i+1),10);
                    gridModel.setCaseSubNum(x, y, i, b);
                }
                
                for(int i = numbDigit;  i < 4 ; i++){
                    gridModel.setCaseSubNum(x, y, i, (byte)0);
                }

            }

        //ne déplace pas le curser si la valeur n'est pas possible
        if(changeFocus){
            stringFocus = j.getText();
            j.setFocusable(false);
            j.setFocusable(true);
        }else{  
            changeFocus = false;
        }
    }

    /**
     * Permet d'enregistrer la valeur de la case seléctionné.
     * @param j est la case Selectionné (JLabel)
     */
    public void caseFocusGained(JTextField j){
        stringFocus = j.getText();
    }
    
    /**
     * Remet l'ancienne valeur dans le JLabel si la valeur n'a pas été validé.
     * @param j est la case Selectionné (JLabel)
     */
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

    /**
     * Remet la couleur du text de la case en noir.
     * @param j est la case Selectionné (JLabel)
     */
    public void setColor(JTextField j){
        j.setForeground(Color.BLACK);
    }

}