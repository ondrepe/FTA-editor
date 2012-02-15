package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api.IDiagramTreeTableModel;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeTableModel implements IDiagramTreeTableModel {

    private AbstractSymbol data;

    public AbstractSymbol getData() {
        return data;
    }

    public void setData(AbstractSymbol data) {
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
        AbstractSymbol symbol = (AbstractSymbol) o;
        if (i == 0) {
            return symbol.getType();
        } else if (i == 1) {
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
        AbstractSymbol symbol = (AbstractSymbol) o1;
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
        return data;
    }

    public Object getChild(Object parent, int index) {
        return ((AbstractGate) parent).getChildren().get(index);
    }

    public int getChildCount(Object parent) {
        return ((AbstractGate) parent).getChildren().size();
    }

    public boolean isLeaf(Object node) {
        if (node instanceof AbstractGate) {
            return ((AbstractGate) node).getChildren().isEmpty();
        }
        return true;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getIndexOfChild(Object parent, Object child) {
        return ((AbstractGate) parent).getChildren().indexOf(child);
    }

    public void addTreeModelListener(TreeModelListener l) {
        System.out.println("add");
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeTreeModelListener(TreeModelListener l) {
        System.out.println("remove");
        //        throw new UnsupportedOperationException("Not supported yet.");
    }
}
