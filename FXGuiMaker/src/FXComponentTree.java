import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;
    public void cursorToRoot(){cursor=root;}
    public void deleteChild(int index)throws IllegalArgumentException,IndexOutOfBoundsException{
        if(index>=cursor.maxChildren||index<0)
            throw new IndexOutOfBoundsException("Out of Bounds");
        FXTreeNode[] temp = cursor.getChildren();
        if(temp[index+1]!=null) {
            while(index<cursor.maxChildren-1 && temp[index]!=null){
                temp[index] = temp[index+1];
                index++;
            }
            temp[cursor.maxChildren-1]=null;
        }
        else
            temp[index]=null;
        cursor.setChildren(temp);
    }
    public void addChild(int index, FXTreeNode node)throws IllegalArgumentException,FullArrayException,IndexOutOfBoundsException{
        FXTreeNode[] temp = cursor.getChildren();
        if(index>=cursor.maxChildren || index<0)
            throw new IndexOutOfBoundsException("Invalid index 28");
        if(temp[index-1]==null)
            throw new IllegalArgumentException("Inserting here will create a hole");
        if(temp[cursor.maxChildren-1]!=null)
            throw new FullArrayException("This node cannot have any more children");
        if(temp[index]!=null){
            for(int i=cursor.maxChildren-2; i>=index;i--){
                if(temp[i]!=null)
                    temp[i+1]=temp[i];
            }
        }
        temp[index]=node;
    }
    public void setTextAtCursor(String text){cursor.setText(text);}
    public void cursorToChild(int index) throws IllegalArgumentException,IndexOutOfBoundsException{
        if(index>=cursor.maxChildren || index<0)
            throw new IndexOutOfBoundsException("Invalid Index 44");
        if(cursor.getChildren()[index]==null)
            throw new IllegalArgumentException("Selected child node is null");
        cursor=cursor.getChildren()[index];
    }
    public void cursorToParent(){cursor=cursor.getParent();}
    public static FXComponentTree readFromFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        if(!f.exists())
            throw new FileNotFoundException(fileName+" not found.");
        Scanner sc = new Scanner(f);
        if(!sc.hasNextLine())
            throw new IllegalArgumentException("File is empty.");
        FXComponentTree tree = new FXComponentTree();
        FXTreeNode temp;
        String line, type, text;
        temp = new FXTreeNode();
        temp.setType(ComponentType.valueOf(sc.nextLine().substring(2)));
        tree.setRoot(temp);
        tree.cursorToRoot();
        int pos1,pos2;
        try {
            while (sc.hasNextLine()) {
                pos1 = 2;
                line = sc.nextLine();
                tree.cursorToRoot();
                while(Character.isDigit(line.charAt(pos1+2))){
                    tree.cursorToChild(line.charAt(pos1));
                    pos1 += 2;
                }
                pos2 = line.indexOf(' ',pos1+2);
                if(pos2==-1)
                    pos2 = line.length();
                type = line.substring(pos1+2,pos2);
                text = line.substring(pos2);
                temp = new FXTreeNode();
                temp.setType(ComponentType.valueOf(type));
                temp.setText(text);
                System.out.println(temp.toString()+(int)(line.charAt(pos1)));
                tree.addChild(line.charAt(pos1), temp);
            }
        } catch (FullArrayException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return tree;
    }
    public static void writeToFile(FXComponentTree tree, String fileName) throws FileNotFoundException {
        File newFile = new File(fileName);
        PrintWriter pw = new PrintWriter(newFile);
        pw.println(tree.toString(tree.getRoot()));
    }
    //public static void exportToFXML(FXComponentTree tree, String fileName){}
    public FXTreeNode getRoot(){return root;}
    public void setRoot(FXTreeNode newRoot){root = newRoot;}
    public FXTreeNode getCursor(){return cursor;}
    public void setCursor(FXTreeNode newCursor){cursor = newCursor;}
    public String toString(FXTreeNode node){
        String print="";
        if(node!=root) {
            if (node == cursor)
                print += "==>";
            else
                print += "--+";
            print += node.toString();
        }
        for(FXTreeNode temp: node.getChildren()){
            if(temp!=null)
                print += "\t" + toString(temp) + "\n";
        }
        return print;
    }
}
