package Exercise_2;
import java.util.Scanner;

public class time {
    //Variable Declarations 
    private int hours ;
    private int minutes ;
    private int seconds ;

    //Input Scanner
    Scanner inpt = new Scanner(System.in) ;
    //Variable Setters
    public void setHours (int or_hours) {
        hours = or_hours ;
    }

    public void setMinutes (int or_minutes) {
        minutes = or_minutes ;
    }

    public void setSeconds (int or_seconds) {
        seconds = or_seconds ;
    }

    //Variable Getters
    public int getHours (){
        return hours ;
    }

    public int getMinutes (){
        return minutes ;
    }

    public int getSeconds () {
        return seconds ;
    }

    //Methods

    public time(){
        //Midnight setter
        hours = 0 ;
        minutes = 0 ;
        seconds = 0 ;
    }

    public time(int or_hours, int or_minutes , int or_seconds) {
        hours = or_hours ;
        minutes = or_minutes ;
        seconds = or_seconds ;
    }
    
    public int convertSeconds (){
        return getSeconds() + (getMinutes()*60) + (getHours()*3600) ;
    }

    public void getTime(){

        //Input checks for each variable
        System.out.println("Input hours (0-23) : ") ;
        System.out.printf(">> ") ;
        setHours(inpt.nextInt());
        inpt.nextLine() ;
        do {

            if (getHours() < 0 || getHours() > 23) {
                System.out.println("Invalid input") ;
                System.out.printf(">> ") ;
                setHours(inpt.nextInt()) ;
                inpt.nextLine() ;
            }
        }
        while (getHours() >= 23 && getHours() <= 0) ;


        System.out.println("Input minute (0-59)") ;
        System.out.printf(">> ") ;
        setMinutes(inpt.nextInt());
        inpt.nextLine() ;
        
        do {

            if (getMinutes() < 0 || getMinutes() > 59) {
                System.out.println("Invalid input") ;
                System.out.printf(">> ") ;
                setMinutes(inpt.nextInt()) ;
                inpt.nextLine() ;
            }
        }
        while (getMinutes() >= 59 && getMinutes() <= 0) ;
        

        System.out.println("Input seconds (0-59)") ;
        System.out.printf(">> ") ;
        setSeconds(inpt.nextInt());
        inpt.nextLine() ;
        do {

            if (getSeconds() < 0 || getSeconds() > 59) {
                System.out.println("Invalid input") ;
                setSeconds(inpt.nextInt());
                inpt.nextLine() ;
            }
        }
        while (getSeconds() >= 59 && getSeconds() <= 0) ;
        
    }
   
    public void displayAttributes() {
        System.out.println("Hours :" + getHours()) ;
        System.out.println("Minutes :" + getMinutes());
        System.out.println("Seconds :" + getSeconds());
        System.out.println("Total seconds from time :" + convertSeconds()) ;
    }
}
