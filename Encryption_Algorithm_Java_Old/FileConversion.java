import java.io.* ;

public class FileConversion {
    private String filePath;

    public FileConversion(String filePath) {
        this.filePath = filePath;
    }
    //Make file into String
    public String readFileToString() throws IOException {
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString() ;
    }
    //Make string into File
    public void writeStringToFile(String contentToWrite,int type) throws IOException {
        String filePathName ;
        switch(type){

        case 1 :
            filePathName = "Encrypted Message.txt" ;
            //File Creation
            try {
                File  encrypt_file = new File(filePathName) ;
                if (encrypt_file.createNewFile()) {
                    System.out.println("Encrypted File Created.") ;
                }
                else {
                    System.out.println("File already exists") ;
                }

                //Write to File

                BufferedWriter buffer = new BufferedWriter(new FileWriter(filePathName)) ;

                String[] linesToWrite = contentToWrite.split("\n") ;
                for (String line :linesToWrite) {
                    buffer.write(line) ;
                }
                buffer.close() ;

            } catch (IOException e) {
                System.out.println("Error occured.");
                e.printStackTrace();
            }
            break ;
        
        case 2 :
            filePathName = "Decrytped Message.txt" ;
            //File Creation
            try {
                File  encrypt_file = new File(filePathName) ;
                if (encrypt_file.createNewFile()) {
                    System.out.println("Decrypted File Created.") ;
                }
                else {
                    System.out.println("File already exists") ;
                }

                //Write to File

                BufferedWriter buffer = new BufferedWriter(new FileWriter(filePathName)) ;

                String[] linesToWrite = contentToWrite.split("\n");
                for (String line :linesToWrite) {
                    
                    buffer.write(line);
                    buffer.newLine() ;
                }
                buffer.close() ;

            } catch (IOException e) {
                System.out.println("Error occured.");
                e.printStackTrace();
            }
            break ;
        }
}
}