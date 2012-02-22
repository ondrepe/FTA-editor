package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import java.awt.Point;

/**
 *
 * @author ondrepe
 */
public class DataCreateItemEvent extends CommonLocalEvent {

    private Point position;
    private int editorState;

    public DataCreateItemEvent(Point position, int editorState) {
        this.position = position;
        this.editorState = editorState;
    }

    public int getEditorState() {
        return editorState;
    }

    public Point getPosition() {
        return position;
    }
}
