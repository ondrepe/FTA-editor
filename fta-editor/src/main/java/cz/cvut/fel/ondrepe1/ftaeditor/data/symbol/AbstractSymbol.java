package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.UndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AndGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.ExclusiveOrGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.InhibitGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.MajorityVoteGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.NotGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.OrGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.PriorityAndGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.TransferGate;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author ondrepe
 */
@XmlSeeAlso({BasicEvent.class, ConditionalEvent.class, DormantEvent.class, UndevelopedEvent.class, AndGate.class, ExclusiveOrGate.class, InhibitGate.class, MajorityVoteGate.class, NotGate.class, OrGate.class, PriorityAndGate.class, TransferGate.class})
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
