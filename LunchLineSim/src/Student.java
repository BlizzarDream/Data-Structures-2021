/**
 * This class represents a Student and his/her name and money.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last documented: 08/30/2021
 */
public class Student {
    private String name;
    private double money;
    /**This is the constructor for creating a new Student object.
     * @param name
     * The name of the Student in line
     * @param money
     * The amount of money the Student has
     */
    public Student(String name, double money){
        this.name = name;
        this.money = money;
    }

    /**
     * This method checks the another Student for equal private variables.
     * @param obj
     * The object to be checked for equality
     * @return
     * returns true or false
     */
    public boolean equals(Object obj){
        if(obj instanceof Student){
            Student s = (Student) obj;
            return this.name.equals(s.getName()) && this.money==s.getMoney();
        }
        return false;
    }

    /**
     * This method makes a copy of the current student and saves it at a different address.
     * @return
     * Returns the copied instance
     */

    public Object clone(){
        return new Student(this.name,this.money);
    }

    /**
     * This method creates a String containing visual information of both private variables of the student.
     * @return
     * returns String mentioned above
     */
    public String toString(){
        return String.format("%15s",name)+"\t"+String.format("$%3.2f",money);
    }

    /**
     * This method retrieves the name of the Student.
     * @return
     * returns the Student's name
     */
    public String getName(){
        return name;
    }

    /**
     * This method retrieves the money owned by the current Student.
     * @return
     * returns the Student's money
     */
    public double getMoney(){
        return money;
    }

    /**
     * This method sets the current Student's name to the passed argument.
     * @param name
     * The new name of the Student in String
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * This method sets the money of the Student to the passed argument.
     * @param money
     * The new amount of money the Student will own
     */
    public void setMoney(double money){
        this.money=money;
    }

}
