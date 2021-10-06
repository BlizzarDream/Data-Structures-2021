/**
 * This is the car class written as required with accessor,setter, and toString methods
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented 9/19/2021
 */
public class Car {
    private Make make;
    private String owner;

    /**
     * The default constructor made as required. Not in use.
     */
    public Car(){}

    /**
     * A constructor for this class that takes an instance of Make and String as parameters.
     * @param make
     * Initializes the member variable make
     * @param str
     * Initializes the member variable owner
     */
    public Car(Make make, String str){
        this.make = make;
        this.owner = str;
    }

    /**
     * Accessor method for make.
     * @return
     * The make of the car.
     */
    public Make getMake(){return make;}

    /**
     * Accessor method for owner.
     * @return
     * The name of the owner of the car.
     */
    public String getOwner(){return owner;}

    /**
     * Setter method for make.
     * @param make
     * Replaces the member variable make with a different instance.
     */
    public void setMake(Make make){this.make=make;}

    /**
     * Setter method for owner.
     * @param str
     * Replaces the member variable owner with a different instance.
     */
    public void setOwner(String str){this.owner = str;}

    /**
     * The toString method for the class.
     * @return
     * A string of the make and owner of the car in Make, Owner order.
     */
    public String toString(){return String.format("%-10s",make.toString())+owner;}
}
