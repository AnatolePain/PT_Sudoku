import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>FileManager<code> permet de gérer la sélection des fichiers de sauvegarde et la conversion de ceux-ci vers un objet de type GridModel
 * Les fichiers doivent réspecter le format de 9 entiers canoniques java a la suite
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class FileManager{

    private File selectedFile;
    private JFileChooser fileChooser;

    private final static String SAVE_DIRECTORTY = "./save/";
    private static final String FILE_EXTENTION = ".gri";

    public FileManager() {
        this.fileChooser = new JFileChooser(FileManager.SAVE_DIRECTORTY);
        this.fileChooser.setFileFilter(new SaveFilter());
    }

    /**
     * appel un sélecteur d'ouverture de fichier JFileChooser et stock le fichier choisie
     */
    public void askForLoadFile() {
        
        
        this.fileChooser.setDialogTitle("Ouvrir un fichier");
        int returnValue = this.fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            this.selectedFile = null;
        } else {
            this.selectedFile = this.fileChooser.getSelectedFile();
        }
    }

    /**
     * appel un sélecteur d'enregistrement de fichier JFileChooser et stock le fichier choisie
     */
    public void askForSaveFile() {
        this.fileChooser.setDialogTitle("sauvegarder une grille");
        this.fileChooser.setSelectedFile(new File("*" + FileManager.FILE_EXTENTION));
        if (this.fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = this.fileChooser.getSelectedFile();
        }
    }
    /**
     * charge le fichier passé en paramètre dans un tableau d'entier de taille 9
     * @param f objet de type File
     * @return tableau d'entier
     */
    private int[] loadFile(File f) {

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
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(this.fileChooser, "le fichier specifier est impossible a ouvrir ou n'existe pas", "Attention", JOptionPane.WARNING_MESSAGE);
            this.selectedFile = null;
        }

        return values;
    }

    /**
     * charge le fichier enregistre avec la methode 'askForFile' et le transforme et retourne un objet de type GridModel correspondant à cette sauvegarde
     * @return objet de type GridModel correspondant au fichier de sauvegarde
     */
    public GridModel loadGridFromFile() {
        GridModel gm = new GridModel();
        int[] valuesInt = this.loadFile(this.selectedFile);

        for (int i = 0; i < 9; i++) {
            String valueStr = valuesInt[i] + "";
            for (int j = 0; j < valueStr.length(); j++) {
                gm.setCaseFirstNum(i, j + (9 - valueStr.length()), Byte.parseByte(valueStr.charAt(j)+""));
            }
        }
        return gm;
    }

    /**
     * Cette methodes enregistre les données passées sous forme d'un tableau de 9 entier dans un fichier dans le dossier de sauvegarde (save)
     * @param values tableau de 9 entiers à enregistrer dans le fichier
     * @param fileName nom du fichier où serons sauvegardé les données
     */
    private static void saveGrid(int[] values, String fileName) {

        if(!fileName.toLowerCase().endsWith(FileManager.FILE_EXTENTION)) {
            fileName += FileManager.FILE_EXTENTION;
        }

        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(FileManager.SAVE_DIRECTORTY + fileName)));
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
     * Cette methode permet de sauvegarder un objet de type GridModel dans un fichier dans le dossier de sauvegarde (save)
     * @param gm Objet de type GridModel qui doit être sauvegardé
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
            if (startStoring) {
                valuesInt[i] = Integer.parseInt(buffer);
                buffer = "";
                startStoring = false;
            } else {
                valuesInt[i] = 0;
            }
        }

        FileManager.saveGrid(valuesInt, this.selectedFile.getName());
    }

    /**
     * 
     * @return retourne le fichier sélectioné par les methodes 'askForLoadFile' ou 'askForSaveFile'
     */
    public File getSelectFile() {
        return this.selectedFile;
    }

    /**
     * 
     * @return retourne l'extention des fichiers de sauvegarde officielles (".gri")
     */
    public static String getFileExtention() {
        return FileManager.FILE_EXTENTION;
    }
}