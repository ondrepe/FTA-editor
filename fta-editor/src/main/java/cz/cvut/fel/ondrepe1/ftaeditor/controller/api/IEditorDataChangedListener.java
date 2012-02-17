package cz.cvut.fel.ondrepe1.ftaeditor.controller.api;

import org.w3c.dom.Document;

/**
 *
 * @author ondrepe
 */
public interface IEditorDataChangedListener extends IEventListener {

    public void onEditorDataChange(Document document);
}
