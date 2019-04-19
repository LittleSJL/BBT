package com.example.bbt_true_app;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText password1;
    private EditText password2;
    private EditText account;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        password1 = (EditText)findViewById(R.id.password_1);
        password2 = (EditText)findViewById(R.id.password_2);
        account = (EditText)findViewById(R.id.account_register);
        register = (Button) findViewById(R.id.registe);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_register();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void check_register()
    {
        if(check_password()&&check_account())
        {
            Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            if(!check_password())
            {
                Toast.makeText(this,"两次输入密码不同",Toast.LENGTH_LONG).show();
                password1.setText("");
                password2.setText("");
                account.setText("");
                account.requestFocus();
            }
            else
            {
                Toast.makeText(this,"登录名已存在",Toast.LENGTH_LONG).show();
                password1.setText("");
                password2.setText("");
                account.setText("");
                account.requestFocus();
            }
        }
    }

    private boolean check_password()
    {
        if(password1.getText().toString().equals(password2.getText().toString()))
            return true;
        return false;
    }

    private boolean check_account()
    {
        return true;//检查用户名是否重复
    }
}
