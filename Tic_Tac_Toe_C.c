/***
 This is a Tic Tac Toe game where 2 players face off each other
 Number of methods
 ***/

#include <stdio.h>
void reset_board(char *game_board,int board_length);       //This method sets the board for each round
void board_state(char *game_board,int board_length);
void clean_stdin();

void new_line(){
    printf("");
}

int main(){
    int board_length ;
    board_length = 9;
    char user_O[50];
    char user_X[50];
    char game_board[board_length] ;
    int round = 1;
    int game_tag = 0;

   //while (game_tag == 0){
        reset_board(game_board,board_length);
        printf("Welcome to Tic tac toe !");
        new_line();
        printf("\n---Round %d---\n",round) ;
        new_line();

        board_state(game_board,board_length);
        new_line();

        printf("Please enter your names.\n");
        printf("Player O : ") ;
        scanf("%c",&user_O);
        
        printf("Player X: ");
        clean_stdin();
        scanf("%c",&user_X);
 //   }
    return 0;
}

void reset_board (char *game_board,int board_length){
    for (int i = 0; i <= board_length; i++){
        game_board[i] = '-' ;
    }
}

void board_state (char *game_board,int board_length){
    printf("");
    int i = 1 ;
    while (i < 9){
        printf("%c  | %c | %c",game_board[i],game_board[i+1],game_board[i+2]) ;
        printf("\n---|---|---\n");
        i = i + 3;
    }
}

void clean_stdin(){
    int buffer;
    do {

        buffer = getchar();
    } 
    while (buffer != '\n' && buffer != EOF);          //The loop continues as long as there are characters in the input buffer

    }



