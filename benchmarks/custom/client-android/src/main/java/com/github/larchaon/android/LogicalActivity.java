package com.github.larchaon.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.larchaon.shared.TrialSuite;

public class LogicalActivity extends Activity {

    private TextView benchmarkResult;
    private Button runBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical);

        benchmarkResult = (TextView) findViewById(R.id.benchmark_result_tv);
        runBtn = ((Button) findViewById(R.id.run_btn));
    }

    public void run(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runBtn.setEnabled(false);
                    }
                });
                TrialSuite ts = new TrialSuite(15000);
                int warmupTimes = 1500;
                for (int times = 0; times <= warmupTimes + 15000; times++) {
                    long now = System.nanoTime();

                    long sum = testableMethod();

                    long end = System.nanoTime();
                    if (times > warmupTimes) ts.addTrial(now, end);
                }
                final String str = ts.getAverage() + "\n";
                System.out.println(ts.getComaSeparatedData());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        benchmarkResult.append(str);
                        runBtn.setEnabled(true);
                    }
                });
            }
        }).start();
    }

    private long testableMethod() {
        long sum = 0;
        for (int x = 1; x <= 500000; x++) {
            sum += x;
        }
        return sum;
    }
}
