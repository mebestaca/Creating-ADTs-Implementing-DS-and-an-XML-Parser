package implementations;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

@SuppressWarnings("unchecked")
public class MyDLL<E> implements ListADT<E> {
	
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;
	
	public MyDLL() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

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
		
		return getNode(index).element;
		
	}

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

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

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

	@Override
	public Iterator<E> iterator() {
		return new MyDLLIterator();
	}
	
	private class MyDLLIterator implements Iterator<E> {
		
		private MyDLLNode<E> current = head;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
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
