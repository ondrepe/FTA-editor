package cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public class SymbolGroup {

    private String name;
    private List<CommonSymbol> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommonSymbol> getList() {
        if (list == null) list = new ArrayList<CommonSymbol>();
        return list;
    }
}
