/**
 * This class defines the EmptyLineException
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented: 08/30/2021
 */

public class EmptyLineException extends Exception{
    /**
     * This constructor creates a new instance of the exception
     * @param str
     * Passes the written message to the Exception superclass
     */
    public EmptyLineException(String str){
        super(str);
    }
}
