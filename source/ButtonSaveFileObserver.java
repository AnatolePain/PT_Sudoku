package source;

import java.awt.event.*;
/**
 * La classe <code>ButtonSaveObserver<code> est utilisée comme ActionListener pour
 * ouvrir un selecteur de fichier afin de sauvegarder l'objet de type GridModel dans un fichier au bon format
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class ButtonSaveFileObserver implements ActionListener {

        /**
         * objet de type FileManager qui sert à la conversion du GridModel 
         * au format du fichier
         */
        private FileManager manager;

        /**
         * objet de type GridModel qui doit être sauvegardé
         */
        private GridModel grid;

        /**
         * 
         * @param f le manager de fichier FileManager
         * @param g la grille GridModel qui sera sauvegardé dans le fichier 
         */
        public ButtonSaveFileObserver(FileManager f, GridModel g) {
            this.manager = f;
            this.grid = g;
        }

        /**
         * fait une demande du fichier à charger et le charge dans l'objet GridModel
         * @param evenement
         */
        public void actionPerformed(ActionEvent evenement) {
            this.manager.askForSaveFile();
            if (this.manager.getSelectFile() != null) {
                this.manager.saveFileFromGrid(this.grid);
            }
        }
    
}