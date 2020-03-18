import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>FileManager<code> permet de gerer la selection des fichier de sauvegarde et la conversion de ceux ci vers un objet de type <code>GridModel<code>
 * Les fichiers doivent respecter le format de 9 entiers canoniques java a la suite
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class FileManager{

    private File selectedFile;

    /**
     * appel un selecteur d'ouverture de fichier <code>JFileChooser<code> et stock le fichier choisie
     * si aucun fichiers n'est choisie ou que l"explorateur de fichier est interrompue le ficher "exemple.gri"
     * sera selectionne par defaut
     * @param jfc selecteur de fichier du type <code>JFileChooser<code>
     */
    public void askForLoadingFile(JFileChooser jfc) {
        
        jfc.setFileFilter(new SaveFilter());
        jfc.setDialogTitle("Ouvrir un fichier");
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
        }
        else  {
            this.selectedFile = new File("./save/exemple.gri");
        }
    }

    /**
     * appel un selecteur d'enregistrement de fichier <code>JFileChooser<code> et stock le fichier choisie
     * si aucun fichiers n'est choisie ou que l"explorateur de fichier est interrompue le ficher "exemple.gri"
     * sera selectionne par defaut
     * @param jfc selecteur de fichier du type <code>JFileChooser<code>
     */
    public void askForSavingFile(JFileChooser jfc) {
        
        jfc.setFileFilter(new SaveFilter());
        jfc.setDialogTitle("sauvegarder une grille");
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = jfc.getSelectedFile();
        }
        else  {
            this.selectedFile = new File("./save/exemple.gri");
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
            } catch (IOException e) {
                System.err.println("impossible de fermer le fichier");
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("impossible d'ouvrir le fichier");
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

    /**
     * Cette methodes enregistre les donnees passe sous forme d'un tableau de 9 entier dans un fichier dans le dossier de sauvegarde (save)
     * @param values tableau de 9 entiers a enregistrer dans le fichier
     * @param fileName nom du fichier ou serons sauvegarder les donnees
     */
    private static void saveGrid(int[] values, String fileName) {

        if(!fileName.toLowerCase().endsWith(SaveFilter.extension)) {
            fileName += SaveFilter.extension;
        }

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("./save/" + fileName)));
            for (int i = 0; i < 9; i++) {
                try {
                    dos.writeInt(values[i]);
                } catch (IOException e) {
                    System.err.println("impossible d'écrire dans le fichier");
                }
            }
            try {
                dos.close();
            } catch (IOException e) {
                System.err.println("Impossible de fermer le fichier");
            }
        } catch (FileNotFoundException e) {
            System.err.println("impossible de créer le fichier ou de l'écraser (fichier possiblement non régulier)");
        }
    }

    /**
     * Cette methode permet de sauvegarder un objet de type <code>GridModel<code> dans un fichier dans le dossier de sauvegarde (save)
     * @param gm Objet de type <code>GridModel<code> qui doit être sauvegarde
     * @param fileName nom du fichier ou serons sauvegarde les donnees de l'objet <code>GridModel<code>
     */
    public void saveFileFromGrid(GridModel gm) {
        int[] valuesInt = new int[9];
        String buffer = "";
        boolean startStoring = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gm.getCaseFirstNum(i, j) != (byte)0 || startStoring == true) {
                    startStoring = true;
                    buffer += (gm.getCaseFirstNum(i, j) + "");
                }
            }
            valuesInt[i] = Integer.parseInt(buffer);
            buffer = "";
            startStoring = false;
        }

        FileManager.saveGrid(valuesInt, this.selectedFile.getName());
    }
}