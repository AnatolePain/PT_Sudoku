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

        if(bouton.equals("LOAD FILE")){
            System.out.println("load file");
            win.loadGridModele();
        }else if(bouton.equals("AUTO")){
            System.out.println("bouton Auto");
            win.afficher();
        }else{
            System.out.println("erreur methode actionPerformed , class ObservateurMenu ");
        }

    }

}