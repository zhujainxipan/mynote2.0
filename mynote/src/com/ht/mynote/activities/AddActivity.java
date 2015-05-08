package com.ht.mynote.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.ht.mynote.dao.DbManager;
import com.ht.mynote.pojo.Notes;

import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.activities
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/2
 */
public class AddActivity extends Activity {

    private EditText addtitle;
    private EditText addcontent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addtitle = (EditText) findViewById(R.id.addtitle);
        addcontent = (EditText) findViewById(R.id.addcontent);


    }

    //如果点击返回键进入上一级，则会默认保存
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String titleStr = addtitle.getText().toString();
        String contentStr = addcontent.getText().toString();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sDateFormat.format(System.currentTimeMillis());
        Notes notes = new Notes();
        notes.setTime(time);
        notes.setContent(contentStr);
        notes.setTitle(titleStr);
        if (titleStr.length() == 0 && contentStr.length() == 0) {
            Toast.makeText(this, "不能添加一个空数据", Toast.LENGTH_SHORT).show();
        } else {
            if (titleStr.length() == 0) {
                if (notes.getContent().length() <= 8)
                    notes.setTitle(notes.getContent());
                else
                    notes.setTitle(notes.getContent().trim().substring(0, 8));
            }
            if (DbManager.getInstance().add(notes))
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

}