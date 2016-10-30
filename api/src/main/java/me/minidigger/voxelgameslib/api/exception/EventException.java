package me.minidigger.voxelgameslib.api.exception;

/**
 * Exception that gets thrown when something is wrong related to the event system
 */
public class EventException extends VoxelGameLibException {
    
    /**
     * @param message the message that explains the issue
     * @param ex      the root issue that was thrown
     */
    public EventException(String message, Exception ex) {
        super(message, ex);
    }
    
    /**
     * @param message the message that explains the issue
     */
    public EventException(String message) {
        super(message);
    }
}