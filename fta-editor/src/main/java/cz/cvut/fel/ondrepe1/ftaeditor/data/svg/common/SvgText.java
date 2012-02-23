package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgText extends SvgObject {

    private Element text;

    public SvgText(double x, double y, String label) {
    
        text = getDocument().createElementNS(SVG_NS, SVG_TYPE_TEXT);
        text.setAttributeNS(null, ATT_X, String.valueOf(x));
        text.setAttributeNS(null, ATT_Y, String.valueOf(y));
        text.setAttributeNS(null, ATT_FILL, COLOR_BLACK);
        text.setTextContent(label);
    }
    
    @Override
    public Element getElement() {
        return text;
    }

}
