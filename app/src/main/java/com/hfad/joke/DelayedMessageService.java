package com.hfad.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DelayedMessageService extends IntentService {
    final static String MESSAGE = "message";
    private static final int NOTIFICATION_ID = 189;

    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) { // WAIT FOR 3 SEC
        synchronized (this) {
            try {
                wait(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = intent.getStringExtra(MESSAGE);
            showMessage(message);
        }
    }

    private void showMessage(String message) {
        //SERVICE CREATES INTENT FOR MAINACTIVITY
        Intent intent = new Intent(this, MainActivity.class);

        //CREATE STACKBUILDER & ADD INTENT TO MAINACTIVITY'S STACKBUILDER
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);

        //STACKBUILDER CREATES PENDING INTENT
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //CREATE NOTIF AND PASS IT THE PENDING INTENT
        Notification notification = new Notification.Builder(this)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle(getResources().getString(R.string.app_name))
                                    .setAutoCancel(true)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setDefaults(Notification.DEFAULT_SOUND)
                                    .setContentIntent(pendingIntent) //PASS THE PENDING INTENT TO SERVICE
                                    .setContentText(message)
                                    .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}
