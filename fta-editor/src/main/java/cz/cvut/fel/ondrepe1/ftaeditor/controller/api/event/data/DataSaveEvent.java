package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import java.io.File;

/**
 *
 * @author ondrepe
 */
public class DataSaveEvent extends CommonEvent {

    private File file;

    public DataSaveEvent(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

}
