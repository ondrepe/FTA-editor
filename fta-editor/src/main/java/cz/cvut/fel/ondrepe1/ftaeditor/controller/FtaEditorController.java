package cz.cvut.fel.ondrepe1.ftaeditor.controller;

/**
 *
 * @author ondrepe
 */
public class FtaEditorController {

    private static FtaEditorController instance;

    public static FtaEditorController getInstance() {
        if (instance == null) {
            instance = new FtaEditorController();
        }
        return instance;
    }
    
    public static final int EDITOR_STATE_NONE = 0;
    public static final int EDITOR_STATE_SELECT = 1;
    public static final int EDITOR_STATE_EDIT = 2;
    public static final int EDITOR_STATE_CONNECT = 3;
    
    public static final int EDITOR_STATE_BASIC_EVENT = 4;
    public static final int EDITOR_STATE_CONDITIONAL_EVENT = 5;
    public static final int EDITOR_STATE_DORMANT_EVENT = 6;
    public static final int EDITOR_STATE_UNDEVELOPED_EVENT = 7;
    
    public static final int EDITOR_STATE_AND_GATE = 8;
    public static final int EDITOR_STATE_OR_GATE = 9;
    public static final int EDITOR_STATE_NOT_GATE = 10;
    public static final int EDITOR_STATE_XOR_GATE = 11;
    public static final int EDITOR_STATE_INHIBIT_GATE = 12;
    public static final int EDITOR_STATE_MAJORITY_VOTE_GATE = 13;
    public static final int EDITOR_STATE_TRANSFER_GATE = 14;
    public static final int EDITOR_STATE_PAND_GATE = 15;
    
    
    private int editorState;

    public FtaEditorController() {
        editorState = 0;
    }

    public int getEditorState() {
        return editorState;
    }

    public void setEditorState(int editorState) {
        this.editorState = editorState;
    }
}
