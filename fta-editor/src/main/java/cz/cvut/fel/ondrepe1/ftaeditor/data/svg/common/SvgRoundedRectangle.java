package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgRoundedRectangle extends SvgObject {

    private Element rectangle;
    
    
    public SvgRoundedRectangle(double x, double y) {

        rectangle = getDocument().createElementNS(SVG_NS, SVG_TYPE_RECTANGLE);
        rectangle.setAttributeNS(null, ATT_X, String.valueOf(x));
        rectangle.setAttributeNS(null, ATT_Y, String.valueOf(y));
        rectangle.setAttributeNS(null, ATT_WIDTH, String.valueOf(SvgRectangle.WIDTH - DEFUAL_STEP_VALUE));
        rectangle.setAttributeNS(null, ATT_HEIGHT, String.valueOf(SvgRectangle.HEIGHT));
        rectangle.setAttributeNS(null, ATT_RADIUS_X, String.valueOf(SvgCircle.RADIUS));
        rectangle.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        rectangle.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        rectangle.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
    }

    @Override
    public Element getElement() {
        return rectangle;
    }
}
