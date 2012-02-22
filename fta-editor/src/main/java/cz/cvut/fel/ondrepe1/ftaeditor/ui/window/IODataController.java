package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataLoadListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataSaveListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

            registerListeners();
        } catch (JAXBException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(DataSaveEvent.class, this);
        FtaController.getInstance().registerEventListener(DataLoadEvent.class, this);
    }

    public void onEvent(DataSaveEvent event) {
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(event.getFile());
//            marshaller.marshal(event.getData(), fw);
//        } catch (IOException ex) {
//            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (JAXBException ex) {
//            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                fw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public void onEvent(DataLoadEvent event) {
        try {
            FileReader fr = new FileReader(event.getFile());
            FtaData object = (FtaData) unmarshaller.unmarshal(fr);
            object.reloadNodes();
            FtaController.getInstance().fireEvent(new DataChangedEvent(object));
        } catch (JAXBException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IODataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
