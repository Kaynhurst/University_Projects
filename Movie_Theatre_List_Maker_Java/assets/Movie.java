package assets;
import java.util.Scanner ;
public class Movie {
    // Class Variables and Movie details
    int duration ;
    String producer ;
    int ISAN;
    int copies_number ;
    String movie_name ;
    String genre ;
    String releaseDay ;
    String releaseMonth ;
    String releaseYear ;

    Scanner inpt = new Scanner(System.in) ;

    //Actor Object for the list of actors
    Actor actor_1 = new Actor() ;
    Actor actor_2 = new Actor() ;
    Actor actor_3 = new Actor() ;
    Actor actor_4 = new Actor() ;

    public Actor[] actor_list ={actor_1,actor_2,actor_3,actor_4} ;

    public void setReleaseDay(){
        int day ;
        do {
            System.out.printf(">>") ;
            releaseDay = inpt.next() ;
            day = Integer.parseInt(releaseDay) ;

            if (day > 31 || day < 1) {
                System.out.println("Invalid input. Please try again.") ;
            }
        }while(day > 31 || day < 1) ;
    }

    public void setReleaseMonth(){
        int month ;

        do {
            System.out.printf(">>") ;
            releaseMonth = inpt.next() ;
            month = Integer.parseInt(releaseMonth) ;
            if (month >= 12 && month <= 1) {
                System.out.println("Invalid input. Please try again.") ;
            }
        }while(month >= 12 && month <= 1) ;
    }
    public void setReleaseYear(){
        System.out.printf(">>") ;
        releaseYear = inpt.next() ;
    }

    public void setActorList(){
        
        System.out.println("Give the number of actors involved.\n") ;
        
        int input ;
        do{
            System.out.printf(">>") ;
            input = inpt.nextInt() - 1;

            if (input > 4){
                System.out.println("Invalid input.") ;
            }
        }while(input > 4) ;

        for(int x = 0; x <= input ; x++) {      //Dynamic Actor Entry

            System.out.println("Give the first name of the actor no." + (x+1)) ;
            System.out.printf(">>") ;
            actor_list[x].setFirstName(inpt.next()) ;

            System.out.println("\nGive the last name of the actor no." + (x+1)) ;
            System.out.printf(">>") ;
            actor_list[x].setLastName(inpt.next());

        }
    }

    public void getActorList(){
        for (var x : actor_list)
            System.out.println(x);
        
    }

    //Getters and setter for all the other object elements

    //Setters
    public void setDuration(int dur){
        duration = dur ;
    }

    public void setProducer(String prod){
        producer = prod ;
    }

    public void setISAN (int isan){

        if (isan >=0){      //Catch negative number for ISAN
            ISAN = isan ;
        }
        else{
            System.out.println("Invalid input. Must be above 0.") ;
            System.out.printf(">>") ;
            setISAN(inpt.nextInt());

        }
        
    }

    public void setCopies(int copies){

        if (copies >= 0){               //Catch negative number of copies
            copies_number = copies ;
        }
        else{
            System.out.println("Invalid input. Must be above 0.") ;
            System.out.printf(">>") ;
            setCopies(inpt.nextInt());
        }
    }

    public void setMovieName (String name){
        movie_name = name ;
    }

    public void setGenre (String type) {
        genre = type ;
    }

    public void setReleaseDate () {
        setReleaseDay();
        setReleaseMonth();
        setReleaseYear();
    }
    //Getters
    public int getDuration(){
        return duration ;
    }

    public String getProducer(){
        return producer ;
    }

    public int getISAN() {
        return ISAN ;
    }

    public int getCopies(){
        return copies_number ;
    }

    public String getMovieName(){
        return movie_name ;
    }

    public String getGenre() {
        return genre ;
    }

    public String getReleaseDate(){
        return releaseDay + "/" + releaseMonth + "/" + releaseYear ;
    }

    //Methods for manipulating the Class

    public void setMovieInfo() { 

        System.out.println("\n---Movie information interface---\n") ;

        System.out.printf("Movie name: ") ;
        setMovieName(inpt.nextLine());

        System.out.printf("Producer name: ");
        setProducer(inpt.nextLine()) ;
        inpt.nextLine();

        System.out.printf("Genre: ") ;
        setGenre(inpt.nextLine()) ;
        inpt.nextLine();

        System.out.printf("ISAN: ") ;
        setISAN(inpt.nextInt()) ;

        setActorList(); 
        
        System.out.printf("Release Date (Day/Month/Year) in numericals values :") ;
        setReleaseDate();

        System.out.printf("Movie duration in minutes: ") ;
        setDuration(inpt.nextInt());

       System.out.printf("Available copies :") ;
        setCopies(inpt.nextInt()) ;

    }

    public void showMovieInfo(){                    //Show the information of the movie in a text based format
        System.out.println("\n---Movie Info ---\n") ;
        System.out.println("Movie name :" + getMovieName()) ;
        System.out.println("Movie release date:" + getReleaseDate()) ;
        System.out.println("Movie duration: " + getDuration() + "mins") ;
        System.out.println("Movie producer: " + getProducer()) ;
        System.out.println("Movie ISAN :" + getISAN()) ;
        System.out.println("Movie Genre : " + getGenre());
        System.out.println("Actor list:") ;
        getActorList() ;
    }

    public void AlterCopies(){          //Alter the number of copies for the movie according to user input
        int input ;
        System.out.println("\nAlter reserved movie copies\n1.Add copies\n2.Remove copies") ;

        do{
            System.out.printf(">>") ;
            input = inpt.nextInt() ;
            if (input !=1 && input !=2){
                System.out.println("Invalid input.") ;
            }
        }while (input != 1 && input !=2) ;

        if (input == 1){
            System.out.println("Select the number of copies you would like to add.") ;
            System.out.printf(">>") ;
            setCopies(getCopies() + inpt.nextInt());
        }
        else {
            System.out.println("Select the number of copies you would like to remove.") ;
            

            do{                             //Check that the number of copies stays above or equal to zero
                System.out.printf(">>") ;
                input = inpt.nextInt() ;
                if ((getCopies() - input) < 0) {
                    System.out.println("Number of copies removed is invalid.") ;
                }
                else{
                    setCopies(getCopies() - input);
            } 
            }while((getCopies() - input) < 0) ;
        }
    }

    public void checkISAN(int ISAN_1 , int ISAN_2){
        if (ISAN_1 == ISAN_2){
            System.out.println("There is a movie that matches the given ISAN.") ;
        }
        else{
            System.out.println("The ISAN is available.") ;
        }
    }

}
