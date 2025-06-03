import java.util.HashMap ;
import java.util.Map;

public class SubBox {
        // S-Box Intialization
        public static Map<Character,Complex> createSubBox(String original_text){

            Map<Character , Complex> sBox = new HashMap() ;
            
            for (char c : original_text.toCharArray()){
                int complexReal = (int) c ; 
                int complexImag = original_text.indexOf(c);

                Complex charValue = new Complex(complexReal, complexImag) ;
                sBox.put(c, charValue) ;

                System.out.println("This is the character : " + c + " with the Real value of " + charValue.getReal() +
                                    " and imaginary value of " + charValue.getImaginary()) ;
            }
            System.out.println("\n----\n----\n----\nHashMap") ;
            
            sBox.forEach((key,value) -> System.out.println("Map value " + value));

            return sBox ;
        }

        // Scramble the S box 
        public static String Scramble (String input, Map<Character, Complex> sBox){
            StringBuilder output = new StringBuilder() ;
            
            for (char c : input.toCharArray()){
                if (sBox.containsKey(c)) {      //Check for the character in the S-box and convert it  
                    
                    //Debugging
                    System.out.println("--"+ c + " (ASCII) : "+ (int) c + " has been acknowledged.") ;

                    Complex charValue = sBox.get(c);
                    char newCharValue = charValue.addition(new Complex ((int) charValue.getReal(),0)) ;

                    System.out.println("\n--- new Char is : " + newCharValue) ;

                    char newChar = (char) newCharValue ;

                    output.append(newChar) ;

                    //Debugging
                    System.out.println("-- ( " + newChar + " ) is the new character") ;
                }
                else {      //Failsafe scenario where the character is not in the S-box 
                    output.append(c) ;
                }
            }

            return output.toString() ;
        }

        public static String UnScramble (String input , Map<Character, Complex> sBox) {
            
            StringBuilder output = new StringBuilder() ;

            for (char c : input.toCharArray()) {

                    Complex charValue = sBox.get(c);
                    Complex newCharValue = charValue.subtraction(new Complex (charValue.getReal(),0)) ;

                    char newChar = (char) newCharValue.getReal() ;

                    output.append(newChar) ;
                }


            
            return output.toString() ;
        }

        public static char getMapKey(Map<Character, Complex> map, Complex value) {

            for (Map.Entry <Character, Complex> entry : map.entrySet()){
                if(entry.getValue().equals(value)){
                    return entry.getKey() ;
                }
            }

            return '\0' ;
        }

        
    // Complex algebra Implementation to avoid using the Apache Library

    static class Complex {
        private int real ;
        private int imaginary ;

        public Complex(int real, int imaginary) {
                this.real = real ;
                this.imaginary = imaginary ;
        }

        // Getters and Setters


        public int getReal() {
            return real;
        }

        public void setReal(int real) {
            this.real = real;
        }

        public int getImaginary() {
            return imaginary;
        }

        public void setImaginary(int imaginary) {
            this.imaginary = imaginary;
        }

        //Operations

        //Addition
        public Complex addition (Complex other) {

            int sumReal = this.real + other.real ;
            int sumImag = this.real + other.imaginary ;

            return new Complex(sumReal, sumImag) ;
        }
        //Subtraction
        public Complex subtraction (Complex other) {

            int remReal = this.real - other.real ;
            int remImag = this.imaginary - other.imaginary ;

            return new Complex(remReal, remImag) ;

        }

        //Multiplication
        public Complex multiplication (Complex other) {
            int prodReal = (this.real * other.real) - (this.imaginary * other.imaginary) ;
            int prodImag = (this.real * other.imaginary) + (this.imaginary * other.imaginary) ;

            return new Complex(prodReal, prodImag) ;
        }

        //XOR bitwise operation
        public Complex bitwiseXOR(Complex other){

            int bitwiseReal = (int) this.real ^ other.imaginary ;
            int bitwiseImag = (int) this.imaginary ^ other.imaginary ;

                    System.out.println("------ Bitwise Operation--------\n" +
                            "Number 1 before : " + (char) this.real + " + " + this.imaginary +"i\n" +
                            "Number 2 before : " + other.real + " + " + other.imaginary +"i\n" +
                            "Result after : " + bitwiseReal + " + " + bitwiseImag +"i\n") ;

            return new Complex(bitwiseReal, bitwiseImag) ;
        }

        @Override
        public String toString() {
            return "Real: " + real + ", Imaginary: " + imaginary;
        }
    }
}
