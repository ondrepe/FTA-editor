package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.*;

/**
 *
 * @author ondrepe
 */
public class TestDataFactory {

    public static AbstractGate generateFTATree() {
        OrGate top = new OrGate();
        
        InhibitGate inhibitGate = new InhibitGate();
        ConditionalEvent cEvent = new ConditionalEvent();
        BasicEvent bEvent = new BasicEvent();
        inhibitGate.addChild(cEvent);
        inhibitGate.addChild(bEvent);
        top.addChild(inhibitGate);
        
        ExclusiveOrGate xor = new ExclusiveOrGate();
        bEvent = new BasicEvent();
        xor.addChild(bEvent);
        bEvent = new BasicEvent();
        xor.addChild(bEvent);
        top.addChild(xor);
        
        AndGate and = new AndGate();
        bEvent = new BasicEvent();
        and.addChild(bEvent);
        bEvent = new BasicEvent();
        and.addChild(bEvent);
        bEvent = new BasicEvent();
        and.addChild(bEvent);
        top.addChild(and);
        
        NotGate t = new NotGate();
        t.addChild(top);
        
        return t;
    }
}
