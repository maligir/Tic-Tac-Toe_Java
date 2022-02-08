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
public class ISM2 {

    String board[][] = new String[3][3];
    boolean end = false;
    Player p1 = new Player();
    TTT t;
//add results to a file and read from file instead of training every time

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
            t.pause();
            int action = t.getO();
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
        int tot = 0;
        double reward = 0;
        for (int i = 0; i < 1000000; i++) {
            reset();
            while (!end) {
                char[] cur = getTable();
                int action = p1.chooseAction(getTable());
                reward = updateState(action);
                char[] next = getTable();
                p1.addReplaySet(new DataObject(cur, action, reward, next, end));
                p1.updateParameters();

            }
            p1.reduce_exp();
        }
    }

    public void play() {
        boolean cont = true;
        double reward = 0;
        while (cont) {
            reset();
            t = new TTT();
            while (!end) {
                char[] cur = getTable();
                int action = p1.chooseAction(getTable());
                t.drawX(action);
                reward = updateStat2(action);
                char[] next = getTable();
                //add (cur, action, reward, next, end) to reply set which is the dataobject should "array"/list of some sort
                //try out pytorch for java
                p1.addReplaySet(new DataObject(cur, action, reward, next, end));
                p1.updateParameters();
            }
            t.stop();
            t.close();
            t.news();
            t.winScreen((int) reward);
            t.stop();
            t.close();
        }
    }

    public static void main(String[] args) {
        ISM2 s = new ISM2();
        s.train();
        s.play();
    }
}
