import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.* ;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class LogEntries {

    public void LogCreation() throws IOException{
        File log = new File("log_entries.txt") ;

        if (log.createNewFile()) {
            
            System.out.println("New file created") ;

        }
        else {
            System.out.println("Log entries files exists. Procceding to write there.") ;
        }
    
    }

    public void LogStart(int type){
        switch (type) {
            case 1:     //Encryption log entry
                try{
            FileWriter logEntry = new FileWriter("log_entries.txt" ,true) ;

            LocalDateTime time = LocalDateTime.now() ;
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            String formattedDate = time.format(myFormatObj);

            logEntry.append("--- Log Entry ---\n") ;
            logEntry.append("Log Type : Encryption.\n");
            logEntry.append("Time of Entry : " + formattedDate + "\n") ;

            logEntry.close();

        } catch (IOException e){
            e.printStackTrace();
        }
                break;
        
            case 2:     //Decryptinon log  entry

                try{
                    FileWriter logEntry = new FileWriter("log_entries.txt" ,true) ;

                    LocalDateTime time = LocalDateTime.now() ;
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                    String formattedDate = time.format(myFormatObj);

                    logEntry.append("\n--- Log Entry ---\n") ;
                    logEntry.append("Log Type : Decryption \n") ;
                    logEntry.append("Time of Entry : " + formattedDate + "\n") ;

                    logEntry.close();

            } catch (IOException e){
                e.printStackTrace();
            }

            break;
        }
        
    }



    public void MasterKeyEntry(String key) {
        try{
            FileWriter logEntry = new FileWriter("log_entries.txt" ,true) ;

            logEntry.append("Master Key : " + key + "\n") ;
            logEntry.append("Master Key Length : " + key.length() + "\n") ;

            logEntry.close();

        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void TextEntry (String text) {

         try{

            FileWriter logEntry = new FileWriter("log_entries.txt" ,true) ;

            logEntry.append("Text length." + text.length()+ "\n") ;
            logEntry.close();
            
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
