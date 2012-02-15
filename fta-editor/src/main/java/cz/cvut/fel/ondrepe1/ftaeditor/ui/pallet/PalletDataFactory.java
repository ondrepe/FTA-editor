package cz.cvut.fel.ondrepe1.ftaeditor.ui.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.SymbolGroup;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.event.BasicEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public class PalletDataFactory {

    public static List<SymbolGroup> getPalletGroups() {
        List<SymbolGroup> groups = new ArrayList<SymbolGroup>();
        
        SymbolGroup group = new SymbolGroup();
        group.setName("Event");
        group.getList().add(new BasicEvent());
        
        return groups;
    }
}
