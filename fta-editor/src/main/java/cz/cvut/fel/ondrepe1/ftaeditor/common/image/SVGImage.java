package cz.cvut.fel.ondrepe1.ftaeditor.common.image;

/**
 *
 * @author ondrepe
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

public final class SVGImage {

    private final GraphicsNode rootSvgNode;
    private final SVGDocument svgDocument;

    public SVGImage(String path) throws IOException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        svgDocument = (SVGDocument) factory.createDocument(path);
        rootSvgNode = getRootNode(svgDocument);
    }

    private static GraphicsNode getRootNode(SVGDocument document) {
        // Build the tree and get the document dimensions
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter);
        GVTBuilder builder = new GVTBuilder();

        return builder.build(bridgeContext, document);
    }

    public GraphicsNode getRootSvgNode() {
        return rootSvgNode;
    }

    public SVGDocument getSvgDocument() {
        return svgDocument;
    }

    public Image getImage(int width, int height) {
        
//        Rectangle2D bounds = rootSvgNode.getPrimitiveBounds();
        Rectangle2D bounds = rootSvgNode.getBounds();
        double scaleX, scaleY;
        scaleX = width / bounds.getWidth();
        scaleY = height / bounds.getHeight();


        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING,  RenderingHintsKeyExt.VALUE_TRANSCODING_PRINTING);

        AffineTransform usr2dev = new AffineTransform(scaleX, 0.0, 0.0, scaleY, 0.0, 0.0);
        g2d.transform(usr2dev);
        rootSvgNode.paint(g2d);

        g2d.dispose();
        return bufferedImage;
    }
    
    public Icon getImageIcon(int width, int height) {
        Image image = getImage(width, height);
        Icon icon = new ImageIcon(image);
        return icon;
    }
}
