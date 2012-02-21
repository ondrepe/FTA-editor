package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaDataController;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.IOData;
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
        IOData data = new IOData();
        MainWindow mw = new MainWindow();
        
        FtaDataController.getInstance();
    }
}
