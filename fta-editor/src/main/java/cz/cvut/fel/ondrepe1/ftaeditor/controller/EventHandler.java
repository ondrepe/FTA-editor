package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.ISymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.event.SymbolSelectEvent;

/**
 *
 * @author ondrepe
 */
public class EventHandler {

    public static void handleEvent(CommonEvent event, IEventListener listener) {
        if(event instanceof SymbolSelectEvent && listener instanceof ISymbolSelectListener) {
            ((ISymbolSelectListener) listener).onSelect(((SymbolSelectEvent) event).getObject());
        }
    }
}
