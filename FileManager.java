import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>FileManager<code> permet de gerer la selection des fichier de sauvegarde et la conversions de ceux ci vers un objet de type <code>GridModel<code>
 * Les fichiers doivent respecter le format de 9 entiers canoniques java a la suite
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class FileManager{

    private File selectedFile;

    /**
     * appel un selecteur de fichier <code>JFileChooser<code> et stock le fichier choisie
     * @param jfc selecteur de fichier du type <code>JFileChooser<code>
     */
    public void askForFile(JFileChooser jfc) {

        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
        }
    }

    /**
     * charge le fichier passe en parametre dans un tableau d'entier de taille 9
     * @param f objet de type <code>File<code>
     * @return tableau d'entier
     */
    private static int[] loadFile(File f) {

        int[] values = new int[9];
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            int i = 0;
            boolean eof = false;
            while(!eof || i < 9) {
                try {
                    values[i] = dis.readInt();
                    i++;
                } catch (IOException e) {
                    eof = true;
                }
            }
            try {
                dis.close();
            } catch (Exception e) {
                System.out.println("impossible de fermer le fichier");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("impossible d'ouvrir le fichier");
        }

        return values;
    }

    /**
     * charge le fichier enregistre avec la methode 'askForFile' et le transforme et retourne un objet de type <code>GridModel<code> correspondant a cette sauvegarde
     * @return objet de type <code>GridModel<code> correspondant au fichier de sauvegarde
     */
    public GridModel loadGridFromFile() {
        GridModel gm = new GridModel();
        int[] valuesInt = FileManager.loadFile(this.selectedFile);

        for (int i = 0; i < 9; i++) {
            String valueStr = valuesInt[i] + "";
            for (int j = 0; j < valueStr.length(); j++) {
                gm.setCaseFirstNum(i, j + (9 - valueStr.length()), Byte.parseByte(valueStr.charAt(j)+""));
            }
        }
        return gm;
    }
}