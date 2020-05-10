import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.PlainDocument;
import javax.swing.event.*;

/**
 * La classe <code>PanelSudoku</code> est utilisée pour gérer l'affichage de la grille, les
 * différentes couleur, récupérer les differentes information des case et les charger dans
 * le gridModel
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
	 * Temps en cas de résolution manuel
	 */
    private TimerModel chronometre;

    /**
     * Constructeur affichant la grille de sudoku vide et non remplissable 
     */
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
        this.bleuClaire = new Color(100,149,237);

        //remplissage du tablaux de JPanel et ajout au panneau principale
        for(int i = 0; i < 9; i++){
            this.tabJPanel[i] = new JPanel();
            this.tabJPanel[i].setLayout(grille);
            this.tabJPanel[i].setBorder(border1);
            this.add(this.tabJPanel[i]);
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

                    this.tabTextField[i][c] = new JTextField();
                    this.tabTextField[i][c].setEditable(false);
                    this.tabTextField[i][c].setFocusable(false);
                    this.tabTextField[i][c].setHorizontalAlignment(JTextField.CENTER);
                    this.tabTextField[i][c].setFont(font);
                    this.tabTextField[i][c].setBorder(border);
                    this.tabTextField[i][c].setBackground(Color.WHITE);

                    ObservateurSudoku obs1 = new ObservateurSudoku(this,this.tabTextField[i][c],i,c);
                    this.tabTextField[i][c].addActionListener(obs1);
                    this.tabTextField[i][c].addFocusListener(obs1);
                    this.tabTextField[i][c].getDocument().addDocumentListener(obs1);

                    PlainDocument doc = (PlainDocument) this.tabTextField[i][c].getDocument();
                    doc.setDocumentFilter(obs1);

                    this.tabJPanel[l+indic].add(tabTextField[i][c]);
                }
            }
        }

    }


    /**
     * Permet de charger le GridModel et de l'afficher.
     * @param gm "Importation" du GridMoedel depuis la class Windows
     */
    public void setScreenGridModele(GridModel gm){
        
        this.gridModel = gm;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(this.gridModel.getCaseFirstNum(i, j) == 0){
                    this.tabTextField[i][j].setText("");
                    this.tabTextField[i][j].setEditable(true);
                    this.tabTextField[i][j].setFocusable(true);
                }else{
                    this.tabTextField[i][j].setText(this.gridModel.getCaseFirstNum(i, j)+"");
                    this.tabTextField[i][j].setEditable(false);
                    this.tabTextField[i][j].setFocusable(false);
                }
            }
        }

        this.chronometre = new TimerModel();
        this.chronometre.startTime();
    }

    /**
     * Change la valeur d'une case case selon le GridModel ( principalement utilisé
     *  par la methode ModeAuto).
     * @param x coordonné x de la grille de sudoku
     * @param y coordonné y de la grille de sudoku
     */
    public void setScreenCase(int x , int y){
        this.tabTextField[x][y].setText(this.gridModel.getCaseFirstNum(x, y)+"");
        this.tabTextField[x][y].setForeground(Color.BLUE);
    } 


    /**
     * Methode utilié quand on valide une nouvelle valeur dans une case CaseEnter 
     * verifie si cette valeur est valide et dans ce cas la charge dans le GridModel.
     * @param j nouvelle valeur de la case 
     * @param x coordonné x de la grille de sudoku
     * @param y coordonné y de la grille de sudoku
     */
    public void CaseEnter(JTextField j,int x, int y){

        String text = j.getText();
        boolean changeFocus = true;

            //si la valeur est égale à zéro
            if(text.length() == 0){
                this.gridModel.setCaseFirstNum(x, y, (byte)0);

                //remet a zero les valeurs dans subNum s'il y'en a
                if(this.stringFocus.length() > 1){
                    for(int i = 0 ; i < this.stringFocus.length(); i++){
                        this.gridModel.setCaseSubNum(x, y, i, (byte)0);
                    }
                }
            
            //si c'est une valeur seul alors (firstNum)
            }else if(text.length() == 1){

                byte b = Byte.parseByte(text,10);

                //vérifie si cette valeur est possile
                if(this.gridModel.isPossible(b, x , y)){

                    j.setForeground(Color.BLUE);

                    this.gridModel.setCaseFirstNum(x, y, b);
                    if(this.gridModel.isCompleted()){
                        this.chronometre.setTime();
                        WinnerVue wv = new WinnerVue(this.chronometre);
                    }
                    
                    //remet a zero les valeurs dans subNum s'il y'en a
                    if(this.stringFocus.length() > 1){
                        for(int i = 0 ; i < this.stringFocus.length(); i++){
                            this.gridModel.setCaseSubNum(x, y, i, (byte)0);
                        }
                    }
                
                //si elle n'est pas possible et n'est pas le même nombre qu'avant alors case en rouge
                }else if (!this.gridModel.isPossible(b, x , y) 
                            && this.gridModel.getCaseFirstNum(x, y) != b){
                    j.setForeground(Color.RED);
                    changeFocus = false;
                }
            
            //sinon s'il y'a plusieurs numéro (subNum)
            }else{
                j.setForeground(this.bleuClaire);
                this.gridModel.setCaseFirstNum(x, y, (byte)0);
                int numbDigit =  text.length();
                for(int i = 0; i < numbDigit ; i++){
                    byte b = Byte.parseByte(text.substring(i,i+1),10);
                    this.gridModel.setCaseSubNum(x, y, i, b);
                }
                
                for(int i = numbDigit;  i < 4 ; i++){
                    this.gridModel.setCaseSubNum(x, y, i, (byte)0);
                }

            }

        //ne déplace pas le curser si la valeur n'est pas possible
        if(changeFocus){
            this.stringFocus = j.getText();
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
        this.stringFocus = j.getText();
    }
    
    /**
     * Remet l'ancienne valeur dans le JLabel si la valeur n'a pas été validé.
     * @param j est la case Selectionné (JLabel)
     */
    public void caseFocusLost(JTextField j){
        if(!j.getText().equals(this.stringFocus)){
            j.setText(this.stringFocus);

            if(this.stringFocus.length() > 1){
                j.setForeground(this.bleuClaire);
            }else{
                j.setForeground(Color.BLUE);
            }
        }
        this.stringFocus = "";
    }

    /**
     * Remet la couleur du text de la case en noir.
     * @param j est la case Selectionné (JLabel)
     */
    public void setColor(JTextField j){
        j.setForeground(Color.BLACK);
    }

}