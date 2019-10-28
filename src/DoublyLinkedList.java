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
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
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
     * @param element data to be added, at what index
     * @throws NullPointerException if data received is null
     * @throws IndexOutOfBoundsException if index is out of bounds
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
     * @param element data to be added, at what index
     * @return whether or not the element is in the list
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
     * @param index at what index the data is added
     * @throws IndexOutOfBoundsException when the index is out of bounds
     * @return the element at the index
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index > (nelems-1) || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node current = getNth(index);

        return current.getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index at what index you want the node
     * @return the node to get
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
     * @return whether or not the list is empty
     */
    @Override
    public boolean isEmpty() {
       if (this.nelems == 0){
           return true;
       }

        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index at what index the node is to be removed
     * @throws IndexOutOfBoundsException when the index is out of bounds
     * @return the element at the index that was removed
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
     * @param index at what index the node is to be removed
     * @param element what is the new data you want it to be set
     * @throws IndexOutOfBoundsException when the index is out of bounds
     * @throws NullPointerException if the element is null
     * @return the element at the index that was previously set
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
     * @return the size of the list
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * @param index at what index the node is to be inserted
     * @param otherList the list to splice iinto
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {
        if (index < 0 && index > otherList.size()) {
            throw new IndexOutOfBoundsException();
        }

        //add other list to the index specified
        int counter = index;
        Node current = otherList.head.getNext();
        while(current != otherList.tail) {
            T newno = current.getElement();
            this.add(counter,newno);
            counter++;
            current = current.getNext();
        }

    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * @param subsequence sequence to match
     * @return an array of ints holding all the starting positions that match the subsequence
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        boolean ismatch = true;
        int counter;
        for(int x = 0; x < this.size(); x++){
            //if the first element matchesuu
            if (subsequence.size() == 0){
                break;
            }
            if (this.get(x) == subsequence.get(0)){
                //check if the rest of the elements match
                counter = x+1;
                for (int y = 1; y<subsequence.size(); y++){
                    if (counter >= this.size()){
                        ismatch = false;
                        break;
                    }
                    if (this.get(counter).equals(subsequence.get(y)) == false){
                        ismatch = false;
                    }
                    counter++;
                }
                if ( ismatch == true) {
                    indices.add(x);
                }
                }
            ismatch = true;
            }

        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }
}



