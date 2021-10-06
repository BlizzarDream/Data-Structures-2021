import java.util.Scanner;

/**
 * This class is where the LunchLine is created and simulated.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last documented: 08/30/2021
 */
public class LunchLineSimulator {
    private static StudentLine realityA, realityB;
    private static boolean originalReality, play;

    /**
     * This is the main method
     * @param args
     * I don't even know what this parameter is help
     */
   public static void main(String[] args) {
       realityA = new StudentLine();
       realityB = new StudentLine();
       originalReality = true;
       play = true;
       System.out.println("You have been appointed as god of the middle school lunch line. How will" +
               "\n you tamper with the fates of these children? You are in reality A");
       playGame(play);
   }

    /**
     * This method starts the simulation of the Lunch Line.
     * It is the reason why the main doesn't look chaotic
     * @param play
     * This boolean determines whether the game should start/continue or not.
     * It had a use until I realized I was starting a Lunch Line in a Lunch Line while catching errors
     */
   private static void playGame(boolean play) {
       Scanner k = new Scanner(System.in);
       String tempStr;
       int tempInt1;
       int tempInt2;
       double tempDouble;
       while (play) {
           System.out.println("Powers:\n" +
                   "1. Add a student to the end of the line\n" +
                   "2. Make a student cut the line\n" +
                   "3. Have two students switch places\n" +
                   "4. Have a student be removed by the bully\n" +
                   "5. Change a student's lunch money\n" +
                   "6. Serve the next student\n" +
                   "7. Print the lunch line\n" +
                   "8. Switch realities\n" +
                   "9. Compare realities\n" +
                   "10. Replace the other reality with the current\n" +
                   "11. Go outside and touch grass");
           print();
           System.out.println("Test your powers:");
           int input = k.nextInt();
           try {
               switch (input) {
                   case 1:
                       System.out.println("What is the name of the student?");
                       tempStr = k.next();
                       System.out.println("How much money does that student have?");
                       tempDouble = k.nextDouble();
                       if (tempDouble<=0){
                           System.out.println("A student can't get on line without money. Please be generous.");
                           tempDouble = k.nextDouble();
                       }
                       addStudent(new Student(tempStr, tempDouble));
                       System.out.println(tempStr + " has entered the back of the line.");
                       break;
                   case 2:
                       System.out.print("What is the name of the student?");
                       tempStr = k.next();
                       System.out.println("How much money does that student have?");
                       tempDouble = k.nextDouble();
                       if (tempDouble<=0){
                           System.out.println("A student can't get on line without money. Please be generous.");
                           tempDouble = k.nextDouble();
                       }
                       if (currentReality().numStudents() == 0) {
                           addStudent(new Student(tempStr, tempDouble));
                           System.out.println(tempStr + " found nobody to cut and simply got in line.");
                       } else {
                           System.out.print("Where do you want to place the student?");
                           tempInt1 = k.nextInt();
                           System.out.println(tempStr + " cuts the line in front of " + currentReality().getStudent(tempInt1).getName());
                           cutStudent(tempInt1, new Student(tempStr, tempDouble));
                       }
                       break;
                   case 3:
                       System.out.println("What place is one student in?");
                       tempInt1 = k.nextInt();
                       System.out.println("What place is the other student in?");
                       tempInt2 = k.nextInt();
                       trade(tempInt1, tempInt2);
                       System.out.println(currentReality().getStudent(tempInt1).getName() + " has traded places with " + currentReality().getStudent(tempInt2).getName());
                       break;
                   case 4:
                       System.out.println("Where is the targeted student?");
                       tempInt1 = k.nextInt();
                       System.out.println(bully(tempInt1).getName() + " had his/her money taken and has moved off the line.");
                       break;
                   case 5:
                       System.out.println("Which student's wallet are you touching?");
                       tempInt1 = k.nextInt();
                       System.out.println("What will his/her money be?");
                       tempDouble = k.nextDouble();
                       System.out.println(currentReality().getStudent(tempInt1).getName() + " discovers that their wallet now has" + tempDouble + " dollars.");
                       if (tempDouble <= 0)
                           System.out.println("He/she has gotten off the line.");
                       changeMoney(tempInt1, tempDouble);
                       break;
                   case 6:
                       System.out.println(currentReality().getStudent(1).getName() + " was served nutritious slop.");
                       serve();
                       break;
                   case 7:
                       print();
                       break;
                   case 8:
                       switchRealities();
                       System.out.print("You are now in ");
                       if (originalReality)
                           System.out.println("Reality A.");
                       else
                           System.out.println("Reality B.");
                       break;
                   case 9:
                       compare();
                       break;
                   case 10:
                       if (originalReality)
                           System.out.println("Reality B was overwritten by Reality A.");
                       else
                           System.out.println("Reality A was overwritten by reality B.");
                       overwrite();
                       break;
                   case 11:
                       play = false;
                       System.out.print("We wish you a good grass touching experience");
                       break;
                   default:
                       System.out.println("You don't have that many powers.");
                       break;
               }
           } catch (EmptyLineException e) {
               System.out.println(e.getMessage());
           }
           catch(ArrayIndexOutOfBoundsException a){
               System.out.println(a.getMessage());
           }
       }
   }

    /**
     * This method determines whether the game is being played in RealityA or RealityB
     * @return
     * Returns True if it is Reality A and False if it is Reality B.
     */
   private static StudentLine currentReality(){
       if(originalReality)
           return realityA;
       else
           return realityB;
   }

    /**
     * Uses addStudent method from StudentLine to place a student at the end of the line.
     * @param student
     * This is the student created by inputs from the user
     */
   private static void addStudent(Student student){
       try {
           currentReality().addStudent(realityA.numStudents()+1, student);
       }
       catch (DeanException d) {
           System.out.println(d.getMessage());
       }
       catch(ArrayIndexOutOfBoundsException a){
           System.out.println(a.getMessage());
       }
   }

    /**
     * Uses addStudent method from StudentLine to cut a student in front of another student in the line.
     * @param index
     * The index of the student being cut
     * @param student
     * The cutting student created by inputs from the user
     */
   private static void cutStudent(int index, Student student){
       try {
           currentReality().addStudent(index, student);
       } catch (DeanException d) {
           System.out.println(d.getMessage());
       }
       catch(ArrayIndexOutOfBoundsException a) {
           System.out.println(a.getMessage());
       }
   }

    /**
     * Uses swapStudents from StudentLine to swap the locations of two students.
     * @param index1
     * The location of one student
     * @param index2
     * The location of the other student
     */
   private static void trade(int index1, int index2){
       try {
           currentReality().swapStudents(index1, index2);
       }
       catch(ArrayIndexOutOfBoundsException a){
           System.out.println(a.getMessage());
       }
   }

    /**
     * This method makes the all powerful bully steal a student's money and remove him/her from the line.
     * @param index
     * The location of the targeted student
     * @return
     * returns the instance of the removed student
     */
   private static Student bully(int index) {
       Student removed = null;
       try {
           removed = currentReality().removeStudent(index);
       } catch (EmptyLineException e) {
           System.out.println(e.getMessage());
       }
       catch(ArrayIndexOutOfBoundsException a){
           System.out.println(a.getMessage());
       }
       return removed;
   }

    /**
     * This changes the amount of money a student in line owns.
     * Shall their money become 0or less, they will get off of the line.
     * @param index
     * The location of the targeted student
     * @param money
     * The new amount of money they will have
     */
   private static void changeMoney(int index, double money){
       try {
           if(money<=0)
               currentReality().removeStudent(index);
           else
               currentReality().getStudent(index).setMoney(money);
           } catch (EmptyLineException e) {
               System.out.println(e.getMessage());
           }
           catch(ArrayIndexOutOfBoundsException a){
               System.out.println(a.getMessage());
           }
       }

    /**
     * This method serves the first student in line some "good" school lunch.
     */
    private static void serve(){
       try {
           currentReality().removeStudent(1);
       } catch (EmptyLineException e) {
           System.out.println(e.getMessage());
       }
   }

    /**
     * This method prints out the line in readable form.
     */
   private static void print(){
       if(originalReality)
           System.out.println("Reality A");
       else
           System.out.println("Reality B");
       System.out.println(currentReality().toString());
   }

    /**
     * This method switches to the other Lunch Line.
     */
   private static void switchRealities(){
       originalReality = !originalReality;
   }

    /**
     * This method compares both Lunch Lines and checks if they are equal.
     */
   private static void compare(){
       if (realityA.equals(realityB))
           System.out.println("Both realities are equal.");
       else
           System.out.println("The realities have diverged.");
   }

    /**
     * This method overwrites the other Lunch Line with the one in use by the user.
     */
   private static void overwrite(){
       if(originalReality)
           realityB = (StudentLine) realityA.clone();
       else
           realityA = (StudentLine) realityB.clone();
   }
}
