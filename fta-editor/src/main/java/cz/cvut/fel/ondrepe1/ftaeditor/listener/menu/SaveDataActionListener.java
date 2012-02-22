package cz.cvut.fel.ondrepe1.ftaeditor.listener.menu;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.MainWindow;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser.FtaFileChooserDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        dialog.showOpenDialog(mainWindow);
        
        File selectedFile = dialog.getSelectedFile();
        FtaController.getInstance().fireEvent(new DataLoadEvent(selectedFile));
    }

}
