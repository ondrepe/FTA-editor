package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataItemMovedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.CanvasGraphicsNode;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.gvt.renderer.ConcreteImageRendererFactory;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.gvt.renderer.ImageRendererFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrejicek
 */
public class EditorCanvas extends JPanel implements IDataChangedListener {

    private static Logger logger = LoggerFactory.getLogger(EditorCanvas.class);
    
    private GVTBuilder builder;
    private GraphicsNode gvtRoot;
    private BridgeContext ctx;
    private BufferedImage canvasOffscreenImage;
    private ImageRenderer renderer;
    private AffineTransform currentAffineTransform;
    private UpdateManager manager;
    private FtaData data;
    private Rectangle renderedRectangle = new Rectangle(0, 0, 1, 1);
    int DISPLAY_WIDTH = 1000;
    int DISPLAY_HEIGHT = 1000;

    public EditorCanvas() {
        this.setBackground(Color.white);
        this.setLayout(null);
        UserAgent userAgent;
        DocumentLoader loader;

        userAgent = new UserAgentAdapter();
        loader = new DocumentLoader(userAgent);
        ctx = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        builder = new GVTBuilder();


        initCanvas();
        registerListeners();
    }

    private void initCanvas() {
        ImageRendererFactory rendererFactory = new ConcreteImageRendererFactory();
        renderer = rendererFactory.createDynamicImageRenderer();
        renderer.setDoubleBuffered(true);

    }

    public void updateCanvas() {
        updateOffsetScreenImage();

        GraphicsNode gnode = null;
        for (FtaDataItem item : data.getItems()) {
            try {
                gnode = ctx.getGraphicsNode(data.getNodeByItem(item));
            } catch (Exception e) {
            }

            if (gnode != null) {
                Rectangle2D bounds = gnode.getGeometryBounds();
                EditorGhostComponent comp = new EditorGhostComponent(item, bounds);
                this.add(comp);
            }
        }
    }

    public void updateOffsetScreenImage() {
        gvtRoot = builder.build(ctx, data.getDocument());

        float docWidth = (float) ctx.getDocumentSize().getWidth();
        float docHeight = (float) ctx.getDocumentSize().getHeight();

        float xscale = DISPLAY_WIDTH / docWidth;
        float yscale = DISPLAY_HEIGHT / docHeight;
        float scale = Math.min(xscale, yscale);

        AffineTransform px = AffineTransform.getScaleInstance(scale, scale);

        double tx = -0 + (DISPLAY_WIDTH / scale - docWidth) / 2;
        double ty = -0 + (DISPLAY_WIDTH / scale - docHeight) / 2;
        px.translate(tx, ty);
        CanvasGraphicsNode cgn = getGraphicsNode(gvtRoot);
        if (cgn != null) {
            cgn.setViewingTransform(px);
            currentAffineTransform = new AffineTransform();
        } else {
            currentAffineTransform = px;
        }

        manager = new UpdateManager(ctx, gvtRoot, data.getDocument());
        manager.setMinRepaintTime(-1);

        try {
            manager.dispatchSVGLoadEvent();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        renderer.updateOffScreen(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        renderer.setTree(gvtRoot);
        renderer.setTransform(currentAffineTransform);
        renderer.clearOffScreen();
        renderer.repaint(new Rectangle(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT));
        manager.manageUpdates(renderer);
        canvasOffscreenImage = renderer.getOffScreen();
    }

    public BridgeContext getBridgeContext() {
        return ctx;
    }

    public GraphicsNode getGraphicsNode() {
        return getGraphicsNode(gvtRoot);
    }

    private CanvasGraphicsNode getGraphicsNode(GraphicsNode gn) {
        if (!(gn instanceof CompositeGraphicsNode)) {
            return null;
        }
        CompositeGraphicsNode cgn = (CompositeGraphicsNode) gn;
        java.util.List children = cgn.getChildren();
        if (children.isEmpty()) {
            return null;
        }
        gn = (GraphicsNode) children.get(0);
        if (!(gn instanceof CanvasGraphicsNode)) {
            return null;
        }
        return (CanvasGraphicsNode) gn;

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (canvasOffscreenImage != null) {
            //getting the sub image that should be painted
            Rectangle clip = g.getClipBounds();
            Rectangle rendRect = new Rectangle(renderedRectangle.x, renderedRectangle.y, canvasOffscreenImage.getWidth(), canvasOffscreenImage.getHeight());

            if (clip.width != 0 && clip.height != 0) {

                if (clip.contains(rendRect)) {
                    ((Graphics2D) g).drawRenderedImage(canvasOffscreenImage, AffineTransform.getTranslateInstance(rendRect.x, rendRect.y));
                } else if (clip.intersects(rendRect)) {
                    //getting the sub image to draw 
                    Rectangle rZone = clip.intersection(rendRect);

                    if (rZone.width != 0 && rZone.height != 0) {
                        BufferedImage subImage = canvasOffscreenImage.getSubimage(rZone.x - rendRect.x, rZone.y - rendRect.y, rZone.width, rZone.height);
                        g2d.drawRenderedImage(subImage, AffineTransform.getTranslateInstance(rZone.x, rZone.y));
                    }
                }
            }
        }

        if (data != null) {
            GraphicsNode gnode = null;

            for (FtaDataItem item : data.getItems()) {
                try {
                    gnode = ctx.getGraphicsNode(data.getNodeByItem(item));
                } catch (Exception e) {
                }

                if (gnode != null) {
                    Rectangle2D bounds = gnode.getGeometryBounds();
                    g2d.draw(bounds);
                }
            }
        }
    }

    public void setData(FtaData data) {
        this.data = data;
        initCanvas();
        updateCanvas();
        repaint();
    }

    public void onEvent(DataChangedEvent event) {
        setData(event.getData());
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(DataChangedEvent.class, this);
    }
}
