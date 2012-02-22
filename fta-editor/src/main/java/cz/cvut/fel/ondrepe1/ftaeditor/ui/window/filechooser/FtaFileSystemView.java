package cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser;

import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author ondrejicek
 */
public class FtaFileSystemView extends FileSystemView {

    @Override
    public File createNewFolder(File containingDir) throws IOException {
        System.out.println("containing dir: " + containingDir.getAbsolutePath());
        return null;
    }

}
