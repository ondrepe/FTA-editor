package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.common.ErrorMessage;
import cz.cvut.fel.ondrepe1.ftaeditor.common.iface.IValidable;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class AbstractSymbol implements IValidable, Cloneable {

    private AbstractGate parent;
    private String text;
    private String label;
    private Float failureProbability;
    
    private List<ErrorMessage> errorMessages;

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
    
    public List<ErrorMessage> getErrorMessages() {
        if (errorMessages == null) errorMessages = new ArrayList<ErrorMessage>();
        return errorMessages;
    }
    
    public boolean hasError() {
        if (errorMessages == null || errorMessages.isEmpty()) return false;
        return true;
    }
    
    public void addErrorMessage(ErrorMessage message) {
        getErrorMessages().add(message);
    }
    
    public void addErrorMessage(String field, String message) {
        getErrorMessages().add(new ErrorMessage(field, message));
    }

    public boolean validate() {
        validateInternal();
        return !hasError();
    }
    
    protected abstract void validateInternal();

    public AbstractGate getParent() {
        return parent;
    }

    public void setParent(AbstractGate parent) {
        this.parent = parent;
    }
    
    public abstract String getType();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    
}
