package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.CommonSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.SymbolGroup;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author ondrepe
 */
public class PalletPanel extends JPanel {

    private List<SymbolGroup> groups;
    private List<PalletItem> items;
    
    public PalletPanel() {
        setBackground(Color.red);
//        groups = PalletDataFactory.getPalletGroups();
//        for (SymbolGroup group : groups) {
//            for (CommonSymbol symbol : group.getList()) {
//                PalletItem item = new PalletItem(symbol);
//                items.add(item);
//                this.add(item);
//            }
//        }
    }

    
}
