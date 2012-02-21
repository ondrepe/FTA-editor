package cz.cvut.fel.ondrepe1.ftaeditor.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ondrepe
 */
public class LoadDataActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        FtaController.getInstance().fireEvent(new DataLoadEvent());
    }

}
