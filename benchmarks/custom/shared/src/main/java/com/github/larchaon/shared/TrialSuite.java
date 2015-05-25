package com.github.larchaon.shared;

import java.text.DecimalFormat;

public class TrialSuite {

    int index = 0;
    double[] trials;

    public TrialSuite(int length) {
        trials = new double[length];
    }

    public void addTrial(long now, long end) {
        trials[index++] = (end - now) * Math.pow(10, -6);
    }

    public double getAverage() {
        double sum = 0.0;
        for (double trial : trials) {
            sum += trial;
        }
        return sum / trials.length;
    }

    public double getHighest() {
        double highest = 0.0;
        for (double trial : trials) {
            if(trial > highest) highest = trial;
        }
        return highest;
    }

    public double getLowest() {
        double lowest =  Double.MAX_VALUE;
        for (double trial : trials) {
            if(trial < lowest) lowest = trial;
        }
        return lowest;
    }

    public String getComaSeparatedData() {
        DecimalFormat formatter = new DecimalFormat("#0.000");
        StringBuilder builder = new StringBuilder();
        for (double trial : trials) {
            builder.append(formatter.format(trial)).append(", ");
        }
        return builder.toString();
    }
}
