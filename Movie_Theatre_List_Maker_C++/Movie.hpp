#include <iostream>
#include <string>
using namespace std;
#include "Actor.hpp"

class Movie {
    //Detail Variables for movie
    private:
        int duration ;
        int ISAN ;
        int copies ;
        string producer ;
        string movieName ;
        string releaseDay ;
        string releaseMonth ;
        string releaseYear ;
        string genre ;

        Actor actor_1 ;
        Actor actor_2 ;
        Actor actor_3 ;
        Actor actor_4 ;

    //Actor List Array

        Actor actor_list[4] = {actor_1,actor_2,actor_3,actor_4} ;

    //Setters

    public:
        void setProducer(string oProducer){
            producer = oProducer ;
        }

        void setDuration(int oDuration) {
            duration = oDuration ;

        }
        void setMovieName(string oName) {
            movieName = oName ;
        }

        void setISAN(int oISAN) {
            ISAN = oISAN ;
        }

        void setCopies(int oCopies) {
            copies = oCopies ;
        }

        void setGenre(string oGenre){
            genre = oGenre ;
        }

        void setActorList(){
            int actor_number ;
            string actor_fname , actor_lname ;
            cout << "\n" << "Give the number of actors invloved: " ;
            cin >> actor_number ;
            cin.clear() ;       //Flush cin to stop interference with other strings

            for (int i = 0 ; i < actor_number ; i++) {
                cout << "Give the first name of actor no." << i + 1 << ": ";
                cin >> actor_fname ;
                actor_list[i].setFirstName(actor_fname) ;

                cout << "\nGive the last name of actor no." << i + 1 << ": ";
                cin >> actor_lname ;
                actor_list[i].setLastName(actor_lname) ;
            }
        }

        void setReleaseDay(int rDay) {

                if (rDay > 32 || rDay < 1){
                    cout << "Invalid input.\n>>" ;
                    cin >> rDay ;
                    setReleaseDay(rDay) ;
                }
                else{
                    releaseDay = rDay ;
                }
        }
        void setReleaseMonth(int rMonth) {
                if (rMonth > 12 || rMonth < 1){
                    cout << "Invalid input.\n>>" ;
                    cin >> rMonth ;
                    setReleaseMonth(rMonth) ;
                }
                else{
                    releaseMonth = rMonth ;
                }
        }
        void setReleaseYear(int rYear) {

                if (rYear <= 0){
                    cout << "Invalid input.\n>>" ;
                    cin >> rYear ;
                    setReleaseDay(rYear) ;
                }
                else{
                    releaseYear = rYear ;
                }
        }

        
    
    //Getters
        string getProducer(){
            return producer ;
        }

        string getMovieName() {
            return movieName ;
        }

        int getDuration() {
            return duration ;
        }

        int getISAN() {
            return ISAN ;
        }

        string getReleaseDate() {
            return releaseDay + "/" + releaseMonth + "/" + releaseYear ;
        }

        string getGenre() {
            return genre ;
        }
        int getCopies(){
            return copies ;
        }

        Actor getActorList(){        //Get Actor list in separate line format
            for (int i = 0 ; i <= sizeof(actor_list) ; i++) {
                return actor_list[i] ;
            }
        }

    //Methods

        void alterCopies(){
            int user_input ;
            cout << "1. Add copies to the movie\n2.Remove copies from the movie." ;

            do {
                cout << "\n>> " ;
                cin >> user_input ;

               
            if(user_input == 1){
                cout << "Select the number of copies you want to add." << "\n>>";
                cin >> user_input ;
                setCopies(getCopies() + user_input) ;
                break ;
            }else if(user_input == 2){  
                cout << "Select the number of copies you want to remove."  ;
                
                do{
                    cout << "\n>>" ;
                    cin >> user_input ;
                    if ( (getCopies() - user_input)< 0){
                        
                        cout << "Invalid input. Copies must remain above 0" ;
                    }
                    else{
                        setCopies(getCopies() -user_input) ;
                    }
                }while((getCopies() - user_input) < 0);

                break;   
            }

            else{

                cout << "Invalid input." ;
            }
            }
            while(user_input != 1 && user_input != 2) ;

        }
        void setMovieInfo(){
            cin.sync() ;        //Clear the cin cache 
            int number_input ;
            string string_input ;

            cout << "Movie Name : " ;
            getline(cin,string_input) ;
            setMovieName(string_input) ;

            cout << "Movie Producer : " ;
            getline(cin,string_input) ;
            setProducer(string_input) ;

             cout << "Movie Genre : " ;
            getline(cin,string_input) ;
            setGenre(string_input) ;

            cout << "Movie Duration in minutes : " ;
            cin >> number_input ;
            setDuration(number_input) ;

            cout << "Movie ISAN : " ;
            cin >> number_input ;
            setISAN(number_input) ;

            cout << "Release Date (DD/MM/YYY) in numerical values: " ;
            cin >> number_input ;
            setReleaseDay(number_input) ;
            cin >> number_input ;
            setReleaseMonth(number_input) ;
            cin >> number_input ;
            setReleaseYear(number_input) ;

            cin.sync() ;
            setActorList();

            cout << "Available copies : " ;
            cin >> number_input ;
            setCopies(number_input) ;

        }
} ;
