package com.github.larchaon.ios;

import com.github.larchaon.shared.TrialSuite;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.Foundation;
import org.robovm.apple.uikit.*;

public class LogicalViewController extends UIViewController implements UIControl.OnTouchUpInsideListener {

    private UILabel benchmarkResult;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        final UIButton runButton = UIButton.create(UIButtonType.RoundedRect);
        runButton.setFrame(new CGRect(0, 100, 200.0f, 37.0f));
        runButton.setTitle("Run", UIControlState.Normal);
        runButton.addOnTouchUpInsideListener(this);

        benchmarkResult = new UILabel(new CGRect(0, 150, 2300, 44));

        getView().addSubview(runButton);
        getView().addSubview(benchmarkResult);
    }

    @Override
    public void onTouchUpInside(UIControl control, UIEvent event) {
        TrialSuite trialSuite = new TrialSuite(200);

        for (int times = 0; times < 200; times++) {
            long now = System.nanoTime();

            long sum = testableMethod();

            long end = System.nanoTime();

            trialSuite.addTrial(now, end);
        }
        benchmarkResult.setText(trialSuite.getAverage()+"");
        System.out.println(trialSuite.getComaSeparatedData());
    }

    private long testableMethod() {
        long sum = 0;
        for (int x = 1; x <= 500000; x++) {
            sum += x;
        }
        return sum;
    }
}
