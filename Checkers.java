// Madiha Fatima & Selena Tang
// Period 6
// CS2 Final Project - Checkers

// This program will allow 2 players to play a game of Checkers

import java.util.Scanner;

public class Checkers{
    public static void main(String args[])
    throws java.lang.InterruptedException{
        Scanner sc = new Scanner(System.in);

        // Declare and initialize the variables
        String directions;
        int noCount=8;
        boolean direct=false;
        String hidden[][]=new String [9][9];  //8 rows, 8 columns
        String seen[][]=new String [9][9];  //8 rows, 8 columns but it's for what the player sees
        int chosenSRow, intChosenSColumn, chosenERow, intChosenEColumn;
        int turn=0;

        // turn=0 (black is playing), when turn=1 white is playing
        String chosenSColumn, chosenEColumn;
        boolean whiteCanProceed = false;
        boolean blackCanProceed = false;
        boolean finished=false;
        int blackPiecesRemaining, whitePiecesRemaining;

        // Directions
        System.out.println("Welcome to Checkers!\nPlease read these directions and indicate whether or not you and your opponent understand the directions before playing.\n");
        while(direct==false){
            System.out.println("Directions:\n~Checkers is a 2-player game which black on the upper half and white on the lower half (typically black vs red; this game will be black vs white). Each player has 12 pieces.\n~Pieces can only move in a forward diagonal direction, one space at a time\n\t(reference: in chess, a bishop can move along its diagonal as many spaces/rows as it'd like but in checkers, it can only be one space at a time unless...)");
            Thread.sleep(1000);
            System.out.println("\nHow to win:\n~Capture all of your opponent's pieces or block all of their pieces from moving. If your opponent's piece is in an adjacent space diagonal to and in front of one\n of your pieces and the next space in that diagonal is open, your piece MUST jump over the opponent's piece and 'kill' that piece.\n\tThis is called capturing a piece. Any time that you have a chance to capture a piece, you MUST capture it.");

            Thread.sleep(1000);
            System.out.println("\tIn our version of the game, there will be no forced jumps.");

            Thread.sleep(1000);
            System.out.println("~As previously mentioned, pieces can only move forward diagonally unless... the piece reaches the very last row. Then the piece becomes a king and is able to move\n forwards and backwards, diagonally!");
            System.out.println("\tA king can be captured like a regular piece.");
            Thread.sleep(1000);
            System.out.println("\nAgain, you win by capturing all of your opponent's pieces or by blocking all their pieces, leaving them with no other moves and needing to forfeit.");
            System.out.print("\nDo both of you understand the directions? (Y/N) ");
            directions = sc.next();

            if(directions.equalsIgnoreCase("Y")){
                System.out.print("\f");
                break;
            }
            else{
                noCount++;
                if(noCount==10){
                    System.out.println("\nYou have struggled to understand the directions for this game 10 times. I encourage you to play an easier game instead.\n\n\tGood-bye!");
                    direct =true;
                    break;
                }
                System.out.print("\f");
                Thread.sleep(1500);
            }
        }

        if (direct==true){
            System.exit(0);
        }

        System.out.print("Enter Player One's Name: ");
        String playerOneName = sc.next();

        System.out.print("Enter Player Two's Name: ");
        String playerTwoName = sc.next();

        Thread.sleep(1500);
        System.out.print("\f");

        //Hidden array key: 0=empty, 1=black pawn, 2=white pawn, 3=black king, 4=white king
        //Initialize arrays
        for(int row=0; row<hidden.length; row++){   //defaults everything to '0 ' aka empty
            for(int column=0; column<hidden[row].length; column++){
                hidden[row][column]="0 ";
            }
        }

        for(int row=0; row<hidden.length; row++){ 
            hidden[row][0]=row + "\t";
            for(int column=1; column<hidden[0].length; column++){
                hidden[0][column]=column + " ";
            }
        } // this tabs/formats the hidden array

        for(int column=1; column<hidden[0].length; column+=2){       //these pieces start from column 1 in-game
            hidden[1][column]="1 ";
            hidden[3][column]="1 ";
            hidden[7][column]="2 ";
        }

        for(int column=2; column<hidden[0].length; column+=2){       //these pieces start from column 2 in-game
            hidden[2][column]="1 ";
            hidden[6][column]="2 ";
            hidden[8][column]="2 ";
        }

        //Special characters: black piece - ♟, white piece - ♙, black king - ♚, white king - ♕ CAN REMOVE THIS LINE AT THE END
        while (finished==false){

            // promotes pieces to king
            for (int g=1; g<9; g++){
                if (hidden[8][g].equals("1 ")){
                    hidden[8][g]="3 ";
                }

                if (hidden[1][g].equals("2 ")){
                    hidden[1][g]="4 ";
                }
            }

            //Thread.sleep(400);

            System.out.println("Scoreboard:\n");

            blackPiecesRemaining=0;
            whitePiecesRemaining=0;

            // counts # of pieces remaining
            for(int row=1; row<hidden.length; row++){
                for(int column=1; column<hidden[row].length; column++){
                    if (hidden[row][column].equals("1 ") || hidden[row][column].equals("3 ")){
                        blackPiecesRemaining++;
                    }
                    else if (hidden[row][column].equals("2 ") || hidden[row][column].equals("4 ")){
                        whitePiecesRemaining++;
                    }
                    else{
                    }
                }
            }

            if (blackPiecesRemaining==0){
                System.out.println("White wins! Congratulations!");
                System.exit(0);
            }
            else if (whitePiecesRemaining==0){
                System.out.println("Black wins! Congratulations!");
                System.exit(0);
            }
            else{}

            System.out.println(playerOneName+"'s (Black) Pieces Remaining: " +blackPiecesRemaining);
            System.out.println(playerTwoName+"'s (White) Pieces Remaining: " +whitePiecesRemaining);
            System.out.println();

            for (int i=0; i<seen.length; i++) {     //seen board
                for (int j=0; j<seen[i].length; j++){  

                    if (hidden[i][j].equals("0 ")) {
                        seen[i][j]="  "; }
                    else if (hidden[i][j].equals("1 ")){       //black piece
                        seen[i][j]="♟"; } 
                    else if (hidden[i][j].equals("2 ")){       //white piece
                        seen[i][j]="♙"; }
                    else if (hidden[i][j].equals("3 ")){       //black king
                        seen[i][j]="♚"; }
                    else if (hidden[i][j].equals("4 ")){       //white king (queen but less filled in than king)
                        seen[i][j]="♕"; }
                }
            }

            // row and column markings

            seen[1][0]="   1    ";   //3 spaces # 4 spaces
            seen[2][0]="   2    ";
            seen[3][0]="   3    ";
            seen[4][0]="   4    ";
            seen[5][0]="   5    ";
            seen[6][0]="   6    ";
            seen[7][0]="   7    ";
            seen[8][0]="   8    ";

            seen[0][0]="\t";
            seen[0][1]="A ";
            seen[0][2]="B ";
            seen[0][3]="C ";
            seen[0][4]="D ";
            seen[0][5]="E ";
            seen[0][6]="F ";
            seen[0][7]="G ";
            seen[0][8]="H ";

            /*System.out.println("Hidden Array");
            for(int row=0; row<hidden.length; row++){
            for(int column=0; column<hidden[row].length; column++){
            System.out.print(hidden[row][column]);
            }
            System.out.println();
            } // prints out array */

            System.out.println("\tCurrent Board:\n");
            for(int row=0; row<hidden.length; row++){
                for(int column=0; column<hidden[row].length; column++){
                    System.out.print(seen[row][column]);
                }
                System.out.println();
            } // prints out array

            if(turn==0){
                System.out.println("\n" + playerOneName +"'s (Black) Piece's Turn:\n");
            }
            else{
                System.out.println("\n" + playerTwoName +"'s (White) Piece's Turn:\n");
            }

            // start location for movement
            System.out.print("Which piece would you like to move? ");

            System.out.print("\nEnter its row #: ");
            chosenSRow = sc.nextInt();

            System.out.print("Enter its column letter: "); 
            chosenSColumn = sc.next();

            intChosenSColumn = 0;

            switch (chosenSColumn){ // makes column numerical
                case "A": intChosenSColumn=1;
                break;

                case "a": intChosenSColumn=1;
                break;

                case "B": intChosenSColumn=2;                      
                break;

                case "b": intChosenSColumn=2;                      
                break;

                case "C": intChosenSColumn=3;
                break;

                case "c": intChosenSColumn=3;
                break;

                case "D": intChosenSColumn=4;
                break;

                case "d": intChosenSColumn=4;
                break;

                case "E": intChosenSColumn=5;
                break;

                case "e": intChosenSColumn=5;
                break;

                case "F": intChosenSColumn=6;
                break;

                case "f": intChosenSColumn=6;
                break;

                case "G": intChosenSColumn=7;
                break;

                case "g": intChosenSColumn=7;
                break;

                case "H": intChosenSColumn=8;
                break;

                case "h": intChosenSColumn=8;
                break;
            }

            // end location for movement
            System.out.print("Where do you want your piece to move?");

            System.out.print("\nEnter its row #: ");
            chosenERow = sc.nextInt();

            System.out.print("Enter its column letter: "); 
            chosenEColumn = sc.next();

            intChosenEColumn = 0;

            switch (chosenEColumn){ // makes column numerical
                case "A": intChosenEColumn=1;
                break;

                case "a": intChosenEColumn=1;
                break;

                case "B": intChosenEColumn=2;                      
                break;

                case "b": intChosenEColumn=2;                      
                break;

                case "C": intChosenEColumn=3;
                break;

                case "c": intChosenEColumn=3;
                break;

                case "D": intChosenEColumn=4;
                break;

                case "d": intChosenEColumn=4;
                break;

                case "E": intChosenEColumn=5;
                break;

                case "e": intChosenEColumn=5;
                break;

                case "F": intChosenEColumn=6;
                break;

                case "f": intChosenEColumn=6;
                break;

                case "G": intChosenEColumn=7;
                break;

                case "g": intChosenEColumn=7;
                break;

                case "H": intChosenEColumn=8;
                break;

                case "h": intChosenEColumn=8;
                break;
            }

            if(turn==0){
                blackCanProceed = BlackTurn(chosenSRow,intChosenSColumn,chosenERow,intChosenEColumn, hidden);
                whiteCanProceed = false;
            }
            else{
                whiteCanProceed = WhiteTurn(chosenSRow,intChosenSColumn,chosenERow,intChosenEColumn, hidden);
                blackCanProceed=false;
            }
            if((turn==0 && ((hidden[chosenSRow][intChosenSColumn]).equals("2 ")||(hidden[chosenSRow][intChosenSColumn]).equals("4 ")))||(turn==1 && ((hidden[chosenSRow][intChosenSColumn]).equals("1 ")||(hidden[chosenSRow][intChosenSColumn]).equals("3 "))))
                System.out.println("\nThis is the other player's piece.");
            if((hidden[chosenSRow][intChosenSColumn]).equals("0 ")){
                System.out.println("\nYou do not have a piece here.");
            }

            if (turn==0 && ((hidden[chosenSRow][intChosenSColumn]).equals("1 "))){
                // black piece moves
                if ((hidden[chosenSRow][intChosenSColumn]).equals("0 ")){
                    System.out.println("\nYou do not have a piece here.");
                }
                else if ((hidden[chosenSRow][intChosenSColumn]).equals("2 ") || (hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                    System.out.println("\nThis is the other player's piece.");
                }
                else{
                    // correct start location
                    if(!(hidden[chosenERow][intChosenEColumn]).equals("0 ")){
                        System.out.println("\nThis space is already occupied.");
                    }
                    else if (((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn+1))) || ((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn-1)))){
                        // normal diagonal move
                        if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                            hidden[chosenERow][intChosenEColumn]="1 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                            hidden[chosenERow][intChosenEColumn]="3 ";
                        }
                        else{
                            System.out.println("\nOk something went really wrong.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=1;
                        blackCanProceed = false;
                        System.out.println("\f");   //resets the board
                    }
                    else if(((chosenERow==(chosenSRow+2)) && (intChosenEColumn==(intChosenSColumn+2))) || ((chosenERow==(chosenSRow+2)) && (intChosenEColumn==(intChosenSColumn-2)))){
                        // killing/jumping move
                        if((hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow+2][intChosenSColumn+2])) {

                            if((hidden[chosenSRow+1][intChosenSColumn+1]).equals("2 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow+1][intChosenSColumn+1]).equals("4 ")){

                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOk something went really wrong.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }

                        }
                        else if ((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn+2))){

                            if ((hidden[chosenSRow-1][intChosenSColumn+1]).equals("2 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("4 ")){

                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }
                        }

                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                    }
                    else {
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }

                }

            }
            else if (turn==0 && ((hidden[chosenSRow][intChosenSColumn]).equals("3 "))){
                // black king moves
                if ((hidden[chosenSRow][intChosenSColumn]).equals("0 ")){
                    System.out.println("\nYou do not have a piece here.");
                }
                else if ((hidden[chosenSRow][intChosenSColumn]).equals("2 ") || (hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                    System.out.println("\nThis is the other player's piece.");
                }
                else{
                    // correct start location
                    if(!(hidden[chosenERow][intChosenEColumn]).equals("0 ")){
                        System.out.println("\nThis space is already occupied.");

                    }
                    else if (((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn+1))) || ((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn-1)))){
                        // normal diagonal move
                        if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                            hidden[chosenERow][intChosenEColumn]="1 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                            hidden[chosenERow][intChosenEColumn]="3 ";
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=1;
                        blackCanProceed = false;
                    }
                    else if (((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn+1))) || ((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn-1)))){
                        // backwards diagonal move
                        if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                            hidden[chosenERow][intChosenEColumn]="1 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                            hidden[chosenERow][intChosenEColumn]="3 ";
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=1;
                        blackCanProceed = false;
                    }
                    else if(((chosenERow==(chosenSRow+2)) && (intChosenEColumn==(intChosenSColumn+2))) || ((chosenERow==(chosenSRow+2)) && (intChosenEColumn==(intChosenSColumn-2)))){
                        // killing/jumping move
                        if((hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow+2][intChosenSColumn+2])) {

                            if((hidden[chosenSRow+1][intChosenSColumn+1]).equals("2 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow+1][intChosenSColumn+1]).equals("4 ")){

                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";

                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }

                        }
                        else if ((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn+2))){

                            if ((hidden[chosenSRow-1][intChosenSColumn+1]).equals("2 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("4 ")){

                                if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                    hidden[chosenERow][intChosenEColumn]="1 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                    hidden[chosenERow][intChosenEColumn]="3 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=1;
                                blackCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("PANIK");
                            }
                        }
                        else if(((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn+2))) || ((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn-2)))){
                            // killing/jumping move
                            if ((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn+2))){

                                if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("2 ")){

                                    if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                        hidden[chosenERow][intChosenEColumn]="1 ";
                                    }
                                    else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                        hidden[chosenERow][intChosenEColumn]="3 ";
                                    }
                                    else{
                                        System.out.println("\nOh no, we're experiencing technical difficulties.");

                                    }
                                    hidden[chosenSRow][intChosenSColumn]="0 ";
                                    hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                    turn=1;
                                    blackCanProceed = false;
                                    System.out.println("\f");   //resets the board
                                }
                                else if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("4 ")){

                                    if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                        hidden[chosenERow][intChosenEColumn]="1 ";
                                    }
                                    else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                        hidden[chosenERow][intChosenEColumn]="3 ";
                                    }
                                    else{
                                        System.out.println("\nOh no, we're experiencing technical difficulties.");
                                    }
                                    hidden[chosenSRow][intChosenSColumn]="0 ";
                                    hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                    turn=1;
                                    blackCanProceed = false;
                                    System.out.println("\f");   //resets the board
                                }
                                else{
                                    System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                                }

                            }
                            else if ((chosenERow==(chosenSRow-2)) && (intChosenEColumn==(intChosenSColumn-2))){

                                if ((hidden[chosenSRow-1][intChosenSColumn-1]).equals("2 ")){
                                    if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                        hidden[chosenERow][intChosenEColumn]="1 ";
                                    }
                                    else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                        hidden[chosenERow][intChosenEColumn]="3 ";
                                    }
                                    else{
                                        System.out.println("\nOh no, we're experiencing technical difficulties.");
                                    }
                                    hidden[chosenSRow][intChosenSColumn]="0 ";
                                    hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                    turn=1;
                                    blackCanProceed = false;
                                    System.out.println("\f");   //resets the board
                                }
                                else if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("4 ")){

                                    if ((hidden[chosenSRow][intChosenSColumn].equals("1 "))){
                                        hidden[chosenERow][intChosenEColumn]="1 ";
                                    }
                                    else if ((hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                                        hidden[chosenERow][intChosenEColumn]="3 ";
                                    }
                                    else{
                                        System.out.println("\nOh no, we're experiencing technical difficulties.");
                                    }
                                    hidden[chosenSRow][intChosenSColumn]="0 ";
                                    hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                    turn=1;
                                    blackCanProceed = false;
                                    System.out.println("\f");   //resets the board
                                }
                                else{
                                    System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                                }
                            }
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                    }

                    else if(hidden[chosenSRow][intChosenSColumn].equals("1 ") && (!(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn+1]) || !(hidden[chosenERow][intChosenEColumn].equals(hidden[chosenSRow+1][intChosenSColumn+1])))){
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }
                    else if(hidden[chosenSRow][intChosenSColumn].equals("3 ") && (!(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn-1]) || !(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn+1]) || !(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow+1][intChosenSColumn+1]) || !(hidden[chosenERow][intChosenEColumn].equals(hidden[chosenSRow+1][intChosenSColumn-1])))){
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }

                }

            }
            else if (turn==1 && ((hidden[chosenSRow][intChosenSColumn]).equals("2 "))){
                // white piece moves
                if ((hidden[chosenSRow][intChosenSColumn]).equals("0 ")){
                    System.out.println("\nYou do not have a piece here.");
                }
                else if ((hidden[chosenSRow][intChosenSColumn]).equals("1 ") || (hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                    System.out.println("\nThis is the other player's piece.");
                }
                else{

                    if(!(hidden[chosenERow][intChosenEColumn]).equals("0 ")){
                        System.out.println("\nThis space is already occupied.");
                    }
                    else if (((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn-1))) || ((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn+1)))){

                        if ((hidden[chosenSRow][intChosenSColumn]).equals("2 ")){
                            hidden[chosenERow][intChosenEColumn]="2 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                            hidden[chosenERow][intChosenEColumn]="4 ";
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=0;
                        whiteCanProceed = false;
                        System.out.println("\f");   //resets the board
                    }
                    else if(((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn-2))||((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn+2))){
                        // killing/jumping move
                        if((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn-2)){

                            if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("1 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                turn=0;        
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                turn=0;  
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }

                        }
                        else if((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn+2)){
                            if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("1 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=0;
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=0;     
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                    }

                    else if(!(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn-1]) || !(hidden[chosenERow][intChosenEColumn].equals(hidden[chosenSRow+1][intChosenSColumn-1]))){
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }
                }

            }
            else if (turn==1 && ((hidden[chosenSRow][intChosenSColumn]).equals("4 "))){
                // white king moves
                if ((hidden[chosenSRow][intChosenSColumn]).equals("0 ")){
                    System.out.println("\nYou do not have a piece here.");
                }
                else if ((hidden[chosenSRow][intChosenSColumn]).equals("1 ") || (hidden[chosenSRow][intChosenSColumn]).equals("3 ")){
                    System.out.println("\nThis is the other player's piece.");
                }
                else{

                    if(!(hidden[chosenERow][intChosenEColumn]).equals("0 ")){
                        System.out.println("\nThis space is already occupied.");
                    }
                    else if (((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn-1))) || ((chosenERow==(chosenSRow-1))&& (intChosenEColumn==(intChosenSColumn+1)))){
                        // normal moves
                        if ((hidden[chosenSRow][intChosenSColumn]).equals("2 ")){
                            hidden[chosenERow][intChosenEColumn]="2 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                            hidden[chosenERow][intChosenEColumn]="4 ";
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=0;
                        whiteCanProceed = false;
                        System.out.println("\f");   //resets the board
                    }
                    else if (((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn-1))) || ((chosenERow==(chosenSRow+1))&& (intChosenEColumn==(intChosenSColumn+1)))){
                        // normal moves
                        if ((hidden[chosenSRow][intChosenSColumn]).equals("2 ")){
                            hidden[chosenERow][intChosenEColumn]="2 ";
                        }
                        else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                            hidden[chosenERow][intChosenEColumn]="4 ";
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                        hidden[chosenSRow][intChosenSColumn]="0 ";
                        turn=0;
                        whiteCanProceed = false;
                        System.out.println("\f");   //resets the board
                    }
                    else if(((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn-2))||((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn+2))){
                        // jumping/killing moves
                        if((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn-2)){

                            if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("1 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                turn=0;        
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn-1]="0 ";
                                turn=0;  
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }

                        }
                        else if((chosenERow==chosenSRow-2)&&(intChosenEColumn==intChosenSColumn+2)){
                            if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("1 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=0;
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow-1][intChosenSColumn+1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow-1][intChosenSColumn+1]="0 ";
                                turn=0;     
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                    }
                    else if(((chosenERow==chosenSRow+2)&&(intChosenEColumn==intChosenSColumn+2))||((chosenERow==chosenSRow+2)&&(intChosenEColumn==intChosenSColumn-2))){
                        // jumping/killing moves backwards
                        if((chosenERow==chosenSRow+2)&&(intChosenEColumn==intChosenSColumn+2)){

                            if((hidden[chosenSRow-1][intChosenSColumn-1]).equals("1 ")){

                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=0;        
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow+1][intChosenSColumn+1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn+1]="0 ";
                                turn=0;  
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }

                        }
                        else if((chosenERow==chosenSRow+2)&&(intChosenEColumn==intChosenSColumn-2)){
                            if((hidden[chosenSRow+1][intChosenSColumn-1]).equals("1 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn-1]="0 ";
                                turn=0;
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else if((hidden[chosenSRow+1][intChosenSColumn-1]).equals("3 ")){
                                if ((hidden[chosenSRow][intChosenSColumn].equals("2 "))){
                                    hidden[chosenERow][intChosenEColumn]="2 ";
                                }
                                else if ((hidden[chosenSRow][intChosenSColumn]).equals("4 ")){
                                    hidden[chosenERow][intChosenEColumn]="4 ";
                                }
                                else{
                                    System.out.println("\nOh no, we're experiencing technical difficulties.");
                                }
                                hidden[chosenSRow][intChosenSColumn]="0 ";
                                hidden[chosenSRow+1][intChosenSColumn-1]="0 ";
                                turn=0;     
                                whiteCanProceed = false;
                                System.out.println("\f");   //resets the board
                            }
                            else{
                                System.out.println("\nOh dear.. you don't have an opponent to kill here.");
                            }
                        }
                        else{
                            System.out.println("\nOh no, we're experiencing technical difficulties.");
                        }
                    }

                    else if(hidden[chosenSRow][intChosenSColumn].equals("2 ") && (!(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn-1]) || !(hidden[chosenERow][intChosenEColumn].equals(hidden[chosenSRow+1][intChosenSColumn-1])))){
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }

                    else if(hidden[chosenSRow][intChosenSColumn].equals("4 ") && (!(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn-1]) || !(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow-1][intChosenSColumn+1]) || !(hidden[chosenERow][intChosenEColumn]).equals(hidden[chosenSRow+1][intChosenSColumn+1]) || !(hidden[chosenERow][intChosenEColumn].equals(hidden[chosenSRow+1][intChosenSColumn-1])))){
                        System.out.println("\nBuddy, have you read the rules? You can't move that way!");
                    }
                }

            }
        }
    }

    public static boolean BlackTurn(int chosenSRowMETH, int intChosenSColumnMETH, int chosenERowMETH, int intChosenEColumnMETH, String space[][]){      //checks if black is able to make certain moves
        if ((space[chosenSRowMETH][intChosenSColumnMETH]).equals("0 ")){
            return false;
        }
        else if ((space[chosenSRowMETH][intChosenSColumnMETH]).equals("2 ") || (space[chosenSRowMETH][intChosenSColumnMETH]).equals("4 ")){
            return false;
        }
        else{

            if(!(space[chosenERowMETH][intChosenEColumnMETH]).equals("0 ")){
                return false;
            }
            else if (((chosenERowMETH==(chosenSRowMETH+1))&& (intChosenEColumnMETH==(intChosenSColumnMETH+1))) || ((chosenERowMETH==(chosenSRowMETH-1))&& (intChosenEColumnMETH==(intChosenSColumnMETH+1)))){
                return true;
            }

            else if(((chosenERowMETH==(chosenSRowMETH+2)) && (intChosenEColumnMETH==(intChosenSColumnMETH+2))) || ((chosenERowMETH==(chosenSRowMETH+2)) && (intChosenEColumnMETH==(intChosenSColumnMETH-2)))){

                if ((chosenERowMETH==(chosenSRowMETH-2)) && (intChosenEColumnMETH==(intChosenSColumnMETH+2))){

                    if((space[chosenSRowMETH+1][intChosenSColumnMETH+1]).equals("2 ") || (space[chosenSRowMETH+1][intChosenSColumnMETH+1]).equals("4 ")){
                        return true; 
                    }
                    else{
                        return false;
                    }

                }
            }
            else if ((chosenERowMETH==(chosenSRowMETH-2)) && (intChosenEColumnMETH==(intChosenSColumnMETH+2))){

                if ((space[chosenSRowMETH-1][intChosenSColumnMETH+1]).equals("2 ")){

                    if ((space[chosenSRowMETH][intChosenSColumnMETH].equals("1 ")) || (space[chosenSRowMETH][intChosenSColumnMETH]).equals("3 ")){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else if((space[chosenSRowMETH-1][intChosenSColumnMETH+1]).equals("4 ")){

                    if ((space[chosenSRowMETH][intChosenSColumnMETH].equals("1 ")) || (space[chosenSRowMETH][intChosenSColumnMETH]).equals("3 ")){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    return false;
                }

            }

            else{
                return false;
            }
            return false;
        }
    }

    public static boolean WhiteTurn(int chosenSRowMETH2, int intChosenSColumnMETH2, int chosenERowMETH2, int intChosenEColumnMETH2, String space[][]){      //checks if white is able to make certain moves
        if ((space[chosenSRowMETH2][intChosenSColumnMETH2]).equals("0 ")){
            return false;
        }
        else if ((space[chosenSRowMETH2][intChosenSColumnMETH2]).equals("1 ") || (space[chosenSRowMETH2][intChosenSColumnMETH2]).equals("3 ")){
            return false;
        }
        else{

            if(!(space[chosenERowMETH2][intChosenEColumnMETH2]).equals("0 ")){
                return false;
            }
            else if (((chosenERowMETH2==(chosenSRowMETH2-1))&& (intChosenEColumnMETH2==(intChosenSColumnMETH2-1))) || ((chosenERowMETH2==(chosenSRowMETH2-1))&& (intChosenEColumnMETH2==(intChosenSColumnMETH2+1)))){
                return true;
            }

            if(((chosenERowMETH2==chosenSRowMETH2-2)&&(intChosenEColumnMETH2==intChosenSColumnMETH2-2))||((chosenERowMETH2==chosenSRowMETH2+2)&&(intChosenEColumnMETH2==intChosenSColumnMETH2-2))){

                if((chosenERowMETH2==chosenSRowMETH2-2)&&(intChosenEColumnMETH2==intChosenSColumnMETH2-2)){

                    if((space[chosenSRowMETH2-1][intChosenSColumnMETH2-1]).equals("1 ") || (space[chosenSRowMETH2-1][intChosenSColumnMETH2-1]).equals("3 ")){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
            }
            else if((chosenERowMETH2==chosenSRowMETH2-2)&&(intChosenEColumnMETH2==intChosenSColumnMETH2-2)||(chosenERowMETH2==chosenSRowMETH2-2)&&(intChosenEColumnMETH2==intChosenSColumnMETH2+2) ){

                if ((space[chosenSRowMETH2+1][intChosenSColumnMETH2-1]).equals("1 ")){
                    if ((space[chosenSRowMETH2][intChosenSColumnMETH2].equals("2 ")) || (space[chosenSRowMETH2][intChosenSColumnMETH2]).equals("4 ")){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else if((space[chosenSRowMETH2-1][intChosenSColumnMETH2+1]).equals("3 ")){

                    if ((space[chosenSRowMETH2][intChosenSColumnMETH2].equals("2 ")) || (space[chosenSRowMETH2][intChosenSColumnMETH2]).equals("4 ")){
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    return false;
                }
            }

            else{
                return false;
            }
            return false;
        }
    }
}
