package com.vl.mak.servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    NotificationManager nm;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendNotification();

        return super.onStartCommand(intent, flags, startId);
    }

    void sendNotification() {
        //create icon and text for closed statusbar
        Notification.Builder nBuilder = new Notification.Builder(this)
                .setContentTitle("Ma title")
                .setContentText("Ma text")
                .setSmallIcon(R.drawable.lo);

        //this stuff runs when user clicks the notification
        Intent intentus = new Intent(this, MainActivity.class);
        intentus.putExtra(MainActivity.FILENAME_PARAM, "filenamus");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentus, 0);

        nBuilder.setContentIntent(pendingIntent);

        Notification notification = nBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_ALL;
        notification.number = 5;

        nm.notify(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the srvice.
        throw new UnsupportedOperationException("Not yet imeplemented");
    }
}
