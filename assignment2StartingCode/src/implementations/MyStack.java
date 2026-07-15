package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.StackADT;

/**
 * @author Reiner Justin Realica, Robert Macklin
 * 
 * This Class is a implementation of the StackADT interface.
 * 
 * @param <E> The data type to be used by the stack.
 */
@SuppressWarnings("unchecked")
public class MyStack<E> implements StackADT<E> {
	
	private MyArrayList<E> list;
	
	/**
	 * The constructor of the MyStack. It initializes the underlying MyArrayList.
	 */
	public MyStack() {
		
		list = new MyArrayList<>();
	}

	/**
	 * This function takes in a element and adds it to the top of the stack. If the element is null, it throws an exception.
	 * 
	 *@param element The element to be added to the top of the stack.
	 *@throws NullPointerException Thrown if the provided element is null.
	 */
	@Override
	public void push(E element) {
		
		if (element == null) 
			throw new NullPointerException();
		
		list.add(element);
		
	}

	/**
	 * Takes the element off of the top of the stack and returns it. Throws an exception of the stack is empty.
	 * 
	 *@return Returns the element that was removed from the stack.
	 *@throws EmptyStackException Thrown if the stack is empty.
	 */
	@Override
	public E pop() {
		
		if (list.isEmpty())
			throw new EmptyStackException();
		
		return list.remove(list.size() - 1);
	}

	/**
	 * This function returns the top value of the stack without removing it. Throws an exception if the stack is empty.
	 * 
	 *@return Returns the value of the item at the top of the stack.
	 *@throws EmptyStackException Thrown if the stack is empty.
	 */
	@Override
	public E peek() {
		
		if (list.isEmpty())
			throw new EmptyStackException();
		
		return list.get(list.size() - 1);
	}

	/**
	 * This function clears the stack.
	 */
	@Override
	public void clear() {
		
		list.clear();
		
	}

	/**
	 * <p>This function searches the stack for the given element, either returning true if it found or false if not. 
	 * Throws and exception if the given element is null.</p>
	 * 
	 *@param element  The element to be used to search the stack.
	 *@return Returns either true if the stack contains the given element, or false if not.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public boolean contains(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		return list.contains(element);
	}

	/**
	 * This function takes in a second Stack and compares it to this one. If they are the same, it returns true. Otherwise, it returns false.
	 * 
	 *@param that The second stack to be compared to this one.
	 *@return Returns true if both stacks match, otherwise returns false.
	 */
	@Override
	public boolean equals(StackADT<E> that) {
		
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
	 * This function returns true if the stack is empty, otherwise returns false.
	 * 
	 *@return Returns true if stack is empty, returns false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	/**
	 * This function returns the size of the stack.
	 * 
	 *@return Returns the size of the stack.
	 */
	@Override
	public int size() {
		
		return list.size();
	}

	/**
	 * <p>This function searches the stack for the given element of the stack. It then returns the position of the element in the stack.
	 * If the element doesn't exist, it instead returns -1. Throws an exception if the given element is null.</p>
	 * 
	 *@param element The element to be used to search the stack.
	 *@return Returns either the position of the element if it exists, or -1 if it does not.
	 *@throws NullPointerException Thrown if the given element is null.
	 */
	@Override
	public int search(E element) {
		
		if (element == null) 
			throw new NullPointerException();
		
		int position = 1;
		
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).equals(element))
				return position;
			
			position++;
		}
		
		return -1;
	}

	/**
	 * This function returns false.
	 * 
	 *@return Returns false.
	 */
	@Override
	public boolean stackOverflow() {
		
		return false;
	}

	/**
	 * This function converts the stack into a Object array and then returns it.
	 * 
	 *@return Returns the stack in the form of a Object array.
	 */
	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(list.size() - 1 - i);
		}
		
		return array;
	}

	/**
	 * <p>This function takes in a array of the same data type as the stack and then copies on the stack onto it, before returning the array.
	 * If the array is unable to hold the stack, a new one is created.</p>
	 * 
	 *@param array The array to be used to store the stack.
	 *@return Returns the array with the stack copied onto it.
	 *@throws NullPointerException Thrown if the given array is null.
	 */
	@Override
	public E[] toArray(E[] array) {
		
		if (array == null)
			throw new NullPointerException();
		
		if (array.length < list.size()) {
			array = (E[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), list.size());
		}
		
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(list.size() - 1 - i);
		}
		
		return array;
	}

	/**
	 * This function creates and initializes an iterator to be used to iterate through the stack.
	 * 
	 *@return Returns the iterator object for this stack.
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			
			private int current = list.size() - 1;
			
			/**
			 * This function checks if there is another value to be iterated through. Returning true if there is, false otherwise.
			 * 
			 *@return Returns true if there is another value to be iterated through, false otherwise.
			 */
			@Override
			public boolean hasNext() {
				return current >= 0;
			}
			
			/**
			 * This function gets the next value of the stack. Throws an exception if it is at the end of the stack.
			 * 
			 *@return Returns the value of the next element in the stack.
			 *@throws NoSuchElementException Thrown if it is at the end of the stack already.
			 */
			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				
				return list.get(current--);
			}
		};
	}

}
