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
        this.outputPoint = new Point(position.x + ((int) (getSize().getWidth() / 2)), position.y);
    }
    
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

    public AbstractSymbol getSymbol() {
        return symbol;
    }
}
