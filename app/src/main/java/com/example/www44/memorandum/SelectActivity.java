package com.example.www44.memorandum;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static com.githang.statusbar.StatusBarTools.getStatusBarHeight;

/**
 *修改便签
 * 选择了某一个便签
 * 进行增删改
 */

public class SelectActivity extends Activity implements View.OnClickListener{

    private Button button_delt,button_bak;
    private EditText tv1,tv2;
    private TextView btn_update;
    private NoteDB noteDB;
    private SQLiteDatabase dbwriter;
    private int rowid;
    private int result;
    private String tvC,tvA;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        initView();
        initEvent();
    }
    //todo 找控件
    private void initView(){
        button_delt=findViewById(R.id.button_delt);
        button_bak=findViewById(R.id.button_bak);
        tv1=findViewById(R.id.textView_select);
        tv2=findViewById(R.id.textView_select2);
        btn_update=findViewById(R.id.btn_update);
        noteDB = new NoteDB(this);
        dbwriter = noteDB.getWritableDatabase();
        rowid=getIntent().getIntExtra(NoteDB.ID,0);
        tvC=getIntent().getStringExtra(NoteDB.CONTENT);
        tvA=getIntent().getStringExtra(NoteDB.ARTICLE);
        tv1.setText(tvC);
        tv2.setText(tvA);
    }
    //todo 初始化点击事件
    private void initEvent(){
        button_delt.setOnClickListener(this);
        button_bak.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_delt:
                //删除数据
                deleteDatebase();
                break;
            case R.id.button_bak:
                if(!tv1.getText().toString().equals(tvA.toString())||!tv2.getText().toString().equals(tvC.toString())){
                    //TODO 用到了dialog
                    new AlertDialog.Builder(this)
                            .setTitle("提示！")
                            .setMessage("内容有变化,是否放弃修改内容")
                            .setPositiveButton("返回保存", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create().show();
                }else{
                    finish();
                }

                break;
            case R.id.btn_update:
                updateDatabase();
                break;
        }
    }
    public void deleteDatebase(){
        result=dbwriter.delete(NoteDB.TABLE_NAME,"_id="+getIntent().getIntExtra(NoteDB.ID,0),null);
        if(result>0){
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 修改数据
     */
    public void updateDatabase(){
        getTime();
        SharedPreferences userSettings= getSharedPreferences("setting", 0);
        String time= userSettings.getString("time","默认值");
        ContentValues values=new ContentValues();
        values.put(NoteDB.CONTENT,tv1.getText().toString());
        values.put(NoteDB.ARTICLE,tv2.getText().toString());
        values.put(NoteDB.TIME,time);
        result=dbwriter.update(NoteDB.TABLE_NAME,values,"_id="+rowid,null);
        if(result>0){
            Toast.makeText(this,"修改提交成功",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"修改提交失败",Toast.LENGTH_SHORT).show();
        }
    }
   //TODO 获取当前时间
    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = new Date();
        String str = format.format(date);
        saveTime(str);
        return str;
    }
//todo  并使用  SharedPreferences 记住当前时间
// todo 得分项  SharedPreferences
    private void saveTime(String time) {
        SharedPreferences userSettings = getSharedPreferences("setting", 0);
//　　b、让setting处于编辑状态
        SharedPreferences.Editor editor = userSettings.edit();
//　　c、存放数据
        editor.putString("time",time);
//　　d、完成提交
        editor.commit();
    }
//TODO     返回键的点击监听事件   再次  使用了dialog
    @Override
    public void onBackPressed() {
        if (!tv1.getText().toString().equals(""+tvA.trim()) || !tv2.getText().toString().equals(""+tvA.trim())) {
            new AlertDialog.Builder(this)
                    .setTitle("提示！")
                    .setMessage("内容有变化,是否放弃修改内容")
                    .setPositiveButton("返回保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).create().show();
        }else{
            finish();
        }
    }
}
