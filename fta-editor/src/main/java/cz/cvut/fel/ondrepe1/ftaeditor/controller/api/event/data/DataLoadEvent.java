package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import java.io.File;

/**
 *
 * @author ondrepe
 */
public class DataLoadEvent extends CommonEvent {

    private File file;
    
    public DataLoadEvent(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
