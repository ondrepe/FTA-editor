package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.data.IDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api.IDiagramTreeTableModel;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeTableModel implements IDiagramTreeTableModel {

    private FtaData data;

    public FtaData getData() {
        return data;
    }

    public void setData(FtaData data) {
        this.data = data;
    }

    public Class<?> getColumnClass(int i) {
        if (i == 0) {
            return String.class;
        } else if (i == 3) {
            return Float.class;
        }
        else return String.class;
    }

    public int getColumnCount() {
        return 4;
    }

    public String getColumnName(int i) {
        if (i == 0) {
            return "Type";
        } else if (i == 1) {
            return "Label";
        } else if (i == 2) {
            return "Text";
        }
        return "Q";
    }

    public int getHierarchicalColumn() {
        return 0;
    }

    public Object getValueAt(Object o, int i) {
        IDataItem symbol = (IDataItem) o;
        if (i == 1) {
            return symbol.getLabel();
        } else if (i == 2) {
            return symbol.getText();
        }
        return symbol.getFailureProbability();
    }

    public boolean isCellEditable(Object o, int i) {
        if (i == 1 || i == 2 || i == 3)
            return true;
        return false;
    }

    public void setValueAt(Object o, Object o1, int i) {
        IDataItem symbol = (IDataItem) o1;
        if (i == 1) {
            String value = (String) o;
            symbol.setLabel(value);
        } else if (i == 2) {
            String value = (String) o;
            symbol.setText(value);
        } else if (i == 3) {
            Float value = (Float) o;
            symbol.setFailureProbability(value);
        }
        
    }

    public Object getRoot() {
        if (data == null) return null;
        return data.getStartItem();
    }

    public Object getChild(Object parent, int index) {
        return ((IDataItem) parent).getChildAt(index);
    }

    public int getChildCount(Object parent) {
        return ((IDataItem) parent).getChildrenCount();
    }

    public boolean isLeaf(Object node) {
        return ((IDataItem) node).isLeaf();
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getIndexOfChild(Object parent, Object child) {
        return ((IDataItem) parent).getIndexOfChild((FtaDataItem) child);
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
