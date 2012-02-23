package cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common;

import static cz.cvut.fel.ondrepe1.ftaeditor.data.svg.common.SvgConstants.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/**
 *
 * @author ondrepe
 */
public class SvgText extends SvgObject {

    private Element svgText;

    public SvgText(double x, double y, String text) {
    
        List<String> list = new ArrayList<String>();
        String tempText = text;
        while (tempText.length() > 12) {
            String temp = tempText.substring(0, 12);
            int spaceIndex = temp.lastIndexOf(' ');
            if (spaceIndex == -1) {
                spaceIndex = temp.indexOf(' ', 12);
            }
            if (spaceIndex == -1) {
                list.add(temp);
                break;
            }
            temp = tempText.substring(0, spaceIndex);
            list.add(temp);
            tempText = tempText.substring(spaceIndex + 1);
        }
        list.add(tempText);
        
        svgText = getDocument().createElementNS(SVG_NS, SVG_TYPE_TEXT);
        svgText.setAttributeNS(null, ATT_X, String.valueOf(x));
        svgText.setAttributeNS(null, ATT_FILL, COLOR_BLACK);
        svgText.setAttributeNS(null, ATT_TEXT_ANCHOR, TEXT_ANCHOR_MIDDLE);
        svgText.setAttributeNS(null, ATT_Y, String.valueOf(y));
        
        int i = 0;
        int size = list.size();
        for (String line : list) {
            Element tline = getDocument().createElementNS(SVG_NS, SVG_TYPE_TEXT_SPAN);
            tline.setAttributeNS(null, ATT_X, String.valueOf(x));
            tline.setAttributeNS(null, ATT_Y, String.valueOf(y + (i * 10) - ((size - 1) * 5) + 5));
            tline.setTextContent(line);
            svgText.appendChild(tline);
            i++;
        }
    }
    
    @Override
    public Element getElement() {
        return svgText;
    }

}
