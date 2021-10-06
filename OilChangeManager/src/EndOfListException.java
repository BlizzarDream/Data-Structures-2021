/**
 * This is the EndOfListException class written as required. The class extends to the Exception superclass and
 * there is an added constructor.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented: 9/19/2021
 */
public class EndOfListException extends Exception {
    /**
     * Sends the String message to the Exception class.
     * @param message
     * Used to print the caught error message in the Exception class.
     */
    public EndOfListException(String message){super(message);}
}
