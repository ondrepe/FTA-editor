package cz.cvut.fel.ondrepe1.ftaeditor.data;

/**
 *
 * @author ondrepe
 */
public class FtaData {

    FtaDataStartItem startItem;
    
    public FtaData() {
        startItem = new FtaDataStartItem();
    }

    public FtaDataStartItem getStartItem() {
        return startItem;
    }
}
