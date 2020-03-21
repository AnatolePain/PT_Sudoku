import javax.swing.event.*;

import java.awt.event.*;
import java.util.regex.*;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class Observateur extends DocumentFilter implements DocumentListener, ActionListener,  FocusListener {


	public Window win;
	public JTextField champ;
	public int indiceX;
   public int indiceY;


	public Observateur(Window w,JTextField j,int l,int c){
		win = w;
		champ = j;
		indiceX = c;
      indiceY = l;
	}


	public void changedUpdate(DocumentEvent e){
		System.out.println("changedUpdate");
		win.returnJTextField(champ,indiceX,indiceY);
		//System.out.println("e = "+e);
		System.out.println();
	}

	public void insertUpdate(DocumentEvent e){
		System.out.println("insertUpdate");
		win.returnJTextField(champ,indiceX,indiceY);
		//System.out.println("e = "+e);
		System.out.println();
	}

	public void removeUpdate(DocumentEvent e){
		System.out.println("removeUpdate");
		win.returnJTextField(champ,indiceX,indiceY);
		//System.out.println("e = "+e);
		System.out.println();
	}


   public void actionPerformed(ActionEvent evenement){

      String text2 = "haaaaa !";

      win.returnJTextField02(champ, evenement.getActionCommand());
      System.out.println("actionPerformed");
      //champ.setText(text2);
      //win.returnJTextField02(champ, evenement.getActionCommand());
   }

   public void focusLost(FocusEvent e){
      win.returnJTextField02(champ, champ.getText());
   }

   public void focusGained(FocusEvent e){}


	/*-------------------------------------------------------*/



	/*@Override
   public void insertString(FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (test(sb.toString())) {
         super.insertString(fb, offset, string, attr);
         System.out.println("insertString");
      } else {
         // warn the user and don't allow the insert
         System.out.println("warn insertString");
      }
   }*/

   private boolean test(String text) {

      Pattern p = Pattern.compile("^(?!.*(.).*\\1)\\d{0,4}$");
      Matcher m = p.matcher(text);

      if(m.find()){
         System.out.println("OUI");
         return true;
      }else{
         System.out.println("NON");
         return false;
      }

   }

   @Override
   public void replace(FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (test(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
         System.out.println("replace");
      } else {
         // warn the user and don't allow the insert
         System.out.println("warn: replace");
      }

   }

   /*@Override
   public void remove(FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if (test(sb.toString())) {
         super.remove(fb, offset, length);
         System.out.println("remove ");
      } else {
         // warn the user and don't allow the insert
         System.out.println("warn :remove ");
      }

   }*/
}
 