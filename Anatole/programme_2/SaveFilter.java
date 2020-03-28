import javax.swing.filechooser.FileFilter;
import java.io.*;

/**
 * Cette classe <code>SaveFilter<code> est utilisé comme FileFilter pour filtrer les types de fichiers dans le JFileChooser
 * 
 * @version 0.1
 * @author Baptiste Asselin
 */
public class SaveFilter extends FileFilter{

    @Override
    public boolean accept(File fileName) {
        if(fileName.getName().toLowerCase().endsWith(FileManager.getFileExtention())) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "(GRI) *.gri";
    }
    
}