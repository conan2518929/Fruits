package com.demo.yige.fruits.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.entity.UserInfo;
import com.demo.yige.fruits.tool.DBTools;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yige on 2016/12/22.
 */

public class LoginActivity extends Activity{

    private Button login,zhuce;
    private EditText name,pwd;
    private String mName = "",mPwd = "";
    private FinalDb db;
    private List<UserInfo>userInfoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        zhuce = (Button) findViewById(R.id.zhuce);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
    }

    private void initData() {
        userInfoList = new ArrayList<>();
        db = DBTools.getFinalDB(LoginActivity.this);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mName = name.getText().toString().trim();
                mPwd = pwd.getText().toString().trim();
                if(mName.length() == 0 || mPwd.length() == 0){
                    Toast.makeText(LoginActivity.this,"帐号和密码不能为空...",Toast.LENGTH_LONG).show();
                    return ;
                }
                userInfoList = db.findAllByWhere(UserInfo.class, "username='"+mName+"'");
                if(userInfoList != null && userInfoList.size() != 0){
                    UserInfo info = userInfoList.get(0);
                    if(mPwd.equals(info.getPassword())){
                        Intent i = null;
                        Bundle data = new Bundle();
                        if(info.getType().equals("企业")){
                            i = new Intent(LoginActivity.this,QMainActivity.class);
                        }
                        if(info.getType().equals("个人")){
                            i = new Intent(LoginActivity.this,GMainActivity.class);
                        }
                        data.putSerializable("data",info);
                        i.putExtras(data);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this,"帐号或者密码不正确...",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"帐号不存在...",Toast.LENGTH_LONG).show();
                }
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ZhuCeActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        name.setText("");
        pwd.setText("");
    }
}
