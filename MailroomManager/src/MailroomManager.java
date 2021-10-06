import java.util.Scanner;

/**
 * This is the class that runs the main method. Uses an array to store the 5 stacks. Should recreate the same results as in HW3.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last Documented 10/03/2021
 */
public class MailroomManager {
    private static PackageStack[] packages = new PackageStack[5];
    private static PackageStack floor = new PackageStack(true);

    /**
     * Prints the menu.
     */
    public static void printMenu(){
        System.out.println("\nMenu:\n" +
                "\tA) Add a package (Deliver a package)\n" +
                "\tG) Get someone's package\n" +
                "\tT) Make it tomorrow\n" +
                "\tP) Print all stacks\n" +
                "\tM) Move first package from one stack to another\n" +
                "\tS) Search for misplaced packages and move to floor\n" +
                "\tL) List all packages awaiting a user\n" +
                "\tE) Empty the floor\n" +
                "\tQ) Quit\n");
    }

    /**
     * The main method.
     * @param args
     * What does this do?
     */
    public static void main(String[] args){
        boolean inUse = true;
        int day = 0,destination,count, returnCount;
        char input;
        String name;
        Scanner a = new Scanner(System.in);
        for(int i=0; i<packages.length;i++)
            packages[i] = new PackageStack(false);
        System.out.println("Welcome to the Irving MailRoom Manager.");
        try {
            while (inUse) {
                printMenu();
                System.out.print("Please select one of the above options:");
                input = a.next().toUpperCase().charAt(0);
                System.out.println("Detected input:" + input);
                switch (input) {
                    case 'A':
                        System.out.print("Please enter the recipient name:");
                        name = a.next();
                        System.out.print("Please enter the weight(lbs):");
                        double weight = a.nextDouble();
                        destination = getDestination(name);
                        for(int i = 0; i<=4; i++){
                            if(destination-i>=0 && !packages[destination-i].isFull()){
                                packages[destination-i].push(new Package(name, day, weight));
                                System.out.println("A "+weight+"lb package is awaiting pickup by "+name);
                                if(i>0)
                                    System.out.println("Due to Stack "+(destination+1)+" being full, it was placed in Stack"+(destination-i+1));
                                break;
                            }
                            if(destination+1<=4 && !packages[destination+i].isFull()){
                                packages[destination+i].push(new Package(name, day, weight));
                                System.out.println("A "+weight+"lb package is awaiting pickup by "+name);
                                if(i>0)
                                    System.out.println("Due to Stack "+(destination+1)+" being full, it was placed in Stack"+(destination+i+1));
                                break;
                            }
                            if(i==4)
                                System.out.println(name+"'s package could not be held due to an unavailability of space.");
                        }
                        break;
                    case 'G':
                        System.out.print("Please enter the recipient name:");
                        name = a.next();
                        destination = getDestination(name);
                        for(int i = 0; i<=4; i++){
                            if(destination-i>=0){
                                count=0;
                                while(!packages[destination-i].isEmpty() && !packages[destination - i].peek().getRecipient().equals(name)) {
                                    floor.push(packages[destination - i].pop());
                                    count++;
                                }
                                if(!packages[destination-i].isEmpty() && packages[destination - i].peek().getRecipient().equals(name)){
                                    if(count>0)
                                        System.out.println(count+" packages moved from Stack "+(destination-i+1)+" to floor");
                                    printPackages();
                                    System.out.println("\n"+name+"'s "+packages[destination-i].peek().getWeight()+" delivered on day "+packages[destination-i].peek().getArrivalDate()+" was picked up.\n");
                                    packages[destination-i].pop();
                                    if(count>0)
                                        System.out.println(count+" packages moved from floor to Stack "+(destination-i+1));
                                    while(count>0) {
                                        packages[destination - i].push(floor.pop());
                                        count--;
                                    }
                                    printPackages();
                                    break;
                                }
                                while(count>0) {
                                    packages[destination - i].push(floor.pop());
                                    count--;
                                }
                            }
                            if(destination+i<=4){
                                count=0;
                                while(!packages[destination+i].isEmpty() && !packages[destination+i].peek().getRecipient().equals(name)) {
                                    floor.push(packages[destination+i].pop());
                                    count++;
                                }
                                if(!packages[destination+i].isEmpty() && packages[destination+i].peek().getRecipient().equals(name)){
                                    System.out.println(count+" packages moved from Stack "+(destination+i+1)+" to floor");
                                    printPackages();
                                    System.out.println("\n"+name+"'s "+packages[destination+i].peek().getWeight()+" delivered on day "+packages[destination+1].peek().getArrivalDate()+" was picked up.\n");
                                    packages[destination+i].pop();
                                    System.out.println(count+" packages moved from floor to Stack "+(destination+i+1));
                                    while(count>0) {
                                        packages[destination + i].push(floor.pop());
                                        count--;
                                    }
                                    printPackages();
                                    break;
                                }
                                while(count>0) {
                                    packages[destination + i].push(floor.pop());
                                    count--;
                                }
                            }
                            if(i==4)
                                System.out.println(name+"'s package could not be found.");
                        }
                        break;
                    case 'T':
                        day++;
                        System.out.println("It is now day "+day);
                        count=0;
                        returnCount=0;
                        for(int i=0; i< packages.length;i++){
                            while(!packages[i].isEmpty()){
                                if(day-packages[i].peek().getArrivalDate()==5){
                                    count++;
                                    packages[i].pop();
                                }
                                else {
                                    floor.push(packages[i].pop());
                                    returnCount++;
                                }
                            }
                            while(returnCount>0) {
                                packages[i].push(floor.pop());
                                returnCount--;
                            }
                        }
                        if(count>0)
                            System.out.println(count+" packages have been discarded.");
                        break;
                    case 'P':
                        printPackages();
                        break;
                    case 'M':
                        int source,receiver;
                        System.out.print("Please enter the source stack (enter 0 for floor):");
                        source = a.nextInt()-1;
                        System.out.print("Please enter the destination stack:");
                        receiver = a.nextInt()-1;
                        if(source<0 || source >5 || receiver<0 || receiver>5 || receiver==source){
                            System.out.println("The source and/or receiver inputs are either invalid or equal.");
                            break;
                        }
                        if(source==0)
                            packages[receiver].push(floor.pop());
                        else if(receiver==0)
                            floor.push(packages[source].pop());
                        else
                            packages[receiver].push(packages[source].pop());
                        break;
                    case 'F':
                        PackageStack temp = new PackageStack(true);
                        for(int i = 0; i<packages.length;i++){
                            while(!packages[i].isEmpty()){
                                if(getDestination(packages[i].peek().getRecipient())!=i) {
                                    floor.push(packages[i].pop());
                                }
                                else
                                    temp.push(packages[i].pop());
                            }
                            while(!temp.isEmpty())
                                packages[i].push(temp.pop());
                        }
                        System.out.println("All misplaced packages were moved to floor.");
                        break;
                    case 'L':
                        System.out.print("Please enter the recipient's name:");
                        name = a.next();
                        returnCount=0;
                        for(int i=0; i<packages.length; i++){
                            count=0;
                            while(!packages[i].isEmpty()){
                                if(packages[i].peek().getRecipient().equals(name))
                                    count++;
                                floor.push(packages[i].pop());
                                returnCount++;
                            }if(count>0)
                                System.out.println("There are "+count+" packages for "+name+" in Stack "+(i+1));
                            while(returnCount>0) {
                                if(floor.peek().getRecipient().equals(name))
                                    System.out.println("\tDay: "+floor.peek().getArrivalDate()+"\tWeight: "+floor.peek().getWeight());
                                packages[i].push(floor.pop());
                                returnCount--;
                            }
                        }
                        break;
                    case 'E':
                        while(!floor.isEmpty())
                            floor.pop();
                        System.out.println("The almighty trash can ahs been fed.");
                        break;
                    case 'Q':
                        inUse = false;
                        System.out.println("Collapsing simulation");
                        break;
                    default:
                        System.out.println("That option does not exist. Returning to main menu");
                        break;
                }
            }
        } catch (FullStackException | EmptyStackException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines which stack a package goes depending on the name of the recipient
     * @param name
     * A String for the name of the reciepient
     * @return
     * An int representing an index in the PackageStack array to place the package
     */
    public static int getDestination(String name){
        char c = name.toUpperCase().charAt(0);
        if (Character.compare(c,'G') <= 0)
            return 0;
        else if (Character.compare(c,'J') <= 0)
            return 1;
        else if (Character.compare(c,'M') <= 0)
            return 2;
        else if (Character.compare(c,'R') <= 0)
            return 3;
        else
            return 4;
    }

    /**
     * Prints the state of all PackageStacks
     */
    public static void printPackages() {
        PackageStack temp = new PackageStack(true);
        try {
            System.out.println("Current Packages:\n-------------------------------");
            for (int i = 0; i <= 4; i++) {
                if (i == 0)
                    System.out.print("Stack 1 (A-G):|");
                else if (i == 1)
                    System.out.print("Stack 2 (H-J):|");
                else if (i == 2)
                    System.out.print("Stack 3 (K-M):|");
                else if (i == 3)
                    System.out.print("Stack 4 (N-R):|");
                else
                    System.out.print("Stack 5 (S-Z):|");
                if (packages[i].isEmpty())
                    System.out.print("empty");
                while (!packages[i].isEmpty())
                    temp.push(packages[i].pop());
                while (!temp.isEmpty()) {
                    System.out.print(temp.peek().toString() + " ");
                    packages[i].push(temp.pop());
                }
                System.out.println();
            }
            System.out.print("Floor: ");
            if(floor.isEmpty())
                System.out.println("empty");
            while(!floor.isEmpty())
                temp.push(floor.pop());
            while(!temp.isEmpty()) {
                System.out.print(temp.peek().toString() + " ");
                floor.push(temp.pop());
            }
            System.out.println();
        } catch (EmptyStackException | FullStackException e) {
            System.out.println(e.getMessage());
        }
    }
}
