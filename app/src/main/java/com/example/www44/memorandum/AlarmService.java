package com.example.www44.memorandum;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.WindowManager;

import static com.example.www44.memorandum.Fragment1.mediaPlayer;

/**
 * 闹钟监听
 */

public class AlarmService extends Service {

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Log.e("TAG_ting","闹钟开启了");
        //TODO 闹钟响起    弹窗提示
        AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("时间到了");
        builder.setIcon(R.drawable.alarm);
        builder.setMessage("点击停止闹钟");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopSelf();
            }
        });
        Dialog dialog=builder.create();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
