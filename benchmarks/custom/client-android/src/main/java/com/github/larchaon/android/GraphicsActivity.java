package com.github.larchaon.android;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.larchaon.shared.TrialSuite;

public class GraphicsActivity extends Activity {

    private TextView benchmarkResult;
    private Button runBtn;
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphics);

        benchmarkResult = (TextView) findViewById(R.id.benchmark_result_tv);
        runBtn = ((Button) findViewById(R.id.run_btn));
        surfaceView = ((SurfaceView) findViewById(R.id.surfaceView));
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

                TrialSuite ts = new TrialSuite(1500);
                for (int times = 0; times < 1500; times++) {
                    Canvas canvas = GraphicsActivity.this.surfaceView.getHolder().lockCanvas();

                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(0xff000000 + (times % 256 << 16) + (256 - times % 256 << 8));
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(1);

                    long now = System.nanoTime();

                    long a = testableMethod(canvas, paint);

                    long end = System.nanoTime();

                    surfaceView.getHolder().unlockCanvasAndPost(canvas);

                    ts.addTrial(now, end);
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

    private long testableMethod(Canvas canvas1, Paint paint) {
        int w = canvas1.getWidth();
        int h = canvas1.getHeight();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                canvas1.drawPoint(x, y, paint);
            }
        }
        return w;
    }
}
