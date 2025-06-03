#Tic Tac Toe Game
"""
This is a simple tic tac toe game where 2 users face off against each other
Number of functions 7
"""

def reset_board(game_board):        #The Function wipes the board for each new round
    game_board={1:"1",2:"2",3:"3",
            4:"4",5:"5",6:"6",
            7:"7",8:"8",9:"9"}

    return game_board
    
def board_state(game_board):        #Print the current state of the board
    print("")
    i = 1
    while i < 9:
        print(f" {game_board[i]} | {game_board[i+1]} | {game_board[i+2]}")
        print("---|---|---")
        i = i + 3
    print("")

def input_validation(validation_tag,game_board):        #This function contains every validation need for the any user input
    
    #User start validation
        #Validates the starting shape
    if validation_tag.lower() == "user_start":
        while True:
            tag = input(">>")
            if tag.lower() =="x":           #Player X
                return True
            elif tag.lower() =="o":         #Player O
                return False
            else:
                print("Input error. Try again.")

    #Hit register
        #Validates whether the player can make that move or not
    if validation_tag.lower() == "hit_register":

            while True:
                try:
                    tag = int(input(">>"))
                    if tag <=9 or tag >= 1:
                        if game_board[tag].lower() !="x" and game_board[tag].lower() != "o":
                            break
                        else:
                            print("Position taken !\nPlease try again :")
                    else:
                        print("Number of range.\nPlease try again: ")
                except:
                    print("Invalid input (Non-readble).\nPlease try again :")

            return tag
    
    #Game end dialogue
        #Validates whether the players want another round
    if validation_tag.lower() == "game_end_dialogue":
        tag = input("Wanna play again?\n>>")
        if tag.lower == "yes" or tag.lower() == 'y':
            return True
        else:
            return False

def game_start_dialogue():          #This just prints the beginning of the game
    print("Welcome to Tic Tac Toe!")
    print("Enter your names below and decide who goes first.")
    
def game_state(user_tag,game_board):        #This is the register of each players move on the board
    if user_tag == True:       #Player X
        print("Enter your desired position:")
        user_choice = input_validation("hit_register",game_board)
        game_board[user_choice] = "X"
    else:                       #Player O
        print("Enter your desired position:")
        user_choice = input_validation("hit_register",game_board)
        game_board[user_choice] = "O"

def game_end_dialogue(user_tag,user_O,user_X):      #Dialogue speech for the end of each round
    if user_tag == True:
        print(f"---Player {user_O} has won !!---")
    else:
        print(f"---Player {user_X} has won !!---")
    
    if input_validation("game_end_dialogue",game_board) == True:
        return True
    else:
        print(f"\nYou played {round} rounds.\nThank you for playing.")
        exit()

def win_condition(game_board,round_counter):        #Win condition for each player to end the corresponding loop
    i = 1
    while i < 9:        #Horizontal lines
        if game_board[i] == game_board[i+1] == game_board[i+2] :
            return True
                        #Vertical lines
        elif game_board[i] == game_board[i+3] == game_board[i+6] :
            return True
                        #Left Diagonal line
        elif game_board[1] == game_board[5] == game_board[9]:
            return True
        elif game_board[3] == game_board[5] == game_board[7]:
            return True
        else:
            return False
        i = i + 3
    
#Main part of the program

game_flag = True
round = 1
game_board={1:"1",2:"2",3:"3",          #Dictionary in order to keep track of the cells of the board
            4:"4",5:"5",6:"6",
            7:"7",8:"8",9:"9"}

while game_flag == True:                #Loop for each new round
    print(f"----Round {round}----")
    game_start_dialogue()
    win_con = False
    board_state(game_board)
    round_counter = 1
    user_O = input("Player 1 (O): ")
    user_X = input("Player 2 (X): ")
    print(f"\nWelcome {user_O} and {user_X} !\nPlease choose who goes first by selecting the shape.")
    user_tag = input_validation("user_start",game_board)

    while win_con == False:     #Checks to see if there is a winner with every move

        round_counter = round_counter + 1
        if round_counter > 9  or win_con == True:                                  #If there is a tie this if statement will detect it and stop the iteration
            break

        
        if user_tag == False:       #Player X
            print(f"{user_O}'s Turn O")
            game_state(user_tag,game_board)
            user_tag = True
            
        elif user_tag == True:      #Player O
            print(f"{user_X}'s Turn X")
            game_state(user_tag,game_board)
            user_tag = False

        board_state(game_board)

        win_con = win_condition(game_board,round_counter)

    if round_counter > 9:                                   #End game dialogue if the round was a draw
        print("\nThe game is a draw...")
        if input_validation("game_end_dialogue",game_board) == True:
            game_flag = game_end_dialogue(user_tag,user_O,user_X)
            game_board = reset_board(game_board)
            round = round + 1
        else:
            print(f"\nYou played {round} rounds.\Thank you for playing.")
            exit()
    else:
        game_flag = game_end_dialogue(user_tag,user_O,user_X)
        game_board = reset_board(game_board)
        round = round + 1