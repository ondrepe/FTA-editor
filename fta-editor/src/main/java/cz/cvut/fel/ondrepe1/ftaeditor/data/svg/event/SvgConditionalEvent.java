package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgCircle.RADIUS;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.DEFUAL_STEP_VALUE;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.SVG_TYPE_GROUP;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLine;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.HEIGHT;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.WIDTH;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRoundedRectangle;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class SvgConditionalEvent extends SvgGroupObject {

    private Element group;
    
    public SvgConditionalEvent(int x, int y) {
        super(x, y);
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(x, y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        SvgRoundedRectangle roundedRectangle = new SvgRoundedRectangle(x + (DEFUAL_STEP_VALUE / 2), y + HEIGHT + DEFUAL_STEP_VALUE);
        Node roundedRect = getDocument().importNode(roundedRectangle.getElement(), true);
        group.appendChild(roundedRect);
        
        SvgLine line = new SvgLine(x + (WIDTH / 2), y + HEIGHT, x + (WIDTH / 2), y + HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
    }

    @Override
    public Element getElement() {
        return group;
    }
    
    @Override
    protected Size initSize() {
        return new Size(WIDTH, HEIGHT + DEFUAL_STEP_VALUE + (2 * RADIUS));
    }

    @Override
    protected AbstractSymbol initSymbol() {
        return new ConditionalEvent();
    }
}
