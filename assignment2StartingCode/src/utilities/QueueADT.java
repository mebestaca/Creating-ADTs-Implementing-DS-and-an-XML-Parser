package utilities;

/**
 * @author Marc Edison Estaca, Robert Macklin, Reiner Justin Realica
 * 
 * A queue abstract data type (ADT) that stores elements in first-in, first-out (FIFO) order.
 * 
 * <p>Elements are added to the rear of the queue and removed from the front of the queue.</p>
 * 
 * <p>When initialized, the Queue will have a set max size.</p>
 * 
 * @param <E> the type of elements stored in the queue
 */
public interface QueueADT<E> {

    /**
     * 
     * This function will take an element and add it to the end of the queue.
     * 
     * @param element the element to be added to the queue.
     */
    void enqueue(E element);

    /**
     * This will assume that the Queue has more than zero elements in the Queue.
     * 
     * This function will remove the first element from the queue before returning it.
     * 
     * @return the element that was taken from the front of the queue.
     */
    E dequeue();

    /**
     * This will assume that there is more than zero elements in the Queue.
     * 
     * This function returns the element at the front of the Queue without removing it
     * 
     * @return the element at the front of the Queue.
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
     * @param element the element to be checked against the Queue.
     * @return - Returns either true if the Queue contains the test element. False if it does not.
     */
    boolean contains(E element);

    /**
     * <p>For equality, this function will first compare the values of isEmpty(), isFull(), and then size(). 
     * If all of these match, it will then iterate through the Queues.
     * If at any point the values don't match, it returns false. If all of these checks pass, it returns true.</p>
     * 
     * @param that the Queue to compare with
     * @return true if this Queue and the compared Queue are the same. Returns false otherwise.
     */
    boolean equals(QueueADT<E> that);

    /**
     * Determines whether the queue contains no elements
     * 
     * @return true if the Queue has no values in it. False otherwise.
     */
    boolean isEmpty();

    /**
     * Determines whether the Queue has reached its maximum capacity.
     * 
     * @return true if the size of the Queue matches the max size. Returns false otherwise.
     */
    boolean isFull();

    /**
     * This function will return the number of elements currently stored in the Queue.
     * 
     * @return the number of elements in the Queue
     */
    int size();

    /**
     * This will assume that the element given is not null.
     * 
     * <p>This function will take in an element and then iterate through the Queue to see if it is contained within.
     * When it finds a match, it returns the position of the element. If it makes it to the end without finding the value, it returns -1.</p>
     * 
     * @param element the element to search for
     * @return the position of the value in the Queue. If it doesn't exist, it returns -1.
     */
    int search(E element);

    /**
     * This will assume that the Queue has more than zero elements in it.
     * 
     * <p>This function will create a Object Array the same length as this Queue before iterating through the Queue and copying them to the Array.
     * It then will return the Array.</p>
     * 
     * @return an Object Array containing all the values of the Queue.
     */
    Object[] toArray();

    /**
     * <p>If the provided array is not large enough to hold all elements, a new array of the same runtime type is created and returned.</p>
     * 
     * @param array the array into which the queue elements are copied
     * @return an array containing the elements of the queue
     */
    E[] toArray(E[] array);

    /**
     * This sets up the Iterator for the Queue by passing it to the underlying data structure.
     * 
     * @return the iterator for this Queue.
     */
    Iterator<E> iterator();
}
