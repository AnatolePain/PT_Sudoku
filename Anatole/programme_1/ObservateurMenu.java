import java.awt.event.*;

import javax.swing.JButton;

public class ObservateurMenu implements ActionListener {

    private JButton jLoad;
    private JButton jAuto;
    private Window win;



    public ObservateurMenu(Window w, JButton jL, JButton jA){
        win = w;
        jLoad = jL;
        jAuto = jA;
    }


    public void actionPerformed(ActionEvent evenement){

        String bouton = evenement.getActionCommand();

        if(bouton.equals("LOAD")){
            win.loadGridModel();
        }else if(bouton.equals("SAVE")){
            //win.afficher();
            win.saveGridModel();
        }else{
            System.out.println("erreur methode actionPerformed , class ObservateurMenu ");
        }

    }

}