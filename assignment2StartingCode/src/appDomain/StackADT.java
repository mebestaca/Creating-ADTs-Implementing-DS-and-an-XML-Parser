package appDomain;

import utilities.Iterator;

/**
 * @author Marc Edison Estaca, Robert Macklin, Reiner Justin Realica
 *
 * A stack abstract data type (ADT) that stores elements in last-in, first-out (LIFO) order.
 * 
 * <p>It stores elements in the form of a stack. 
 * When a element is added to the Stack, it is placed at the top. 
 * Values are then pulled from the top.</p>
 * 
 * <p>This will use MyArrayList as the underlying data structure.</p>
 * 
 * <p>When initialized, the Stack will have a set max size.</p>
 * 
 * @param <E> the type of elements stored in the stack
 */
public interface StackADT<E> {

    /**
     * This will assume that the underlying MyArrayList has been initialized.
     * 
     * This function will take in a given element and add it to the top of the Stack.
     * 
     * @param element the element to be added to the Stack.
     */
    void push(E element);

    /**
     * This will assume that the Stack has at least one element in it.
     * 
     * Removes and returns the element at the top of the stack
     * 
     * @return the element removed from the top of the stack
     */
    E pop();

    /**
     * This will assume that the Stack has at least one element in it.
     * 
     * Returns the element at the top of the stack without removing it.
     * 
     * @return the value of the element that is at the top of the Stack.
     */
    E peek();

    /**
     * This function will remove all elements from the Stack.
     */
    void clear();

    /**
     * This will assume that the element given is not null.
     * 
     * This function will take in a element and then iterate through the Stack to see if it is contained within.
     * When it finds a match, it returns true. If it makes it to the end without finding the value, it returns false.
     * 
     * @param element the element to search for
     * @return either true if the Stack contains the test element, false if it does not.
     */
    boolean contains(E element);

    /**
     * For equality, this function will first compare the values of isEmpty(), isFull(), and then size(). If all of these match, it will then iterate through the Stacks.
     * If at any point the values don't match, it returns false. If all of these checks pass, it returns true.
     * 
     * @param that the stack to compare with 
     * @return true if this Queue and the compared Stack are the same. Returns false otherwise.
     */
    boolean equals(StackADT<E> that);

    /**
     * This function will return true if the Stack is empty. If not, it returns false.
     * 
     * @return - Returns true if the Stack has no values in it. False otherwise.
     */
    boolean isEmpty();

    /**
     * This function will return the number of elements currently stored in the Stack.
     * 
     * @return the number of elements in the Stack.
     */
    int size();

    /**
     * This will assume that the element given is not null.
     * 
     * <p>This function will take in a element and then iterate through the Stack to see if it is contained within.
     * When it finds a match, it returns the position of the element. If it makes it to the end without finding the value, it returns -1.</p>
     * 
     * @param element the element to search for
     * @return the position of the value in the Stack. If it doesn't exist, it returns -1.
     */
    int search(E element);

    /**
     * This function will check if the size of the Stack has reached or exceeded its maximum capacity. If not, it returns false.
     * 
     * @return true if the size of the Stack has reached its maximum capacity. Returns false otherwise.
     */
    boolean stackOverflow();

    /**
     * This will assume that the Stack has more than zero elements in it.
     * 
     * <p>This function will create a Object Array the same length as this Stack before iterating through the Stack and copying them to the Array.
     * It then will return the Array.</p>
     * 
     * @return an Object Array containing the elements of the Stack.
     */
    Object[] toArray();

    /**
     * Returns an array containing all elements in the stack in stack order.
     *
     *<p>If the provided array is not large enough to hold all elements, a new array of the same runtime type is created and returned.</p>
     *
     * @param array the array into which the stack elements are copied
     * @return an array containing the elements of the stack
     */
    E[] toArray(E[] array);

    /**
     * This sets up the Iterator for the Stack by passing it to the underlying data structure.
     * 
     * @return an iterator for this Stack
     */
    Iterator<E> iterator();
}
