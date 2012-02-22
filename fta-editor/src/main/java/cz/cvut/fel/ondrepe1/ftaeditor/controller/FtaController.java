package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ondrepe
 */
public class FtaController {
    
    private Map<Class, List<ILocalEventListener>> listeners;

    public FtaController() {
        listeners = new HashMap<Class, List<ILocalEventListener>>();
    }
    
    public void fireEvent(CommonLocalEvent event) {
        List<ILocalEventListener> list = listeners.get(event.getClass());
        if (list != null) {
            for (ILocalEventListener listener : list) {
                try {
                    Method method = listener.getClass().getMethod("onEvent", event.getClass());
                    method.invoke(listener, event);
                } catch (Exception ex) {
                    Logger.getLogger(FtaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void registerEventListener(Class<? extends CommonLocalEvent> clazz, ILocalEventListener listener) {
    
        List<ILocalEventListener> list = listeners.get(clazz);
        if (list == null) {
            list = new ArrayList<ILocalEventListener>();
        }
        list.add(listener);
        listeners.put(clazz, list);
    }
}
