package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddChildEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataAddChildListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataItemMovedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class FtaData implements IDataAddChildListener, IDataItemMovedListener {

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
        FtaController.getInstance().registerEventListener(DataItemMovedEvent.class, this);
    }

    public void onEvent(DataAddChildEvent event) {
        Node node = document.importNode(event.getDataItem().getSvgElement(), true);
        svgRoot.appendChild(node);
        nodes.put(event.getDataItem(), node);
    }

    public Node getNodeByItem(FtaDataItem item) {
        return nodes.get(item);
    }

    public List<FtaDataItem> getItems() {
        List<FtaDataItem> list = new ArrayList<FtaDataItem>();
        
        if (!getStartItem().isLeaf()) {
            FtaDataItem parentItem = getStartItem().getChildAt(0);
            list.add(parentItem);
            
            getItemsForParent(list, parentItem);
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

    public void onEvent(DataItemMovedEvent event) {
        FtaDataItem item = event.getDataItem();
        Node node = nodes.get(item);
        svgRoot.removeChild(node);
        item.setPosition(item.getPosition().x + event.getMouseEvent().getX(), item.getPosition().y + event.getMouseEvent().getY());
        node = document.importNode(item.getSvgElement(), true);
        nodes.put(item, node);
        FtaController.getInstance().fireEvent(new DataChangedEvent(this));
    }
}
