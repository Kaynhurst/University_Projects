import assets.* ;   //Importing folder of needed assets

import java.io.BufferedWriter;
import java.io.File ;
import java.io.IOException;
import java.util.Scanner ;
import java.io.FileWriter; 

public class MovieTheater {
    public static void main (String[] args) throws IOException {
      //Variables and objects required for the program
      
      Movie movie = new Movie();
      Scanner scan = new Scanner(System.in) ;

       //Create Movie List file

        File movie_list = new File("Movie List.txt") ;

        if (movie_list.createNewFile()){
          System.out.println(movie_list.getName() + " file has been created.") ;
        }
        else{
          System.out.println(movie_list.getName() + " already exists.") ;
        }

      int user_input ;
      boolean continue_movie = true ;
      System.out.println("Welcome to the Movie Theater.") ;


      while(continue_movie){
        System.out.println("\n---Movie Theater---\n");
        System.out.println("Menu options:\n1.Add a new movie.\n2.Search for a movie.\n3.Update stock for a movie.\n4.Update duration for a movie.\n5.Exit.\n") ;

        System.out.printf(">>") ;
        user_input = scan.nextInt() ;

        switch(user_input){

          case 1:       //Add movie case
            System.out.println("Option 1 -- Add a movie.") ;
            movie.setMovieInfo();

            //Add movies to the movie list

            try{
              FileWriter movie_file = new FileWriter(movie_list,true);
              BufferedWriter buffer = new BufferedWriter(movie_file) ;
              
              //Movie details file format
              buffer.append("--Movie details--");
              buffer.newLine();
              buffer.append("Movie name:" + movie.getMovieName() + "\n") ;
              buffer.newLine();
              buffer.append("Movie release date:" + movie.getReleaseDate() + "\n");
              buffer.newLine();
              buffer.append("Movie duration: " + movie.getDuration() + "\n") ;
              buffer.newLine();
              buffer.append("Movie Producer:" + movie.getProducer() + "\n") ;
              buffer.newLine();
              buffer.append("Movie ISAN: " + movie.getISAN() + "\n") ;
              buffer.newLine();
              buffer.append("Movie Genre: " + movie.getGenre() + "\n");
              buffer.newLine();
              buffer.append("Actor List: ");
              buffer.newLine();
              for (int i = 0 ; i < movie.actor_list.length ; i++){
                buffer.write("  -Actor no."+ (i+1) +" "+ movie.actor_list[i]+"") ;
                buffer.newLine();
              }
              buffer.append("Number of copies available: " + movie.getCopies()) ;
              buffer.newLine();
              buffer.newLine();
              buffer.close();
              }
            catch(IOException e){
            System.out.println("An error has occurred.") ;
            e.printStackTrace();

          }

            break ;
            
          case 2:       //Search for a movie case
            System.out.println("Option 2 -- Search for a movie.") ;

            String user_ISAN ;
            System.out.printf("Give the ISAN of the movie: ") ;
            user_ISAN = scan.next() ;
            Scanner isan_reader = new Scanner(movie_list) ;
            boolean isan_false = true ;

            while (isan_reader.hasNext()){
              if(isan_reader.next().contains(user_ISAN)){
                System.out.println("There is a movie with that ISAN.") ;
                isan_false = false;
                break ;
              }
            }

            if(isan_false){
              System.out.println("There is no movie with the given ISAN.") ;
            }
            
            break ;

          case 3:       //Update the copies of a movie
            System.out.println("Option 3 -- Update stock.") ;
            movie.AlterCopies();


            //Read the file and replace the altered copies from the user
            try{
            Scanner file_reader = new Scanner(movie_list);
            FileWriter writer = new FileWriter(movie_list,true) ;

            if(movie_list.exists()) {
              while(file_reader.hasNextLine()){
                if(file_reader.nextLine().contains("Number of copies available:")) {
                  writer.append("Number of copies available: " + movie.getCopies());
                  writer.flush() ;
                  writer.close();
                }
              }

            }
            file_reader.close();
            }
            catch(IOException e){
              System.out.println("An error has occurred.") ;
            }

            
            break ;

          case 4:     //Update the duration of a movie
            System.out.println("Option 4 -- Update duration.") ;
            System.out.println("Give the new duration for the movie.") ;
            movie.setDuration(scan.nextInt());

            //Read the file and replace the altered duration from the user

            try{
              Scanner file_reader = new Scanner(movie_list);
              FileWriter writer = new FileWriter(movie_list) ;
  
              if(movie_list.exists()) {
                while(file_reader.hasNextLine()){
                  if(file_reader.nextLine().contains("Movie duration:")) {
                    writer.write("Movie duration: " + movie.getDuration());
                    writer.close();
                  }
                }
              }
              file_reader.close();
              }
              catch(IOException e){
                System.out.println("An error has occurred.") ;
              }
  
              
            break ;

          case 5:
              System.out.println("Exitting program....\n") ;
              continue_movie = false; 
              break ;

          default:
              System.out.println("\nInvalid input.") ;
        }
      
      }

      scan.close();
  }

  
}
