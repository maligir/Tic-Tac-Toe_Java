/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ism;

import java.util.Random;

/**
 *
 * @author rahul
 */
public class WandB {
    public double[] w;
    public double b;
    Random rand = new Random();
    public double[] getW()
    {
        return w;
    }
    public double getB()
    {
        return b;
    }
    public WandB(double[] arr, double a)
    {
        w = arr;
        b = a;
    }

    public WandB(int n)
    {
        w = new double[n];
        for (int i = 0; i < n; i++) {
            w[i] = rand.nextDouble()*2-1;
        }
        
        b = Math.random()*2-1;
    }    
}
