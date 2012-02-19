package org.example;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.editor.EditorSvgController;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;


import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.BridgeException;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UpdateManager;
import org.apache.batik.bridge.UpdateManagerAdapter;
import org.apache.batik.bridge.UpdateManagerEvent;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.util.RunnableQueue;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.batik.gvt.CanvasGraphicsNode;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.gvt.renderer.ConcreteImageRendererFactory;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.gvt.renderer.ImageRendererFactory;
import org.apache.xmlgraphics.image.codec.png.PNGEncodeParam;
import org.apache.xmlgraphics.image.codec.png.PNGImageEncoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.java2d.pipe.RenderBuffer;


public class TestOffScreenRender {
    static final String SVGNS = "http://www.w3.org/2000/svg";

    Document document;
    UserAgentAdapter userAgent;
    GVTBuilder builder;
    BridgeContext ctx;
    ImageRenderer renderer;
    AffineTransform curTxf;
    UpdateManager manager;
    GraphicsNode gvtRoot;
    int DISPLAY_WIDTH = 1280;
    int DISPLAY_HEIGHT = 1024;


    public TestOffScreenRender (Document doc) {
        userAgent = new UserAgentAdapter();
        ctx       = new BridgeContext(userAgent);
        builder   = new GVTBuilder();
        document  = doc;
    }

    public GraphicsNode getGvtRoot() {
        return gvtRoot;
    }
    
    

    public void init() {
        GraphicsNode gvtRoot = null ;

        try {
            ctx.setDynamicState(BridgeContext.DYNAMIC);
            gvtRoot = builder.build(ctx, document);
        }
        catch (BridgeException e) { 
            e.printStackTrace(); 
            System.exit(1);
        }

        ImageRendererFactory rendererFactory;
        rendererFactory = new ConcreteImageRendererFactory();
        renderer        = rendererFactory.createDynamicImageRenderer();
        renderer.setDoubleBuffered(false);

        float docWidth  = (float) ctx.getDocumentSize().getWidth();
        float docHeight = (float) ctx.getDocumentSize().getHeight();

        float xscale = DISPLAY_WIDTH/docWidth;
        float yscale = DISPLAY_HEIGHT/docHeight;
        float scale = Math.min(xscale, yscale);

        AffineTransform px  = AffineTransform.getScaleInstance(scale, scale);

        double tx = -0 + (DISPLAY_WIDTH/scale - docWidth)/2;
        double ty = -0 + (DISPLAY_WIDTH/scale - docHeight)/2;
        px.translate(tx, ty);
        CanvasGraphicsNode cgn = getGraphicsNode(gvtRoot);
        if (cgn != null) {
            cgn.setViewingTransform(px);
            curTxf = new AffineTransform();
        } else {
            curTxf = px;
        }
        manager = new UpdateManager(ctx, gvtRoot, document);
        // 'setMinRepaintTime' was added to SVN.  This isn't
        // essential but prevents 'frame skipping' (useful
        // for "recording" content, not needed for live display).
        manager.setMinRepaintTime(-1);

        try {
            manager.dispatchSVGLoadEvent();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        renderer.updateOffScreen(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        renderer.setTree(gvtRoot);
        renderer.setTransform(curTxf);
        renderer.clearOffScreen();
        renderer.repaint(new Rectangle(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT));
        manager.addUpdateManagerListener(new UpdateManagerAdapter() {
                public void updateCompleted(UpdateManagerEvent e) {
                    render(e.getImage());
                }

                public void managerSuspended(UpdateManagerEvent e) {
                    // Make sure pending updates are completed.
                    System.exit(0);
                }
            });
        manager.manageUpdates(renderer);
        render(renderer.getOffScreen());
        this.gvtRoot = gvtRoot;
    }

    private CanvasGraphicsNode getGraphicsNode(GraphicsNode gn) {
        if (!(gn instanceof CompositeGraphicsNode))
            return null;
        CompositeGraphicsNode cgn = (CompositeGraphicsNode) gn;
        List children = cgn.getChildren();
        if(children.size() == 0)
            return null;
        gn = (GraphicsNode) children.get(0);
        if (!(gn instanceof CanvasGraphicsNode))
            return null;
        return (CanvasGraphicsNode) gn;

    }

    int imgCount = 1;
    public void render(java.awt.image.BufferedImage img) {
        // paint the image or stream the image to the client display
        try {
            String file = "frame."+(imgCount++)+".png";
            System.err.println("Output: " + file);
            OutputStream os = new FileOutputStream(file);
            
            PNGEncodeParam params = PNGEncodeParam.getDefaultEncodeParam(img);
            PNGImageEncoder pngEncoder = new PNGImageEncoder(os, params);
            pngEncoder.encode(img);
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("You must provide background SVG file");
//            System.exit(1);
        }
//        String docStr = args[0];
        
        String xmlParser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory df;
        df = new SAXSVGDocumentFactory(xmlParser);
        Document doc = null;
        doc = EditorSvgController.getInstance().test();
        final TestOffScreenRender render = new TestOffScreenRender(doc);
        Element r=null;
//        try {
//            File f = new File("aa.svg");
//            doc = df.createSVGDocument(f.toURL().toString());
//            r = doc.createElementNS(SVGNS, "rect");
//            r.setAttributeNS(null, "x", "100");
//            r.setAttributeNS(null, "y", "200");
//            r.setAttributeNS(null, "width", "200");
//            r.setAttributeNS(null, "height", "150");
//            r.setAttributeNS(null, "fill", "crimson");
//            r.setAttributeNS(null, "stroke", "gold");
//            r.setAttributeNS(null, "stroke-width", "3");
//            doc.getDocumentElement().appendChild(r);
            render.init();
            
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//            System.exit(0);
//        }

        final Element rect = r;

        RunnableQueue rq = render.manager.getUpdateRunnableQueue();
//        for (int i=1; i<10; i++) {
//            final int x = 100+ (i*10);
//            try {
//                rq.invokeAndWait(new Runnable() {
//                        public void run() {
//                            rect.setAttributeNS(null, "x", ""+x);
//                        }
//                    });
//            } catch (InterruptedException ie) {
//                ie.printStackTrace();
//            }
//        }
//        render.manager.suspend();
        
        JFrame frame =new JFrame();
        frame.setSize(800, 800);
        frame.setVisible(true);
        
        
        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                
                g.setClip(render.getGvtRoot().getOutline());
                
                super.paintComponent(g);
            }
        
        };
        frame.add(panel);
    }
}
