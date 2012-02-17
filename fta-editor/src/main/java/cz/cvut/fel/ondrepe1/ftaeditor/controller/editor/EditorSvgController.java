package cz.cvut.fel.ondrepe1.ftaeditor.controller.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgRectangle;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgBasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate.SvgAndGate;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrepe
 */
public class EditorSvgController {

    private static EditorSvgController instance;

    public static EditorSvgController getInstance() {
        if (instance == null) {
            instance = new EditorSvgController();
        }
        return instance;
    }

    private SvgRectangle rectangle;
    private Document document;
    private Element svgRoot;
    
    public EditorSvgController() {
        
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        document = impl.createDocument(svgNS, "svg", null);
        svgRoot = document.getDocumentElement();
        svgRoot.setAttributeNS(null, "width", "2000");
        svgRoot.setAttributeNS(null, "height", "2000");
        
        registerListeners();
    }

    public Document test() {
        
        SvgBasicEvent basic = new SvgBasicEvent(100, 200);
        Node basicNode = document.importNode(basic.getElement(), true);
        svgRoot.appendChild(basicNode);
        
        SvgConditionalEvent conditional = new SvgConditionalEvent(0, 200);
        Node conditionalNode = document.importNode(conditional.getElement(), true);
        svgRoot.appendChild(conditionalNode);
        
        SvgAndGate andGate = new SvgAndGate(50, 50);
        Node andNode = document.importNode(andGate.getElement(), true);
        svgRoot.appendChild(andNode);
        
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        String uri = EditorSvgController.class.getResource("/img/symbols/gate/and.svg").toString();
        try {
            
            Document doc1 = f.createDocument(uri);
            Element and = doc1.getDocumentElement();
            and.setAttributeNS(null, "x", "130");
            and.setAttributeNS(null, "y", "20");
            Node and1 = document.importNode(and, true);
            
            svgRoot.appendChild(and1);
            
        } catch (IOException ex) {
            Logger.getLogger(EditorSvgController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return document;
    }

    public void onPalletSelect(AbstractSymbol symbol) {
        if (rectangle != null) {
            
        }
    }

    private void registerListeners() {
        
    }

    public Document getDocument() {
        return document;
    }
}
