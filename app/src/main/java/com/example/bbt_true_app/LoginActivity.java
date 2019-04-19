package com.example.bbt_true_app;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (Button) findViewById(R.id.log_in);
        account = (EditText) findViewById(R.id.account_login);
        password = (EditText) findViewById(R.id.password);
        TextView register = (TextView) findViewById(R.id.to_registe);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_log_in();
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

    private void check_log_in()
    {
        //有无该账户
        if(check_account()&&check_password())
        {
            Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(this,Main_Activity.class);
            finish();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            //startActivity(intent);
        }
        //有的话检查密码是否正确
        else
        {
            Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            account.setText("");
            password.setText("");
            account.requestFocus();
        }
    }

    private boolean check_account()
    {
        return true;
    }

    private boolean check_password()
    {
        return true;
    }

}
