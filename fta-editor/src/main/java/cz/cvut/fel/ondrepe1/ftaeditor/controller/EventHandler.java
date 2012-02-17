package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEditorDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IOpenAddSymbolWindowListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.EditorDataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenAddSymbolWindowEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.AddSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.RemoveSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.DiagramTreeSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.PalletSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IAddSymbolListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IRemoveSymbolListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IDiagramTreeSymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IPalletSymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;

/**
 *
 * @author ondrepe
 */
public class EventHandler {

    public static void handleEvent(CommonEvent event, IEventListener listener) {
        if(event instanceof DiagramTreeSymbolSelectEvent && listener instanceof IDiagramTreeSymbolSelectListener) {
            ((IDiagramTreeSymbolSelectListener) listener).onDiagramTreeSelect(((DiagramTreeSymbolSelectEvent) event).getSymbol());
        } else if (event instanceof RemoveSymbolEvent && listener instanceof IRemoveSymbolListener) {
            ((IRemoveSymbolListener) listener).onRemoveSymbol(((RemoveSymbolEvent) event).getSymbol());
        } else if (event instanceof AddSymbolEvent && listener instanceof IAddSymbolListener) {
            ((IAddSymbolListener) listener).onAddSymbol(((AddSymbolEvent) event).getSymbol(), (AbstractGate)((AddSymbolEvent) event).getParent());
        }
        
        
        else if (event instanceof PalletSymbolSelectEvent && listener instanceof IPalletSymbolSelectListener) {
            ((IPalletSymbolSelectListener) listener).onPalletSelect(((PalletSymbolSelectEvent) event).getSymbol());
        }
        
        
        else if (event instanceof DataChangedEvent && listener instanceof IDataChangedListener) {
            ((IDataChangedListener) listener).onDataChanged(((DataChangedEvent) event).getData());
        } else if (event instanceof OpenAddSymbolWindowEvent && listener instanceof IOpenAddSymbolWindowListener) {
            ((IOpenAddSymbolWindowListener) listener).onOpenAddSymbolWindow(((OpenAddSymbolWindowEvent) event).getParent());
        } else if (event instanceof EditorDataChangedEvent && listener instanceof IEditorDataChangedListener) {
            ((IEditorDataChangedListener) listener).onEditorDataChange(((EditorDataChangedEvent) event).getDocument());
        }
    }
}
