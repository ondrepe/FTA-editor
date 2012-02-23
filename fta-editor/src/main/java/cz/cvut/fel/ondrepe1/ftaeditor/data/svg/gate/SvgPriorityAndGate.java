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
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.PriorityAndGate;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="svgPriorityAndGate")
public class SvgPriorityAndGate extends SvgGroupObject {

    private Element group;

    public SvgPriorityAndGate() {
    }
    
    public SvgPriorityAndGate(int x, int y) {
        super(x, y);
    }

    @Override
    public void initElement() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        Element innerGroup = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(0, 0);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        innerGroup.appendChild(rect);
        
        Element andGate = getDocument().createElementNS(SVG_NS, SVG_TYPE_PATH);
        String data = "M " + String.valueOf((WIDTH / 2) - RADIUS) + " " + String.valueOf(HEIGHT + DEFUAL_STEP_VALUE + 55) +" l 0 -30 a " + String.valueOf(RADIUS) + " " + String.valueOf(RADIUS) + " 0 0,1 50,0 l 0,30 Z";
        andGate.setAttributeNS(null, ATT_DATA, data);
        andGate.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        andGate.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        andGate.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        innerGroup.appendChild(andGate);
        
        SvgLine pandLine = new SvgLine((WIDTH / 2) - RADIUS, HEIGHT + 55, (WIDTH / 2) + RADIUS, HEIGHT + 55);
        Node pln = getDocument().importNode(pandLine.getElement(), true);
        innerGroup.appendChild(pln);
        
        SvgLine line = new SvgLine((WIDTH / 2), HEIGHT, (WIDTH / 2), HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        innerGroup.appendChild(ln);
        
        String label = getSymbol().getLabel();
        if (label != null) {
            SvgLabel text = new SvgLabel((WIDTH / 2), getSize().getHeight() - 20, label);
            Node tx = getDocument().importNode(text.getElement(), true);
            innerGroup.appendChild(tx);
        }
        
        innerGroup.setAttributeNS(null, "transform", "translate(" + String.valueOf(getPosition().x) + "," + String.valueOf(getPosition().y)+")");
        
        group.appendChild(innerGroup);
    }

    @Override
    @XmlTransient
    public Element getElement() {
        return group;
    }

    @Override
    protected Size initSize() {
        return new Size(WIDTH, HEIGHT + DEFUAL_STEP_VALUE + 55);
    }

    @Override
    protected AbstractSymbol initSymbol() {
        return new PriorityAndGate();
    }

}
