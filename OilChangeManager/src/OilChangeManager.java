import java.util.Scanner;

/**
 * This is the class in which the main method is executed
 * @author Gavin Chen
 * SBU ID:114293808
 * Last Documented: 9/20/2021
 */
public class OilChangeManager {
    private static boolean selectedJoe;
    private static CarList joe, donny, finished;
    private static Car removed;

    /**
     * This is the main method.
     * @param args
     * No clue what this parameter does.
     */
    public static void main(String[]args) {
        String carMake, carOwner;
        int mechanicSelection;
        /** Initializes all the CarLists stated as static variables*/
        joe = new CarList();
        donny = new CarList();
        finished = new CarList();
        boolean isManagerInUse = true; /**Boolean to loop the Manager*/
        Scanner menu = new Scanner(System.in);
        while (isManagerInUse) {
            try {
                printMainMenu();
                System.out.print("Select an option:");
                System.out.println();
                /**This switch statement decides which main menu option to execute based on user input */
                switch (menu.nextInt()) {
                    case 1: //Edit
                        System.out.print("Please select a mechanic:\n" +
                                "\t1) Joe\n" +
                                "\t2) Donny\n" +
                                "Selection:");
                        mechanicSelection = menu.nextInt();
                        System.out.println();
                        if (mechanicSelection == 1)
                            selectedJoe = true;
                        else if (mechanicSelection == 2)
                            selectedJoe = false;
                        else {
                            System.out.println("There are only two mechanics available. Returning to main menu.\n");
                            break;
                        }
                        printEditOptions();
                        System.out.print("Please select an option:");
                        /**This switch statement determines which edit menu option to execute based on user input*/
                        switch (menu.nextInt()) {
                            case 1: //Add Car
                                System.out.print("Please enter the make of the vehicle:");
                                carMake = menu.next();
                                if (!Make.isAcceptedMake(carMake)) {
                                    System.out.println("This make is not accepted\n");
                                    break;
                                }
                                System.out.print("Please enter the name of the owner:");
                                carOwner = menu.next();
                                selectList().appendToTail(new Car(Make.valueOf(carMake.toUpperCase()), carOwner));
                                System.out.println(carOwner + "'s " + carMake.toUpperCase() + " was added to the end of the list\n");
                                break;
                            case 2: //Move cursor forward
                                selectList().cursorForward();
                                System.out.println("Cursor moved forward\n");
                                break;
                            case 3: //Move cursor to head
                                if(selectList().isEmpty()) {
                                    System.out.println("Nothing to select.\n");
                                    break;
                                }
                                selectList().resetCursorToHead();
                                System.out.println("Cursor moved to head.\n");
                                break;
                            case 4: //Move cursor to tail
                                if(selectList().isEmpty()) {
                                    System.out.println("Nothing to select.\n");
                                    break;
                                }
                                selectList().cursorToTail();
                                System.out.println("Cursor moved to tail.\n");
                                break;
                            case 5: //Move cursor backwards
                                selectList().cursorBackward();
                                System.out.println("Cursor moved backwards.\n");
                                break;
                            case 6: //Insert before cursor
                                if(selectList().isEmpty())
                                    System.out.println("Due to the list being empty, the following car will simply be added");
                                System.out.print("Please enter the make of the vehicle:");
                                carMake = menu.next();
                                if (!Make.isAcceptedMake(carMake)) {
                                    System.out.println("This make is not accepted");
                                    break;
                                }
                                System.out.print("Please enter the name of the owner:");
                                carOwner = menu.next();
                                selectList().insertBeforeCursor(new Car(Make.valueOf(carMake.toUpperCase()), carOwner));
                                System.out.println(carOwner + "'s "+carMake.toUpperCase()+" was added before the cursor.\n");
                                break;
                            case 7: //Cut at cursor
                                removed = selectList().removeCursor();
                                System.out.println(removed.getOwner() + "'s " + removed.getMake() + " was cut.\n");
                                break;
                            case 8: //Paste before cursor
                                System.out.println(removed.getOwner() + "'s " + removed.getMake() + " was added before the cursor.\n");
                                selectList().insertBeforeCursor(removed);
                                removed = null;
                                break;
                            case 9: //Remove cursor
                                Car deleted = selectList().removeCursor();
                                System.out.println(deleted.getOwner()+"'s "+deleted.getMake()+" was removed.");
                                break;
                            default:
                                System.out.println("That isn't an option. Returning to main menu.\n");
                        }

                        break;
                    case 2: /**Merges one list into the other. First inserts a new node before each node in the
                     receiving list if possible. If there are nodes left over, they are all added to the end.*/
                        System.out.print("Please select a destination list:\n" +
                                "\t1) John\n" +
                                "\t2) Donny\n" +
                                "Selection:");
                        mechanicSelection = menu.nextInt();
                        System.out.println();
                        if (mechanicSelection == 1)
                            selectedJoe = true;
                        else if (mechanicSelection == 2)
                            selectedJoe = false;
                        else {
                            System.out.println("There are only two mechanics. Returning to main menu.\n");
                            break;
                        }
                        selectList().resetCursorToHead();
                        otherList().resetCursorToHead();
                        while (!otherList().isEmpty() && !selectList().isEmpty()) {
                            selectList().insertBeforeCursor(otherList().removeCursor());
                            if(!selectList().isCursorAtTail())
                                selectList().cursorForward();
                            else
                                break;
                        }
                        while (!otherList().isEmpty())
                            selectList().appendToTail(otherList().removeCursor());
                        if (selectedJoe)
                            System.out.println("Donny's list was merged into Joe's.");
                        else
                            System.out.println("Joe's list was merged into Donny's.");
                        break;
                    case 3://Prints
                        System.out.println("Joe's List:\n" +
                                String.format("%-10s","Make")+"Owner\n" +
                                "-------------------------------------\n" +
                                joe.toString() + "\n");
                        System.out.println("Donny's List:\n" +
                                String.format("%-10s","Make")+"Owner\n" +
                                "-------------------------------------\n" +
                                donny.toString() + "\n");
                        System.out.println("Finished List:\n" +
                                String.format("%-10s","Make")+"Owner\n" +
                                "-------------------------------------\n" +
                                finished.toString() + "\n");
                        break;
                    case 4://Paste car to finished list
                        if (removed == null)
                            System.out.println("There is no car to paste\n");
                        else {
                            System.out.println(removed.getOwner() + "'s " + removed.getMake() +
                                    " was added to the finished list.\n");
                            finished.appendToTail(removed);
                            removed = null;
                        }
                        break;
                    case 5:/**Sorts either John's or Donny's list based on user input. Does so by moving the head of
                     the list to the tail before looking through unmodified parts of the list for matching models and
                     moving those to the tail. Afterwards, it will repeat from the beginning until each node has been
                     moved.*/
                        System.out.print("Please select a list to sort:\n" +
                                "\t1) John\n" +
                                "\t2) Donny\n" +
                                "Selection:");
                        mechanicSelection = menu.nextInt();
                        System.out.println();
                        if (mechanicSelection == 1)
                            selectedJoe = true;
                        else if (mechanicSelection == 2)
                            selectedJoe = false;
                        else {
                            System.out.println("There are only two mechanics. Returning to main menu.\n");
                            break;
                        }
                        if(selectList().numCars()<3){
                         System.out.println("The selected list is too small to sort. Returning to main menu.\n");
                         break;
                        }
                        selectList().resetCursorToHead();
                        Car sortType;
                        int sorted = 0, count=0;
                        while(sorted<selectList().numCars()){
                            selectList().resetCursorToHead();
                            sortType = selectList().removeCursor();
                            selectList().appendToTail(sortType);
                            sorted++;
                            for(int i=0; i<selectList().numCars()-sorted; i++) {
                                if (selectList().getCursorCar().getMake().equals(sortType.getMake())) {
                                    selectList().appendToTail(selectList().removeCursor());
                                    count++;
                                    if(i!=0)
                                        selectList().cursorForward();
                                }
                                else{
                                    selectList().cursorForward();
                                }
                            }
                            sorted+=count;
                            count=0;
                        }
                        selectList().resetCursorToHead();
                        System.out.println("List has been sorted. Cursor has been reset to head\n");
                        break;
                    case 6://Quit
                        isManagerInUse = false;
                        System.out.println("All in a day's work!");
                        break;
                    default:
                        System.out.println("That's not an option\n");
                }
            } catch (EndOfListException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Prints the main menu
     */
    private static void printMainMenu(){
        System.out.println("Menu:\n"+
                "\t1) Edit Job Lists for Joe and Donny\n"+
                "\t2) Merge Job Lists\n"+
                "\t3) Print Job Lists\n"+
                "\t4) Paste car to end of finished car list\n" +
                "\t5) Sort Job Lists\n" +
                "\t6) Quit Oil Change Manager\n");
    }

    /**
     * Prints the edit menu
     */
    private static void printEditOptions(){
        System.out.println("Options\n"+
                "\t1) Add a car to the end of the list\n"+
                "\t2) Cursor Forward\n"+
                "\t3) Cursor to Head\n"+
                "\t4) Cursor to Tail\n"+
                "\t5) Cursor Backward\n"+
                "\t6) Insert car before cursor\n"+
                "\t7) Cut car at cursor\n"+
                "\t8) Paste before cursor\n"+
                "\t9) Remove cursor\n");
    }

    /**
     * Determines which mechanic to use based on boolean selectedJoe.
     * @return
     * Either joe or donny
     */
    private static CarList selectList(){
        if(selectedJoe)
            return joe;
        else
            return donny;
    }

    /**
     * Returns to opposite list to selectList(). Used only in merging.
     * @return
     * Either joe or donny
     */
    private static CarList otherList(){
        if(selectedJoe)
            return donny;
        else
            return joe;
    }

}
