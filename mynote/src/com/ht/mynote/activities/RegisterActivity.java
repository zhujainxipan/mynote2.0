package com.ht.mynote.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.activities
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/1
 */
public class RegisterActivity extends Activity {


    private EditText name;
    private EditText password;
    private Button register;
    private Button reset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.name1);
        password = (EditText) findViewById(R.id.password1);
        register = (Button) findViewById(R.id.register);
        reset = (Button) findViewById(R.id.reset1);
    }

    //注册与重置按钮的监听事件
    public void btOnClick(View v) {
        int id = v.getId();
        String nameStr = name.getText().toString().trim();
        String paswdStr = password.getText().toString().trim();

        switch (id) {
            case R.id.register :
                if (nameStr == null || paswdStr == null) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", nameStr)
                            .putString("password", paswdStr)
                            .commit();
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.reset1 :
                name.setText("");
                password.setText("");
                name.requestFocus();
                break;
        }


    }
}