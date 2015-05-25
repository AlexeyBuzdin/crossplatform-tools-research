
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
using Java.Interop;
using Java.Lang;
using Android.Graphics;

namespace xamarin.Droid
{
    [Activity(Label = "GraphicsActivity")]			
    public class GraphicsActivity : Activity
    {
        private TextView benchmarkResult;
        private Button runBtn;
        private SurfaceView surfaceView;

        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);
            SetContentView(Resource.Layout.activity_graphics);

            benchmarkResult = FindViewById<TextView>(Resource.Id.benchmark_result_tv);
            runBtn = FindViewById<Button>(Resource.Id.run_btn);
            surfaceView = FindViewById<SurfaceView>(Resource.Id.surfaceView);
        }

        [Export("run")]
        public void run(View view) {          
            runBtn.Enabled = false;
            TrialSuite ts = new TrialSuite(1500);
            for (int times = 0; times < 1500; times++) {
                Canvas canvas = surfaceView.Holder.LockCanvas();

                Paint paint = new Paint(PaintFlags.AntiAlias);
                paint.Color = new Color(times % 255, 255 - times % 255, 0);
                paint.StrokeWidth = 1;

                long now = JavaSystem.NanoTime();

                long a = testableMethod(canvas, paint);

                long end = JavaSystem.NanoTime();

                surfaceView.Holder.UnlockCanvasAndPost(canvas);

                ts.addTrial(now, end);
            }
            Console.WriteLine(ts.getComaSeparatedData());
            string str = ts.getAverage() + "\n";                   
            benchmarkResult.Append(str);
            runBtn.Enabled = true;                    
        }

        private long testableMethod(Canvas canvas1, Paint paint) {
            int w = canvas1.Width;
            int h = canvas1.Height;
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    canvas1.DrawPoint(x, y, paint);
                }
            }
            return w;
        }
    }
}

