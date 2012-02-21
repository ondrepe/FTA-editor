package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrepe
 */
public class DataConnectItemsEvent extends CommonEvent {

    private FtaDataItem parent;
    private FtaDataItem child;

    public DataConnectItemsEvent(FtaDataItem parent, FtaDataItem child) {
        this.parent = parent;
        this.child = child;
    }

    public FtaDataItem getParent() {
        return parent;
    }

    public FtaDataItem getChild() {
        return child;
    }
}
