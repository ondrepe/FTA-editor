package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.DiagramTreeSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDiagramTreeSymbolSelectListener extends IEventListener {

    public void onEvent(DiagramTreeSymbolSelectEvent event);
}
