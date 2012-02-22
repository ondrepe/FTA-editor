package cz.cvut.fel.ondrepe1.ftaeditor.data.svg;

import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Position;
import cz.cvut.fel.ondrepe1.ftaeditor.data.common.Size;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgBasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate.SvgAndGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.Point;
import javax.xml.bind.annotation.*;

/**
 *
 * @author ondrepe
 */
@XmlSeeAlso({ SvgBasicEvent.class, SvgConditionalEvent.class, SvgAndGate.class })
@XmlAccessorType( XmlAccessType.PUBLIC_MEMBER )
@XmlType(propOrder={"size", "pos", "symbol"})
public abstract class SvgGroupObject extends SvgObject {
    
    private Size size;
    private Position pos;
    
    private Point position;
    private Point outputPoint;
    private Point inputPoint;
    private AbstractSymbol symbol;

    public SvgGroupObject() {
    }
    
    public SvgGroupObject(int x, int y) {
        this(new Point(x, y));
    }
    
    public SvgGroupObject(Point position) {
        this.position = position;
        this.pos = new Position(position.x, position.y);
        initGroup();
    }
    
    private void initGroup() {
        size = initSize();
        symbol = initSymbol();
        setOutputPoint(position);
        setInputPoint(position);
        init();
    }
    
    protected abstract void init();
    
    protected abstract Size initSize();
    protected abstract AbstractSymbol initSymbol();
    
    public Size getSize() {
        return size;
    };

    public void setSize(Size size) {
        this.size = size;
    }
    
    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
        setPosition(pos.getX(), pos.getY());
    }
    
    public AbstractSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(AbstractSymbol symbol) {
        this.symbol = symbol;
    }
    

    @XmlTransient
    public Point getPosition() {
        return position;
    }
    
    public Point getOutputPoint() {
        return outputPoint;
    }

    public Point getInputPoint() {
        return inputPoint;
    }

    private void setOutputPoint(Point position) {
        this.outputPoint = new Point(position.x + ((int) (getSize().getWidth() / 2)), position.y);
    }
    
    private void setInputPoint(Point position) {
        this.inputPoint = new Point(position.x + ((int) (getSize().getWidth() / 2)), (int) (position.y + getSize().getHeight()));
    }
    
    public void setPosition(int x, int y) {
        setPosition(new Point(x, y));
    }

    public void setPosition(Point position) {
        this.position = position;
        this.pos = new Position(position.x, position.y);
        setOutputPoint(position);
        setInputPoint(position);
        init();
    }
}
