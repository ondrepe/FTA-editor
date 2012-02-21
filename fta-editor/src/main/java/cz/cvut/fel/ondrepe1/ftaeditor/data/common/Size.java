package cz.cvut.fel.ondrepe1.ftaeditor.data.common;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="size")
public class Size {

    private double width;
    private double height;

    public Size() {
    }
    
    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
