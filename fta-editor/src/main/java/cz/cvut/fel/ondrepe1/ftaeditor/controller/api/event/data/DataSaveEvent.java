package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;

/**
 *
 * @author ondrepe
 */
public class DataSaveEvent extends CommonGlobalEvent {

    private String filePath;

    public DataSaveEvent(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

}
