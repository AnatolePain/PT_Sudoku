import java.awt.event.*;
import java.util.regex.*;

import javax.swing.JTextField;
import javax.swing.text.*;

import javax.swing.event.*;

/**
 * La classe <code>ObservateurSudoku</code> gère tout les listeners nécéssaire au PanelSudoku
 *  
 * @version 0.1
 * @author Anatole Pain
 */
public class ObservateurSudoku extends DocumentFilter implements ActionListener, FocusListener, DocumentListener {

	private PanelSudoku sudoku;
	private JTextField champ;
	private int x;
    private int y;

   /**
    * Constructeur utilisé pour récupérer la grille dudoku, le JTextFiel actionné, et ses coordonnés
    * @param s grille dudoku
    * @param j JTextFiel actionné
    * @param l coordonné x de la grille 
    * @param c coordonné y de la grille 
    */
	public ObservateurSudoku(PanelSudoku s,JTextField j,int l,int c){
		this.sudoku = s;
		this.champ = j;
		this.y = c;
   }
   
   
   /*--------------ActionListener--------------------*/

   /**
    * Actionné lorsque l'on appuie sur entrée
    */
   @Override
   public void actionPerformed(ActionEvent evenement){
      sudoku.CaseEnter(this.champ , this.x , this.y);
   }


   /*--------------FocusListener--------------------*/

   /**
    * Acttionné quand le focus d'une case est perdue
    */
   @Override
   public void focusLost(FocusEvent e){
      this.sudoku.caseFocusLost(this.champ);
   }

   /**
    * Acttionné quand le focus d'une case est gagné
    */
   @Override
   public void focusGained(FocusEvent e){
      this.sudoku.caseFocusGained(this.champ);
   }



   /*--------------DocumentListener-----------------*/

   /**
    * Quand l'utilisateur écrit dans la case
    */
   @Override
   public void insertUpdate(DocumentEvent e){
      this.sudoku.setColor(this.champ);
   }
 
   /**
    * Quand le text de la case est effacé
    */
   @Override
   public void removeUpdate(DocumentEvent e){
      this.sudoku.setColor(this.champ);
   }
 
   public void changedUpdate(DocumentEvent e){}



   /*--------------extends DocumentFilter---------------*/

   /**
    * permet de faire un régexe de la case pour qu'on puisse seulement écrire 4 chiffre
    * enrtre 1 et 9
    * @param text est le text entrée au clavier
    * @return true s'il le régexe est respecté false sinon
    */
   private boolean test(String text) {

      Pattern p = Pattern.compile("^(?!.*(.).*\\1)([1-9]){0,4}$");
      Matcher m = p.matcher(text);

      if(m.find()){
         return true;
      }else{
         return false;
      }

   }

   /**
    * Désactive les case de clavier qui ne respecte pas le regex
    */
   @Override
   public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (test(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
      }

   }
}
 