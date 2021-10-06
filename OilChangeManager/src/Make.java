import java.util.Locale;

/**
 * This is the Make enum written according to requirements along with an additional method
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented: 9/19/2021
 */
public enum Make {
    FORD,
    GMC,
    CHEVY,
    JEEP,
    DODGE,
    CHRYSLER,
    LINCOLN;

    /**
     * This method takes a String and checks if it matches any of the constants converted into strings after
     * converting the string to all upper case.
     * @param make
     * The name of the make used to compare with makes Tony will service
     * @return
     * returns a boolean based on if a match is found
     */
    public static boolean isAcceptedMake(String make){
        for(Make temp: Make.values()){
         if(temp.toString().equals(make.toUpperCase()))
             return true;
        }
        return false;
    }
    public static boolean equals(Object obj1,Object obj2){
        if(obj1==null||obj2==null)
            return false;
        if(obj1 instanceof Make && obj2 instanceof Make)
            return obj1.toString().equals(obj2.toString());
        return false;
    }
}
