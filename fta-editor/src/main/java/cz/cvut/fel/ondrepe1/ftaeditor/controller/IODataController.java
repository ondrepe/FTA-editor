package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.UniversalErrorEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataLoadListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataSaveListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataStartItem;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrepe
 */
public class IODataController implements IDataSaveListener, IDataLoadListener {

    private static final Logger logger = LoggerFactory.getLogger(IODataController.class);
    
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
            logger.error("Chyba při načítání JAXB kontextu", ex);
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
            
            FtaControllCenter.fireGlobalEvent(new DataSaveCompleteEvent(event.getFileName()));
        } catch (Exception ex) {
            FtaControllCenter.fireGlobalEvent(new UniversalErrorEvent("Data nelze uložit do vybraného souboru"));
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                FtaControllCenter.fireGlobalEvent(new UniversalErrorEvent("Problém při ukládání souboru"));
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
        } catch (Exception ex) {
            FtaControllCenter.fireGlobalEvent(new UniversalErrorEvent("Soubor nelze nahrát"));
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
