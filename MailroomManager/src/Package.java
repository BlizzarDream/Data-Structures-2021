/**
 * This is the class for the Package object. All methods that are required are implemented.
 * @author Gavin Chen
 * SBU ID:114293808
 * Last Documented: 10/03/2021
 */
public class Package {
    private String recipient;
    private int arrivalDate;
    private double weight;

    /**
     * This is the required constructor for the class written as required. All parameters are
     * written to the responding member variables of the same name.
     * @param recipient
     * A String that holds the name of the owner of the package
     * @param arrivalDate
     * An int that holds the date the package arrived at the mailroom
     * @param weight
     * A double that holds the weight of the package
     */
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     * The getter method for recipient
     * @return recipient
     */
    public String getRecipient(){return recipient;}

    /**
     * The getter method for arrivalDate
     * @return arrivalDate
     */
    public int getArrivalDate(){return arrivalDate;}

    /**
     * The getter method for weight
      * @return weight
     */
    public double getWeight(){return weight;}

    /**
     * The setter method for recipient. Not in use.
     * @param recipient
     * A new String data for the name of the recipient
     */
    public void setRecipient(String recipient){this.recipient = recipient;}

    /**
     * The setter method for arrivalDate. Not in use.
     * @param arrivalDate
     * A new int data for the arrival date of the package
     */
    public void setArrivalDate(int arrivalDate){this.arrivalDate = arrivalDate;}

    /**
     * The setter method for weight. Not in use.
     * @param weight
     * A new double data for the weight of the package
     */
    public void setWeight(double weight){this.weight = weight;}

    /**
     * The toString method for the package. Written for convenience
     * @return A String with the name and arrivalDate of the package displayed.
     */
    public String toString(){
        return "["+recipient+" "+arrivalDate+"]";
    }
}
