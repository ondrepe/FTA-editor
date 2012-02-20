package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import java.awt.Point;

/**
 *
 * @author ondrepe
 */
public class DataItemMovedCompleteEvent extends CommonEvent {

    private FtaDataItem dataItem;
    private Point position;

    public DataItemMovedCompleteEvent(FtaDataItem dataItem, Point position) {
        this.dataItem = dataItem;
        this.position = position;
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }

    public Point getPosition() {
        return position;
    }
}
