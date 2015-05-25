package com.github.larchaon.ios;

import com.github.larchaon.shared.TrialSuite;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.uikit.*;
import org.robovm.objc.annotation.IBAction;
import org.robovm.objc.annotation.IBOutlet;

public class GraphicsViewController extends UIViewController implements UIControl.OnTouchUpInsideListener {

    UILabel benchmarkResult;
    UIImageView imageView;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        final UIButton runButton = UIButton.create(UIButtonType.RoundedRect);
        runButton.setFrame(new CGRect(0, 100, 200.0f, 37.0f));
        runButton.setTitle("Run", UIControlState.Normal);
        runButton.addOnTouchUpInsideListener(this);

        benchmarkResult = new UILabel(new CGRect(0, 150, 2300, 44));

        imageView = new UIImageView(new CGRect(0, 200, 100, 100));

        getView().addSubview(runButton);
        getView().addSubview(benchmarkResult);
        getView().addSubview(imageView);
    }

    @Override
    public void onTouchUpInside(UIControl control, UIEvent event) {
        TrialSuite suite = new TrialSuite(200);
        for (int times = 0; times < 200; times++) {

            CGRect bounds = new CGRect(CGPoint.Zero(), new CGSize(100, 100));
            float scale = 0;
            UIGraphics.beginImageContext(new CGSize(200, 200), false, scale);
            CGContext context = UIGraphics.getCurrentContext();

            context.setFillColor(CGColor.create(CGColorSpace.createDeviceRGB(), new float[]{times % 256, 256 - times % 256, 0, 1}));

            long now = System.nanoTime();

            long sum = testableMethod(context, bounds);

            long end = System.nanoTime();

            suite.addTrial(now, end);
        }
        benchmarkResult.setText(suite.getAverage() + "");
        System.out.println(suite.getComaSeparatedData());
    }

    public int testableMethod(CGContext context, CGRect bounds) {
        double w = bounds.getWidth();
        double h = bounds.getHeight();
        for(float x = 0; x < w; x+=2) {
            for(float y = 0; y < h; y+=2) {
                context.fillRect(new CGRect(x, y, 1, 1));
            }
        }

        UIImage image = UIGraphics.getImageFromCurrentImageContext();
        UIGraphics.endImageContext();
        imageView.setImage(image);
        return 0;
    }
}
