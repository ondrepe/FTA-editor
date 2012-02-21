package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.editor.EditorCanvasItemMouseListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import org.apache.batik.bridge.*;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.gvt.renderer.ConcreteImageRendererFactory;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.gvt.renderer.ImageRendererFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author ondrejicek
 */
public class EditorCanvasItem extends JComponent {

    private FtaDataItem dataItem;
    private Document document;
    private Element svgRoot;
    private BufferedImage offscreenImage;
    private Rectangle2D rectangle;

    public EditorCanvasItem(FtaDataItem dataItem, Rectangle2D rectangle) {
        this.dataItem = dataItem;
        this.rectangle = rectangle;

        DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        document = impl.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI, "svg", null);
        svgRoot = document.getDocumentElement();
        svgRoot.setAttributeNS(null, "width", String.valueOf(rectangle.getBounds().width + rectangle.getBounds().x));
        svgRoot.setAttributeNS(null, "height", String.valueOf(rectangle.getBounds().height + rectangle.getBounds().y));

        Node node = document.importNode(dataItem.getSvgElement(), true);
        svgRoot.appendChild(node);
        initOffscreenImage();

        this.setBounds(rectangle.getBounds());
        registerListeners();
    }

    private void initOffscreenImage() {

        UserAgent userAgent = new UserAgentAdapter();
        DocumentLoader loader = new DocumentLoader(userAgent);
        BridgeContext ctx = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode gvtRoot = builder.build(ctx, document);

        ImageRendererFactory rendererFactory = new ConcreteImageRendererFactory();
        ImageRenderer renderer = rendererFactory.createDynamicImageRenderer();
        renderer.setDoubleBuffered(true);

        int width = rectangle.getBounds().width;
        int height = rectangle.getBounds().height;
        renderer.updateOffScreen(width, height);
        AffineTransform translate = AffineTransform.getTranslateInstance(-rectangle.getX(), -rectangle.getY());
        renderer.setTransform(translate);
        renderer.setTree(gvtRoot);
        renderer.clearOffScreen();
        renderer.repaint(new Rectangle(rectangle.getBounds().x, rectangle.getBounds().y, width, height));
        offscreenImage = renderer.getOffScreen();
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }

    private void registerListeners() {
        EditorCanvasItemMouseListener listener = new EditorCanvasItemMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRenderedImage(offscreenImage, AffineTransform.getTranslateInstance(0, 0));
    }

    @Override
    public String toString() {
        return dataItem.getSymbolClass().getSimpleName() + ":  " + super.toString();
    }
}
