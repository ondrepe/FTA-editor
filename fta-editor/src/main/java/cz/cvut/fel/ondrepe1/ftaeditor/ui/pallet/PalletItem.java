package cz.cvut.fel.ondrepe1.ftaeditor.ui.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.CommonSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol.event.BasicEvent;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.batik.swing.JSVGCanvas;

/**
 *
 * @author ondrepe
 */
public class PalletItem extends JComponent {

    private CommonSymbol symbol;
    private JSVGCanvas canvas;
    
    public PalletItem(CommonSymbol symbol) {
        super();
        this.setLayout(new FlowLayout());
        this.symbol = symbol;
        canvas = new JSVGCanvas();
        canvas.setSVGDocument(symbol.getImage().getSvgDocument());
        canvas.setBounds(5, 5, 20, 20);
        JLabel label = new JLabel(symbol.getLabel());
//        this.setBorder(new Bo);
        this.add(label);
        this.add(canvas);
    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.drawImage(symbol.getImage().getImage(30, 30), 5, 5, this);
////        g2.drawRect(0, 0, 300, 120);
////        g2.setColor(Color.red);
////        g2.drawRect(5, 5, 100, 100);
//    }
    
    

    public CommonSymbol getSymbol() {
        return symbol;
    }

}
