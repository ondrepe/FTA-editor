package cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.event;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.CommonSymbol;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author ondrepe
 */
public class BasicEvent extends CommonSymbol {

    public BasicEvent() {
        try {
            URL url = getClass().getResource("/img/symbols/event/basic.svg");
            this.setImagePath(url.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setLabel("Basic");
        this.setDescription("Basic Event");
    }
    
}
