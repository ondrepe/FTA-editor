package cz.cvut.fel.ondrepe1.ftaeditor.listener.menu;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ondrejicek
 */
public class NewDataActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        FtaControllCenter.addNewUnit();
    }

}
