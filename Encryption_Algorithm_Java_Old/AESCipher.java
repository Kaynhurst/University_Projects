//Library Imports
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap ;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;




public class AESCipher {
    private String key;
    private String subKey1;
    private String subKey2;

    
    public void setKeys(String key) {
        this.key = key;
        this.subKey1 = generateSubKey();
        this.subKey2 = generateSubKey();
    }
    //Subkey Generation
    private String generateSubKey() {
        try {
            SecureRandom random = new SecureRandom(key.getBytes());
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, random);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encodedKey = secretKey.getEncoded();
            return Base64.getEncoder().encodeToString(encodedKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Encryption Process

    public String encrypt(String message) {
        try {
            SecretKeySpec sk1 = new SecretKeySpec(Base64.getDecoder().decode(subKey1), "AES");
            SecretKeySpec sk2 = new SecretKeySpec(Base64.getDecoder().decode(subKey2), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sk1);

            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, sk2);
            byte[] encryptedMessage = cipher.doFinal(encryptedBytes);

            String encryptedText = Base64.getEncoder().encodeToString(encryptedMessage) ;
            Map<Character, SubBox.Complex> sBox = SubBox.createSubBox(encryptedText) ;

            encryptedText = SubBox.Scramble(encryptedText, sBox) ;

            return encryptedText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //Decryption Process

    public String decrypt(String encryptedMessage) {
        try {

            Map<Character, SubBox.Complex> sBox = SubBox.createSubBox(encryptedMessage) ;
            encryptedMessage = SubBox.UnScramble(encryptedMessage, sBox) ;

            SecretKeySpec sk1 = new SecretKeySpec(Base64.getDecoder().decode(subKey1), "AES");
            SecretKeySpec sk2 = new SecretKeySpec(Base64.getDecoder().decode(subKey2), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sk2);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
            cipher.init(Cipher.DECRYPT_MODE, sk1);

            byte[] decryptedMessage = cipher.doFinal(decryptedBytes);
            return new String(decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

        /***
        //Variable Declarations
        AESCipher cipher = new AESCipher();
        Scanner scanner = new Scanner(System.in);

        //File to String Conversion
        String filePath ;
        String input = "";

        // Menu
        Boolean menu_flag = true ;
        
        while(menu_flag) {
            int menu_input = 0 ;

            //Master Key input
    
            String key ;

            while(true) {
                System.out.print("Enter the key (at least 16 characters): ");
                key = scanner.nextLine();

                if (key.length() < 16 ) {
                    System.out.println("Insufficient key length. Please enter a new one.\n") ;
                }
                else{
                    break ;
                }
            } 

            //Subkey Generation 
            cipher.setKeys(key);

            
            // Menu Selection

            System.out.println("--- Encryption Algorithm Menu ---") ;
            System.out.println("1.Encryption\n2.Decryption") ;

            menu_input = scanner.nextInt() ;

            
            System.out.println("Give the file path : ") ;
            filePath = scanner.next() ;

            FileConversion convert = new FileConversion(filePath) ;

            switch (menu_input) {

                

                case 1:             //Encryption Process

                    System.out.println("\n--- Encryption Menu ---") ;

                    try {

                        
                        input =  convert.readFileToString() ;

                        String encryptedMessage = cipher.encrypt(input);
                        convert.writeStringToFile(encryptedMessage,1);
                                   

                    } catch (IOException e) {

                    e.printStackTrace();
                        }
                        

                    break;

                case 2:     //Decryption Process

                    System.out.println("\n--- Decryption Menu ---") ;

                    try {

                        
                        System.out.println("\n--- Decryption Menu ---");
                        input = convert.readFileToString();

                        String decryptedMessage = cipher.decrypt(input);

                        convert.writeStringToFile(decryptedMessage, 2);
                        System.out.println("Decryption completed.");

                    } catch (IOException e) {

                        System.err.println("Decryption error: " + e.getMessage());
                        e.printStackTrace();
                        }
                        

                    break;
                    
                default:

                    System.out.println("\n--- Exitting program... ---\n") ;
                    menu_flag = false ;

                    break;
            }
        }

        scanner.close();

  

        String inpt = "Hello" ;

        Map<Character, SubBox.Complex> sBox = SubBox.createSubBox(inpt) ;

        String outpt = SubBox.Scramble(inpt, sBox) ;

        System.out.println("Sbox output : " + outpt) ;

        String originalinput = SubBox.UnScramble(outpt, sBox) ;

        System.out.println("\nSbox input : " + originalinput) ;

     ***/
    String inputString = "Hello, World!";
    String scrambledString = ComplexSBox.scramble(inputString);
    
    System.out.println("Input: " + inputString);
    System.out.println("Scrambled: " + scrambledString);
    
     
    }
   
}
