package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.IRegisterable;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.move.IDataItemMovedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.editor.EditorCanvasMouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.GraphicsNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrejicek
 */
public class EditorCanvas extends JComponent implements IRegisterable, IDataChangedListener, IDataItemMovedListener {

    private static Logger logger = LoggerFactory.getLogger(EditorCanvas.class);
    
    private GVTBuilder builder;
    private BridgeContext ctx;
    private FtaData data;
    private List<EditorCanvasItem> canvasItems;

    public EditorCanvas() {
        initCanvas();
        FtaControllCenter.addRegistrable(this);
    }

    private void initCanvas() {
        canvasItems = new ArrayList<EditorCanvasItem>();

        this.setBackground(Color.white);
        this.setLayout(null);
        UserAgent userAgent;
        DocumentLoader loader;

        userAgent = new UserAgentAdapter();
        loader = new DocumentLoader(userAgent);
        ctx = new BridgeContext(userAgent, loader);
        ctx.setDynamicState(BridgeContext.DYNAMIC);
        builder = new GVTBuilder();
    }

    public void updateCanvas() {
        builder.build(ctx, data.getDocument());

        for (EditorCanvasItem item : canvasItems) {
            this.remove(item);
        }

        GraphicsNode gnode = null;
        for (FtaDataItem item : data.getItems()) {
            try {
                gnode = ctx.getGraphicsNode(data.getNodeByItem(item));
            } catch (Exception e) {
            }

            if (gnode != null) {
                Rectangle2D bounds = gnode.getGeometryBounds();
                bounds.setRect(bounds.getX(), bounds.getY(), bounds.getWidth() + 1, bounds.getHeight() + 1);
                EditorCanvasItem comp = new EditorCanvasItem(item, bounds);
                canvasItems.add(comp);
                this.add(comp);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data != null) {
            for (FtaDataItem item : data.getItems()) {
                for (int i = 0; i!= item.getChildrenCount(); i++) {
                    FtaDataItem child = item.getChildAt(i);
                    g.drawLine(item.getInputPoint().x, item.getInputPoint().y, child.getOutputPoint().x, child.getOutputPoint().y);
                }
            }
        }
    }

    private void setData(FtaData data) {
        this.data = data;
        updateCanvas();
        repaint();
    }

    public void registerListeners() {
        addMouseListener(new EditorCanvasMouseListener(this));
        FtaControllCenter.registerLocalEventListener(DataChangedEvent.class, this);
        FtaControllCenter.registerLocalEventListener(DataItemMovedEvent.class, this);
    }

    public void onEvent(DataItemMovedEvent event) {
        repaint();
    }
    
    public void onEvent(DataChangedEvent event) {
        setData(event.getData());
    }
}
