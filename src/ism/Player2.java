package ism;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;


public class Player2 {

    double exp = 1;
    double gamma = .9;
    HashMap<StateAction, Double> value = new HashMap<>();
    Random rand = new Random();
    double alpha = .3;

    
    public int chooseAction(char[] s) {
        int action = -1;
        if (Math.random() < exp) {
            action = rand.nextInt(9);
            while (s[action] != '0') {
                action = rand.nextInt(9);
            }
        } else {
            double max = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 9; i++) {

                if (s[i] == '0') {
                    StateAction current = new StateAction(s, i);
                    double res = 0;
                    if (value.containsKey(current)) {
                        res = value.get(current);
                    }
                    if (res > max) {
                        max = res;
                        action = i;
                    }
                }
            }
        }
        return action;
    }
 
    public void updateValue(char[] cur, int action, double reward, char[] next, boolean end) {
        if (end) {
            StateAction current = new StateAction(cur, action);
            value.put(current, reward);
        } else {
            double max = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < 9; i++) {

                if (next[i] == '0') {
                    StateAction current = new StateAction(next, i);
                    double res = 0;
                    if (value.containsKey(current)) {
                        res = value.get(current);
                    }
                    if (res > max) {
                        max = res;
                    }
                }
            }
            StateAction current = new StateAction(cur, action);
            double add = 0;
            if (value.containsKey(current)) {
                add = value.get(current);
            }
            value.put(current, alpha * add + (1 - alpha) * (reward + gamma * max));
        }

    }

    public void reduce_exp() {
        exp = Math.max(.1, .999 * exp);

    }

}