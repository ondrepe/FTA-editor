package cz.cvut.fel.ondrepe1.ftaeditor.listener.menu;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.MainWindow;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser.FtaFileChooserDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author ondrejicek
 */
public class SaveDataActionListener implements ActionListener {

    private MainWindow mainWindow;
    private FtaFileChooserDialog dialog;

    public SaveDataActionListener(MainWindow mainWindow, FtaFileChooserDialog dialog) {
        this.mainWindow = mainWindow;
        this.dialog = dialog;
    }
    
    public void actionPerformed(ActionEvent e) {
        int option = dialog.showSaveDialog(mainWindow);
        
        if(option == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = dialog.getSelectedFile().getAbsolutePath();
            String selectedFileName = dialog.getSelectedFile().getName();
            FtaControllCenter.fireGlobalEvent(new DataSaveEvent(selectedFilePath, selectedFileName));
        }
    }

}
