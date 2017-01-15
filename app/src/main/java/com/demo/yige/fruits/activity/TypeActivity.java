package com.demo.yige.fruits.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.entity.UserInfo;

/**
 * Created by yige on 2016/12/23.
 */

public class TypeActivity extends Activity{

    private UserInfo info;
    private LinearLayout ll_apple, ll_xiangjiao, ll_xigua;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_type);

        info = (UserInfo) getIntent().getExtras().getSerializable("data");
        ll_apple = (LinearLayout) findViewById(R.id.ll_apple);
        ll_xiangjiao = (LinearLayout) findViewById(R.id.ll_xiangjiao);
        ll_xigua = (LinearLayout) findViewById(R.id.ll_xigua);

        ll_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypeActivity.this, ModelActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("data",info);
                i.putExtras(data);
                startActivity(i);
            }
        });

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}
