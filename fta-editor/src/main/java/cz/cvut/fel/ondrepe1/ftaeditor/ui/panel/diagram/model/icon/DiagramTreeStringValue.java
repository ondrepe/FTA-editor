package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeStringValue implements StringValue {

    public String getString(Object value) {
        if (value instanceof FtaDataItem) {
            String key = ((FtaDataItem) value).getSymbolType();
            return key;
        }
        return StringValues.TO_STRING.getString(value); 
    }

}
