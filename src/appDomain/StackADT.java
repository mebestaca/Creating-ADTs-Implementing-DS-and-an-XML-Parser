package appDomain;

import utilities.Iterator;

public interface StackADT<E> {

    void push(E element);

    E pop();

    E peek();

    void clear();

    boolean contains(E element);

    boolean equals(StackADT<E> that);

    boolean isEmpty();

    int size();

    int search(E element);

    boolean stackOverflow();

    Object[] toArray();

    E[] toArray(E[] array);

    Iterator<E> iterator();
}
