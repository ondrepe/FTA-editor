package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
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
        FtaControllCenter fcc = FtaControllCenter.init();
        MainWindow mw = new MainWindow();
        fcc.setDiagramTreePanel(mw.getDiagramTreePanel());
    }
}
