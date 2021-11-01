import java.io.FileNotFoundException;
import java.util.Scanner;

public class FXGuiMaker {
    static FXComponentTree tree;
    public static void main(String[] args){
        try{
            System.out.println("Welcome to counterfeit SceneBuilder.\n");
            Scanner sc = new Scanner(System.in);
            boolean quit = false;
            while(!quit) {
                printMenu();
                System.out.print("Please select from an option:");
                switch (sc.next().toUpperCase().charAt(0)) {
                    case 'L':
                        System.out.print("Please enter the filename:");
                        tree = FXComponentTree.readFromFile(sc.next());
                        break;
                    case 'P':
                        System.out.println(tree.toString(tree.getRoot()));
                        break;
                    case 'C':
                        System.out.print("Please enter the number of the child node (starting with 1):");
                        tree.cursorToChild(sc.nextInt()-1);
                        System.out.println("Cursor moved to"+tree.getCursor().toString()+".");
                        break;
                    case 'U':
                        tree.cursorToParent();
                        System.out.println("Cursor moved to"+tree.getCursor().toString()+".");
                        break;
                    case 'E':
                        System.out.print("Please enter new text:");
                        tree.setTextAtCursor(sc.next());
                        break;
                    case 'D':
                        System.out.print("Please enter the number of the child node(starting with 1):");
                        int temp = sc.nextInt()-1;
                        System.out.println(tree.getCursor().getChildren()[temp].toString()+" removed.");
                        tree.deleteChild(sc.nextInt()-1);
                        break;
                    case 'R':
                        tree.cursorToRoot();
                        System.out.println("Cursor is at root.");
                        break;
                    case 'S':
                        System.out.print("Please enter a filename:");
                        FXComponentTree.writeToFile(tree,sc.next());
                        System.out.println("File saved.");
                        break;
                    case 'X':
                        System.out.println("Exporting to FXML not implemented.");
                        break;
                    case 'Q':
                        quit = true;
                        System.out.println("Quitting counterfeit SceneBuilder.");
                }
            }
        }catch(IndexOutOfBoundsException | FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    private static void printMenu(){
        System.out.println("Menu:\n" +
                "\tL) Load from file\n" +
                "\tP) Print tree\n" +
                "\tC) Move cursor to a child node\n" +
                "\tR) Move cursor to root\n" +
                "\tA) Add a child\n" +
                "\tU) Cursor up(to parent)\n" +
                "\tE) Edit text of cursor\n" +
                "\tD) Delete child\n" +
                "\tS) Save to file\n" +
                "\tX) Export to FXML(not implemented)\n" +
                "\tQ) Quit\n");
    }
}

