import java.io.*;
import java.awt.*;
import javax.swing.*;

public class FileManager {

    private static int[] loadFile(File f) {

        JFileChooser dialogue = new JFileChooser(new File("."));
	    PrintWriter sortie;
	    File fichier;
	
	    if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        fichier = dialogue.getSelectedFile();
	        sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
	        sortie.println(arg[0]);
	        sortie.close();
	    }

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

    public GridModel loadGridFromFile(File f) {
        GridModel gm = new GridModel();
        int[] valuesInt = FileManager.loadFile(f);

        for (int i = 0; i < 9; i++) {
            String valueStr = valuesInt[i] + "";
            for (int j = 0; j < valueStr.length(); j++) {
                gm.setCaseFirstNum(i, j + (9 - valueStr.length()), Byte.parseByte(valueStr.charAt(j)+""));
            }
        }
        return gm;
    }
}