package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import appDomain.StackADT;
import utilities.Iterator;

@SuppressWarnings("unchecked")
public class MyStack<E> implements StackADT<E> {
	
	private MyArrayList<E> list;
	
	public MyStack() {
		
		list = new MyArrayList<>();
	}

	@Override
	public void push(E element) {
		
		if (element == null) 
			throw new NullPointerException();
		
		list.add(element);
		
	}

	@Override
	public E pop() {
		
		if (list.isEmpty())
			throw new EmptyStackException();
		
		return list.remove(list.size() - 1);
	}

	@Override
	public E peek() {
		
		if (list.isEmpty())
			throw new EmptyStackException();
		
		return list.get(list.size() - 1);
	}

	@Override
	public void clear() {
		
		list.clear();
		
	}

	@Override
	public boolean contains(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		return list.contains(element);
	}

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

	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	@Override
	public int size() {
		
		return list.size();
	}

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

	@Override
	public boolean stackOverflow() {
		
		return false;
	}

	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(list.size() - 1 - i);
		}
		
		return array;
	}

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

	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			
			private int current = list.size() - 1;
			
			@Override
			public boolean hasNext() {
				return current >= 0;
			}
			
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
