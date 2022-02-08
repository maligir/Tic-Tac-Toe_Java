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
public class StateAction {

    char[] state;
    int action;

    public StateAction(char[] s, int v) {
        state = s;
        action = v;
    }

    public char[] getState() {
        return state;
    }

    public int getAction() {
        return action;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return Arrays.equals(((StateAction) o).getState(), state) && (((StateAction) o).getAction() == action);
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Arrays.hashCode(this.state);
        hash = 59 * hash + this.action;
        return hash;
    }
    @Override
    public String toString()
    {
        return Arrays.toString(state) + action + "";
    }
}
