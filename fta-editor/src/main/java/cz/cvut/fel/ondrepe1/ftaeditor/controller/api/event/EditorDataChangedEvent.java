package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event;

import org.w3c.dom.Document;

/**
 *
 * @author ondrepe
 */
public class EditorDataChangedEvent extends CommonEvent {

    private Document document;

    public EditorDataChangedEvent(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
}
