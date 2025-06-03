import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File.* ;


public class Main {
    private static final int KEY_SIZE = 16; // Key size in bytes

    private byte[] key; // Main key
    private byte[] subKey1; // Subkey 1
    private byte[] subKey2; // Subkey 2

    public Main(String key) {
        if (key.length() < KEY_SIZE) {
            throw new IllegalArgumentException("Key must be at least 16 characters long.");
        }

        this.key = key.getBytes(StandardCharsets.UTF_8);
        generateSubKeys();
    }

    // Generate subkeys
    private void generateSubKeys() {
        SecureRandom random = new SecureRandom();

        // Generate random bytes for subkeys
        subKey1 = new byte[KEY_SIZE];
        subKey2 = new byte[KEY_SIZE];
        random.nextBytes(subKey1);
        random.nextBytes(subKey2);
    }

    private static final Point2D.Double[] generateSBox() {
        Point2D.Double[] sBox = new Point2D.Double[256]; // Array to hold 256 values

        for (int i = 0; i < 256; i++) {
            // Generate a custom complex number
            double real = Math.sin(i * 0.1); // Generate a sinusoidal wave for the real component
            double imaginary = Math.cos(i * 0.1); // Generate a sinusoidal wave for the imaginary component

            Point2D.Double complexNumber = new Point2D.Double(real, imaginary);
            sBox[i] = complexNumber; // Assign the complex number to the S-Box array
        }

        return sBox;
    }

    private static final Point2D.Double[] S_BOX = generateSBox();

    private static final byte[] generatePBox() {
        byte[] pBox = new byte[256];

        // Initialize the pBox with sequential values
        for (int i = 0; i < 256; i++) {
            pBox[i] = (byte) i;
        }

        // Perform Fisher-Yates shuffle
        Random random = new Random();
        for (int i = 255; i > 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap values
            byte temp = pBox[i];
            pBox[i] = pBox[j];
            pBox[j] = temp;
        }

        return pBox;
    }

    private static final byte[] P_BOX = generatePBox();

    // Custom substitution using S-box
    private byte[] substitute(byte[] input, Point2D.Double[] sBox) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            int index = input[i] & 0xFF;
            Point2D.Double complexNumber = sBox[index];
            output[i] = (byte) complexNumber.getX();
        }
        return output;
    }

    // Custom permutation using P-box
    private byte[] permute(byte[] input, byte[] pBox) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = pBox[input[i] & 0xFF];
        }
        return output;
    }

    // Custom symmetric encryption algorithm
    public String encrypt(String message) throws IOException {

        FileWriter logEntry = null ;
        try {
            logEntry = new FileWriter("log_entries.txt",true) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis() ;
        try {
            SecretKeySpec sk1 = new SecretKeySpec(subKey1, "AES");
            SecretKeySpec sk2 = new SecretKeySpec(subKey2, "AES");
    
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    
            // Encrypt using sk1
            cipher.init(Cipher.ENCRYPT_MODE, sk1);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    
            // Ensure the length is a multiple of 16 before custom operations
            int paddingSize = 16 - (encryptedBytes.length % 16);
            byte[] paddedBytes = Arrays.copyOf(encryptedBytes, encryptedBytes.length + paddingSize);
    
            // Apply custom operations (substitute and permute)
            paddedBytes = substitute(paddedBytes, S_BOX);
            paddedBytes = permute(paddedBytes, P_BOX);
    
            // Add one more byte to ensure multiple of 16 during decryption
            byte[] finalPaddedBytes = Arrays.copyOf(paddedBytes, paddedBytes.length + 1);
            finalPaddedBytes[paddedBytes.length] = 0; // You can set this byte value as needed
    
            // Encrypt using sk2
            cipher.init(Cipher.ENCRYPT_MODE, sk2);
            byte[] finalEncryptedBytes = cipher.doFinal(finalPaddedBytes);
    
            // Convert to Base64
            return Base64.getEncoder().encodeToString(finalEncryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();

        } 
        //Calculate time taken for encyption and write to log file
        finally{

            long endTime = System.currentTimeMillis() ;
            double finalTime = (endTime - startTime) / 1000.0 ;

            String formattedTime = String.format("Encryption time: %.5f seconds", finalTime);
            logEntry.append(formattedTime) ;
            
            logEntry.close() ;
        }
        return null;
    }
    
    public String decrypt(String encryptedText) throws IOException {
        
        FileWriter logEntry = null ;
        try {
            logEntry = new FileWriter("log_entries.txt",true) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis() ;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec sk1 = new SecretKeySpec(subKey1, "AES");
            SecretKeySpec sk2 = new SecretKeySpec(subKey2, "AES");
    
            System.out.println("Input String: " + encryptedText);
    
            encryptedText = encryptedText.trim();
            encryptedText = encryptedText.replaceAll("[^a-zA-Z0-9+/=]", "");
    
            System.out.println("Cleaned String: " + encryptedText);
    
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
    
            System.out.println("Length after decoding: " + encryptedBytes.length);
    
            // Decrypt using sk2
            try {
                cipher.init(Cipher.DECRYPT_MODE, sk2);
                byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
    
                // Reverse Substitution (inverse S-box) and Reverse Permutation
                decryptedBytes = reverseSubstitute(decryptedBytes, S_BOX);
                decryptedBytes = reversePermute(decryptedBytes, P_BOX);
    
                // Decrypt using sk1 with standard PKCS5Padding
                try {
                    cipher.init(Cipher.DECRYPT_MODE, sk1);
                    byte[] finalDecryptedBytes = cipher.doFinal(decryptedBytes);
    
                    // Remove the extra byte added during encryption
                    byte[] trimmedBytes = Arrays.copyOf(finalDecryptedBytes, finalDecryptedBytes.length - 1);
    
                    // Convert to string using UTF-8
                    return new String(trimmedBytes, StandardCharsets.UTF_8);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Calculate time taken for decrypt and write to log file
        finally{

            long endTime = System.currentTimeMillis() ;
            double finalTime = (endTime - startTime) / 1000.0 ;

            String formattedTime = String.format("Decryption time: %.5f seconds", finalTime);
            logEntry.append(formattedTime) ;
            
            logEntry.close() ;

        }

        return null;
    }
    
    private byte[] reversePermute(byte[] input, byte[] pBox) {
        int paddingSize = input[input.length - 1] & 0xFF;
    
        // Add an extra byte for padding information
        byte[] output = new byte[input.length + 1];
        for (int i = 0; i < input.length; i++) {
            output[i] = pBox[input[i] & 0xFF];
        }
    
        // Store padding information in the extra byte
        output[output.length - 1] = (byte) paddingSize;
    
        // Reverse padding
        return Arrays.copyOfRange(output, 0, output.length - paddingSize - 1);
    }
    
    private byte[] reverseSubstitute(byte[] input, Point2D.Double[] sBox) {
        int paddingSize = input[input.length - 1] & 0xFF;
    
        // Exclude the extra byte for padding information
        byte[] output = new byte[input.length - 1];
        for (int i = 0; i < input.length - 1; i++) {
            int index = input[i] & 0xFF;
            double real = sBox[index].getX();
            double imaginary = sBox[index].getY();
            // Assuming the imaginary part is not used during encryption
            // If it is used, adjust accordingly
            output[i] = (byte) real;
        }
    
        // Reverse padding
        return Arrays.copyOfRange(output, 0, output.length - paddingSize);
    }
     
    
    
    public static void main(String[] args) {

        //File Creation
        LogEntries logs = new LogEntries() ;
        
        String filePath = FileConversion.checkFileValidity();

        FileConversion convert = new FileConversion(filePath) ;
        
        //Diagnostics
        
        try {
            logs.LogCreation();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Get Master key from user.

        String key = "" ;
        Scanner scanner = new Scanner(System.in);

            do {
            System.out.print("Enter the encryption key (minimum 16 characters): ");
            key = scanner.nextLine() ;

            if (key.length() < 16) {
                System.out.println("Invalid key input. Key length too short.") ;
            }
        
        } while(key.length() < 16) ;


        System.out.println("Choose an option.\n1.Encrypt file.\n2.Decrypt file.\nOther to exit program.") ;
        int menuflag = scanner.nextInt() ;
        switch(menuflag){
        
        case 1 :

        // Encryption of File

            logs.LogStart(1);
            System.out.println("--- Encrypting ---") ;
            try {
                //Diagnostic

                Main encryption = new Main(key);
                logs.MasterKeyEntry(key);
                            
                String input ;
                
                input =  convert.readFileToString() ;                        
                logs.TextEntry(input);

                String encryptedMessage = encryption.encrypt(input);        //Encrypt File
                convert.writeStringToFile(encryptedMessage,1);         //Write String to File

                } catch (IOException e) {

                    e.printStackTrace();
                }
            
            break ;
        case 2 :
            //Decryption Process
                logs.LogStart(2);
                System.out.println("--- Decrypting ---") ;

            break; 

        default :
                scanner.close();
                break ;
    }
    
}

}
