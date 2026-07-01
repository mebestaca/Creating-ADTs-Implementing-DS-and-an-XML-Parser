package appDomain;

import utilities.Iterator;

/**
 * @author Marc Edison Estaca, Robert Macklin
 * 
 * This interface is for a StackADT implementation. It stores elements in the form of a stack.
 * When a element is added to the Stack, it is placed at the top. Values are then pulled from the top.
 * 
 * This will use MyArrayList as the underlying data structure.
 * 
 * When initialized, the Stack will have a set max size.
 * 
 * @param <E> - This is a placeholder for the data type to be used.
 */
public interface StackADT<E> {

    /**
     * This will assume that the underlying MyArrayList has been initialized.
     * 
     * This function will take in a given element and add it to the top of the Stack.
     * 
     * @param element - The element to be added to the Stack.
     */
    void push(E element);

    /**
     * This will assume that the Stack has at least one element in it.
     * 
     * This function will store the value of the topmost element in the Stack before removing it. It will then return the stored valued.
     * 
     * @return - Returns the value of the element that was at the top of the Stack.
     */
    E pop();

    /**
     * This will assume that the Stack has at least one element in it.
     * 
     * This function will get the value of the topmost element before returning it.
     * 
     * @return - Returns the value of the element that is at the top of the Stack.
     */
    E peek();

    /**
     * This function will remove all elements of the Stack.
     */
    void clear();

    /**
     * This will assume that the element given is not null.
     * 
     * This function will take in a element and then iterate through the Stack to see if it is contained within.
     * When it finds a match, it returns true. If it makes it to the end without finding the value, it returns false.
     * 
     * @param element - The element to be checked against the Stack.
     * @return - Returns either true if the Stack contains the test element. False if it does not.
     */
    boolean contains(E element);

    /**
     * This function will first compare the values of isEmpty(), isFull(), and then size(). If all of these match, it will then iterate through the Stacks.
     * If at any point the values don't match, it returns false. If all of these checks pass, it returns true.
     * 
     * @param that - The Stack to be compared too.
     * @return - Returns true if this Queue and the compared Stack are the same. Returns false otherwise.
     */
    boolean equals(StackADT<E> that);

    /**
     * This function will return true if the Stack is empty. If not, it returns false.
     * 
     * @return - Returns true if the Stack has no values in it. False otherwise.
     */
    boolean isEmpty();

    /**
     * This function will return the size of the Stack.
     * 
     * @return - Returns the size of the Stack.
     */
    int size();

    /**
     * This will assume that the element given is not null.
     * 
     * This function will take in a element and then iterate through the Stack to see if it is contained within.
     * When it finds a match, it returns the position of the element. If it makes it to the end without finding the value, it returns -1.
     * 
     * @param element - The element to be checked against the Stack.
     * @return - Returns the position of the value in the Stack. If it doesn't exist, it returns -1.
     */
    int search(E element);

    /**
     * This function will check if the size of the Stack is equal too or greater than the max size of the Stack. If not, it returns false.
     * 
     * @return - Returns true if the size of the Stack is equal too or greater than the max size of the Stack. Returns false otherwise.
     */
    boolean stackOverflow();

    /**
     * This will assume that the Stack has more than zero elements in it.
     * 
     * This function will create a Object Array the same length as this Stack before iterating through the Stack and copying them to the Array.
     * It then will return the Array.
     * 
     * @return - Returns a Object Array of the values in the Stack.
     */
    Object[] toArray();

    /**
     * This function will create a Array the same length as this Stack before iterating through the it and copying them to the Array.
     * It then will return the Array. If the given Array is too small to hold the Stack, it will create one that can fit it.
     * 
     * @param array - A pre-existing array for the Stack to be copied into.
     * @return - Returns the Stack in the form of a array.
     */
    E[] toArray(E[] array);

    /**
     * This sets up the Iterator for the Stack by passing it to the underlying data structure.
     * 
     * @return - Returns the iterator for this Queue.
     */
    Iterator<E> iterator();
}
