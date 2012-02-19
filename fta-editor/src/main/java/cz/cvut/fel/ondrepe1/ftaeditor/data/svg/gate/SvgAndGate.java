package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgCircle.RADIUS;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgLine;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.HEIGHT;
import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle.WIDTH;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AndGate;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class SvgAndGate extends SvgGroupObject {

    private Element group;
    
    public SvgAndGate(int x, int y) {
        super(x, y);
    }

    @Override
    protected void init() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(getPosition().x, getPosition().y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        Element andGate = getDocument().createElementNS(SVG_NS, SVG_TYPE_PATH);
        String data = "M " + String.valueOf(getPosition().x + (WIDTH / 2) - RADIUS) + " " + String.valueOf(getPosition().y  + HEIGHT + DEFUAL_STEP_VALUE + 55) +" l 0 -30 a " + String.valueOf(RADIUS) + " " + String.valueOf(RADIUS) + " 0 0,1 50,0 l 0,30 Z";
        andGate.setAttributeNS(null, ATT_DATA, data);
        andGate.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        andGate.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        andGate.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        group.appendChild(andGate);
        
        SvgLine line = new SvgLine(getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT, getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
    }

    @Override
    public Element getElement() {
        return group;
    }

    @Override
    protected Size initSize() {
        return new Size(WIDTH, HEIGHT + DEFUAL_STEP_VALUE + 55);
    }

    @Override
    protected AbstractSymbol initSymbol() {
        return new AndGate();
    }

}
