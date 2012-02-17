package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgLine extends SvgObject {

    private Element line;
    
    public SvgLine(double x1 ,double y1, double x2, double y2) {

        line = getDocument().createElementNS(SVG_NS, SVG_TYPE_LINE);
        line.setAttributeNS(null, ATT_X_1, String.valueOf(x1));
        line.setAttributeNS(null, ATT_Y_1, String.valueOf(y1));
        line.setAttributeNS(null, ATT_X_2, String.valueOf(x2));
        line.setAttributeNS(null, ATT_Y_2, String.valueOf(y2));
        
        line.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        line.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
    }
    
    @Override
    public Element getElement() {
        return line;
    }

}
