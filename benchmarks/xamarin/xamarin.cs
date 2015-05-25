using System;

using System.Text;

namespace xamarin
{
    public class TrialSuite
    {
        private int index = 0;
        private double[] trials;

        public TrialSuite(int length) {
            trials = new double[length];
        }

        public void addTrial(long now, long end) {
            trials[index++] = (end - now) * Math.Pow(10, -6);
        }

        public double getAverage() {
            double sum = 0.0;
            foreach (double trial in trials) {
                sum += trial;
            }
            return sum / trials.Length;
        }

        public double getHighest() {
            double highest = 0.0;
            foreach (double trial in trials) {
                if(trial > highest) highest = trial;
            }
            return highest;
        }

        public double getLowest() {
            double lowest =  Double.MaxValue;
            foreach (double trial in trials) {
                if(trial < lowest) lowest = trial;
            }
            return lowest;
        }

        public string getComaSeparatedData() {            
            var builder = new StringBuilder();
            foreach (double trial in trials) {
                builder.Append(String.Format("{0:0.000}", trial)).Append(", ");
            }
            return builder.ToString();
        }
    }
}

