package Exercise_1 ;
import java.lang.Math;

// Equation is a(x^2) + bx + c

public class Parabola {
    // Variable Declarations

    // Equation Coefficients
    private double par_a ;
    private double par_b ;

    // Equation Constant
    private double par_c ;

    // Equation Variable
    private double var_x ;

    // Variable Setters

    public void setPar_A (double parA) {
        par_a = parA ;
    }

    public void setPar_B (double parB) {
        par_b = parB ;
    }

    public void setPar_C (double parC) {
        par_c = parC ;
    }

    public void setVar_X (double varX) {
        var_x = varX ;
    }
    
    // Variable Getters
    
    public double getPar_A (){
        return par_a ;
        }

    public double getPar_B (){
        return par_b ;
    }

    public double getPar_C (){
        return par_c ;
    }

    public double getVar_X () {
        return var_x ;
    }
    
    // Default Constructor
    public Parabola() {
        par_a = par_b = par_c = var_x = 1.0 ;
    }

    public Parabola(double parA , double parB , double parC , double varX) {
        par_a = parA ;
        par_b = parB ;
        par_c = parC ;
        var_x = varX ;
    }

    // Methods

    public double getCalculationY (){
        return (getPar_A() * Math.pow(getVar_X(),2)) + (getPar_B() * getVar_X()) + getPar_C();
    }

    public double getExtremePointX() {
        // 1st derivate is 2ax + b

        return -(getPar_B()/(2*getPar_A())) ;
    }
    public double getExtremePointY() {
        return (getPar_A() * Math.pow(getExtremePointX(),2)) + (getPar_B() * getExtremePointX()) + getPar_C();
    }
    public double getDiscriminant() {
        return Math.pow(getPar_B(),2) - 4*(getPar_A()*getPar_C()) ;
    }

    public double getRoot1(){
        return -(getPar_B()+Math.sqrt(Math.pow((getPar_B()),2)-(4*getPar_A()*getPar_C())))/(2*getPar_A());
    }
    public double getRoot2(){
        return  -(getPar_B()-Math.sqrt(Math.pow((getPar_B()),2)-(4*getPar_A()*getPar_C())))/(2*getPar_A());
    }
    public void displayAttributes (){
        System.out.println("The parabolic function is a*x^2 + b*x + c\n") ;
        System.out.println("The attributes are :\n-a = " + getPar_A() + "\n-b = "+ getPar_B() + "\n-c = " + getPar_C()) ;
        System.out.println("-For X = " + getVar_X() + " the result of the equation is " + getCalculationY()) ;
        System.out.printf("-The extreme point of the function is [%.2f,%.2f]\n",getExtremePointX(),getExtremePointY()) ;
        System.out.println("-The polynomial discriminant is " + getDiscriminant()) ;
        System.out.printf("-Root 1 is %.2f\n",getRoot1());
        System.out.printf("-Root 2 is %.2f\n",getRoot2()) ;
    }
}