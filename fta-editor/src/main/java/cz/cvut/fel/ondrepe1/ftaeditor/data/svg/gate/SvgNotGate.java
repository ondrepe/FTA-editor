package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgCircle.RADIUS;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLabel;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLine;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.HEIGHT;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.WIDTH;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.NotGate;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="svgNotGate")
public class SvgNotGate extends SvgGroupObject {

    private Element group;

    public SvgNotGate() {
    }

    public SvgNotGate(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void init() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(getPosition().x, getPosition().y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        Element not = getDocument().createElementNS(SVG_NS, SVG_TYPE_POLYGON);
        
        String points = String.valueOf(getPosition().x + (WIDTH / 2) - RADIUS) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2) + RADIUS) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2) - RADIUS) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + 55) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2) + RADIUS) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + 55) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2) - RADIUS) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        
        not.setAttributeNS(null, ATT_POINTS, points);
        not.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        not.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        not.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        group.appendChild(not);
        
        SvgLine line = new SvgLine(getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT, getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
        
        String label = getSymbol().getLabel();
        if (label != null) {
            SvgLabel text = new SvgLabel(getPosition().x + (WIDTH / 2), + getPosition().y + getSize().getHeight() - 20, label);
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
        return new NotGate();
    }

}
