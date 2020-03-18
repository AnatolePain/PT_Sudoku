import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dessin extends JComponent {

	private int xRect;
	private int yRect;
	private int lRect;
	private int hRect;

	public Dessin(){
		xRect = 0;
		yRect = 0;
		lRect = 0;
		hRect = 0;
	}


	@Override
  	protected void paintComponent(Graphics pinceau) {

	    Graphics secondPinceau = pinceau.create();
	    if (this.isOpaque()) {
	      secondPinceau.setColor(this.getBackground());
	      secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
	    }

	    secondPinceau.setColor(Color.BLUE);
	   	secondPinceau.fillRect(xRect,yRect,lRect,hRect);
  	}


  	public void changeRect(int x, int y, int l, int h){
  		xRect = x;
		yRect = y;
		lRect = l;
		hRect = h;
		this.repaint();
  	}

}