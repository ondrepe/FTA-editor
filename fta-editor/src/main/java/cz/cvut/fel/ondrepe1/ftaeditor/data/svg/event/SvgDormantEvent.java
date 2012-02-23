package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event;

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
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="svgDormantEvent")
public class SvgDormantEvent extends SvgGroupObject {

    private Element group;

    public SvgDormantEvent() {
    }

    public SvgDormantEvent(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void initElement() {
        group = getDocument().createElementNS(SVG_NS, SVG_TYPE_GROUP);
        
        SvgRectangle rectangle = new SvgRectangle(getPosition().x, getPosition().y);
        Node rect = getDocument().importNode(rectangle.getElement(), true);
        group.appendChild(rect);
        
        Element dormant1 = getDocument().createElementNS(SVG_NS, SVG_TYPE_POLYGON);
        
        String points = String.valueOf(getPosition().x + (WIDTH / 2)) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE) + " " +
                String.valueOf(getPosition().x + WIDTH - DEFUAL_STEP_VALUE) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + RADIUS) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2)) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + (2 * RADIUS)) + " " + 
                String.valueOf(getPosition().x + DEFUAL_STEP_VALUE) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + RADIUS);
        dormant1.setAttributeNS(null, ATT_POINTS, points);
        dormant1.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        dormant1.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        dormant1.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        group.appendChild(dormant1);
        
        Element dormant2 = getDocument().createElementNS(SVG_NS, SVG_TYPE_POLYGON);
        
        String points2 = String.valueOf(getPosition().x + (WIDTH / 2)) + "," + String.valueOf(getPosition().y + HEIGHT + (2 * DEFUAL_STEP_VALUE)) + " " +
                String.valueOf(getPosition().x + WIDTH - (2.5 * DEFUAL_STEP_VALUE)) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + RADIUS) + " " +
                String.valueOf(getPosition().x + (WIDTH / 2)) + "," + String.valueOf(getPosition().y + HEIGHT + (2 * RADIUS)) + " " + 
                String.valueOf(getPosition().x + (2.5 * DEFUAL_STEP_VALUE)) + "," + String.valueOf(getPosition().y + HEIGHT + DEFUAL_STEP_VALUE + RADIUS);
        dormant2.setAttributeNS(null, ATT_POINTS, points2);
        dormant2.setAttributeNS(null, ATT_STROKE_WIDTH, String.valueOf(LINE_WIDTH));
        dormant2.setAttributeNS(null, ATT_STROKE, COLOR_BLACK);
        dormant2.setAttributeNS(null, ATT_FILL, COLOR_WHITE);
        group.appendChild(dormant2);
        
        SvgLine line = new SvgLine(getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT, getPosition().x + (WIDTH / 2), getPosition().y + HEIGHT + DEFUAL_STEP_VALUE);
        Node ln = getDocument().importNode(line.getElement(), true);
        group.appendChild(ln);
        
        String label = getSymbol().getLabel();
        if (label != null) {
            SvgLabel text = new SvgLabel(getPosition().x + (WIDTH / 2), getPosition().y + getSize().getHeight() + 20, label);
            Node tx = getDocument().importNode(text.getElement(), true);
            group.appendChild(tx);
        }
        
        Float fp = getSymbol().getFailureProbability();
        if (label != null) {
            String q = "Q = " + String.valueOf(fp);
            SvgLabel svgQ = new SvgLabel(getPosition().x + (WIDTH / 2), getPosition().y + getSize().getHeight() + 30, q);
            Node lblQ = getDocument().importNode(svgQ.getElement(), true);
            group.appendChild(lblQ);
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
        return new DormantEvent();
    }

}
