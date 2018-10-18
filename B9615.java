/** Jonas Improgo
 *  This program is a Tic-tac-toe game, where if a player gets 3 of their markers, X or O, diagonally,
 *  vertically, or horizontally on the board, the game is won. Otherwise, the game is a tie.
 *  
 *  Assisted with: Josh Improgo & Tim Oh
 * 
 *  Krishna CSCI 212 Fall 2018
 *  Assignment 2
 */
import java.util.Scanner;

public class B9615
{

    public static void main(String[] args)
    {
        char[][] ttToe  = initializeBoard();    //Creates array; Declares ttToe and initializes array with initializeBoard() method.
        int player = 2;                         //Player 2 Starts Game** (Changes when it reaches while loop)
        print(ttToe);                           //Prints the empty board.
        int[] nxt;                              //Declaration of int char, nxt.
        int moves = 0;                          //Counter for # of moves
        boolean complete = false;               //A statement that the status of game is incomplete.
        while(complete == false)                //A while loop that continues until the status of the game is complete (true).
        {
            moves++;                            //A counter to the number of moves made.
            player = nextPlayer(player);        //Changes to next player (From 1 to 2, and 2 to 1)** Changes to player 1 at beginning of game.
            System.out.print("[MOVE #" + moves + "] Player " + player + "'s turn. ");   //States Move# and who's turn it is.
            nxt = nextMove(ttToe);                                                      //Asks for next players input
            update(ttToe, nxt, player);                                                 //Updates input.
            
            // 3 Conditions for Completion:
            //Checks if Player 1, who has the X marker, won, and completes game if true.
            if (checkWin(ttToe, 'X') == true)
            {
                System.out.println("WIN (X): Player 1 won.");
                complete = true;
            }
            
            //Checks if Player 2, who has the O marker, won, and completes game if true.
            if (checkWin(ttToe, 'O') == true)
            {
                System.out.println("WIN (O): Player 2 won.");
                complete = true;
            }
            
            //Checks if no player wins, and all parts of the array are filled with a char.
            if (checkTie(ttToe) == true) 
            {
                System.out.println("TIE: No one won.");
                complete = true;
            }
        }
        
        System.out.println("GAME COMPLETE!");   //Stated when while loop is completed.
    }
    
    
    /**
     * Method to Initialize Game Board
     * @return Returns a 3x3 2D-Array of chars, emptied, which is signified by a space.
     */
    public static char[][] initializeBoard()
    {
        char[][] array = new char[3][3];
        //A loop to create a 3x3 array and fill it with a space.
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                array[r][c] = ' ';
            }
        }
        return array;
    }
    
    /**
     * Method which asks the users input and validates it using isValid method.
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board.
     * @return Returns an array composed of 2 integers.
     * 
     * **SCANNER DOES NOT ACCOUNT FOR OTHER DATATYPES (Strings, chars) EXCEPT FOR INTS**
     */
    public static int[] nextMove(char[][] gameArray)
    {
        int[] val = new int[2];
        //Ask for next player's input:
        System.out.print("Enter a number, 0, 1 or 2, for the row: ");
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        
        System.out.print("Enter a number, 0, 1 or 2, for the column: ");
        int column = input.nextInt();
        
        val[0] = row;
        val[1] = column;
       
        //Checks if ints given are false. 
        while (isValid(gameArray, val) == false)
        {
            //Why does it return it twice?
            System.out.print("Enter a number, 0, 1 or 2, for the row: ");
            row = input.nextInt();

            System.out.print("Enter a number, 0, 1 or 2, for the column: ");
            column = input.nextInt();

            val[0] = row;
            val[1] = column;
        }
        
        val[0] = row;
        val[1] = column;
        return val;
    }
    
    /**
     * Method to check the validity of the move the player entered; If row/col != 0, 1, 2 or if marker already exists.
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board.
     * @param inputArray Requires an array of integers, which is specifically provided by the nextMove method.
     * @return Returns true or false, depending on whether the inputArray meets the requirements.
     */
    public static boolean isValid(char[][] gameArray, int[] inputArray)
    {
        //An inputArray which is provided by nextMove (composed of only 2 ints)
        //Declares and intializes row and col into integers from nextMove
        int row = inputArray[0];
        int col = inputArray[1];
        
        //If row/col is not 0, 1 or 2, return false validity.
        if ((row > 2 || row < 0) || (col > 2 || col < 0))
        {
            System.out.println("INPUT INVALID: Number out of range.");
            return false;
        } 

        //If the array is filled with a particular character.
        if (gameArray[row][col] == 'X' || gameArray[row][col] == 'O')
        {
            System.out.println("INPUT INVALID: Box is filled.");
            return false;
        }
        
        //If row/col is 0, 1, or 2 and can be placed into an empty array, then this is valid.
        return true;
    }
    
    /**
     * A method to update the player #'s turn.
     * @param player Requires an integer to change to either 1 or 2, and vice versa. Only 2 players can play this game.
     * @return Returns the opposing number, to alternate turns.
     */
    public static int nextPlayer(int player)
    {
        if (player == 1) player = 2;
        else player = 1;
        return player;
    }

    /**
     * A method to place an X or O in an array depending on the player's number.
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board, so that a marker can be placed.
     * @param inputArray Requires an array of integers, which is specifically extracted from the nextMove method.
     * @param player Requires an integer, to correspond a marker to a player. Player 1 has 'X', Player 2 has 'O'
     */
    public static void placeXorO(char[][] gameArray, int[] inputArray, int player)
    {
        //Extracts input numbers from inputArray given by nextMove.
        int row = inputArray[0];
        int col = inputArray[1];
        
        //If player is #1, then designated marker is 'X'
        if (player == 1) gameArray[row][col] = 'X';
        
        //If player is #2, then designated marker is 'O'
        else gameArray[row][col] = 'O';
            
    }
    
    //WIN CONDITIONS
    /**
     * Method to check if move makes the player win (Not all chars full, but if win conditions are met) ***
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board, so that a set of markers can be checked.
     * @param a Requires a char, specifically 'X' or 'O' to check if this particular letter has won.
     * @return Returns true if there are 3 of the same letters that are consecutive, false if not.
     */
    public static boolean checkWin(char[][] gameArray, char a)
    {
        //Checks the array of the board. 
        for (int r = 0; r < 3; r++)
        {
            //Number of consecutive chars (Horizontal Win Condition)
            int num = 0;
            for (int c = 0; c < 3; c++)
            {
                if (gameArray[r][c] == a)
                    num+=1;
            }
            if (num == 3) return true;
            
            //Assumed Horizontal is false; Reset num of consecutive chars; Check Vertical Win Condition
            num = 0;
            for (int c = 0; c < 3; c++)
            {
                if (gameArray[c][r] == a)
                    num+=1;
            }
            if (num == 3) return true;
            
            //Assumed Vertical is false; Reset num of consecutive chars; Check Down Diagonal Win Condition
            num = 0;
            for (int c = 0; c < 3; c++)
            {
                if (gameArray[c][c] == a)
                    num+=1;
            }
            if (num == 3) return true;
        }
        
        //Assumed D-Diagonal is false; Check Up Diagonal Win Condition
        int num = 0;
        for (int r = 0, c = 2; c >= 0; r++, c--)
        {
            if (gameArray[r][c] == a)
                num+=1;
            if (num == 3) return true;
        }
        

        return false;
    }

    
    /**
     * Method to check if the game is a tie. (All chars are full, and win conditions not met)
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board, to check if it is full or empty.
     * @return Returns true if the array is filled (Full of X's and O's) or false if there is still an eligible move (a char that has ' ')
     */
    public static boolean checkTie(char[][] gameArray)
    {
        //Checks to see if there is still an empty char in the array. If not, game is not finished.
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                if (gameArray[r][c] ==  ' ') return false;
            }
        }
        return true;
    }


    
    /**
     * Refers to and prints the Tic-tac-toe game board.
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board, so that it can be printed in the console.
     */
    public static void print(char[][] gameArray)
    { 
        System.out.println("   [0]|[1]|[2]");
        System.out.println("[0] " + gameArray[0][0] + " | " + gameArray[0][1] + " | " + gameArray[0][2]);
        System.out.println("   -----------");
        System.out.println("[1] " + gameArray[1][0] + " | " + gameArray[1][1] + " | " + gameArray[1][2]);
        System.out.println("   -----------");
        System.out.println("[2] " + gameArray[2][0] + " | " + gameArray[2][1] + " | " + gameArray[2][2]);
    }
    
    
    //Printing the board (With updated information)
    
    /**
     * Updates the game by showing the updated information using the print method after each player makes a move.
     * @param gameArray Requires a 2D array, specifically the Tic-Tac-Toe game board, so it can update the moves on the board.
     * @param inputArray Requires an array of integers, which is specifically provided by the nextMove method.
     * @param player Requires a player number to update the correct marker.
     */
    public static void update(char[][] gameArray, int[] inputArray, int player)
    {
        //Updates information by placing 'X' or 'O' depending on the player's number.
        placeXorO(gameArray, inputArray, player);
        //After placing in a void method, prints array.
        print(gameArray);
    }
    
}
