import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaseView extends JComponent {

	private int a;
	private int b;
	private int c;

	@Override
  	protected void paintComponent(Graphics pinceau) {

	    Graphics secondPinceau = pinceau.create();
	    if (this.isOpaque()) {
	      secondPinceau.setColor(this.getBackground());
	      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
	    }

	    secondPinceau.setColor(Color.BLACK);

	    a = (int)(this.getWidth()*0.01);
	    b = (int)(this.getWidth()/3);
	    c = (int)((b-a)/3);
	   	
	   	for(int i = 0; i < 3; i++){
	   		secondPinceau.fillRect(0,b*i,this.getWidth(),a);
	   		secondPinceau.drawLine(0,b*i+(c+a),this.getWidth(),b*i+(c+a));
	   		secondPinceau.drawLine(0,b*i+(2*c+a),this.getWidth(),b*i+(2*c+a));
	   	}
	   	secondPinceau.fillRect(0,b*3,this.getWidth(),a);

	   	for(int i = 0; i < 3; i++){
	   		secondPinceau.fillRect(b*i,0,a,this.getWidth());
	   		secondPinceau.drawLine(b*i+(c+a),0,b*i+(c+a),this.getWidth());
	   		secondPinceau.drawLine(b*i+(2*c+a),0,b*i+(2*c+a),this.getWidth());
	   	}
	   	secondPinceau.fillRect((b*3)-(int)(a*0.99),0,a,this.getWidth()); //on baisse de 1% la valeur de a pour eviter un beug d'affichage

  	}

}