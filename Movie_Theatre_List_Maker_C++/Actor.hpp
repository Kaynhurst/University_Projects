#include <iostream>
using namespace std;

class Actor{
    private:
    string FirstName ;
    string LastName ;

    
    public: 
    //Setters
        void setFirstName(string fname){
            FirstName = fname ;
        }
        void setLastName(string lname){
            LastName = lname ;
        }
    
        string getFirstName(){
            return FirstName ;
        }

        string getLastName(){
            return LastName ;
        }

        Actor(){        //Constructor
            FirstName = "." ;
            LastName = "." ;
        }

        ~Actor(){       //Destructor
            FirstName = "" ;
            LastName = "" ;
        }

        string getName (){
            return getLastName() + " " + getFirstName()[0] +"."  ;
        } 
};