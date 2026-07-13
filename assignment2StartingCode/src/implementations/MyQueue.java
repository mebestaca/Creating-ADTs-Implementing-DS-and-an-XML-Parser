package implementations;

import utilities.Iterator;
import utilities.QueueADT;
import exceptions.EmptyQueueException;

public class MyQueue<E> implements QueueADT<E> {
	
	private MyDLL<E> list;
	
	public MyQueue() {
		list = new MyDLL<>();
	}

	@Override
	public void enqueue(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		list.add(element);
	}

	@Override
	public E dequeue() {
		
		if (list.isEmpty())
			throw new EmptyQueueException();
		
		return list.remove(0);
	}

	@Override
	public E peek() {
		
		if (list.isEmpty())
			throw new EmptyQueueException();
		
		return list.get(0);
	}

	@Override
	public void dequeueAll() {
		
		list.clear();
		
	}

	@Override
	public boolean contains(E element) {
		
		if (element == null)
			throw new NullPointerException();
		
		return list.contains(element);
	}

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

	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		
		return false;
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
		
		Iterator<E> it = list.iterator();
		
		while (it.hasNext()) {
			
			if (it.next().equals(element))
				return position;
			
			position++;
		}
		
		return -1;
	}

	@Override
	public Object[] toArray() {
		
		return list.toArray();
	}

	@Override
	public E[] toArray(E[] array) {
		
		return list.toArray(array);
	}

	@Override
	public Iterator<E> iterator() {
		
		return list.iterator();
	}

}
