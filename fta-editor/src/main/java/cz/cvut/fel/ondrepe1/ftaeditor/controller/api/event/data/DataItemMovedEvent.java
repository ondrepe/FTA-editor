package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author ondrejicek
 */
public class DataItemMovedEvent extends CommonEvent {

    private FtaDataItem dataItem;
    private MouseEvent mouseEvent;
    private Point initialPosition;

    public DataItemMovedEvent(FtaDataItem dataItem, MouseEvent mouseEvent, Point initialPosition) {
        this.dataItem = dataItem;
        this.mouseEvent = mouseEvent;
        this.initialPosition = initialPosition;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public Point getInitialPosition() {
        return initialPosition;
    }
}
