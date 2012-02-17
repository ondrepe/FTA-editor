package cz.cvut.fel.ondrepe1.ftaeditor.data.svg;

import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgCircle;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.SVG_TYPE_GROUP;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLine;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class SvgBasicEvent extends SvgObject {

    private Element group;
    
    public SvgBasicEvent(double x, double y) {

        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(x, y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        SvgCircle circle = new SvgCircle(x + (SvgRectangle.WIDTH / 2), y + SvgRectangle.HEIGHT + SvgCircle.RADIUS + 5);
        Node circ = getDocument().importNode(circle.getElement(), true);
        group.appendChild(circ);
        
        SvgLine line = new SvgLine(x + (SvgRectangle.WIDTH / 2), y + SvgRectangle.HEIGHT, x + (SvgRectangle.WIDTH / 2), y + SvgRectangle.HEIGHT + 5);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
    }

    @Override
    public Element getElement() {
        return group;
    }

}
