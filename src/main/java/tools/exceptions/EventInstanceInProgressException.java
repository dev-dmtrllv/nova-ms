package tools.exceptions;

/**
 * @author Ronan
 */
public class EventInstanceInProgressException extends Exception {

    public static String EIIP_KEY = "Event instance ";

    public EventInstanceInProgressException(String eventName, String eventInstance) {
        super(EIIP_KEY + "already in progress - " + eventName + ", EM: " + eventInstance);
    }

}
