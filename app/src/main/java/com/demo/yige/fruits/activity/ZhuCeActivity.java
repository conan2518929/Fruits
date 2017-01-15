package com.demo.yige.fruits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.tool.DBTools;
import com.demo.yige.fruits.entity.UserInfo;

import net.tsz.afinal.FinalDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yige on 2016/11/24.
 */

public class ZhuCeActivity extends Activity {

    private static final String TAG = "tag";

    private ImageView back;
    private Button zhuce;
    private EditText name, pwd;
    private String mName, mPwd;
    private RadioGroup group;
    private String type = "企业";
    private FinalDb db;
    private List<UserInfo> userInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zhuce);
        initView();
        initData();
    }

    private void initView() {
        zhuce = (Button) findViewById(R.id.zhuce);
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        back = (ImageView) findViewById(R.id.back);
        group = (RadioGroup) findViewById(R.id.radioGroup);
    }

    private void initData() {
        userInfoList = new ArrayList<>();
        db = DBTools.getFinalDB(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                int radioButtonId = arg0.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                type = rb.getText() + "";
            }
        });

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = name.getText().toString().trim();
                mPwd = pwd.getText().toString().trim();
                if (!mName.equals("") && !mPwd.equals("")) {
                    userInfoList = db.findAllByWhere(UserInfo.class, "username='"+mName+"'");
                    if(userInfoList != null && userInfoList.size() != 0){
                        Toast.makeText(ZhuCeActivity.this,"帐号已经存在...",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ZhuCeActivity.this,"注册成功...",Toast.LENGTH_LONG).show();
                        UserInfo info = new UserInfo();
                        info.setUserId(getRandomString(12));
                        info.setPassword(mPwd);
                        info.setUsername(mName);
                        info.setType(type);
                        db.save(info);
                        finish();
                    }
                } else {
                    Toast.makeText(ZhuCeActivity.this, "请设置帐号和密码...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
