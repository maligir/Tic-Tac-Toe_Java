/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ism;

import java.util.Arrays;

/**
 *
 * @author rahul
 */
public class DataObject {

    double rewards;
    char current[];
    int act;
    char next[];
    boolean end;

    public double returnReward() {
        return rewards;
    }

    public char[] returnCur() {
        return current;
    }

    public char[] returnNext() {
        return next;
    }

    public int returnAction() {
        return act;
    }

    public boolean returnEnd() {
        return end;
    }

    public DataObject(char[] cur, int action, double reward, char[] n, boolean e) {
        rewards = reward;
        current = cur;
        act = action;
        next = n;
        end = e;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        DataObject d = (DataObject) o;
        return Arrays.equals(d.returnCur(), current) && act == d.returnAction() && rewards == d.returnReward() && Arrays.equals(next, d.returnNext()) && end == d.returnEnd();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.rewards) ^ (Double.doubleToLongBits(this.rewards) >>> 32));
        hash = 29 * hash + Arrays.hashCode(this.current);
        hash = 29 * hash + this.act;
        hash = 29 * hash + Arrays.hashCode(this.next);
        hash = 29 * hash + (this.end ? 1 : 0);
        return hash;
    }
}
