import java.util.ArrayList;

/**
 * This is the class that manages a stack of packages. Simulates a stack with ArrayList. All required methods are implemented.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented 10/03/2021
 */
public class PackageStack extends ArrayList<Package>{
    private final int CAPACITY = 7;
    private boolean sizeUnlimited;

    /**
     * A custom constructor for the purpose of differentiating stacks with a size limit and ones who don't.
     * @param sizeUnlimited
     * Initializes the sizeUnlimited member variable, which determines whether a PackageStack has unlimited capacity.
     */
    public PackageStack(boolean sizeUnlimited){this.sizeUnlimited=sizeUnlimited;}

    /**
     * Adds a Package to the top of the stack.
     * @param x
     * The package that will be added to the stack.
     * @throws FullStackException
     * Thrown if the size of the stack(ArrayList) equals CAPACITY and sizeUnlimited is true.
     */
    public void push(Package x) throws FullStackException{
        if (isFull())
            throw new FullStackException("Stack is full.");
        super.add(size(),x);
    }

    /**
     * Removes a Package from the top of the stack.
     * @return The removed package.
     * @throws EmptyStackException
     * Thrown if the size of the stack(ArrayList) is 0.
     */
    public Package pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException("Stack is empty.");
        return super.remove(super.size()-1);
    }

    /**
     * Retrieves information on the package on the top without affecting the stack.
     * @return The top package.
     * @throws EmptyStackException
     * Thrown if the size of the stack(ArrayList) is 0.
     */
    public Package peek() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException("Stack is empty.");
        return super.get(super.size()-1);
    }

    /**
     * Determines the size of the stack(ArrayList) is equal to CAPACITY
     * @return true if full, else false
     */
    public boolean isFull(){
        return super.size() == CAPACITY && !sizeUnlimited;
    }

    /**
     * Determines whether the size if the stack of the stack(ArrayList) is 0
     * @return true if empty, else false
     */
    public boolean isEmpty(){
        return super.size() == 0;
    }
}
