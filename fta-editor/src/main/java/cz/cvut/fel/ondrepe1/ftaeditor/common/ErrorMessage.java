package cz.cvut.fel.ondrepe1.ftaeditor.common;

/**
 *
 * @author ondrepe
 */
public class ErrorMessage {

    public String field;
    public String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
