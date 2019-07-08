package com.example.www44.memorandum;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * 第三个fragment
 */

public class Fragment3 extends Fragment {
    private View view;
    private Context context;
    private Button button_copy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.fragment3,container,false);//解析布局文件

        button_copy=(Button)view.findViewById(R.id.button_copy);
        view.findViewById(R.id.button_clock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置闹钟
                clocks();
            }
        });
        button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用dialog 简单模拟退出
                new AlertDialog.Builder(getActivity())
                        .setTitle("提示！")
                        .setMessage("确定退出应用吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });
        return view;
    }
    //TODO     这里是添加设置闹钟
    public static int i=230,j=230;
    public static AlarmManager aManager;
    private void clocks() {
        aManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
        //TODO 初始化当前时间的日历
        Calendar currentTime = Calendar.getInstance();
        //TODO  创建一个TimePickerDialog实例，并把它显示出来。绑定监听器
        //TODO 就是调用原生的选择时间的弹窗dialog组件 设置闹钟的时间
        new TimePickerDialog(getActivity(), 0 ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker tp, int hourOfDay, int minute){
                Toast.makeText(context,""+hourOfDay+":"+minute,Toast.LENGTH_SHORT).show();
                // 指定启动AlarmActivity组件
                Intent intent = new Intent();
                intent.setAction("Alarm.Service");
                // 创建PendingIntent对象
                PendingIntent pi = PendingIntent.getService(getActivity(), j, intent, 0);
                i++;
                j++;
                Calendar c = Calendar.getInstance();
                // 根据用户选择时间来设置Calendar对象
                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                c.set(Calendar.MINUTE, minute);
                c.set(Calendar.SECOND,0);
                // 设置AlarmManager将在Calendar对应的时间启动指定组件
                aManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pi);
                Toast.makeText(context,":"+c.getTimeInMillis(),Toast.LENGTH_SHORT).show();
                String str = String.format("%tF %<tT", c.getTimeInMillis());
                Log.e("time",""+str);
                // 显示闹铃设置成功的提示信息
                Toast.makeText(getActivity(), "闹铃设置成功啦", Toast.LENGTH_SHORT).show();
            }
        }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime.get(Calendar.MINUTE), true){
            @Override
            protected void onStop() {
            }
        }.show();
    }

//    这里是重写的方法不管
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
