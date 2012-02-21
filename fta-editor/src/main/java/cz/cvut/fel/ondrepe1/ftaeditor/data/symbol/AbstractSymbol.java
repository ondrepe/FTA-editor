package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AndGate;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author ondrepe
 */
@XmlSeeAlso({BasicEvent.class, ConditionalEvent.class, AndGate.class})
public abstract class AbstractSymbol implements Cloneable {

    private String text;
    private String label;
    private Float failureProbability;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Float getFailureProbability() {
        return failureProbability;
    }

    public void setFailureProbability(Float failureProbability) {
        this.failureProbability = failureProbability;
    }
    
    public abstract String getType();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    
}
