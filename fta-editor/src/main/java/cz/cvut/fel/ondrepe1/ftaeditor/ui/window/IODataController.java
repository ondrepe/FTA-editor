package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataLoadListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataSaveListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataStartItem;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ondrepe
 */
public class IODataController implements IDataSaveListener, IDataLoadListener {

    private JAXBContext context;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public IODataController() {
        try {
            context = JAXBContext.newInstance(FtaData.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            unmarshaller = context.createUnmarshaller();

        } catch (JAXBException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registerListeners() {
        FtaControllCenter.registerGlobalEventListener(DataSaveEvent.class, this);
        FtaControllCenter.registerGlobalEventListener(DataLoadEvent.class, this);
    }

    public void onEvent(DataSaveEvent event) {
        FileWriter fw = null;
        try {
            String selectedFilePath = event.getFilePath();
            if(!selectedFilePath.endsWith(".fta")) {
                selectedFilePath += ".fta";
            } 
            fw = new FileWriter(selectedFilePath);
            marshaller.marshal(FtaControllCenter.getActualData(), fw);
        } catch (IOException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void onEvent(DataLoadEvent event) {
        try {
            FileReader fr = new FileReader(event.getFile());
            FtaData object = (FtaData) unmarshaller.unmarshal(fr);
            completeLoadData(object);
            object.reloadNodes();
            FtaControllCenter.addUnit(event.getFile().getName(), object);
            FtaControllCenter.fireLocalEvent(new DataChangedEvent(object));
        } catch (JAXBException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void completeLoadData(FtaData data) {
        if (data != null) {
            FtaDataStartItem startItem = data.getStartItem();
            completeLoadDataInternal(startItem);
        }
    }
    
    private void completeLoadDataInternal(FtaDataItem parent) {
        if (parent != null) {
            for (int i = 0; i != parent.getChildrenCount(); i++) {
                FtaDataItem child = parent.getChildAt(i);
                child.setParent(parent);
                completeLoadDataInternal(child);
            }
        }
    }
}
