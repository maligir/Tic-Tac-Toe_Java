/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ism;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rahul
 */
public class ISM {

    String board[][] = new String[3][3];
    boolean end = false;
    Player p1 = new Player();
//add results to a file and read from file instead of training every time


    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("0")) {
                    System.out.print(" |");
                } else {
                    System.out.print(board[i][j] + "|");
                }
            }
            System.out.println("");
            if (i < 2) {
                System.out.println("-------");
            }
        }
        System.out.println("");
    }

    public char[] getTable() {
        char[] s = new char[9];
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s[count] = board[i][j].charAt(0);
                count++;
            }
        }
        return s;
    }

    public double updateState(int n) {
        double reward = 0.0;
        int count = 0;
        char s[] = getTable();
        if (!end) {
            s[n] = 'X';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = s[count] + "";
                    count++;
                }
            }
            reward = win();
        }
        if (!end) {
            Random rand = new Random();
            int action = rand.nextInt(9);
            while (s[action] != '0') {
                action = rand.nextInt(9);
            }
            s[action] = 'O';
            count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = s[count] + "";
                    count++;
                }
            }
            reward = win();
        }

        return reward;
    }

    public double updateStat2(int n) {
        int count = 0;

        char s[] = getTable();
        double reward = 0.0;
        if (!end) {
            System.out.println("AI Move: ");
            System.out.println("");

            s[n] = 'X';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = s[count] + "";
                    count++;
                }
            }
            reward = win();
            printBoard();
        }
        if (!end) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Your move: ");
            int action = scan.nextInt();
            System.out.println("");
            while (s[action] != '0') {
                System.out.println("Input a valid number");
                System.out.println("");
                System.out.print("Your move: ");
                action = scan.nextInt();
            }
            s[action] = 'O';
            count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = s[count] + "";
                    count++;
                }
                reward = win();
            }
        }
        return reward;
    }

    public double win() {
        int one = 0;
        int two = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("X")) {
                    one++;
                } else if (board[i][j].equals("O")) {
                    two++;
                }
            }
            if (one == 3) {
                end = true;
                return 1;
            } else if (two == 3) {
                end = true;
                return -1;
            } else {
                one = 0;
                two = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i].equals("X")) {
                    one++;
                } else if (board[j][i].equals("O")) {
                    two++;
                }
            }
            if (one == 3) {
                end = true;
                return 1;
            } else if (two == 3) {
                end = true;
                return -1;
            } else {
                one = 0;
                two = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][i].equals("X")) {
                one++;
            } else if (board[i][i].equals("O")) {
                two++;
            }
        }
        if (one == 3) {
            end = true;
            return 1;
        } else if (two == 3) {
            end = true;
            return -1;
        } else {
            one = 0;
            two = 0;
        }
        for (int i = 2; i >= 0; i--) {
            if (board[i][2 - i].equals("X")) {
                one++;
            } else if (board[i][2 - i].equals("O")) {
                two++;
            }
        }
        if (one == 3) {
            end = true;
            return 1;
        } else if (two == 3) {
            end = true;
            return -1;
        } else {
            one = 0;
            two = 0;
        }
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("O") || board[i][j].equals("X")) {
                    count++;
                }
            }
        }
        if (count == 9) {
            end = true;
        }
        return 0;
    }

    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "0";
            }
        }
        end = false;
    }

    public void train() {
        System.out.println("Training...");
        int tot = 0;
        double reward = 0;
        for (int i = 0; i < 1000000; i++) {
            reset();
            while (!end) {
                char[] cur = getTable();
                int action = p1.chooseAction(getTable());
                reward = updateState(action);
                char[] next = getTable();
                p1.updateValue(cur, action, reward, next, end);

            }
            p1.reduce_exp();
        }
    }

    public void play() {
        boolean cont = true;
        double reward = 0;
        while (cont) {
            reset();
            System.out.println("This is a game of tic tac toe against an AI.");
            System.out.println("Here are the positions that you can choose.");
            System.out.println("");
            System.out.println("|0|1|2|"
                    + "\n-------"
                    + "\n|3|4|5|"
                    + "\n-------"
                    + "\n|6|7|8|");
            System.out.println("");
            System.out.println("The AI is X and you are O.");
            System.out.println("LET THE GAMES BEGIN!");
            System.out.println("");
            while (!end) {
                printBoard();
                char[] cur = getTable();
                int action = p1.chooseAction(getTable());
                reward = updateStat2(action);
                char[] next = getTable();
                p1.updateValue(cur, action, reward, next, end);
            }
            System.out.println("");
            if (reward == 1) {
                System.out.println("You Lost!");
            }
            if (reward == 0) {
                System.out.println("It's a Tie!");
            }
            if (reward == -1) {
                System.out.println("You Win!");
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("If you want to play again, type yes. "
                    + "\nOtherwise, type no.");
            String answer = scan.next();
            if (answer.equalsIgnoreCase("yes")) {
                cont = true;
            } else if (answer.equalsIgnoreCase("no")) {
                cont = false;
            } else {
                System.out.println("I don't understand that. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        ISM s = new ISM();
        s.train();
        s.play();
    }
}
