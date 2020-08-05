package game;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println();
        System.out.println("Welcome to Minesweeper game!");
        System.out.println("Try your luck ;)");
        System.out.println();
        System.out.println();
        System.out.println("Please choose the Difficulty Level");
        System.out.println();
        System.out.println("Enter 0 for BEGINNER (9 * 9 Board and 10 Mines)");
        System.out.println("Enter 1 for INTERMEDIATE (16 * 16 Board and 40 Mines)");
        System.out.println("Enter 2 for ADVANCED (24 * 24 Board and 99 Mines)");

        int chosenNumber = Integer.parseInt(s.nextLine());

        int boardSize = 0;
        int totalMines = 0;


        //check for invalid number in console

        while (chosenNumber != 0 && chosenNumber != 1 && chosenNumber != 2) {
            System.out.println("Invalid number! Please choose between 0, 1 or 2 !!!");
            chosenNumber = Integer.parseInt(s.nextLine());
        }

        //board size and mine total count

        if (chosenNumber == 0) {
            boardSize = 9;
            totalMines = 10;
        } else if (chosenNumber == 1) {
            boardSize = 16;
            totalMines = 40;
        } else {
            boardSize = 24;
            totalMines = 99;
        }

        //creation of board for player

        String[][] board = new String[boardSize][boardSize];
        System.out.println("Current status of the Board:");
        for (int m = 0; m < board.length; m++) {
            for (int n = 0; n < board[0].length; n++) {
                board[m][n] = "-";
            }
        }

        //creation of an additional board collecting the mines

        String[][] secretBoard = new String[boardSize][boardSize];
        for (int m = 0; m < board.length; m++) {
            for (int n = 0; n < board[0].length; n++) {
                secretBoard[m][n] = "-";
            }
        }

        //random pasting of mines in secret board

        Random random = new Random();
        int count = 0;

        while (count < totalMines) {
            int rowNumber = random.nextInt(boardSize);
            int columnNumber = random.nextInt(boardSize);
            if (!secretBoard[rowNumber][columnNumber].equals("*")) {
                secretBoard[rowNumber][columnNumber] = "*";
                count++;
            }
        }

        // secret board with mines to check for working code!!!

//        System.out.print("  ");
//        for (int k = 0; k < secretBoard.length; k++) {
//            if (k != (secretBoard.length - 1)) {
//                System.out.print(k + " ");
//            } else {
//                System.out.println(k);
//            }
//        }
//        for (int i = 0; i < secretBoard.length; i++) {
//            System.out.print(i + " ");
//            for (int j = 0; j < secretBoard[i].length; j++) {
//                System.out.print(secretBoard[i][j] + " ");
//            }
//            System.out.println();
//        }



        //game process

        boolean gameOver = false;
        boolean firstMove = true;
        int checkerForWinner = 0;

        while (!gameOver) {
            //printed board
            System.out.print("  ");
            for (int k = 0; k < board.length; k++) {
                if (k != (board.length - 1)) {
                    System.out.print(k + " ");
                } else {
                    System.out.println(k);
                }
            }
            for (int i = 0; i < board.length; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

            //Making a move
            System.out.println("Enter your move, (row and column; with space between them)");

            String[] line = s.nextLine().split(" ");
            int row = Integer.parseInt(line[0]);
            int col = Integer.parseInt(line[1]);


            //first move check
            if (firstMove) {
                if (secretBoard[row][col].equals("*")) {
                    board[row][col] = "+";
                    secretBoard[row][col] = "+";
                    firstMove = false;
                    continue;
                }
            }

            //check for mine

            if (secretBoard[row][col].equals("*")) {
                System.out.println("Unfortunately, you stepped into a mine! You lose!");
                System.out.println("Game Over");
                gameOver = true;
                break;
            } else {
                //reflection of the step
                board[row][col] = "+";
                //showing count of mines around
                int counter = 0;
                if (row == 0 && col == 0) {
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                } else if (row == 0 && col == boardSize - 1) {
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                } else if (row == boardSize - 1 && col == 0) {
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                } else if (row == boardSize - 1 && col == boardSize - 1) {
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                } else if (row == 0) {
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col - 1].equals("*")) {
                        counter++;
                    }
                } else if (row == boardSize - 1) {
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                } else if (col == 0) {
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                } else if (col == boardSize - 1) {
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                } else {
                    if (secretBoard[row - 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row - 1][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row][col + 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col - 1].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col].equals("*")) {
                        counter++;
                    }
                    if (secretBoard[row + 1][col + 1].equals("*")) {
                        counter++;
                    }
                }

                if (counter > 0) {
                    board[row][col] = counter + "";
                } else {
                    if (row == 0 && col == 0) {
                        board[row][col + 1] = "+";
                        board[row + 1][col + 1] = "+";
                        board[row + 1][col] = "+";
                    } else if (row == 0 && col == boardSize - 1) {
                        board[row][col - 1] = "+";
                        board[row + 1][col - 1] = "+";
                        board[row + 1][col] = "+";
                    } else if (row == boardSize - 1 && col == 0) {
                        board[row][col + 1] = "+";
                        board[row - 1][col + 1] = "+";
                        secretBoard[row - 1][col] = "+";
                    } else if (row == boardSize - 1 && col == boardSize - 1) {
                        board[row][col - 1] = "+";
                        board[row - 1][col - 1] = "+";
                        board[row - 1][col] = "+";
                    } else if (row == 0) {
                        board[row][col - 1] = "+";
                        board[row + 1][col + 1] = "+";
                        board[row + 1][col] = "+";
                        board[row][col + 1] = "+";
                        board[row + 1][col - 1] = "+";
                    } else if (row == boardSize - 1) {
                        board[row][col - 1] = "+";
                        board[row - 1][col - 1] = "+";
                        board[row - 1][col] = "+";
                        board[row - 1][col + 1] = "+";
                        board[row][col + 1] = "+";
                    } else if (col == 0) {
                        board[row - 1][col] = "+";
                        board[row + 1][col + 1] = "+";
                        board[row][col + 1] = "+";
                        board[row - 1][col + 1] = "+";
                        board[row + 1][col] = "+";
                    } else if (col == boardSize - 1) {
                        board[row - 1][col] = "+";
                        board[row - 1][col - 1] = "+";
                        board[row][col - 1] = "+";
                        board[row + 1][col - 1] = "+";
                        board[row + 1][col] = "+";
                    } else {
                        board[row - 1][col - 1] = "+";
                        board[row - 1][col] = "+";
                        board[row - 1][col + 1] = "+";
                        board[row][col - 1] = "+";
                        board[row][col + 1] = "+";
                        board[row + 1][col - 1] = "+";
                        board[row + 1][col] = "+";
                        board[row + 1][col + 1] = "+";
                    }
                }

                //check for no empty spaces and possible winner
                for (int m = 0; m < board.length; m++) {
                    for (int n = 0; n < board[0].length; n++) {
                        if (board[m][n].equals("-")) {
                            if (!secretBoard[m][n].equals("*"))
                                checkerForWinner++;
                        }
                    }
                }
                if (checkerForWinner == 0) {
                    System.out.println("WINNER!!!");
                    System.out.println("Congratulations. You have won the game!");
                    break;
                } else {
                    checkerForWinner = 0;
                }
            }
        }
    }
}
