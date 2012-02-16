package cz.cvut.fel.ondrepe1.ftaeditor.listener.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenAddSymbolWindowEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.DiagramTreeSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IDiagramTreeSymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ondrepe
 */
public class AddButtonActionListener implements ActionListener, IDiagramTreeSymbolSelectListener {

    private AbstractSymbol symbol;

    public AddButtonActionListener() {
        FtaController.getInstance().registerEventListener(DiagramTreeSymbolSelectEvent.class, this);
    }
    
    public void actionPerformed(ActionEvent e) {
        FtaController.getInstance().fireEvent(new OpenAddSymbolWindowEvent(symbol));
    }

    public void onDiagramTreeSelect(AbstractSymbol symbol) {
        this.symbol = symbol;
    }

}
