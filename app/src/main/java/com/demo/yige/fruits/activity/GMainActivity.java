package com.demo.yige.fruits.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.entity.UserInfo;
import com.demo.yige.fruits.tool.DBTools;

import net.tsz.afinal.FinalDb;

/**
 * Created by yige on 2016/12/23.
 */

public class GMainActivity extends Activity {

    private FinalDb db;
    private UserInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gmain);
        info = (UserInfo) getIntent().getExtras().getSerializable("data");
        db = DBTools.getFinalDB(this);

    }

}
