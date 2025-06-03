#Fibonacci Sequence Program
"""
The following program asks the user for a number and displays the fibonacci sequence accordingly
Number of functions : 2
"""

def fibonacci(user_input):      #This function is the fibonacci sequence
    fibonacci_array = [0,1]
    for n in range(2,user_input+1):

        fibonacci_array.append(fibonacci_array[n-1] + fibonacci_array[n-2])
    return fibonacci_array

def input_validation():         #This function ensures that the user inputs a number and positive
    
    while True:
        try:
            user_input=int(input(">>"))
            if user_input > 0 :
                break           #If the number is positive then break the loop
            else:           
                print("Invalid input. Number must be positive")
                continue        #If the number is negative repeat the loop
        except:
            print("Invalid input.")
    return user_input

#Main body of the program

print("This is a fibonacci sequence calculator")
print("Enter your number below.")
user_input = input_validation()

print(f"The fibonacci sequence for {user_input} is {str(fibonacci(user_input))[1:-1]}")