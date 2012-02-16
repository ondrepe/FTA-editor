package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ondrepe
 */
public class FtaController {

    private static FtaController instance;
    
    public static FtaController getInstance() {
        if (instance == null) {
            instance = new FtaController();
        }
        return instance;
    }
    
    private Map<Class, List<IEventListener>> listeners;

    public FtaController() {
        listeners = new HashMap<Class, List<IEventListener>>();
    }
    
    public void fireEvent(CommonEvent event) {
        List<IEventListener> list = listeners.get(event.getClass());
        if (list != null) {
            for (IEventListener listener : list) {
                EventHandler.handleEvent(event, listener);
            }
        }
    }
    
    public void registerEventListener(Class<? extends CommonEvent> clazz, IEventListener listener) {
    
        List<IEventListener> list = listeners.get(clazz);
        if (list == null) {
            list = new ArrayList<IEventListener>();
        }
        list.add(listener);
        listeners.put(clazz, list);
    }
}
