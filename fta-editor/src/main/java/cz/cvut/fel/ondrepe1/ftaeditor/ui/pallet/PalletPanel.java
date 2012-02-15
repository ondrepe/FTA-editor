package cz.cvut.fel.ondrepe1.ftaeditor.ui.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.CommonSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.SymbolGroup;
import java.util.List;
import javax.swing.JScrollPane;

/**
 *
 * @author ondrepe
 */
public class PalletPanel extends JScrollPane {

    private List<SymbolGroup> groups;
    private List<PalletItem> items;
    
    public PalletPanel() {
        groups = PalletDataFactory.getPalletGroups();
        for (SymbolGroup group : groups) {
            for (CommonSymbol symbol : group.getList()) {
                PalletItem item = new PalletItem(symbol);
                items.add(item);
                this.add(item);
            }
        }
    }

    
}
