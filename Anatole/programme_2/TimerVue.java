import java.awt.*;
import javax.swing.*;

public class TimerVue extends JPanel {

    private JTextArea chronometre;
    private TimerModel tm;

    public TimerVue(){

        chronometre = new JTextArea();
        this.add(chronometre, BorderLayout.CENTER);
        chronometre.setEditable(false);
        chronometre.setFocusable(false);

        Font font = new Font("Courier", Font.BOLD,25);
        chronometre.setFont(font);

        tm = new TimerModel();

        //while(true){
            tm.setTime();
            chronometre.setText(tm.getStirngHeures()+" : "+tm.getStringMinutes()+" : "+tm.getStringSecondes()+" : "+tm.getStringmillisecondes());
        //}

    }

    public void updateTimer(){
        tm.setTime();
        chronometre.setText(tm.getStirngHeures()+" : "+tm.getStringMinutes()+" : "+tm.getStringSecondes()+" : "+tm.getStringmillisecondes());
    }

}