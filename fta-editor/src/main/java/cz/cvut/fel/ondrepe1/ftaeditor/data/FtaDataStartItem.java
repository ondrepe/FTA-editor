package cz.cvut.fel.ondrepe1.ftaeditor.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
@XmlRootElement( name="ftaDataStartItem" )
@XmlAccessorType( XmlAccessType.NONE )
public class FtaDataStartItem extends FtaDataItem {

    @Override
    public boolean canAddChild() {
        return true;
    }
    
    @Override
    public Element getSvgElement() {
        return null;
    }
    
    @Override
    public String getSymbolType() {
        return null;
    }
    
    @Override
    public Class getSymbolClass() {
        return null;
    }
}
