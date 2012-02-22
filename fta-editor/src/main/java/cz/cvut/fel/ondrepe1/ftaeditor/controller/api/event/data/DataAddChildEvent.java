package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrejicek
 */
public class DataAddChildEvent extends CommonLocalEvent {

    private FtaDataItem dataItem;

    public DataAddChildEvent(FtaDataItem dataItem) {
        this.dataItem = dataItem;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }
}
