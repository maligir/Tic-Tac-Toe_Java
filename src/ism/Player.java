package ism;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Player {

    double exp = 1;
    double gamma = .9;
    HashMap<StateAction, Double> value = new HashMap<>();
    HashMap<DataObject, Double> data = new HashMap<>();
    ArrayList<DataObject> arr = new ArrayList<>();
    Random rand = new Random();
    double alpha = .3;
    int replaySample = 5;
    WandB wb = new WandB(10);
    //0. fix x array and wandb to be 10 length with last one action
    //7. neural network will take in 10 inputs and return an output
    //6. everywhere i have wandb or parameters, replace it with neural network function
    //1. read how tensorflow works in java 
    //2. step 1 set up a neural network
    //3. train a neural network with a data set like you did with datadescent
    //4. main goal: understand tensorflow!
    //5. plot the results just like datadescent
    public int chooseAction(char[] s) {
        int action = -1;
        if (Math.random() < exp) {
            action = rand.nextInt(9);
            while (s[action] != '0') {
                action = rand.nextInt(9);
            }
        } else {
                }
        return action;
    }
       //science fair i did in middle school for high school

    public double linear_bellman_update(DataObject x, double gamma, double[] w, double b) {
        //convert to vector
        //for every state it computes Q(s',a')
        double mx = -999;
        for (double act = 0; act < 9/*or10*/; act++) {
            double wtx = 0;

            for (int i = 0; i < 9/*or 10*/; i++) {
                wtx = wtx + Double.parseDouble(x.returnNext()[i] + "") * w[i] + b;
            }
            wtx = wtx + act * w[9];
            mx = Double.max(wtx, mx);
        }
        return x.returnReward() + gamma * mx;
    }

    public double dot(double a[], double b[]) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public double[] add(double a[], double b[]) {
        double sum[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            sum[i] = a[i] + b[i];
        }
        return sum;
    }

    public WandB gradientDescent(double w[], double b, double[][] x, double y[]) {
        int n = x.length;
        double len = n;
        double yPredicted[] = new double[n];
        double learningRate = .01;
        for (int j = 0; j < n; j++) {
            yPredicted[j] = dot(w, x[j]) + b;
        }
        double gradw[] = new double[w.length];
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < n; j++) {
                gradw[i] += -(2 / len) * learningRate * x[j][i] * (y[j] - yPredicted[j]);
            }

        }

        double gradb = 0;
        for (int j = 0; j < n; j++) {
            gradb += -(2 / len) * learningRate * (y[j] - yPredicted[j]);
        }
        w = add(w, gradw);
        b = gradb + b;
        return new WandB(w, b);
    }
    public void addReplaySet(DataObject set)
    {
        arr.add(set);
    }

    //update w and b using a sample from the replay set
    public void updateParameters() {
        //-----new stuff----
        double x[][] = new double[5][10];
        double y[] = new double[5];
        //construct x and y
        for (int i = 0; i < 5; i++) {
            DataObject random = arr.get(rand.nextInt(arr.size()));
            //build x
            for (int j = 0; j < 9; j++) {
                x[i][j] = Double.parseDouble(random.returnCur()[j] + "");
            }
            y[i] = alpha * linear_bellman_update(random, gamma, wb.getW(), wb.getB()) + (1 - alpha) * wb.getW()[i] * dot(wb.getW(), x[i]) + wb.getB();
        }
        wb = gradientDescent(wb.getW(), wb.getB(), x, y);
    }

    public void reduce_exp() {
        exp = Math.max(.1, .999 * exp);

    }

}
