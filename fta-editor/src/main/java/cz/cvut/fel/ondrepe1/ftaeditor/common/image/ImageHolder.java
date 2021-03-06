package cz.cvut.fel.ondrepe1.ftaeditor.common.image;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.ext.awt.RenderingHintsKeyExt;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.jdesktop.swingx.graphics.GraphicsUtilities;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author ondrepe
 */
public class ImageHolder {

    private static Icon tickIcon = loadFromResource("tick.png");
    private static Icon crossIcon = loadFromResource("cross.png");
    
    private static BufferedImage removeImage = loadBufferedImageFromResource("remove.png");
    
    private static Icon selectIcon = loadFromResource("select.png");
    private static Icon editIcon = loadFromResource("edit.png");
    private static Icon connectIcon = loadFromResource("connect.png");
    private static Icon deleteIcon = loadFromResource("delete.png");
    
    private static Icon basicIcon = loadFromResource("basic.png");
    private static Icon conditionalIcon = loadFromResource("conditional.png");
    private static Icon dormantIcon = loadFromResource("dormant.png");
    private static Icon undevelopedIcon = loadFromResource("undeveloped.png");
    
    private static Icon andIcon = loadFromResource("and.png");
    private static Icon orIcon = loadFromResource("or.png");
    private static Icon notIcon = loadFromResource("not.png");
    private static Icon xorIcon = loadFromResource("xor.png");
    private static Icon inhibitIcon = loadFromResource("inhibit.png");
    private static Icon majorityVoteIcon = loadFromResource("majorityVote.png");
    private static Icon pandIcon = loadFromResource("pand.png");
    private static Icon transferIcon = loadFromResource("transfer.png");

    public static Icon getSelectIcon() {
        return selectIcon;
    }

    public static Icon getEditIcon() {
        return editIcon;
    }

    public static Icon getConnectIcon() {
        return connectIcon;
    }

    public static Icon getDeleteIcon() {
        return deleteIcon;
    }

    public static Icon getBasicIcon() {
        return basicIcon;
    }

    public static Icon getConditionalIcon() {
        return conditionalIcon;
    }

    public static Icon getDormantIcon() {
        return dormantIcon;
    }

    public static Icon getUndevelopedIcon() {
        return undevelopedIcon;
    }

    public static Icon getAndIcon() {
        return andIcon;
    }

    public static Icon getOrIcon() {
        return orIcon;
    }

    public static Icon getNotIcon() {
        return notIcon;
    }

    public static Icon getXorIcon() {
        return xorIcon;
    }

    public static Icon getInhibitIcon() {
        return inhibitIcon;
    }

    public static Icon getMajorityVoteIcon() {
        return majorityVoteIcon;
    }

    public static Icon getPandIcon() {
        return pandIcon;
    }

    public static Icon getTransferIcon() {
        return transferIcon;
    }
    
    public static Icon getTickIcon() {
        return tickIcon;
    }

    public static Icon getCrossIcon() {
        return crossIcon;
    }

    public static BufferedImage getRemoveImage() {
        return removeImage;
    }
    
    public static Icon loadFromResource(String name) {
        URL url = ImageHolder.class.getResource("/img/" + name);
        if (url == null) {
            return null;
        }
        try {
            BufferedImage image = ImageIO.read(url);
            if (image.getHeight() > 30) {
                image = GraphicsUtilities.createThumbnail(image, 16);
            }
            return new ImageIcon(image);
        } catch (IOException e) {
        }
        return null;
    }
    
    private static BufferedImage loadBufferedImageFromResource(String name) {
        URL url = ImageHolder.class.getResource("/img/" + name);
        if (url == null) {
            return null;
        }
        try {
            BufferedImage image = ImageIO.read(url);
//            if (image.getHeight() > 30) {
                image = GraphicsUtilities.createThumbnail(image, 16);
//            }
            return image;
        } catch (IOException e) {
        }
        return null;
    }
    
    public static Icon loadFromSvgResource(String name) {
        return loadFromSvgResource(name, true);
    }
    
    public static Icon loadFromSvgResource(String name, boolean thumbnail) {
        URL url = ImageHolder.class.getResource("/img/symbols/" + name);
        if (url == null) {
            return null;
        }
        try {
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
            SVGDocument svgDocument  = (SVGDocument) factory.createDocument(url.toString());
            GraphicsNode rootSvgNode = getRootNode(svgDocument);
            
            int width = 50;
            int height = 50;
            
            Rectangle2D bounds = rootSvgNode.getPrimitiveBounds();
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
           
            if (thumbnail && bufferedImage.getHeight() > 30) {
                bufferedImage = GraphicsUtilities.createThumbnail(bufferedImage, 16);
            }
            Icon icon = new ImageIcon(bufferedImage);
            return icon;
            
        } catch(IOException e) {
        }
        return null;
    }

    private static GraphicsNode getRootNode(SVGDocument document) {
        // Build the tree and get the document dimensions
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter);
        GVTBuilder builder = new GVTBuilder();

        return builder.build(bridgeContext, document);
    }
}
