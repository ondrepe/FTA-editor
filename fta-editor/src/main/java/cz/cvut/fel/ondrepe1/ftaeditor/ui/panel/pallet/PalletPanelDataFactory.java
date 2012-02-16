package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.UndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public class PalletPanelDataFactory {

    private static PalletPanelDataFactory instance;

    public static PalletPanelDataFactory getInstance() {
        if (instance == null) {
            instance = new PalletPanelDataFactory();
        }
        return instance;
    }
    
    private List<AbstractSymbol> eventsList;
    private List<AbstractSymbol> gatesList;

    public PalletPanelDataFactory() {
        eventsList = new ArrayList<AbstractSymbol>();
        gatesList = new ArrayList<AbstractSymbol>();
        fillEventsList();
        fillGatesList();
    }
    
    public List<AbstractSymbol> getEvents() {
        return eventsList;
    }

    public List<AbstractSymbol> getGates() {
        return gatesList;
    }
    
    private void fillEventsList() {
        eventsList.add(new BasicEvent());
        eventsList.add(new ConditionalEvent());
        eventsList.add(new DormantEvent());
        eventsList.add(new UndevelopedEvent());
    }
    
    private void fillGatesList() {
        gatesList.add(new OrGate());
        gatesList.add(new AndGate());
        gatesList.add(new NotGate());
        gatesList.add(new ExclusiveOrGate());
        gatesList.add(new MajorityVoteGate());
        gatesList.add(new PriorityAndGate());
    }
}
