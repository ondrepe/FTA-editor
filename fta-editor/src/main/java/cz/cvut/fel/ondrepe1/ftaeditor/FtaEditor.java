package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaDataController;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.MainWindow;

/**
 *
 * @author ondrepe
 */
public class FtaEditor {

    private FtaDataController controller;
    
    public static void main(String[] args) {
        FtaEditor editor = new FtaEditor();
    }

    public FtaEditor() {
        controller = new FtaDataController();
        MainWindow mw = new MainWindow(this);
        
    }

    public FtaDataController getController() {
        return controller;
    }
}
