package cz.cvut.fel.ondrepe1.ftaeditor.listener.menu;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.MainWindow;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser.FtaFileChooserDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author ondrepe
 */
public class OpenDataActionListener implements ActionListener {

    private MainWindow mainWindow;
    private FtaFileChooserDialog dialog;

    public OpenDataActionListener(MainWindow mainWindow, FtaFileChooserDialog dialog) {
        this.mainWindow = mainWindow;
        this.dialog = dialog;
    }
    
    public void actionPerformed(ActionEvent e) {
        int option = dialog.showOpenDialog(mainWindow);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = dialog.getSelectedFile();
            FtaControllCenter.fireGlobalEvent(new DataLoadEvent(selectedFile));
        }
    }

}
