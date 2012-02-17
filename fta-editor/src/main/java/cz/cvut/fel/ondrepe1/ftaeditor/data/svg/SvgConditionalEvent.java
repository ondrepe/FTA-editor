package cz.cvut.fel.ondrepe1.ftaeditor.data.svg;

import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.*;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.SVG_TYPE_GROUP;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class SvgConditionalEvent extends SvgObject {

    private Element group;
    
    public SvgConditionalEvent(double x, double y) {

        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(x, y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        SvgCircle circle1 = new SvgCircle(x + (SvgRectangle.WIDTH / 2) - SvgCircle.RADIUS + 5, y + SvgRectangle.HEIGHT + SvgCircle.RADIUS + 5);
        Node circ1 = getDocument().importNode(circle1.getElement(), true);
        group.appendChild(circ1);
        
        SvgCircle circle2 = new SvgCircle(x + (SvgRectangle.WIDTH / 2) + SvgCircle.RADIUS - 5, y + SvgRectangle.HEIGHT + SvgCircle.RADIUS + 5);
        Node circ2 = getDocument().importNode(circle2.getElement(), true);
        group.appendChild(circ2);
        
        SvgCommonRectangle rectangle1 = new SvgCommonRectangle(x + SvgCircle.RADIUS, y + SvgRectangle.HEIGHT + 5, SvgRectangle.WIDTH - (2 * SvgCircle.RADIUS), SvgRectangle.HEIGHT);
        Node rect1 = getDocument().importNode(rectangle1.getElement(), true);
        group.appendChild(rect1);
        
        SvgCommonFillRectangle rectangle2 = new SvgCommonFillRectangle(x + SvgCircle.RADIUS  - (LINE_WIDTH / 2), y + SvgRectangle.HEIGHT + 5 + (LINE_WIDTH / 2) + 0.5, SvgRectangle.WIDTH - (2 * SvgCircle.RADIUS) + (2 * LINE_WIDTH), SvgRectangle.HEIGHT - LINE_WIDTH);
        Node rect2 = getDocument().importNode(rectangle2.getElement(), true);
        group.appendChild(rect2);
        
        SvgLine line = new SvgLine(x + (SvgRectangle.WIDTH / 2), y + SvgRectangle.HEIGHT, x + (SvgRectangle.WIDTH / 2), y + SvgRectangle.HEIGHT + 5);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
    }

    @Override
    public Element getElement() {
        return group;
    }
}
