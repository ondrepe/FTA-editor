package cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author ondrejicek
 */
public class FtaFileChooserDialog extends JFileChooser {

    private boolean load;
    
    public FtaFileChooserDialog(boolean load) {
        super(new FtaFileSystemView());
        this.load = load;
        initDialog();
    }

    private void initDialog() {
        this.setDialogTitle("Load file");
        this.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                if (load) {
                    if (f.canRead() && f.isFile()) {
                        if (f.getName().endsWith(".fta")) {
                            return true;
                        }
                    }
                } else {
                    if (f.canWrite()) {
                        if (f.getName().endsWith(".fta")) {
                            return true;
                        }
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "FTA editor files (*.fta)";
            }
        });
    }

    
}
