package com.example.www44.memorandum;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.app.WallpaperManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import net.qiujuer.genius.blur.StackBlur;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 第一个fragment
 * 也就是第一个页面
 */

public class Fragment1 extends Fragment implements View.OnClickListener {
    private View view;
    private EditText editText_title,editText_article;
    private Button savebtn,deletebtn;
    private ImageButton clockbtn;
    private NoteDB noteDB;
    private SQLiteDatabase dbwriter;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private String date;
    private long rowId;
    public static MediaPlayer mediaPlayer;

    private Spinner spinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       if (view==null){
        view = inflater.inflate(R.layout.fragment1,container,false);//解析布局文件
        ininView();
       }
        return view;
    }

    private void ininView(){
        // todo 得分点       使用了spinner
        spinner=(Spinner)view.findViewById(R.id.spinner);
        editText_title = view.findViewById(R.id.editText_title);
        editText_article = view.findViewById(R.id.editText_article);
        savebtn=view.findViewById(R.id.button_save);
        deletebtn=view.findViewById(R.id.button_cancel);
        datePicker=view.findViewById(R.id.datePicker);
        timePicker=view.findViewById(R.id.timePicker) ;
        timePicker.setIs24HourView(true);
        clockbtn=view.findViewById(R.id.imageButton_clock);
        datePicker.setCalendarViewShown(false);
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        clockbtn.setOnClickListener(this);
        //以写的方式打开database 打开数据库 待会要添加数据
        noteDB = new NoteDB(getActivity());
        dbwriter=noteDB.getWritableDatabase();
    }

    private void ininEvent(){

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_save:
//                保存一条数据
                addDB();
                if(rowId!=-1) {
                    editText_title.setText("");
                    editText_article.setText("");
                    Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "保存失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_cancel:
//TODO                 取消保存 使用了dialog
                new AlertDialog.Builder(getActivity())
                        .setTitle("取消")
                        .setMessage("确认取消编辑并清空编辑框？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                editText_title.setText("");
                                editText_article.setText("");
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .show();
                break;
        }
    }
//数据库增加方法
    public void addDB(){
        dbwriter=noteDB.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NoteDB.CONTENT,editText_title.getText().toString());
        cv.put(NoteDB.ARTICLE,editText_article.getText().toString());
        cv.put(NoteDB.TIME,getTime());
        /*
         * insert方法得参数列表：
         *              第一个为数据库表名，
         *              第二个为希望插入值为空的列名（如果插入值没有为空得列，全是有数据得就写null），
         *              第三个为一个ContentValues对象   键值对
         *  插入操作会返回以一个行id值提示是否插入成功
         */
        rowId=dbwriter.insert(NoteDB.TABLE_NAME,null,cv);

        //操作完成关闭数据库
        dbwriter.close();
    }
/*
    获取当前时间作为储存是的时间设置
 */
    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        return str;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("info","Fragment1--onCreate()");
    }



}
