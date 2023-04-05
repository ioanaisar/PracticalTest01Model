package ro.pub.cs.systems.eim.practicaltest01model;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PracticalTest01Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       // return super.onStartCommand(intent, flags, startId);
        int left = Integer.parseInt(intent.getStringExtra(Constants.LEFT_TEXT));
        int right = Integer.parseInt(intent.getStringExtra(Constants.RIGHT_TEXT));

        // creez thread si start
        ProcessingThread processingThread = new ProcessingThread(this, left, right);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }
}
