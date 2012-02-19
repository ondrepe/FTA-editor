package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import java.awt.event.MouseEvent;

/**
 *
 * @author ondrejicek
 */
public class DataItemMovedEvent extends CommonEvent {

    private FtaDataItem dataItem;
    private MouseEvent mouseEvent;

    public DataItemMovedEvent(FtaDataItem dataItem, MouseEvent mouseEvent) {
        this.dataItem = dataItem;
        this.mouseEvent = mouseEvent;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}
