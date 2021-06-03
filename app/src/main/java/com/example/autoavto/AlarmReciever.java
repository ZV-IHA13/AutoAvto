package com.example.autoavto;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReciever extends BroadcastReceiver {
Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(MainActivity.statusAlarm){return;}
        this.context = context;
        Intent intent2 = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getResources().getString(R.string.app_name))
                .setContentText(context.getResources().getString(R.string.notifyRemember))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        createNotificationChannel();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        MainActivity.statusAlarm = true;

 // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, builder.build());
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}