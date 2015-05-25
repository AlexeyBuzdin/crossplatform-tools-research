using System;
using System.Collections.Generic;
using System.Linq;

using Foundation;
using UIKit;

namespace xamarin.iOS
{
    [Register("AppDelegate")]
    public partial class AppDelegate : UIApplicationDelegate
    {
        public override bool FinishedLaunching(UIApplication app, NSDictionary options)
        {
//            var st = UIStoryboard.FromName("LaunchScreen", null);
//            var vc = st.InstantiateInitialViewController();
//            Window.RootViewController = vc;
            UIWindow window = new UIWindow(UIScreen.MainScreen.Bounds);

            // make the window visible
            window.MakeKeyAndVisible();

            return true;
        }
    }
}

