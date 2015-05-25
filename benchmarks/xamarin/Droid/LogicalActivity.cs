
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Java.Lang;
using Java.Interop;

namespace xamarin.Droid
{
    [Activity(Label = "LogicalActivity")]			
    public class LogicalActivity : Activity
    {
        private TextView benchmarkResult;
        private Button runBtn;

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            SetContentView(Resource.Layout.activity_logical);
                       
            benchmarkResult = FindViewById<TextView>(Resource.Id.benchmark_result_tv);
            runBtn = FindViewById<Button>(Resource.Id.run_btn);
        }

        [Export("run")]
        public void run(View view) {          
            runBtn.Enabled = false;
            TrialSuite ts = new TrialSuite(15000);
            int warmupTimes = 1500;
            for (int times = 0; times <= warmupTimes + 15000; times++) {
                long now = JavaSystem.NanoTime();

                long sum = testableMethod();

                long end = JavaSystem.NanoTime();
                if (times > warmupTimes) ts.addTrial(now, end);
            }
            Console.WriteLine(ts.getComaSeparatedData());
            string str = ts.getAverage() + "\n";                   
            benchmarkResult.Append(str);
            runBtn.Enabled = true;                    
        }

        private long testableMethod() {
            long sum = 0;
            for (int x = 1; x <= 500000; x++) {
                sum += x;
            }
            return sum;
        }
    }
}

