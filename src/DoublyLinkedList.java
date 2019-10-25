/*
 * NAME: Jason Chau
 * PID: A14388619
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Jason Chau
 * @since 1999 wtf put my DOB here?
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            this.data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            //TODO: complete implementation
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            //TODO: complete implementation
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            //TODO: complete implementation
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            //TODO: complete implementation
            this.next = n;
            n.prev = this;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {

            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
            p.next = this;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        //TODO: Implementation for throwing exceptions followed by
        if (element == null){
            throw new NullPointerException();
        }
        //loop through until the next available spot to be added
        Node current = this.head;
        while (current.getNext() != this.tail) {
            current = current.getNext();
            }

        //simple node add
        Node currnext = current.getNext();
        Node temp = new Node(element);
        current.setNext(temp);
        currnext.setPrev(temp);
        temp.setNext(currnext);
        temp.setPrev(current);
        this.nelems++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * TODO: Javadoc comments
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        //edge case where the index is too big
        if (index > (this.nelems) || index < 0 ) {
           throw new IndexOutOfBoundsException();
        }

        //edge case where elem is null
        if (element == null){
            throw new NullPointerException();
        }

        Node current = this.head;
        //loop through until get to specified location
        for (int x = 0; x < index; x++){
            current = current.getNext();
        }

        //add in the new node.
        Node currnext = current.getNext();
        Node temp = new Node(element);
        current.setNext(temp);
        currnext.setPrev(temp);
        temp.setNext(currnext);
        temp.setPrev(current);
        nelems++;

    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node current = this.head;
        //loop through all the nodes and check if they match the element
        while(current.getNext() != this.tail){
            current = current.getNext();
            if (current.getElement() == data){
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * TODO: Javadoc comments
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation to get the node at index
        if (index > (nelems-1) || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node current = getNth(index);

        return current.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * TODO: Javadoc comments
     */
    private Node getNth(int index) {
        Node current = this.head.getNext();
        for (int x = 0; x < index; x++){
            current = current.getNext();
        }
        return current;
    }

    /**
     * Determine if the list empty
     *
     * TODO: javadoc comments
     */
    @Override
    public boolean isEmpty() {
        //TODO: implement isEmpty
       if (this.nelems == 0){
           return true;
       }

        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * TODO: javadoc comments
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        //TODO: Fill in implementation
        if (index > (this.nelems -1) || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node toremove = getNth(index);
        T datatoget = toremove.getElement();
        Node previ = toremove.getPrev();
        Node nexte = toremove.getNext();
        previ.setNext(nexte);
        nexte.setPrev(previ);
        this.nelems--;


        return datatoget;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index > (this.nelems -1) || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        //get the node at nth elem and set the data
        Node toset = getNth(index);
        T prevdata = toset.getElement();
        toset.setElement(element);

        return prevdata;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * TODO: javadoc comments
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * TODO: javadoc comments
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        //TODO: Determine if index is valid

        //TODO: Splicing implementation. HINT: remember DoublyLinkedList's  have dummy nodes
    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * TODO: javadoc comments
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        //TODO: Add implementation to find the starting indices

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}



