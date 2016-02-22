/**
 * Circular Doubly Linked List implementation of the List interface.
 * @author Peter Durham, Allan Wang
 * pdurham2, awang53
 * 600.226.01/0.2
 * Assignment P1
 * @param <T> the type of the List
 */
public class CList<T> implements List<T> {

    /**
     * Inner doubly linked Node class for convenience.
     */
    public class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
        }
    }

    /** Head node. */
    private Node head;
    /** Tail node. */
    private Node tail;
    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;
    /** The current position in the List. */
    private int pos;

    /**
     * Create an empty list.
     */
    public CList() {
        this.clear();  // code reuse!
    }

    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.head = new Node(null, this.head, this.head);
        this.tail = new Node(null, this.tail, this.tail);
        this.curr = new Node(null, this.head, this.head);
        this.pos = 0;
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        Node n = new Node(t, null, null);
        if (this.size == 0) {
            n = new Node(t, n, n);
            this.head = n;
            this.tail = this.head;
            this.curr.next = this.head;
            this.curr.prev = this.head;
        } else if (this.size == 1) {
            n = new Node(t, this.head, this.head);
            this.head.prev = n;
            this.head.next = n;
            this.tail = this.head;
            this.head = n;
            this.curr.next = n;
            this.curr.prev = this.tail;
        } else {
            n = new Node(t, this.curr.prev, this.curr.next);
            if (this.pos == 0) {
                this.head = n;
            }
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.curr.next = n; // connect cursor to new data node
        }
        if (n.prev == null || n.next == null) {
            return false;
        }
        this.size++;
        return true;
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        Node temp = this.curr;       // hold onto original position
        this.curr.prev = this.tail;       // move to after the tail sentinel
        this.curr.next = this.head;
        Node n = new Node(t, null, null);
        if (this.size == 0) {
            n = new Node(t, n, n);
            this.head = n;
            this.tail = this.head;
        } else if (this.size == 1) {
            n = new Node(t, this.head, this.head);
            this.head.prev = n;
            this.head.next = n;
            this.tail = n;
        } else {
            n = new Node(t, this.curr.prev, this.curr.next);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.tail = n;
            this.head.prev = n;
        }
        if (n.prev == null || n.next == null) {
            return false;
        }
        this.size++;
        this.curr = temp;        // restore cursor to original position
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor).
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.size == 0) {
            this.pos = 0;
            return null;
        }
        T val = this.curr.next.data;
        this.curr.next = this.curr.next.next;  // bypass node being deleted
        this.curr.next.prev = this.curr.prev;  // bypass it in other direction
        this.curr.prev.next = this.curr.next;
        if (this.size == 1) {
            this.clear();
            return val;
        } else if (this.pos == 0) {
            this.head = this.curr.next;
            this.head.prev = this.tail;
            this.tail.next = this.head;
        } else if (this.pos == (this.size - 1)) {
            this.tail = this.curr.prev;
            this.tail.next = this.head;
            this.head.prev = this.tail;
            this.pos = 0;
        }
        this.size--;
        return val;
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        return this.curr.next.data;
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
        this.curr.next = this.head;
        this.curr.prev = this.tail;
        this.pos = 0;
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
        if (this.size != 0) {
            this.curr.prev = this.tail.prev;
            this.curr.next = this.tail;
            this.pos = this.size - 1;
        }
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
        if (this.pos != 0) {
            this.curr.prev = this.curr.prev.prev;
            this.curr.next = this.curr.prev.next;
            this.pos--;
        }
    }

    /**
     * Move the current position one step left, will continue if already at
     * beginning.
     */
    public void back() {
        if (this.size != 0) {
            this.curr.prev = this.curr.prev.prev;
            this.curr.next = this.curr.prev.next;
            if (this.pos == 0) {
                this.pos = this.size - 1;
            } else {
                this.pos--;
            }
        }
    }
    /**
     * Move the current position one step right, no change if already at end.
     */
    public void next() {
        if (this.pos < this.size - 1) {
            this.curr.prev = this.curr.next;
            this.curr.next = this.curr.next.next;
            this.pos++;
            this.pos = this.pos % this.size;
        }
    }

    /**
     *Move the current position one step right, will continue if already at end.
     */
    public void forward() {
        this.curr.prev = this.curr.next;
        this.curr.next = this.curr.prev.next;
        this.pos++;
        this.pos = this.pos % this.size;
    }
    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        return this.pos;
    }

    /**
     * Set the current position.
     * @param p the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int p) {
        if (this.size == 0 && p == 0) {
            this.pos = 0;
            this.curr.next = this.head;
            this.curr.prev = this.head;
            return true;
        } else if (p >= 0 && p < this.size) {
            this.moveToStart();
            for (int i = 0; i < p; i++) {
                this.next();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        return this.pos == this.size - 1;
    }

    /**
     * Prints all items in list from head to tail.
     * @return true if the current position is the end of the list
     */
    public String toString() {
        Node temp = new Node(null, this.curr.prev, this.curr.next);
        int origPos = this.pos;
        String str = "[ ";
        this.moveToStart();
        for (int i = 0; i < this.size; i++) {
            str += this.curr.next.data + " ";
            this.forward();
        }
        str += "]";
        this.curr = temp;
        this.pos = origPos;
        return str;
    }

}
