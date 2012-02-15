package cz.cvut.fel.ondrepe1.ftaeditor.ui.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.SVGImage;
import java.io.IOException;
import java.io.Serializable;
import org.w3c.dom.svg.SVGDocument;

/**
 *
 * @author ondrepe
 */
public abstract class CommonSymbol implements Serializable {

    private String label;
    private SVGImage image;
    private String description;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SVGImage getImage() {
        return image;
    }
    
    public void setImagePath(String path) throws IOException {
        this.image = new SVGImage(path);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
