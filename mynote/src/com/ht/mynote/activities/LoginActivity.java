package com.ht.mynote.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.activities
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/1
 */
public class LoginActivity extends Activity {

    private EditText name;
    private EditText password;
    private CheckBox rememberPsw;
    private CheckBox autoLogin;
    private Button login;
    private Button reset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        rememberPsw = (CheckBox) findViewById(R.id.rememberpsw);
        autoLogin = (CheckBox) findViewById(R.id.autologin);
        login = (Button) findViewById(R.id.login);
        reset = (Button) findViewById(R.id.reset);

        SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
        boolean isRememberPsw = sp.getBoolean("rememberPsw", false);
        boolean isAutoLogin = sp.getBoolean("autoLogin", false);

        rememberPsw.setChecked(isRememberPsw);
        autoLogin.setChecked(isAutoLogin);

        if (isRememberPsw) {
            name.setText(sp.getString("name", null));
            password.setText(sp.getString("password", null));
        }

    }

    //多选框的监听器
    public void cbLoginOnClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.autologin:
                checkBc();
                break;
            case R.id.rememberpsw:
                checkBc();
                break;
        }

    }

    //判断多选框的逻辑实现
    private void checkBc() {
        if (autoLogin.isChecked()) {
            if (!rememberPsw.isChecked()) {
                rememberPsw.setChecked(true);
            }
            SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("autoLogin", true)
                    .putBoolean("rememberPsw", true)
                    .commit();
        }
        else {
            if (rememberPsw.isChecked()) {
                SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("autoLogin", false)
                        .putBoolean("rememberPsw", true)
                        .commit();
            }
            else {
                SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("autoLogin", false)
                        .putBoolean("rememberPsw", false)
                        .commit();
            }

        }
    }

    //登录以及重置按钮的监听器
    public void btLoginOnClick(View v) {
        String nameStr = name.getText().toString().trim();
        String passwdStr = password.getText().toString().trim();

        int id = v.getId();
        switch (id) {
            case R.id.login:
                SharedPreferences sp = getSharedPreferences("mydata", MODE_PRIVATE);
                if (sp.getString("name", null).equals(nameStr) && sp.getString("password", null).equals(passwdStr))
                    startActivity(new Intent(this, MainActivity.class));
                else
                    Toast.makeText(this, "用户名或密码输入错误", Toast.LENGTH_SHORT).show();
                break;

            case R.id.reset:
                name.setText("");
                password.setText("");
                name.requestFocus();
                break;
        }
    }
}