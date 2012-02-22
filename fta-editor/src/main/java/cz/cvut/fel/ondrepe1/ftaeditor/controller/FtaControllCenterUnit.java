package cz.cvut.fel.ondrepe1.ftaeditor.controller;

/**
 *
 * @author ondrejicek
 */
public class FtaControllCenterUnit {

    private FtaController ftaController;

    public FtaControllCenterUnit() {
        ftaController = new FtaController();
    }

    public FtaController getFtaController() {
        return ftaController;
    }
}
