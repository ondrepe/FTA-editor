package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrepe
 */
public class DataEditItemEvent extends CommonLocalEvent {

    private FtaDataItem dataItem;

    public DataEditItemEvent(FtaDataItem dataItem) {
        this.dataItem = dataItem;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }
}
