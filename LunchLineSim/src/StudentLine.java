/**
 * This class represents a line of Students represented by an array.
 * @author Gavin Chen
 * SBU ID: 114293808
 * Last documented: 08/30/2021
 */
public class StudentLine {
    private static final int CAPACITY = 20;
    private Student[] students;
    private int studentCount;

    /**
     * This is the constructor for creating a new instance of a student line.
     * A new array is created and the number of students is set to 0.
     */
    public StudentLine(){
        students = new Student[CAPACITY];
        studentCount=0;
    }

    /**
     * This method returns the number of students in the line.
     * @return
     * Returns the number of students
     */
    public int numStudents(){
        return studentCount;
    }

    /**
     * This method returns a Student the passed index.
     * @param index
     * The index of the array students to be checked
     * @return
     * The student at the passed index of students
     * @throws ArrayIndexOutOfBoundsException
     * if the passed index is lower than 1 or higher than the number of students, as 1 is subtracted from the passed value.
     * The number of students is used as the upper limit instead of the capacity out of convenience.
     * @throws EmptyLineException
     * if the line has no students
     */
    public Student getStudent(int index) throws ArrayIndexOutOfBoundsException,EmptyLineException{
        if (studentCount!=0&&(index>studentCount || index<0))
            throw new ArrayIndexOutOfBoundsException("This spot doesn't exist on the lunch line. The line remains the same.");
        else if(studentCount==0)
            throw new EmptyLineException("B-but the line is empty. The line remains the same.");
        return students[index-1];
    }

    /**
     * This method removes the student at the given index and returns the removed student.
     * @param index
     * This index identifies the location of the targeted student in the array students
     * @return
     * returns the removed student
     * @throws EmptyLineException
     * if there are no students in line
     * @throws ArrayIndexOutOfBoundsException
     * if the passed index is less than 1 or greater than the number of students, as this value is subtracted by 1 when used.
     * The number of students is used as the upper limit instead of the capacity out of convenience.
     */
    public Student removeStudent(int index) throws EmptyLineException, ArrayIndexOutOfBoundsException{
        if (studentCount!=0&&(index>studentCount || index<1))
            throw new ArrayIndexOutOfBoundsException("This spot doesn't exist on the lunch line.  The line remains the same.");
        else if (studentCount==0)
            throw new EmptyLineException("B-but there's nobody on the line.  The line remains the same.");
        Student removed = students[index-1];
        while(students[index]!=null){
            students[index-1]=students[index];
            index++;
        }
        studentCount--;
        return removed;
    }

    /**
     * This method adds a passed Student to the array students at the passed index.
     * The array will be shifted if there is already a student at the index.
     * @param index
     * This identifies the location where the student will be added
     * @param student
     * This is the student that will be added
     * @throws DeanException
     * If the line has twenty students, the student being added will be sent to the Dean's office instead
     */
    public void addStudent(int index, Student student) throws ArrayIndexOutOfBoundsException,DeanException {
        if (studentCount>=CAPACITY)
            throw new DeanException("With the reflexes of a viper, the dean snatches the student attempting to enter the line. The line remains the same. ");
        else if (index<1||index>studentCount+1)
            throw new ArrayIndexOutOfBoundsException("This spot doesn't exist on the lunch line. The line remains unchanged.90");
        for (int x = studentCount - 1; x >= index-1; x--) students[x + 1] = students[x];
        students[index-1] = student;
        studentCount++;
    }

    /**
     * This method swaps the locations of two students in line.
     * @param index1
     * The index that indicates the location of one student on the array
     * @param index2
     * The index that indicates the location of another student on the array
     * @throws ArrayIndexOutOfBoundsException
     * if the passed index is less than 1 or greater than the number of students, as this value is subtracted by 1 when used.
     * The number of students is used as the upper limit instead of the capacity out of convenience.
     */
    public void swapStudents(int index1, int index2) throws ArrayIndexOutOfBoundsException{
        if(index1>=CAPACITY || index1<0 ||index2>=CAPACITY || index2<0)
            throw new ArrayIndexOutOfBoundsException("This spot doesn't exist on the lunch line.  The line remains the same.");
        Student temp = students[index1-1];
        students[index1-1]=students[index2-1];
        students[index2-1]=temp;
    }

    /**
     * This method makes a copy of the current line and stores it at a different address.
     * @return
     * Returns the copy of the line
     */
    public Object clone(){
        StudentLine result = new StudentLine();
        for(int i = 0; i<studentCount; i++) {
            try {
                result.addStudent(i+1,(Student) students[i].clone());
            } catch (DeanException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result.toString());
        return result;
    }

    /**
     * This method checks if the current line and another object are equal.
     * It is done by comparing each Student in the array students with the corresponding value in the object if possible.
     * @param obj
     * The object to be checked for equality
     * @return
     * returns true or false
     */
    public boolean equals(Object obj){
        if (obj instanceof StudentLine) {
            StudentLine sL = (StudentLine) obj;
            for (int i = 0; i < 20; i++) {
                try {
                    if (!students[i].equals(sL.getStudent(i)))
                        return false;
                } catch (EmptyLineException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Places the line's information in String form.
     * Null values are skipped.
     * @return
     * Returns the String mentioned above
     */
    public String toString(){
        String output = "";
        for(int x = 0; x<studentCount; x++)
            output+= (x+1)+":"+students[x].toString()+"\n";
        return output;
    }
}
