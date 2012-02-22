package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrepe
 */
public class OpenEditDialogEvent extends CommonGlobalEvent {

    private FtaDataItem dataItem;

    public OpenEditDialogEvent(FtaDataItem dataItem) {
        this.dataItem = dataItem;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }
}
