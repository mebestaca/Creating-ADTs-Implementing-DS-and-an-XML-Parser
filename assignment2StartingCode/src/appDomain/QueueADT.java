package appDomain;

import utilities.Iterator;

public interface QueueADT<E> {

    void enqueue(E element);

    E dequeue();

    E peek();

    void dequeueAll();

    boolean contains(E element);

    boolean equals(QueueADT<E> that);

    boolean isEmpty();

    boolean isFull();

    int size();

    int search(E element);

    Object[] toArray();

    E[] toArray(E[] array);

    Iterator<E> iterator();
}
