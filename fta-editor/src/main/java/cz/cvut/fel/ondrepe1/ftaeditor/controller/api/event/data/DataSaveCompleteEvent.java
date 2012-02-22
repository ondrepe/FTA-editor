package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;

/**
 *
 * @author ondrepe
 */
public class DataSaveCompleteEvent extends CommonGlobalEvent {

    private String fileName;

    public DataSaveCompleteEvent(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
