package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import java.awt.Dimension;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public abstract class SvgObject {
    
    public static final int LINE_WIDTH = 2;
    
    public static final String SVG_NS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    
    private Document document;

    public SvgObject() {
        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        document = impl.createDocument(SVG_NS, "svg", null);
    }
    
    public abstract Element getElement();
    
    protected Document getDocument() {
        return document;
    }
}
