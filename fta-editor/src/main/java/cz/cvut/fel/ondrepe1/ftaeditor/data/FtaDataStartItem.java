package cz.cvut.fel.ondrepe1.ftaeditor.data;

/**
 *
 * @author ondrepe
 */
public class FtaDataStartItem implements IDataItem {

    FtaDataItem child;
    
    public FtaDataItem getChildAt(int index) {
        return child;
    }
    
    public int getIndexOfChild(FtaDataItem child) {
        return 1;
    }
    
    public int getChildrenCount() {
        return 1;
    }
    
    public boolean canAddChild() {
        boolean result = false;
        if (child == null) {
            result = true;
        }
        return result;
    }
    
    public boolean setChild(FtaDataItem child) {
        boolean result = false;
        if (canAddChild()) {
            this.child = child;
            result = true;
        }
        return result;
    }
    
    public boolean isLeaf() {
        boolean result = false;
        if (child == null) {
            result = true;
        }
        return result;
    }

    public String getLabel() {
        return null;
    }

    public String getText() {
        return null;
    }

    public boolean isValid() {
        return true;
    }

    public Float getFailureProbability() {
        return null;
    }

    public void setLabel(String label) {
    }

    public void setText(String text) {
    }

    public void setFailureProbability(Float failureProbability) {
    }
}
