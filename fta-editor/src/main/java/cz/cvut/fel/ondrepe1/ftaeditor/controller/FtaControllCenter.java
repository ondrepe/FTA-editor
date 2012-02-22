package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import java.util.List;

/**
 *
 * @author ondrejicek
 */
public class FtaControllCenter {

    private static FtaControllCenter instance;

    public static FtaControllCenter getInstance() {
        if (instance == null) {
            instance = new FtaControllCenter();
        }
        return instance;
    }
    
    private static List editorTabList;
    
    private FtaControllCenter() {
    }
}
