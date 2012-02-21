package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.*;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataAddChildListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataAddLonelyItemListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataConnectItemsListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataEditItemListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.move.IDataItemMovedCompleteListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
@XmlRootElement( name="ftaData" )
public class FtaData implements IDataAddChildListener, IDataItemMovedCompleteListener, IDataAddLonelyItemListener, IDataConnectItemsListener, IDataEditItemListener {

    @XmlElement
    private FtaDataStartItem startItem;
    
    private Document document;
    private Element svgRoot;
    private Map<FtaDataItem, Node> nodes;
    
    public FtaData() {
        startItem = new FtaDataStartItem();
        nodes = new HashMap<FtaDataItem, Node>();
        
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        document = impl.createDocument(svgNS, "svg", null);
        svgRoot = document.getDocumentElement();
        svgRoot.setAttributeNS(null, "width", "1000");
        svgRoot.setAttributeNS(null, "height", "1000");
        
        registerListeners();
    }

    public FtaDataStartItem getStartItem() {
        return startItem;
    }

    public Document getDocument() {
        return document;
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(DataAddChildEvent.class, this);
        FtaController.getInstance().registerEventListener(DataItemMovedCompleteEvent.class, this);
        FtaController.getInstance().registerEventListener(DataAddLonelyItemEvent.class, this);
        FtaController.getInstance().registerEventListener(DataConnectItemsEvent.class, this);
        FtaController.getInstance().registerEventListener(DataEditItemEvent.class, this);
    }

    public void onEvent(DataAddChildEvent event) {
        Node node = document.importNode(event.getDataItem().getSvgElement(), true);
        svgRoot.appendChild(node);
        nodes.put(event.getDataItem(), node);
    }
    
    public void reloadNodes() {
        nodes.clear();
        
        for (FtaDataItem item : getItems()) {
            Node node = document.importNode(item.getSvgElement(), true);
            svgRoot.appendChild(node);
            nodes.put(item, node);
        }
    }

    public Node getNodeByItem(FtaDataItem item) {
        return nodes.get(item);
    }

    public List<FtaDataItem> getItems() {
        List<FtaDataItem> list = new ArrayList<FtaDataItem>();
        
        if (!getStartItem().isLeaf()) {
            for (int i=0; i!= getStartItem().getChildrenCount(); i++) {
                FtaDataItem parentItem = getStartItem().getChildAt(i);
                list.add(parentItem);

                getItemsForParent(list, parentItem);
            }
        }
        
        return list;
    }
    
    private void getItemsForParent(List<FtaDataItem> list, IDataItem parent) {
        for (int i=0; i!=parent.getChildrenCount(); i++) {
            FtaDataItem item = parent.getChildAt(i);
            if (!item.isLeaf()) {
                getItemsForParent(list, item);
            }
            list.add(item);
        }
    }

    public void onEvent(DataItemMovedCompleteEvent event) {
        FtaDataItem item = event.getDataItem();
        Node node = nodes.get(item);
        svgRoot.removeChild(node);
        item.setPosition(event.getPosition());
        node = document.importNode(item.getSvgElement(), true);
        svgRoot.appendChild(node);
        nodes.put(item, node);
        FtaController.getInstance().fireEvent(new DataChangedEvent(this));
    }

    public void onEvent(DataAddLonelyItemEvent event) {
        FtaDataItem item = event.getDataItem();
        startItem.addChild(item);
        Node node = document.importNode(item.getSvgElement(), true);
        svgRoot.appendChild(node);
        nodes.put(item, node);
        FtaController.getInstance().fireEvent(new DataChangedEvent(this));
    }

    public void onEvent(DataConnectItemsEvent event) {
        if(event.getChild().getParent() instanceof FtaDataStartItem) {
            startItem.removeChild(event.getChild());
        }
        event.getParent().addChild(event.getChild());
        FtaController.getInstance().fireEvent(new DataChangedEvent(this));
    }

    public void onEvent(DataEditItemEvent event) {
        FtaController.getInstance().fireEvent(new DataChangedEvent(this));
    }
}
