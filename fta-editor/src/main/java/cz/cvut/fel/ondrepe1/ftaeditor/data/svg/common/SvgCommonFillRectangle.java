package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgCommonFillRectangle extends SvgObject {
    
    private Element rectangle;
    
    
    public SvgCommonFillRectangle(double x, double y, double width, double height) {

        rectangle = getDocument().createElementNS(SVG_NS, SVG_TYPE_RECTANGLE);
        rectangle.setAttributeNS(null, ATT_X, String.valueOf(x));
        rectangle.setAttributeNS(null, ATT_Y, String.valueOf(y));
        rectangle.setAttributeNS(null, ATT_WIDTH, String.valueOf(width));
        rectangle.setAttributeNS(null, ATT_HEIGHT, String.valueOf(height));
        rectangle.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
    }

    @Override
    public Element getElement() {
        return rectangle;
    }
    
    
}
