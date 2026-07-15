package implementations;

/**
 *  @author Reiner Justin Realica, Robert Macklin
 *  
 *  This is the class for a Doubly Linked List node used by the MyDLL.
 * 
 * @param <E> The data type of the MyDLLNode.
 */
public class MyDLLNode<E> {
	
	E element;
	MyDLLNode<E> next;
	MyDLLNode<E> prev;
	
	/**
	 * The constructor the MyDLLNode. It takes in a element to be stored in the node.
	 * 
	 * @param element The element to be stored in the node.
	 */
	public MyDLLNode(E element) {
		this.element = element;
		this.next = null;
		this.prev = null;
	}
}
