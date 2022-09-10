import java.util.*;

public class Game {
    //default prompt
    public static void promptUser() {
        System.out.println("Please try entering data again!!");
    }

    //remove
    public static void removeCurr(String grid[][], int i, int j, String playerPawns[]) {
        String s[] = new String[playerPawns.length - 1];
        int c = 0;

        for(int m = 0; m < playerPawns.length; m++) {
            if(playerPawns[m].equals(grid[i][j].substring(2)))
                s[c++] = playerPawns[m];
        }
        playerPawns = s;
    }

    //swap
    public static void swap(String grid[][], int i, int j, int k, int l) {
        String s= grid[i][j];
        grid[i][j] = grid[k][l];
        grid[k][l] = s;
    }

    //checkmate
    public static void checkMate(String grid[][], int i, int j, int k, int l, String playerPawns[]) {
        grid[i][j] = grid[k][l];
        grid[k][l] = "-";

        removeCurr(grid, k, l, playerPawns);
    }

    //checkmove
    public static void checkMove(String grid[][], int currI, int currJ, char mov, int player, boolean c, String playerPawns[]) {
        if(player == 1) {
            if(mov == 'L' && currJ - 1 >= 0) {
                if(grid[currI][currJ-1].equals("-")) {
                    swap(grid, currI, currJ, currI, currJ - 1);
                    showGrid(grid);
                }
                else if(grid[currI][currJ-1].substring(0,2).equals("B")) {
                    checkMate(grid, currI, currJ, currI, currJ - 1, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI][currJ-1].substring(0,2).equals("A"))
                    c = false;
            } else if(mov == 'F' && currI - 1 >= 0) {
                if(grid[currI - 1][currJ].equals("-")) {
                    swap(grid, currI, currJ, currI - 1, currJ);
                    showGrid(grid);
                }
                else if(grid[currI - 1][currJ].substring(0,2).equals("B")) {
                    checkMate(grid, currI, currJ, currI - 1, currJ, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI - 1][currJ].substring(0,2).equals("A"))
                    c = false;
            }

            else if(mov == 'B' && currI + 1 < 5) {
                if(grid[currI + 1][currJ].equals("-")) {
                    swap(grid, currI, currJ, currI + 1, currJ);
                    showGrid(grid);
                }
                else if(grid[currI + 1][currJ].substring(0,2).equals("B")) {
                    checkMate(grid, currI, currJ, currI + 1, currJ, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI + 1][currJ].substring(0,2).equals("A"))
                    c = false;
            }

            else if(mov == 'R' && currJ + 1 < 5) {
                if(grid[currI][currJ + 1].equals("-")) {
                    swap(grid, currI, currJ, currI, currJ + 1);
                    showGrid(grid);
                }
                else if(grid[currI][currJ + 1].substring(0,2).equals("B")) {
                    checkMate(grid, currI, currJ, currI, currJ + 1, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI][currJ + 1].substring(0,2).equals("A"))
                    c = false;
            }
        } else {
            if(mov == 'L' && currJ - 1 >= 0) {
                if(grid[currI][currJ-1].equals("-")) {
                    swap(grid, currI, currJ, currI, currJ - 1);
                    showGrid(grid);
                }
                else if(grid[currI][currJ-1].substring(0,2).equals("A")) {
                    checkMate(grid, currI, currJ, currI, currJ - 1, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI][currJ-1].substring(0,2).equals("B"))
                    c = false;
            } else if(mov == 'B' && currI - 1 >= 0) {
                if(grid[currI - 1][currJ].equals("-")) {
                    swap(grid, currI, currJ, currI - 1, currJ);
                    showGrid(grid);
                }
                else if(grid[currI - 1][currJ].substring(0,2).equals("A")) {
                    checkMate(grid, currI, currJ, currI - 1, currJ, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI - 1][currJ].substring(0,2).equals("B"))
                    c = false;
            }

            else if(mov == 'F' && currI + 1 < 5) {
                if(grid[currI + 1][currJ].equals("-")) {
                    swap(grid, currI, currJ, currI + 1, currJ);
                    showGrid(grid);
                }
                else if(grid[currI + 1][currJ].substring(0,2).equals("A")) {
                    checkMate(grid, currI, currJ, currI + 1, currJ, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI + 1][currJ].substring(0,2).equals("B"))
                    c = false;
            }

            else if(mov == 'R' && currJ + 1 < 5) {
                if(grid[currI][currJ + 1].equals("-")) {
                    swap(grid, currI, currJ, currI, currJ + 1);
                    showGrid(grid);
                }
                else if(grid[currI][currJ + 1].substring(0,2).equals("A")) {
                    checkMate(grid, currI, currJ, currI, currJ + 1, playerPawns);
                    showGrid(grid);
                }
                else if(grid[currI][currJ + 1].substring(0,2).equals("B"))
                    c = false;
            }
        }
    }

       //movement for player
       public static void move(String grid[][], int player, String playerPawns[]) {
        Scanner sc = new Scanner(System.in);
        boolean c;
        do {
            c = true;
            System.out.print("Player"+player+"move: ");
            String mov = sc.next();
            char com = mov.charAt(mov.length() - 1);
            String p;

            if(player == 1)
                p = "A";
            else p = "B";

            for(int i = 0 ; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(grid[i][j].equals(p+"-"+mov.substring(0, 2))) {
                        checkMove(grid, i, j, com, player, c, playerPawns);
                    }
                }
            }
            

        if(c == false) {
            promptUser();
        }
    } while(c == false);
}

    //Module to take player n input
    public static String[] playerInput(int n) {
        Scanner sc = new Scanner(System.in);
        boolean c = true;
        String playerPawns[];
        do {
            c=true;
            System.out.print("Player"+n +" Input: ");

            String playerInput = sc.nextLine();

            playerPawns = playerInput.split(", ");

            if(playerPawns.length == 5) {
                for(int i =0; i < playerPawns.length; i++) {
                    if((int)playerPawns[i].charAt(1) > 5 && (int)playerPawns[i].charAt(1) < 1 && playerPawns[i].charAt(0) != 'P') {
                        c = false;
                    }
                }
            } else {
                c=false;
            }
            if(c == false) {
                promptUser();
            }
        } while (c==false);

        return playerPawns;
    }
    
    //To initialize the grid after the player enters input
    public static void intializeGrid(String grid[][], String playerInput[], int player) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(player == 1) {
                    if(i==4) {
                        grid[i][j] = "A-" + playerInput[j];
                    }
                } else {
                    if(i==0) {
                        grid[i][j] = "B-" + playerInput[j];
                    }
                }
            }
        }
    }

    //to display the grid
    public static void showGrid(String grid[][]) {
        for(int i = 0; i < 5; i++) {
            for(int j =0; j < 5; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //Initialize empty chess board
        String grid[][] = new String[5][5];

        //Fill the grid with "-"
        for(String row[] : grid)
            Arrays.fill(row, "-");

        Scanner sc = new Scanner(System.in);
        int replay;
        do {
            //Player1 Input
            String player1Input[] = playerInput(1);
            intializeGrid(grid, player1Input, 1);

            showGrid(grid);

            //Player2 Input
            String player2Input[] = playerInput(2);
            intializeGrid(grid, player2Input, 2);

            showGrid(grid);

            //startGame(grid, player1Input, player2Input);
            while(player1Input.length != 0 || player2Input.length != 0) {
                move(grid, 1, player1Input);
                move(grid, 2, player2Input);
            }

            if(player1Input.length == 0) {
                System.out.println("Player 2 Won!!");
            } else {
                System.out.println("Player 1 Won!!");
            }
            System.out.print("Dou ypu want to play again? (Yes - 1/ No - 0)?:");
            replay = sc.nextInt();
        } while(replay == 1);
    }
}