import java.awt.event.*;
import java.util.regex.*;

import javax.swing.JTextField;
import javax.swing.text.*;

public class ObservateurSudoku extends DocumentFilter implements ActionListener,  FocusListener {

	public PanelSudoku sudoku;
	public JTextField champ;
	public int x;
   public int y;

	public ObservateurSudoku(PanelSudoku w,JTextField j,int l,int c){
		sudoku = w;
		champ = j;
		x = l;
      y = c;
	}

   public void actionPerformed(ActionEvent evenement){
      sudoku.CaseaEnter(champ , x , y);
   }

   public void focusLost(FocusEvent e){
      sudoku.caseFocusLost(champ);
   }

   public void focusGained(FocusEvent e){
      sudoku.caseFocusGained(champ);
   }


   /*-------------------------------------------------------*/
   
   private boolean test(String text) {

      Pattern p = Pattern.compile("^(?!.*(.).*\\1)([1-9]){0,4}$");
      Matcher m = p.matcher(text);

      if(m.find()){
         return true;
      }else{
         return false;
      }

   }

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
 