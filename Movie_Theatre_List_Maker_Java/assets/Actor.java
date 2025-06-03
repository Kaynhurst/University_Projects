package assets;

public class Actor {
    //Actor Class Variables 

    String FirstName ;
    String LastName ;

    //Relevant getters and setters for the Class

    public void setFirstName(String fname){
        FirstName = fname ;
    }

    public void setLastName (String lname){
        LastName = lname ;
    }

    public String getFirstName(){
        return FirstName; 
    }

    public String getLastName() {
        return LastName ;
    }

    //Method to get the name of the Actor

    public String toString(){
        return getLastName() + " " + getFirstName().charAt(0) +"." ; 

    }

    public Actor(){
        FirstName = " " ;
        LastName = " " ;
    }
    
    public Actor(String fname, String lname){
        FirstName = fname;
        LastName = lname ;
    }
}
