/**
 * This is the CarList class written as required and with several additional methods.
 * @author Gavin Chen
 * SBU ID:114293808
 * Last Documented: 9/20/2021
 */
public class CarList {
    private CarListNode head, tail, cursor;
    private int size;

    /**
     * A default constructor that initializes all nodes to null and size to 0.
     */
    public CarList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Checks if the LinkedList is empty based on whether both head and tail are null.
     * @return
     * A boolean based on the result.
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * An accessor method for the size of the list.
     * @return
     * The size of the LinkedList.
     */
    public int numCars() {
        return size;
    }

    /**
     * Accesses the data at the cursor
     * @return
     * The car data in the node the cursor is pointing to. Will return null if cursor is null.
     */
    public Car getCursorCar() {
        if (cursor == null)
            return null;
        return cursor.getCar();
    }

    /**
     * Points the cursor to the head node.
     */
    public void resetCursorToHead() {
        if (head == null)
            cursor = null;
        else
            cursor = head;
    }

    /**
     * Points the cursor to the tail node.
     */
    public void cursorToTail() {
        cursor = tail;
    }

    /**
     * Points the cursor to the next node.
     * @throws EndOfListException
     * When the cursor or list is null or if the cursor has reached the tail.
     */
    public void cursorForward() throws EndOfListException {
        if (this.isEmpty() || cursor == tail || cursor == null)
            throw new EndOfListException("Cursor cannot move forwards CarList Line 32");
        else
            cursor = cursor.getNext();
    }

    /**
     * Points the cursor to the previous node.
     * @throws EndOfListException
     * When the cursor or list is null or if the cursor has reached the head.
     */
    public void cursorBackward() throws EndOfListException {
        if (this.isEmpty() || cursor == head || cursor == null)
            throw new EndOfListException("Cursor cannot move backwards CarList Line 38");
        else
            cursor = cursor.getPrev();
    }

    /**
     * Inserts a new node right before the cursor. Accounts for empty list and cursor being at head.
     * @param newCar
     * Uses the parameter to generate the new node to be inserted.
     */
    public void insertBeforeCursor(Car newCar) {
        CarListNode newNode = new CarListNode(newCar);
        if (cursor != null) {
            newNode.setPrev(cursor.getPrev());
            if (cursor != head)
                cursor.getPrev().setNext(newNode);
            newNode.setNext(cursor);
            cursor.setPrev(newNode);
            if (cursor == head)
                head = newNode;
        } else {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        size++;
    }

    /**
     * Inserts a new node after the tail and makes it the tail. Accounts for empty list.
     * @param newCar
     * Uses the parameter to generate a new node to be added.
     */
    public void appendToTail(Car newCar) {
        CarListNode newNode = new CarListNode(newCar);
        if (tail != null) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        size++;
    }

    /**
     * Removes the node at the cursor and returns the data. Accounts for empty list, null cursor, 1 node list,
     * cursor at head, and cursor at tail.
     * @return
     * The car data of the removed node.
     * @throws EndOfListException
     * If cursor happens to be null, which signifies an issue or empty list.
     */
    public Car removeCursor() throws EndOfListException {
        Car removed;
        if (cursor == null)
            throw new EndOfListException("Cursor is not pointing to anything CarList Line 77");
        else if (numCars() == 1) {
            removed = cursor.getCar();
            cursor = null;
            head = null;
            tail = null;
        } else if (cursor == head) {
            removed = cursor.getCar();
            cursor.getNext().setPrev(null);
            head = cursor.getNext();
            cursor = head;
        } else if (cursor == tail) {
            removed = cursor.getCar();
            cursor.getPrev().setNext(null);
            tail = cursor.getPrev();
            cursor = tail;
        } else {
            removed = cursor.getCar();
            cursor.getNext().setPrev(cursor.getPrev());
            cursor.getPrev().setNext(cursor.getNext());
            cursor = cursor.getPrev();
        }
        size--;
        return removed;
    }

    /**
     * An added method to determine whether the cursor reaches the tail. Used in merging lists.
     * @return
     * A boolean based on the result.
     */
    public boolean isCursorAtTail() {return cursor == tail;}

    /**
     * Creates a String of the makes and owners of all cars in the list. An arrow will be added to the car the cursor
     * points to. Accounts for empty string.
     * @return
     * The string mentioned above
     */
    public String toString() {
        if (isEmpty())
            return "This list is empty";
        CarListNode temp = cursor;
        String result = "";
        resetCursorToHead();
        for (int i = 0; i < size; i++) {
            if(cursor==temp)
                result+="-->";
            result += getCursorCar().toString() + "\n";
            try {
                cursorForward();
            } catch (EndOfListException e) {
            }
        }
        cursor = temp;
        return result;
    }
}
