import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * La classe <code>ButtonLoadObserver<code> est utilisee comme <code>ActionListener<code> pour
 * ouvrir une un selecteur de fichier afin de charger le fichier dans un objet du type <code>GridModel<code>
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class ButtonLoadFileObserver implements ActionListener {

        /**
         * objet de type <code>FileManager<code> qui sert a la conversion du ficher 
         * au <code>GridModel<code>
         */
        private FileManager m_manager;

        /**
         * Le selecteur de fichier a utiliser pour choisir le fichier
         */
        private JFileChooser m_jfc;
        /**
         * objet de type <code>GridModel<code> dans lequel sera charge le fichier
         */
        private GridModel m_gm;

        /**
         * 
         * @param fm le manager de fichier <code>FileManager<code>
         * @param jfc le selecteur de fichier <code>JFileChooser<code>
         * @param gm la grille ou sera charge le fichier <code>GridModel<code>
         */
        public ButtonLoadFileObserver(FileManager fm, JFileChooser jfc, GridModel gm) {
            this.m_manager = fm;
            this.m_jfc = jfc;
            this.m_gm = gm;
        }

        /**
         * fait une demande du fichier a charger et le charge dans l objet <code>GridModel<code>
         */
        public void actionPerformed(ActionEvent evenement) {
            this.m_manager.askForFile(this.m_jfc);
            m_gm = this.m_manager.loadGridFromFile();
        }
    
}