#include <iostream>
#include "Movie.hpp"
#include <fstream>
#include <string>

using namespace std;

int main() {

    cout << "Welcome to the Movie Theater." << "\n";
    //Variables for the execution of the program

    Movie movie ;
    ofstream Movie_list ("Movie_list.txt") ;
    bool stop_movie = true ;
    int user_input ;
    string input ;

    while(stop_movie) {
        //User Interface Menu
        cout << "1.Add movie.\n"
             << "2.Search for a movie.\n"
             << "3.Alter copies of a movie.\n"
             << "4.Alter the duration of a movie.\n"
             << "5.Exit." ;

            cout << "\n>>" ;
            cin >> user_input ;


        //Menu expressions and cases
        switch(user_input) {
            
            case 1 :        //Add movie to file
                Movie_list << "---Movie Details ---\n" ;
                movie.setMovieInfo() ;
                Movie_list  << "Movie Name : "      << movie.getMovieName() << "\n"
                            << "Producer Name : "   << movie.getProducer() << "\n"
                            << "Movie Genre : "     << movie.getGenre() << "\n"
                            << "Movie ISAN : "      << movie.getISAN() << "\n"
                            << "Duration : "        << movie.getDuration() << "\n"
                            << "Release date : "    << movie.getReleaseDate() << "\n"
                            << "Actor List : \n "   << movie.getActorList() ;
                                                       
                
                break ;
            case 2 :        //Search for a movie in the file
                cout << "Search for a movie option.\n" ;
                int user_ISAN ;
                string file_text ;
                bool isan_notFound = true ;
                cout << "Give the ISAN of the movie you want to search for : " ;
                cin >> user_ISAN ;

                while(getline(Movie_list,file_text) {

                        if (file_text.find(user_ISAN)){
                            cout << "There is a movie with the given ISAN." << endl ;
                            isan_notFound = false ;
                            break ;
                        }

                        if(isan_notFound){
                            cout << "There is not a movie with the given ISAN." << endl ;
                        }

                }
                break ;
            case 3 :        //Alter the copies of a movie
                cout << "Alter the copies of a movie.\n" ;
                movie.alterCopies() ;
                
                break ;
            case 4 :        //Alter the duration of the movie
                int number_input ;
                cout << "Alter the duration of a movie.\n" ;
                cout << "Give the new duratio of the movie : " ;
                cin >> number_input ;

                movie.setDuration(number_input) ;

                break ;
            case 5 :        //Exit the prorgram
                cout << "Exitting program...\n" ;
                stop_movie = false ;
                break ;
            default :       //User input error
                cout << "Inavlid input. Please select on of the options." ;
        

    }

    
    Movie_list.close() ;
    return 0;
}