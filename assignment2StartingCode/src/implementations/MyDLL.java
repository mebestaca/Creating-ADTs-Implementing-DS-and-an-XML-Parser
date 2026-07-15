package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * @author Marc Edison Estaca, Robert Macklin, Reiner Justin Realica
 * 
 * This Class is a implementation of a Doubly Linked List.
 * 
 * @param <E> The data type to be used by the DLL.
 */
@SuppressWarnings("unchecked")
public class MyDLL<E> implements ListADT<E> {
	
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	/**
	 * The constructor of the MyDLL.
	 */
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * This function returns the size of the DLL.
	 * 
	 *@return Returns the size of the DLL.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 *
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * This function takes in a index and a element. They are used to add a element to the DLL. An exception is thrown if the given element is null or the index is out of the bounds of the DLL.
	 * 
	 *@param index The position in the DLL for the element to be inserted.
	 *@param toAdd The element to be added to the DLL.
	 *@return Returns true if the insertion was successful.
	 *@throws NullPointerException Thrown if the given element is null.
	 *@throws IndexOutOfBoundsException Thrown if the index is outside the bounds of the DLL.
	 */
	@Override
	public boolean add(int index, E toAdd) {
		
		if (toAdd == null)
			throw new NullPointerException();
		
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if (index == size)
			return add(toAdd);
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		if (index == 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		else {
			MyDLLNode<E> current = getNode(index);
			
			MyDLLNode<E> prevNode = current.prev;
			
			prevNode.next = newNode;
			newNode.prev = prevNode;
			
			newNode.next = current;
			current.prev = newNode;
		}
		
		size++;
		return true;
	}
	
	/**
	 * This helper function takes in a index and returns the node at the given position.
	 * 
	 * @param index The position of the node to be returned.
	 * @return Returns the node at the given index.
	 */
	private MyDLLNode<E> getNode(int index) {
		
		MyDLLNode<E> current;
		
		if (index < size / 2) {
			current = head;
			for (int i =0; i < index; i++)
				current = current.next;
		}
		else {
			current = tail;
			for (int i = size - 1; i > index; i--)
				current = current.prev;
		}
		
		return current;
	}

	/**
	 * This version of the Add function only takes in a element to be added to the end of the DLL. An exception is thrown if the given element is null.
	 * 
	 *@param toAdd The element to be added to the DLL.
	 *@return Returns true if the insertion was successful.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean add(E toAdd) {
		
		if (toAdd == null)
			throw new NullPointerException();
		
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
		
		if (isEmpty()) {
			head = tail = newNode;
		}
		else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		
		size++;
		return true;
	}

	/**
	 * <p>This function takes in a object that is an implementation of a ListADT and iterates through it, adding all elements of the list to the DLL.
	 * An exception is thrown if the given ListADT is null.
	 * </p>
	 * 
	 *@param toAdd The ListADT implementation who's elements will be added to the DLL.
	 *@return Returns true if the additions were a success.
	 *@throws NullPointerException Thrown if the ListADT implementation is null.
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) {
		
		if (toAdd == null)
			throw new NullPointerException();
		
		Iterator<? extends E> it = toAdd.iterator();
		
		while (it.hasNext()) {
			add(it.next());
		}
		
		return true;
	}

	/**
	 * This function takes in a index and returns the element stored at that position. An exception is thrown if the given index is out of the bounds of the DLL.
	 * 
	 *@param index The index of the node the element will be taken from.
	 *@return Returns the stored element.
	 *@throws IndexOutOfBoundsException Thrown if the index is outside the bounds of the DLL.
	 */
	@Override
	public E get(int index) {
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		return getNode(index).element;
		
	}

	/**
	 * <p>This function takes in a index that corresponds to the node that will be removed from the DLL. It returns the element that was stored in the removed node.
	 * An exception is thrown if the given index is out of the bounds of the DLL.
	 * </p>
	 * 
	 *@param index The position of the node to be removed.
	 *@return Returns the element that was contained in the removed node.
	 *@throws IndexOutOfBoundsException Thrown if the index is outside the bounds of the DLL.
	 */
	@Override
	public E remove(int index) {
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		MyDLLNode<E> target = getNode(index);
		
		if (target.prev != null)
			target.prev.next = target.next;
		else
			head = target.next;
		
		if (target.next != null)
			target.next.prev = target.prev;
		else
			tail = target.prev;
		
		size--;
		
		return target.element;
	}

	/**
	 * <p>This function is a variation of the remove function. This version takes in the element instead of the index.
	 * It then searches the DLL for the node containing the element before removing it form the DLL.
	 * It returns the element if there was a node with it in it.
	 * An exception is thrown if the given element is null.
	 * </p>
	 * 
	 *@param toRemove The element who's node is to be removed from the DLL.
	 *@return Returns the element if there was a node with it in it. Otherwise, it returns null.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public E remove(E toRemove) {
		
		if (toRemove == null) 
			throw new NullPointerException();
		
		MyDLLNode<E> current = head;
		
		while (current != null) {
			
			if (current.element.equals(toRemove)) {
				
				if (current.prev != null)
					current.prev.next = current.next;
				else
					head = current.next;
				
				if (current.next != null)
					current.next.prev = current.prev;
				else
					tail = current.prev;
				
				size--;
				return current.element;
			}
			
			current = current.next;
		}
		
		return null;
	}

	/**
	 * <p>This function takes in a index and a element. It then finds the node at the given index and changes it's contained element to the given one.
	 * It then returns the old element that was replaced.
	 * Exceptions are thrown if the given element is null or the index is out of the bounds of the DLL.
	 * </p>
	 * 
	 *@param index The index of the node who's value will be changed.
	 *@param toChange The new element to replace the old.
	 *@return Returns the old element that was replaced.
	 *@throws NullPointerException Thrown if the given element is null.
	 *@throws IndexOutOfBoundsException Thrown if the index is out of bounds of the DLL.
	 */
	@Override
	public E set(int index, E toChange) {
		
		if (toChange == null)
			throw new NullPointerException();
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		MyDLLNode<E> target = getNode(index);
		
		E oldValue = target.element;
		target.element = toChange;
		
		return oldValue;
	}

	/**
	 * This function returns true if the DLL is empty, otherwise returns false.
	 * 
	 *@return Returns true if DLL is empty, returns false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * This function will search the DLL for the given element. It returns true if the element can be found, false otherwise. Exception is thrown if the given element is null.
	 * 
	 *@param toFind The element that will be searched for in the DLL.
	 *@return Returns true if the DLL contains the element, false otherwise.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean contains(E toFind) {
		
		if (toFind == null)
			throw new NullPointerException();
		
		MyDLLNode<E> current = head;
		
		while (current != null) {
			if (current.element.equals(toFind))
				return true;
			
			current = current.next;
		}
		
		return false;
	}

	/**
	 *<p> This function takes in a array of the same data type as the DLL and then copies on the DLL's stored elements onto it, before returning the array.
	 * If the array is unable to hold the DLL, a new one is created.
	 * An exception is thrown if the given array is null.
	 * </p>
	 * 
	 *@param array The array to be used to store the DLL.
	 *@return Returns the array with the DLL's elements copied onto it.
	 *@throws NullPointerException Thrown if the given array is null.
	 */
	@Override
	public E[] toArray(E[] toHold) {
		
		if (toHold == null)
			throw new NullPointerException();
		
		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		
		MyDLLNode<E> current = head;
		
		for (int i =0; i < size; i++) {
			toHold[i] = current.element;
			current = current.next;
		}
		
		return toHold;
	}

	/**
	 * This function converts the DLL into a Object array and then returns it.
	 * 
	 *@return Returns the DLL in the form of a Object array.
	 */
	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[size];
		
		MyDLLNode<E> current = head;
		int i = 0;
		
		while (current != null) {
			array[i++] = current.element;
			current = current.next;
		}
		
		return array;
	}

	/**
	 * This function creates and initializes an iterator to be used to iterate through the DLL.
	 * 
	 *@return Returns the iterator object for this DLL.
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyDLLIterator();
	}
	
	private class MyDLLIterator implements Iterator<E> {
		
		private MyDLLNode<E> current = head;
		
		/**
		 * This function checks if there is another value to be iterated through. Returning true if there is, false otherwise.
		 * 
		 *@return Returns true if there is another value to be iterated through, false otherwise.
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		/**
		 * This function gets the next value of the DLL. Throws an exception if it is at the end of the DLL. An exception is thrown if the current element is the last one in the DLL.
		 * 
		 *@return Returns the value of the next element in the DLL.
		 *@throws NoSuchElementException Thrown if it is at the end of the DLL already.
		 */
		@Override
		public E next() {
			
			if (!hasNext())
				throw new NoSuchElementException();
			
			E value = current.element;
			current = current.next;
			
			return value;
		}
	}

}
