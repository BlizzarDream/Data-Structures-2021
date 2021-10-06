/**
 * This is the CarListNode class all written as required
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented 9/19/2021
 */
public class CarListNode {
    private Car car;
    private CarListNode next, prev;

    /**
     * This is the only written constructor for this class. Sets both the next and previous node to null.
     * @param car
     * Takes an instance of na car for the node's data.
     * @throws IllegalArgumentException
     * Throws an IllegalArgumentException if the argument is null. On a side note, the usage of a different constructor
     * and a method for confirming the enum in the Car class seems to have solved this problem. I can't find a situation
     * where the user can use null as an argument.
     */
    public CarListNode(Car car) throws IllegalArgumentException{
        if (car==null) throw new IllegalArgumentException("The car is invalid");
        this.car = car;
        next = null;
        prev = null;
    }

    /**
     * Accessor method for member variable car.
     * @return
     * returns the member variable car.
     */
    public Car getCar(){return car;}

    /**
     * Accessor method the member variable next.
     * @return
     * returns the member variable next.
     */
    public CarListNode getNext(){return next;}

    /**
     * Accessor method for the member variable prev.
     * @return
     * returns the member variable prev.
     */
    public CarListNode getPrev(){return prev;}

    /**
     * Setter method for the variable car. Hasn't been used.
     * @param newCar
     * uses newCar to replace car.
     */
    public void setCar(Car newCar){this.car = newCar;}

    /**
     * Setter method for the member variable next.
     * @param node
     * replaces member variable next with parameter node.
     */
    public void setNext(CarListNode node){this.next = node;}

    /**
     * Setter method for the member variable prev.
     * @param node
     * replaces member variable prev with parameter node.
     */
    public void setPrev(CarListNode node){this.prev= node;}
}
