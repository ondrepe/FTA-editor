package cz.cvut.fel.ondrepe1.ftaeditor.data;

/**
 *
 * @author ondrepe
 */
public interface IDataItem {

    public FtaDataItem getChildAt(int index);
    public int getIndexOfChild(FtaDataItem child);
    public int getChildrenCount();
    public boolean canAddChild();
    public boolean isLeaf();
    
    public String getLabel();
    public String getText();
    public Float getFailureProbability();
    public void setLabel(String label);
    public void setText(String text);
    public void setFailureProbability(Float failureProbability);
    public boolean isValid();
}
