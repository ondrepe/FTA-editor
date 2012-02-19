package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddChildEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class FtaDataItem implements IDataItem{

    private SvgGroupObject svgObject;
    private FtaDataItem parent;
    private List<FtaDataItem> children;

    public FtaDataItem(SvgGroupObject svgObject) {
        this.svgObject = svgObject;
        children = new ArrayList<FtaDataItem>();
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
            FtaController.getInstance().fireEvent(new DataAddChildEvent(child));
            result = true;
        }
        return result;
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
}
