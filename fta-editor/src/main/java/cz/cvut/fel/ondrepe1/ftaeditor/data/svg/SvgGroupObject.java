package cz.cvut.fel.ondrepe1.ftaeditor.data.svg;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.Point;

/**
 *
 * @author ondrepe
 */
public abstract class SvgGroupObject extends SvgObject {
    
    private Size size;
    private Point position;
    private Point outputPoint;
    private AbstractSymbol symbol;

    public SvgGroupObject(int x, int y) {
        this(new Point(x, y));
    }
    
    public SvgGroupObject(Point position) {
        this.position = position;
        initGroup();
    }
    
    private void initGroup() {
        size = initSize();
        symbol = initSymbol();
        setOutputPoint(position);
        init();
    }
    
    protected abstract void init();
    
    protected abstract Size initSize();
    protected abstract AbstractSymbol initSymbol();
    
    public Size getSize() {
        return size;
    };

    public Point getPosition() {
        return position;
    }
    
    public Point getOutputPoint() {
        return outputPoint;
    }

    private void setOutputPoint(Point position) {
        this.outputPoint = new Point(position.x + ((int) (getSize().getWidth() / 2)), position.y);
    }

    public AbstractSymbol getSymbol() {
        return symbol;
    }
    
    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }
    
    public void setPosition(Point position) {
        this.position = position;
        setOutputPoint(position);
        init();
    }
}
