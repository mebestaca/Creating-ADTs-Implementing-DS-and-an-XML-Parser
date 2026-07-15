package exceptions;

/**
 * @author Marc Edison Estaca, Robert Macklin, Reiner Justin Realica
 * 
 * This is a basic exception used by the MyQueue when a function effecting a element of the queue is run while it is empty.
 * 
 */
@SuppressWarnings("serial")
public class EmptyQueueException extends RuntimeException {
	
	/**
	 * 
	 */
	public EmptyQueueException() {
		super();
	}
	
	/**
	 * @param message
	 */
	public EmptyQueueException(String message) {
		super(message);
	}

}
