import java.util.HashMap;

public class ComplexSBox {
    private static HashMap<Character, ComplexNumber> complexMapping = new HashMap<>();
    private static ComplexNumber[][] sBox = {
        {new ComplexNumber(0.5, 0.5), new ComplexNumber(0.7, 0.2)},
        {new ComplexNumber(0.3, 0.8), new ComplexNumber(0.6, 0.4)}
    };

    static {
        // Initialize the complex mapping for characters
        for (char c = 0; c < 128; c++) {
            double realPart = Math.random();
            double imagPart = Math.random();
            complexMapping.put(c, new ComplexNumber(realPart, imagPart));
        }
    }

    public static String scramble(String input) {
        StringBuilder scrambled = new StringBuilder();
        for (char c : input.toCharArray()) {
            ComplexNumber inputComplex = complexMapping.get(c);
            if (inputComplex != null) {
                int numRows = sBox.length;
                int numCols = sBox[0].length;

                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        if (inputComplex.equals(sBox[i][j])) {
                            ComplexNumber scrambledComplex = sBox[(i + 1) % numRows][(j + 1) % numCols];
                            scrambled.append(formatComplex(scrambledComplex));
                            break;
                        }
                    }
                }
            } else {
                // If the character is not in the complex mapping, add it as is.
                scrambled.append(c);
            }
        }
        return scrambled.toString();
    }

    private static String formatComplex(ComplexNumber complex) {
        return "(" + complex.getReal() + ", " + complex.getImaginary() + ")";
    }

}

class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ComplexNumber other = (ComplexNumber) obj;
        return real == other.real && imaginary == other.imaginary;
    }
}
