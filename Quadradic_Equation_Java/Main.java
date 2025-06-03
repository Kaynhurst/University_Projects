package Exercise_1;
//Quadradic Equation solver

public class Main {
    public static void main(String[] args) {
        Parabola equation = new Parabola () ;

        equation.setPar_A(5.34);
        equation.setPar_B(32);
        equation.setPar_C(6.7);
        equation.setVar_X(4);

        equation.displayAttributes();
    }
}
