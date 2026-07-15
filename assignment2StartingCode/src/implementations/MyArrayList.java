package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

/**
 * @author Reiner Justin Realica, Robert Macklin
 * 
 * This Class is a implementation of a ArrayList.
 * 
 * @param <E> The data type to be used by the ArrayList.
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> implements ListADT<E> {
	
	private E[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * The constructor of the MyArrayList.
	 */
	public MyArrayList() {
		
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	/**
	 * This helper function checks if the size of the ArrayList reaches the size of the underlying array. If it does, it doubles the length of the array.
	 */
	private void ensureCapacity() {
		
		if (size == data.length) {
			
			int newCapacity = data.length * 2;
			E[] newData = (E[]) new Object[newCapacity];
			
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
			
			data = newData;
		}
	}
	
	/**
	 * This function returns the size of the ArrayList.
	 * 
	 *@return Returns the size of the ArrayList.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This function returns true if ArrayList is empty, otherwise returns false.
	 * 
	 *@return Returns true if the ArrayList is empty. False otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This function clears the ArrayList.
	 */
	@Override
	public void clear() {
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	/**
	 * This function adds a given element to the ArrayList. An exception is thrown if the given element is null.
	 * 
	 *@param toAdd The element to be added to the ArrayList.
	 *@return Returns true if the function was successful.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean add(E toAdd) {
		
		if (toAdd == null)
			throw new NullPointerException("Cannot add null");
		
		ensureCapacity();
		
		data[size] = toAdd;
		size++;
		
		return true;
			
	}

	/**
	 * <p>A variation of the Add function. This version takes in both the element and a index. The element is added at the indexed position.
	 * Returns true if the insertion was successful.
	 * Exceptions are thrown if the element is null or if the index is out of bounds of the ArrayList.
	 * </p>
	 * 
	 *@param index The index that the element will be inserted into.
	 *@param toAdd The element to be added.
	 *@return Returns true if the insertion was successful.
	 *@throws NullPointerException Thrown if the element is null.
	 *@throws IndexOutOfBoundsException Thrown if the index is out of bounds of the ArrayList.
	 */
	@Override
	public boolean add(int index, E toAdd) {
		
		if (toAdd == null) 
			throw new NullPointerException();
		
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		ensureCapacity();
		
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}
		
		data[index] = toAdd;
		size++;
		
		return true;
	}

	/**
	 * This function takes in a object that is an implementation of a ListADT and iterates through it, adding all elements of the list to the ArrayList. Exception is thrown if the ListADT implementation is null.
	 * 
	 *@param toAdd The ListADT implementation who's elements will be added to the ArrayList.
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
	 * Returns the element at the given index of the ArrayList. Throws an exception if the given index is out of bounds.
	 * 
	 *@param index The index of the element to be returned.
	 *@return Returns the element at the given index.
	 *@throws IndexOutOfBoundsException Thrown if the given index is out of the bounds of the ArrayList.
	 */
	@Override
	public E get(int index) {
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		return data[index];
	}

	/**
	 * Removes the the element at the given index before returning it. Throws an exception if the index is out of bounds. Exception is thrown if the index is out of the bounds of the ArrayList.
	 * 
	 *@param index Index of the element to be removed.
	 *@return Returns the removed element.
	 *@throws IndexOutOfBoundsException Thrown if the index is out of the bounds of the ArrayList.
	 */
	@Override
	public E remove(int index) {
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		E removed = data[index];
		
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		
		data[size - 1] = null;
		size--;
		
		return removed;
	}

	/**
	 *<p> This function is a variation of the remove function. This version takes in the element instead of the index.
	 * It then searches the ArrayList for the element before removing it. It is then returned.
	 * Exception is thrown if the given element is null.
	 * </p>
	 * 
	 *@param toRemove The element to be removed from the ArrayList.
	 *@return Returns the element if it was contained within the ArrayList. Otherwise, it returns null.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public E remove(E toRemove) {
		
		if (toRemove == null) 
			throw new NullPointerException();
		
		for (int i = 0; i < size; i++) {
			
			if (data[i].equals(toRemove)) {
				return remove(i);
			}
		}
		
		return null;
	}

	/**
	 * <p>This function takes in a index and a element. It then finds the element at the given index and changes it to the given one.
	 * It then returns the old element that was replaced.
	 * Exceptions are thrown if the given element is null or if the index is out of the bounds of the ArrayList.
	 * </p>
	 * 
	 *@param index The index of the element that will be changed.
	 *@param toChange The new element to replace the old.
	 *@return Returns the old element that was replaced.
	 *@throws NullPointerException Thrown if the given element is null.
	 *@throws IndexOutOfBoundsException Thrown if the index is out of the bounds of the ArrayList.
	 */
	@Override
	public E set(int index, E toChange) {
		
		if (toChange == null)
			throw new NullPointerException();
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		E old = data[index];
		data[index] = toChange;
		
		return old;
	}

	/**
	 * This function will search the ArrayList for the given element. It returns true if the element can be found, false otherwise. An exception is thrown if the given element is null.
	 * 
	 *@param toFind The element that will be searched for in the ArrayList.
	 *@return Returns true if the ArrayList contains the element, false otherwise.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean contains(E toFind) {
		
		if (toFind == null)
			throw new NullPointerException();
		
		for (int i = 0; i < size; i++) {
			
			if (data[i].equals(toFind))
				return true;
		}
		
		return false;
	}

	/**
	 * <p>This function takes in a array of the same data type as the ArrayList and then copies on the elements onto it, before returning the array.
	 * If the array is unable to hold the ArrayList, a new one is created.
	 * An exception is thrown if the given array is null.
	 * </p>
	 * 
	 *@param array The array to be used to store the ArrayList.
	 *@return Returns the array with the ArrayList's elements copied onto it.
	 *@throws NullPointerException Thrown if the given array is null.
	 */
	@Override
	public E[] toArray(E[] toHold) {
		
		if (toHold == null)
			throw new NullPointerException();
		
		if (toHold.length < size) {
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}
		
		for (int i = 0; i < size; i++) {
			toHold[i] = data[i];
		}
		
		return toHold;
	}

	/**
	 * This function converts the ArrayList into a Object array and then returns it.
	 * 
	 *@return Returns the ArrayList in the form of a Object array.
	 */
	@Override
	public Object[] toArray() {

		Object[] array = new Object[size];
		
		for (int i = 0; i < size; i++) {
			array[i] = data[i];
		}
		
		return array;
	}

	/**
	 * This function creates and initializes an iterator to be used to iterate through the ArrayList.
	 * 
	 *@return Returns the iterator object for this ArrayList.
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator<E> {
		
		private int current = 0;
		
		/**
		 * This function checks if there is another element to be iterated through. Returning true if there is, false otherwise.
		 * 
		 *@return Returns true if there is another element to be iterated through, false otherwise.
		 */
		@Override
		public boolean hasNext() {
			return current < size;
		}
		
		/**
		 * This function gets the next value of the ArrayList. Throws an exception if it is at the end of the ArrayList. An exception is thrown if the current element is the last one in the ArrayList.
		 * 
		 *@return Returns the value of the next element in the ArrayList.
		 *@throws NoSuchElementException Thrown if it is at the end of the ArrayList already.
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return data[current++];
		}
	}
}
	
