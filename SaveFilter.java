import javax.swing.filechooser.FileFilter;
import java.io.*;

public class SaveFilter extends FileFilter{

    public static final String extension = ".gri";

    @Override
    public boolean accept(File fileName) {
        if(fileName.getName().toLowerCase().endsWith(SaveFilter.extension)) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "*.gri";
    }
    
}