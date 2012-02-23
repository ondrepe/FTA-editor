package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgCircle.RADIUS;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.DEFUAL_STEP_VALUE;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.SVG_TYPE_GROUP;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLabel;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLine;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.HEIGHT;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.WIDTH;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRoundedRectangle;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="svgConditionalEvent")
public class SvgConditionalEvent extends SvgGroupObject {

    private Element group;

    public SvgConditionalEvent() {
    }
    
    public SvgConditionalEvent(int x, int y) {
        super(x, y);
    }

    @Override
    public void initElement() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(getPosition().x, getPosition().y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        SvgRoundedRectangle roundedRectangle = new SvgRoundedRectangle(getPosition().x + (DEFUAL_STEP_VALUE / 2), getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        Node roundedRect = getDocument().importNode(roundedRectangle.getElement(), true);
        group.appendChild(roundedRect);
        
        SvgLine line = new SvgLine(getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT, getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
        
        String label = getSymbol().getLabel();
        if (label != null) {
            SvgLabel text = new SvgLabel(getPosition().x + (WIDTH / 2), getPosition().y + getSize().getHeight() + 20, label);
            Node tx = getDocument().importNode(text.getElement(), true);
            group.appendChild(tx);
        }
    }

    @Override
    @XmlTransient
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
