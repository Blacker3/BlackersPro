package com.example.neeraj.blackerspro.notification;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.neeraj.blackerspro.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

    }

    public void showNotification(String title,String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotifications").setContentTitle(title)
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(999,builder.build());
    }
}
