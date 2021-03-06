package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;

/**
 *
 * @author ondrepe
 */
public class DataSaveEvent extends CommonGlobalEvent {

    private String filePath;
    private String fileName;

    public DataSaveEvent(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

}
