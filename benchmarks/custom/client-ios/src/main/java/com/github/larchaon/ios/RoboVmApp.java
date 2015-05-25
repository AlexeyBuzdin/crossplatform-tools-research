package com.github.larchaon.ios;

import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;

public class RoboVmApp extends UIApplicationDelegateAdapter {

    @Override
    public boolean didFinishLaunching(UIApplication application,
                                      UIApplicationLaunchOptions launchOptions) {

        UIWindow window = new UIWindow(UIScreen.getMainScreen().getBounds());
        window.makeKeyAndVisible();
        window.setBackgroundColor(UIColor.lightGray());
        window.setRootViewController(new MainViewController());

        return true;
    }

    public static void main(String[] args) {
        try (NSAutoreleasePool pool = new NSAutoreleasePool()) {
            UIApplication.main(args, null, RoboVmApp.class);
        }
    }
}