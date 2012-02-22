package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorCloseTabEvent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author ondrepe
 */
public class EditorTabbedPanelUI extends BasicTabbedPaneUI implements MouseListener, MouseMotionListener {

    private static final int TABBED_EXTRA_SPACE = 30;
    
    private Rectangle selectedCloseBtnRectangle;
    private boolean inCloseBtn;
    private boolean closeBtnPressed;
    
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        int width = super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        if (tabPane.getSelectedIndex() == tabIndex) {
            width += TABBED_EXTRA_SPACE;
        }
        return width;
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        if (isSelected) {
            Rectangle textRectangle = new Rectangle(textRect.x - (TABBED_EXTRA_SPACE / 2), textRect.y, textRect.width, textRect.height);
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRectangle, isSelected);
        
            selectedCloseBtnRectangle = new Rectangle(textRectangle.x + calculateTabWidth(tabPlacement, tabIndex, metrics) - (2 * textRect.height), textRect.y + 3, textRect.height, textRect.height - 3);
            paintCloseIcon(g);
        } else {
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
        }
    }

    private void paintCloseIcon(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        BufferedImage removeImage = ImageHolder.getRemoveImage();
        AffineTransform tf = AffineTransform.getTranslateInstance(selectedCloseBtnRectangle.x, selectedCloseBtnRectangle.y);
        tf.scale(0.5, 0.5);
        tf.translate(10, 7);
        if (closeBtnPressed) {
            Rectangle rect = new Rectangle(selectedCloseBtnRectangle);
            g2d.setPaint(lightHighlight);
            g2d.fill(rect);
        }
        g2d.drawImage(removeImage, tf, tabPane);
        if (inCloseBtn) {
            g2d.setPaint(Color.BLACK);
            g2d.draw(selectedCloseBtnRectangle);
        }
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void mouseMoved(MouseEvent e) {
        if (tabPane.getComponentCount() > 0 && selectedCloseBtnRectangle != null) {
            inCloseBtn = selectedCloseBtnRectangle.getBounds2D().contains(e.getX(), e.getY());
            tabPane.repaint();
        }
    }
    
    public void mousePressed(MouseEvent e) {
        if (tabPane.getComponentCount() > 0 && selectedCloseBtnRectangle != null) {
            if (inCloseBtn) {
                closeBtnPressed = true;
            }
            tabPane.repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (tabPane.getComponentCount() > 0 && selectedCloseBtnRectangle != null) {
            boolean startInCloseBtn = inCloseBtn;
            closeBtnPressed = false;
            tabPane.repaint();
            inCloseBtn = selectedCloseBtnRectangle.getBounds2D().contains(e.getX(), e.getY());
            if (startInCloseBtn && inCloseBtn) {
                EditorTabbedPanelItem item = (EditorTabbedPanelItem) tabPane.getSelectedComponent();
                FtaControllCenter.fireGlobalEvent(new EditorCloseTabEvent(item));
            }
        }
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
}
