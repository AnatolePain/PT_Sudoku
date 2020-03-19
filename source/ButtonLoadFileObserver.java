package source;

import java.awt.event.*;
/**
 * La classe <code>ButtonLoadObserver<code> est utilisee comme ActionListener pour
 * ouvrir un sélecteur de fichier afin de charger le fichier dans un objet du type GridModel
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class ButtonLoadFileObserver implements ActionListener {

        /**
         * objet de type FileManager qui sert à la conversion du ficher 
         * au GridModel
         */
        private FileManager manager;

        /**
         * objet de type GridModel dans lequel sera chargé le fichier
         */
        private GridModel grid;

        /**
         * 
         * @param m le manager de fichier FileManager
         * @param g la grille où sera chargé le fichier GridModel
         */
        public ButtonLoadFileObserver(FileManager m, GridModel g) {
            this.manager = m;
            this.grid = g;
        }

        /**
         * fait une demande du fichier à charger et le charge dans l'objet GridModel
         * @param evenement
         */
        public void actionPerformed(ActionEvent evenement) {
            this.manager.askForLoadFile();
            if (this.manager.getSelectFile() != null) {
                this.grid = this.manager.loadGridFromFile();
            }
        }
    
}