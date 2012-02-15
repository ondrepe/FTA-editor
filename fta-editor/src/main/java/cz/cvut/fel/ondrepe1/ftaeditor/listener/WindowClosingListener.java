package cz.cvut.fel.ondrepe1.ftaeditor.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author ondrepe
 */
public class WindowClosingListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
