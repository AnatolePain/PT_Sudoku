import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Observateur implements TextListener {


	public void textValueChanged(TextEvent e){
		System.out.println("TEST 01");
	}

}