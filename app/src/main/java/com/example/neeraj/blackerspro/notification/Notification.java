package com.example.neeraj.blackerspro.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("genral")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "succesfull";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }

                        Toast.makeText(Notification.this, msg , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
