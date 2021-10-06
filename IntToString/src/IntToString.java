/*
Gavin Chen
114293808
Program can be improved with switch statements
*/
public class IntToString {
    private int num;
    private String word;
    public IntToString(int num){
        this.num = num;
        word = "";
    }
    //Based on testing, this mehod will convert any 10 digit number
    public String convert(){
        int count = 1;
        int digit;
        if(num == 0) {
            word = "Zero";
            return word;
        }
        while (num>0 && count<=10){
            digit = num%10;
            if(count%3==1) {
                    word=addNewNumClass(count)+word;
                if(num%100>=10 && num%100<20) {
                    word = teensToString(num % 10)+word;
                    num/= 10;
                    count += 1;
                }
                else {
                    word=onesToString(digit)+word;
                }
            }
            else if(count%3==2){
                word=tensToString(digit)+word;
            }
            else {
                word = onesToString(digit)+"-Hundred-"+word;
            }
            num/=10;
            count+=1;
        }
        return word;
    }
    //Converts a single digit to its counterpart
    private String onesToString(int digit){
        if(digit==1) return "One";
        else if(digit==2) return "Two";
        else if (digit==3) return "Three";
        else if (digit==4) return "Four";
        else if (digit==5) return "Five";
        else if (digit==6) return "Six";
        else if (digit==7) return "Seven";
        else if (digit==8) return "Eight";
        else return "Nine";
    }
    //Accounts for the exceptions to onesToString from the numbers 10-19
    private String teensToString(int digit){
        if(digit == 0) return "Ten";
        else if(digit==1) return "Eleven";
        else if (digit==2) return "Twelve";
        else if (digit==3) return "Thirteen";
        else if (digit==5) return "Fifteen";
        else if (digit==8) return "Eighteen";
        else return onesToString(digit)+"teen";
    }
    //Converts tens digits to String
    private String tensToString(int digit){
        if(digit==2) return "Twenty-";
        else if (digit==3) return "Thirty-";
        else if (digit==5) return "Fifty-";
        else if (digit==8) return "Eighty-";
        else return onesToString(digit)+"ty-";
    }
    //Adds hundred, thousand, million, etc.
    private String addNewNumClass(int count) {
        if (count == 4) return "-Thousand-";
        else if(count == 7) return "-Million-";
        else if(count==10) return "-Billion-";
        else if(count==13) return "-Trillion-";
        else if(count==16) return "-Quadrillion-";
        else if(count==19) return "-Quintillion-";
        else return "";
    }
}
