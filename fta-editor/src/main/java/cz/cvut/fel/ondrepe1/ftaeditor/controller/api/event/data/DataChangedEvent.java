package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;

/**
 *
 * @author ondrepe
 */
public class DataChangedEvent extends CommonEvent {

    private FtaData data;

    public DataChangedEvent(FtaData data) {
        this.data = data;
    }

    public FtaData getData() {
        return data;
    }
}
