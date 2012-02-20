package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrepe
 */
public class DataAddLonelyItemEvent extends CommonEvent {

    private FtaDataItem dataItem;

    public DataAddLonelyItemEvent(FtaDataItem dataItem) {
        this.dataItem = dataItem;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }
}
