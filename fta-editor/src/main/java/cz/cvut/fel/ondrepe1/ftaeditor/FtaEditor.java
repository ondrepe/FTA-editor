package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaDataController;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.IODataController;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.MainWindow;

/**
 *
 * @author ondrepe
 */
public class FtaEditor {
    
    public static void main(String[] args) {
        FtaEditor editor = new FtaEditor();
    }

    public FtaEditor() {
        IODataController data = new IODataController();
        MainWindow mw = new MainWindow();
        
        FtaDataController.getInstance();
    }
}
