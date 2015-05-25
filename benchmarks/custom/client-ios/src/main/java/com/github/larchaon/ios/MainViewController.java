package com.github.larchaon.ios;

import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.uikit.*;

public class MainViewController extends UINavigationController {

    public MainViewController() {
        super(new InnerViewController());
    }

    static class InnerViewController extends UIViewController {

        @Override
        public void viewDidLoad() {
            super.viewDidLoad();

            final UIButton logicalButton = UIButton.create(UIButtonType.RoundedRect);
            logicalButton.setFrame(new CGRect(0, 100, 200.0f, 37.0f));
            logicalButton.setTitle("Logical", UIControlState.Normal);
            logicalButton.addOnTouchUpInsideListener((control, event) -> {
                ((UINavigationController) getParentViewController())
                        .pushViewController(new LogicalViewController(), true);
            });
            getView().addSubview(logicalButton);

            final UIButton graphicsButton = UIButton.create(UIButtonType.RoundedRect);
            graphicsButton.setFrame(new CGRect(0, 150f, 200.0f, 37.0f));
            graphicsButton.setTitle("Graphics", UIControlState.Normal);
            graphicsButton.addOnTouchUpInsideListener((control, event) -> {
                ((UINavigationController) getParentViewController())
                        .pushViewController(new GraphicsViewController(), true);
            });
            getView().addSubview(graphicsButton);
        }
    }
}
