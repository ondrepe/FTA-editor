package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddChildEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
@XmlRootElement( name="ftaDataItem" )
@XmlAccessorType( XmlAccessType.NONE )
public class FtaDataItem implements IDataItem{

    @XmlElement
    private SvgGroupObject svgObject;
    private FtaDataItem parent;
    @XmlElement
    private List<FtaDataItem> children;

    public FtaDataItem() {
        children = new ArrayList<FtaDataItem>();
    }
    
    public FtaDataItem(SvgGroupObject svgObject) {
        this();
        this.svgObject = svgObject;
    }
    
    public Element getSvgElement() {
        return svgObject.getElement();
    }
    
    public String getSymbolType() {
        return svgObject.getSymbol().getType();
    }
    
    public Class getSymbolClass() {
        return svgObject.getSymbol().getClass();
    }

    // Parent
    public FtaDataItem getParent() {
        return parent;
    }

    public void setParent(FtaDataItem parent) {
        this.parent = parent;
    }
    
    public boolean hasParent() {
        return parent != null;
    }
    
    // Children
    public FtaDataItem getChildAt(int index) {
        return children.get(index);
    }
    
    public int getIndexOfChild(FtaDataItem child) {
        return children.indexOf(child);
    }
    
    public int getChildrenCount() {
        return children.size();
    }
    
    public boolean hasChilren() {
        return !children.isEmpty();
    }
    
    public boolean canAddChild() {
        boolean result = false;
        if (svgObject.getSymbol() instanceof AbstractGate) {
            AbstractGate gate = (AbstractGate) svgObject.getSymbol();
            int childCount = children.size();
            if (childCount < gate.getMaxCountChildren()) {
                result = true;
            }
        }
        return result;
    }
    
    public boolean addChild(FtaDataItem child) {
        boolean result = false;
        if (canAddChild()) {
            children.add(child);
            child.setParent(this);
            // fire event
            FtaControllCenter.fireLocalEvent(new DataAddChildEvent(child));
            result = true;
        }
        return result;
    }

    protected List<FtaDataItem> getChildren() {
        return children;
    }
    
    public boolean isLeaf() {
        boolean result = false;
        if (children.isEmpty()) {
            result = true;
        }
        return result;
    }

    public String getLabel() {
        return svgObject.getSymbol().getLabel();
    }

    public String getText() {
        return svgObject.getSymbol().getText();
    }

    public Float getFailureProbability() {
        return svgObject.getSymbol().getFailureProbability();
    }

    public void setLabel(String label) {
        svgObject.getSymbol().setLabel(label);
    }

    public void setText(String text) {
        svgObject.getSymbol().setText(text);
    }

    public void setFailureProbability(Float failureProbability) {
        svgObject.getSymbol().setFailureProbability(failureProbability);
    }

    public boolean isValid() {
        return false;
    }
    
    public Point getPosition() {
        return svgObject.getPosition();
    }
    
    public void setPosition(int x, int y) {
        svgObject.setPosition(x, y);
    }
    
    public void setPosition(Point position) {
        svgObject.setPosition(position);
    }
    
    public Point getOutputPoint() {
        return svgObject.getOutputPoint();
    }
    
    public Point getInputPoint() {
        return svgObject.getInputPoint();
    }
    
    public void removeChild(FtaDataItem child){
        getChildren().remove(child);
    }
}
