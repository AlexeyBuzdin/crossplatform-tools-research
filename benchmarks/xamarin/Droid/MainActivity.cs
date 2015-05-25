using System;

using Android.App;
using Android.Content;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Java.Interop;

namespace xamarin.Droid
{
    [Activity(Label = "xamarin.Droid", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public class MainActivity : Activity
    {
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            SetContentView(Resource.Layout.activity_main);
        }

        [Export("logical")]
        public void logical(View view) {
            StartActivity(typeof(LogicalActivity));
        }

        [Export("graphics")]
        public void graphics(View view) {
            StartActivity(typeof(GraphicsActivity));
        }
    }
}

