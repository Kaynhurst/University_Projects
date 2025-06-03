//Fibonacci Sequence Program

/***
 The following program gets a number from a user and returns the corresponding fibonacci sequence
 Number of methods 3
 ***/

#include <stdio.h>
void fibonacci(int user_input);     //This is the fibonacci sequence function that prints the result
int input_validation();             //This function ensures that the user enters an integer number
void clean_stdin();                 //This function cleans the input buffer required for the input validation
int main (){

    int user_input ;
    printf("This is a fibonacci sequence program, where you input a number\nand you get the corresponding sequence.\n\n") ;
    user_input = input_validation();
    fibonacci(user_input);

    return 0;
}

void fibonacci(int user_input){
    int fibonacci_array[user_input];        //Since every fibonacci sequence has the same 3 numbers this is done for convinience
    fibonacci_array[0] = 0 ;
    fibonacci_array[1] = 1;
    fibonacci_array[2] = 1 ;

    for(int i = 2; i <= user_input; i++){
        fibonacci_array[i] = fibonacci_array[i-1] + fibonacci_array[i-2];
    }

    printf("\nThe fibonacci sequence is : ");
    for (int i = 0 ; i <= user_input; i++){
        printf(" %d ",fibonacci_array[i]);
        
    }
}

int input_validation(){
    int user_input;
    int tag = 0;

    printf("Give a number: ");
    do{
        tag = scanf("%d",&user_input);
        clean_stdin();
        if (tag == 0){
            printf("Input error. Try again :");
        }
    }
    while(tag == 0);

    return user_input ;    
}

void clean_stdin(){
    int buffer;
    do {

        buffer = getchar();
    } while (buffer != '\n' && buffer != EOF);          //The loop continues as long as there are characters in the input buffer
}