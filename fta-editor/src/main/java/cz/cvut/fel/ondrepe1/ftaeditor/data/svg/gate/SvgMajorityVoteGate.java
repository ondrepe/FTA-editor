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
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.MajorityVoteGate;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="svgMajorityVoteGate")
public class SvgMajorityVoteGate extends SvgGroupObject {

    private Element group;

    public SvgMajorityVoteGate() {
    }
    
    public SvgMajorityVoteGate(int x, int y) {
        super(x, y);
    }

    @Override
    public void initElement() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        Element innerGroup = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(0, 0);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        innerGroup.appendChild(rect);
        
        Element orGate = getDocument().createElementNS(SVG_NS, SVG_TYPE_PATH);
        String data = "M " + String.valueOf((WIDTH / 2) - RADIUS) + "," + String.valueOf(HEIGHT + DEFUAL_STEP_VALUE + 55) + " " +
                String.valueOf((WIDTH / 2) - RADIUS) + "," + String.valueOf(HEIGHT + DEFUAL_STEP_VALUE + 30) + 
                " c 1,-17 16,-29 25,-30 c 11,1 25,16 25,30 l 0,25 c -15,-15 -35,-15 " + String.valueOf(-(2 * RADIUS)) + ",0z";
        orGate.setAttributeNS(null, ATT_DATA, data);
        orGate.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        orGate.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        orGate.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        innerGroup.appendChild(orGate);
        
        SvgLabel mText = new SvgLabel((WIDTH / 2), HEIGHT + 3 * DEFUAL_STEP_VALUE, "m");
        Node mNode = getDocument().importNode(mText.getElement(), true);
        innerGroup.appendChild(mNode);
        
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
        return new MajorityVoteGate();
    }

}
