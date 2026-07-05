package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

@SuppressWarnings("unchecked")
public class MyArrayList<E> implements ListADT<E> {
	
	private E[] data;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	public MyArrayList() {
		
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
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
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		data = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	@Override
	public boolean add(E toAdd) {
		
		if (toAdd == null)
			throw new NullPointerException("Cannot add null");
		
		ensureCapacity();
		
		data[size] = toAdd;
		size++;
		
		return true;
			
	}

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

	@Override
	public E get(int index) {
		
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		return data[index];
	}

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

	@Override
	public Object[] toArray() {

		Object[] array = new Object[size];
		
		for (int i = 0; i < size; i++) {
			array[i] = data[i];
		}
		
		return array;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator<E> {
		
		private int current = 0;
		
		@Override
		public boolean hasNext() {
			return current < size;
		}
		
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			return data[current++];
		}
	}
}
	
