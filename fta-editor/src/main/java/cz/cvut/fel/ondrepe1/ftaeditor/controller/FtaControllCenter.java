package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorAddTabEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorTabChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor.IEditorTabChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.DiagramTreePanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorTabbedPanelItem;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ondrejicek
 */
public class FtaControllCenter implements IEditorTabChangedListener {

    private static FtaControllCenter instance;

    public static FtaControllCenter init() {
        if (instance == null) {
            instance = new FtaControllCenter();
        }
        instance.initInternal();
        return instance;
    }
    
    public static void fireGlobalEvent(CommonGlobalEvent event) {
        instance.fireGlobalEventInternal(event);
    }
    
    public static void registerGlobalEventListener(Class<? extends CommonGlobalEvent> clazz, IGlobalEventListener listener) {
        instance.registerGlobalEventListenerInternal(clazz, listener);
    }
    
    public static void fireLocalEvent(CommonLocalEvent event) {
        instance.getActiveUnit().fireEvent(event);
    }
    
    public static void registerLocalEventListener(Class<? extends CommonLocalEvent> clazz, ILocalEventListener listener) {
        instance.getActiveUnit().registerEventListener(clazz, listener);
        //        for (FtaControllCenterUnit unit : instance.getUnits()) {
//            unit.registerEventListener(clazz, listener);
//        }
    }
    
    public static FtaData getActualData() {
        return instance.getActiveUnit().getData();
    }
    
    public static void addRegistrable(IRegisterable registerable) {
        instance.addRegistrableInternal(registerable);
    }
    
    public static FtaDataController getDataController() {
        return instance.getActiveUnit().getFtaDataController();
    }
    
    public static void addNewUnit() {
        instance.addNewUnitInternal();
    }
    
    public static void addUnit(String name, FtaData data) {
        instance.addUnitInternal(name, data);
    }
    
    private Map<Class, List<IGlobalEventListener>> listeners;
    private List<FtaControllCenterUnit> units;
    private FtaControllCenterUnit activeUnit;
    private List<IRegisterable> tempRegisterables;
    private DiagramTreePanel diagramTreePanel;
    
    private FtaControllCenter() {
        units = new ArrayList<FtaControllCenterUnit>();
        tempRegisterables = new ArrayList<IRegisterable>();
        listeners = new HashMap<Class, List<IGlobalEventListener>>();
    }
    
    public void initInternal() {
        IODataController ioData = new IODataController();
        ioData.registerListeners();
        registerListeners();
    }

    public void setDiagramTreePanel(DiagramTreePanel diagramTreePanel) {
        this.diagramTreePanel = diagramTreePanel;
    }
    
    public void addRegistrableInternal(IRegisterable registerable) {
        tempRegisterables.add(registerable);
    }

    public FtaControllCenterUnit getActiveUnit() {
        return activeUnit;
    }

    public void setActiveUnit(FtaControllCenterUnit activUnit) {
        this.activeUnit = activUnit;
    }
    
    public void addNewUnitInternal() {
        FtaControllCenterUnit unit = new FtaControllCenterUnit();
        addAnyUnit(unit);
    }
    
    public void addUnitInternal(String name, FtaData data) {
        FtaControllCenterUnit unit = new FtaControllCenterUnit(name, data);
        addAnyUnit(unit);
    }

    public List<FtaControllCenterUnit> getUnits() {
        return units;
    }
    
    private void addAnyUnit(FtaControllCenterUnit unit) {
        units.add(unit);
        setActiveUnit(unit);
        
        diagramTreePanel.registerListeners();
        for (IRegisterable registerable : tempRegisterables) {
            registerable.registerListeners();
        }
        tempRegisterables.clear();
        
        this.fireGlobalEventInternal(new EditorAddTabEvent(unit.getEditorTabbedPanelItem()));
    }
    
    private void changeActiveUnitInternal(EditorTabbedPanelItem item) {
        for (FtaControllCenterUnit unit : units) {
            if (unit.getEditorTabbedPanelItem() == item) {
                setActiveUnit(unit);
                break;
            }
        }
    }
    
    public void fireGlobalEventInternal(CommonGlobalEvent event) {
        List<IGlobalEventListener> list = listeners.get(event.getClass());
        if (list != null) {
            for (IGlobalEventListener listener : list) {
                try {
                    Method method = listener.getClass().getMethod("onEvent", event.getClass());
                    method.invoke(listener, event);
                } catch (Exception ex) {
                    Logger.getLogger(FtaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void registerGlobalEventListenerInternal(Class<? extends CommonGlobalEvent> clazz, IGlobalEventListener listener) {
    
        List<IGlobalEventListener> list = listeners.get(clazz);
        if (list == null) {
            list = new ArrayList<IGlobalEventListener>();
        }
        list.add(listener);
        listeners.put(clazz, list);
    }

    public void onEvent(EditorTabChangedEvent event) {
        changeActiveUnitInternal(event.getItem());
        fireLocalEvent(new DataChangedEvent(getActiveUnit().getData()));
    }

    private void registerListeners() {
        this.registerGlobalEventListenerInternal(EditorTabChangedEvent.class, this);
    }
}
