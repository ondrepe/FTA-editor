package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event;

/**
 *
 * @author ondrepe
 */
public class UniversalErrorEvent extends CommonGlobalEvent {

    private String message;

    public UniversalErrorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
