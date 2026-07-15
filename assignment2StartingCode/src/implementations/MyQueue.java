package implementations;

import utilities.Iterator;
import utilities.QueueADT;
import exceptions.EmptyQueueException;

/**
 * @author Marc Edison Estaca, Robert Macklin, Reiner Justin Realica
 * 
 * This Class is a implementation of the QueueADT interface.
 * 
 * @param <E> The data type to be used by the queue.
 */
public class MyQueue<E> implements QueueADT<E> {
	
	private MyDLL<E> list;
	
	/**
	 * The constructor of the MyQueue. It initializes the underlying MyDLL.
	 */
	public MyQueue() {
		list = new MyDLL<>();
	}

	/**
	 * This function takes in a element and adds it to the end of the queue. Throws an exception if the element is null.
	 * 
	 *@param element The element to be added to the queue.
	 *@throws NullPointerException Thrown if the element is null.
	 */
	@Override
	public void enqueue(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		list.add(element);
	}

	/**
	 * This function removes the first value of the queue and returns it's value.
	 * 
	 *@return Returns the value that was at the front of the queue.
	 */
	@Override
	public E dequeue() {
		
		if (list.isEmpty())
			throw new EmptyQueueException();
		
		return list.remove(0);
	}

	/**
	 * This function returns the first value of the queue without removing it. Throws an exception if the queue is empty.
	 * 
	 *@return Returns the value of the item at the front of the queue.
	 *@throws EmptyQueueException Thrown if the queue is empty.
	 */
	@Override
	public E peek() {
		
		if (list.isEmpty())
			throw new EmptyQueueException();
		
		return list.get(0);
	}

	/**
	 * Clears the queue of all values.
	 */
	@Override
	public void dequeueAll() {
		
		list.clear();
		
	}

	/**
	 * <p>This function searches the queue for the given element, either returning true if it found or false if not. 
	 * Throws and exception if the given element is null.</p>
	 * 
	 *@param element  The element to be used to search the queue.
	 *@return Returns either true if the queue contains the given element, or false if not.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean contains(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		return list.contains(element);
	}

	/**
	 * This function takes in a second queue and compares it to this one. If they are the same, it returns true. Otherwise, it returns false.
	 * 
	 *@param that The second queue to be compared to this one.
	 *@return Returns true if both queues match, otherwise returns false.
	 */
	@Override
	public boolean equals(QueueADT<E> that) {
		
		if (that == null)
			return false;
		
		if (this.size() != that.size())
			return false;
		
		Iterator<E> it1 = this.iterator();
		Iterator<E> it2 = that.iterator();
		
		while (it1.hasNext()) {
			if (!it1.next().equals(it2.next()))
				return false;
		}
		
		return true;
	}

	/**
	 * This function returns true if the queue is empty, otherwise returns false.
	 * 
	 *@return Returns true if queue is empty, returns false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	/**
	 * This function always returns false.
	 * 
	 *@return Returns false.
	 */
	@Override
	public boolean isFull() {
		
		return false;
	}

	/**
	 * This function returns the size of the queue.
	 * 
	 *@return Returns the size of the queue.
	 */
	@Override
	public int size() {
		
		return list.size();
	}

	/**
	 * <p>This function searches the queue for the given element. It then returns the position of the element in the queue.
	 * If the element doesn't exist, it instead returns -1. Throws an exception if the given element is null.</p>
	 * 
	 *@param element The element to be used to search the queue.
	 *@return Returns either the position of the element if it exists, or -1 if it does not.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public int search(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		int position = 1;
		
		Iterator<E> it = list.iterator();
		
		while (it.hasNext()) {
			
			if (it.next().equals(element))
				return position;
			
			position++;
		}
		
		return -1;
	}

	/**
	 * This function converts the queue into a Object array and then returns it.
	 * 
	 *@return Returns the queue in the form of a Object array.
	 */
	@Override
	public Object[] toArray() {
		
		return list.toArray();
	}

	/**
	 * This function copies the queue onto a given array of the same data type. The array is then returned.
	 * 
	 *@param array The array to have the queue copied onto.
	 *@return Returns the array with the queue copied onto.
	 */
	@Override
	public E[] toArray(E[] array) {
		
		return list.toArray(array);
	}

	/**
	 * Creates and initializes a iterator object to iterate through the queue. The iterator is then returned.
	 * 
	 *@return Returns the iterator object.
	 */
	@Override
	public Iterator<E> iterator() {
		
		return list.iterator();
	}

}
