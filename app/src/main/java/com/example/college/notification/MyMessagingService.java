package com.example.college.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.college.LoginActivity;
import com.example.college.MainActivity;
import com.example.college.R;
import com.example.college.SplashAcitivty;
import com.example.college.assignment.viewAssignmentsList;
import com.example.college.check;
import com.example.college.event.EventList;
import com.example.college.faq.ViewFaqList;
import com.example.college.library.ViewIssueList;
import com.example.college.notice.ViewNotice;
import com.example.college.onlineexam.ViewOnlineExamList;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {
    String title,message,details;
    String CHANNEL_ID = "my_notification";
    static int x=1;
    public MyMessagingService() {
    }

    @Override
    public void onNewToken(@NonNull String s) {

        check.writeshared(this, "login", "no");
        check.writeshared(this, "token", s);
//        SharedPreferences sharedPreferences = getSharedPreferences("name", MODE_PRIVATE);
//        String email = sharedPreferences.getString("email", "no");
//        Retrofit retrofit= MyRetrofit.getRetrofit();
//        APIclient api=retrofit.create(APIclient.class);
//        Call<String> call=api.insertKey(s,email);
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Toast.makeText(MyMessagingService.this, "Welcome", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//            }
//        });
    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        title=remoteMessage.getData().get("Title");
        message=remoteMessage.getData().get("Message");
        details=remoteMessage.getData().get("Details");
//        Log.d("messages",details);
        notification();
    }

    void notification() {
        createNotificationChannel();

//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(SplashAcitivty.class);

        SharedPreferences sharedPreferences = getSharedPreferences("name",MODE_PRIVATE);
        String login_status = sharedPreferences.getString("login","no");
        Intent intent=null;
        if(!login_status.equals("done")) {
//            Log.d("messages","nn");
            intent = new Intent(this, LoginActivity.class);
//            startActivity(i);

        }
        if(title.equals("Assignment"))
        {
            intent = new Intent(this, viewAssignmentsList.class);
//            startActivity(intent);

        }
        if(title.equals("Event"))
        {
            intent=new Intent(this, EventList.class);
        }
        if(title.equals("Library"))
        {
            intent = new Intent(this, ViewIssueList.class);
//            startActivity(intent);

        }
        if(title.equals("MCQ Test"))
        {
            intent = new Intent(this, ViewOnlineExamList.class);
//            startActivity(intent);

        }
        if(title.equals("Faq"))
        {
            intent = new Intent(this, ViewFaqList.class);


        }
        if(title.equals("Notice"))
        {
            intent = new Intent(this, ViewNotice.class);
//            startActivity(intent);
//            finish();
        }
//        Intent intent = new Intent(this, CheckLogin.class);
//        intent.putExtra("title",title);
        intent.putExtra("faq_id",details);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(404, builder.build());

//        x++;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "my notification";
            String description = "general notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
