package appDomain;

import utilities.Iterator;

/**
 * @author Marc Edison Estaca, Robert Macklin
 * 
 * This is a interface for a QueueADT implementation. It stores values in the form a queue.
 * When a element is added to the Queue, it is placed at the back. Values are then pulled from the front.
 * 
 * This will use MyDLL as the underlying data structure.
 * 
 * When initialized, the Queue will have a set max size.
 * 
 * @param <E> - This is a placeholder for the data type to be used.
 */
public interface QueueADT<E> {

    /**
     * This function will assume that the MyDLL used as the underlying data structure exists.
     * 
     * This function will take a element and add it to the end of the queue.
     * 
     * @param element - The element to be added to the queue.
     */
    void enqueue(E element);

    /**
     * This will assume that the Queue has more than zero elements in the Queue.
     * 
     * This function will remove the first element from the queue before returning it.
     * 
     * @return - Returns the element that was taken from the front of the queue.
     */
    E dequeue();

    /**
     * This will assume that there is more that zero elements in the Queue.
     * 
     * This function checks the element at the front of the Queue and returns it.
     * 
     * @return - Returns the element at the front of the Queue.
     */
    E peek();

    /**
     * This function will go through and remove each element of the Queue until it is empty.
     */
    void dequeueAll();

    /**
     * This will assume that the element given is not null.
     * 
     * This function will take in a element and then iterate through the Queue to see if it is contained within.
     * When it finds a match, it returns true. If it makes it to the end without finding the value, it returns false.
     * 
     * @param element - The element to be checked against the Queue.
     * @return - Returns either true if the Queue contains the test element. False if it does not.
     */
    boolean contains(E element);

    /**
     * This function will first compare the values of isEmpty(), isFull(), and then size(). If all of these match, it will then iterate through the Queues.
     * If at any point the values don't match, it returns false. If all of these checks pass, it returns true.
     * 
     * @param that - The Queue to be compared too.
     * @return - Returns true if this Queue and the compared Queue are the same. Returns false otherwise.
     */
    boolean equals(QueueADT<E> that);

    /**
     * This function will return true if the Queue is empty. If not, it returns false.
     * 
     * @return - Returns true if the Queue has no values in it. False otherwise.
     */
    boolean isEmpty();

    /**
     * This function will check if the size of the Queue is greater than or equal to the max size. If it is, it returns true. If not, it returns false.
     * 
     * @return - Returns true if the size of the Queue matches the max size. Returns false otherwise.
     */
    boolean isFull();

    /**
     * This function will return the size of the Queue.
     * 
     * @return - Returns the size of the Queue.
     */
    int size();

    /**
     * This will assume that the element given is not null.
     * 
     * This function will take in a element and then iterate through the Queue to see if it is contained within.
     * When it finds a match, it returns the position of the element. If it makes it to the end without finding the value, it returns -1.
     * 
     * @param element - The element to be checked against the Queue.
     * @return - Returns the position of the value in the Queue. If it doesn't exist, it returns -1.
     */
    int search(E element);

    /**
     * This will assume that the Queue has more than zero elements in it.
     * 
     * This function will create a Object Array the same length as this Queue before iterating through the Queue and copying them to the Array.
     * It then will return the Array.
     * 
     * @return - Returns a Object Array of the values in the Queue.
     */
    Object[] toArray();

    /**
     * This function will create a Array the same length as this Queue before iterating through the Queue and copying them to the Array.
     * It then will return the Array. If the given Array is too small to hold the Queue, it will create one that can fit it.
     * 
     * @param array - A pre-existing array for the Queue to be copied into.
     * @return - Returns the Queue in the form of a array.
     */
    E[] toArray(E[] array);

    /**
     * This sets up the Iterator for the Queue by passing it to the underlying data structure.
     * 
     * @return - Returns the iterator for this Queue.
     */
    Iterator<E> iterator();
}
