public class FXTreeNode {
    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    final int maxChildren = 10;
    public String getText(){return text;}
    public void setText(String newText){text=newText;}
    public ComponentType getType(){return type;}
    public void setType(ComponentType newType){type=newType;}
    public FXTreeNode getParent(){return parent;}
    public void setParent(FXTreeNode newNode){parent=newNode;}
    public FXTreeNode[] getChildren(){return children;}
    public void setChildren(FXTreeNode[] newNodes){children=newNodes;}
    public String toString(){
        String print = type.toString();
        if(!text.equals(""))
            print+=": "+text;
        return print;
    }
}