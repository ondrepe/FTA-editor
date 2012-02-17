package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgCircle extends SvgObject{

    public static final int RADIUS = 25;
    
    private Element circle;
    
    public SvgCircle(double x ,double y) {

        circle = getDocument().createElementNS(SVG_NS, SVG_TYPE_CIRCLE);
        circle.setAttributeNS(null, ATT_CENTER_X, String.valueOf(x));
        circle.setAttributeNS(null, ATT_CENTER_Y, String.valueOf(y));
        circle.setAttributeNS(null, ATT_RADIUS, String.valueOf(RADIUS));
        
        circle.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        circle.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        circle.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
    }
    
    @Override
    public Element getElement() {
        return circle;
    }

}
