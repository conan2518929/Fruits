package com.demo.yige.fruits.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.entity.UserInfo;
import com.demo.yige.fruits.zxing.activity.CaptureActivity;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class QMainActivity extends FinalActivity {

    private UserInfo info;
    @ViewInject(id = R.id.btn_type, click = "btnclick")
    private Button btn_type;
    @ViewInject(id = R.id.btn_saomiao, click = "btnclick")
    private Button btn_saomiao;
    @ViewInject(id = R.id.back, click = "btnclick")
    private TextView back;
    private final int SCANER_CODE = 1;

    @ViewInject(id = R.id.content)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qmain);
        info = (UserInfo) getIntent().getExtras().getSerializable("data");
    }

    public void btnclick(View view) {

        switch(view.getId()){
            case R.id.btn_type:
                Intent i = new Intent(QMainActivity.this,TypeActivity.class);
                Bundle data = new Bundle();
                data.putSerializable("data",info);
                i.putExtras(data);
                startActivity(i);
                break;
            case R.id.btn_saomiao:

                //6.0权限处理
//                Acp.getInstance(QMainActivity.this).request(new AcpOptions.Builder().setPermissions(
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA
//                        ,Manifest.permission.FLASHLIGHT,Manifest.permission.VIBRATE
//                ).build(), new AcpListener() {
//                    @Override
//                    public void onGranted() {
//                        scan();
//                    }
//
//                    @Override
//                    public void onDenied(List<String> permissions) {
//                        Log.i("TAG",permissions.toString() + "权限拒绝");
//                    }
//                });
                scan();
                break;
            case R.id.back:
                finish();
                break;
            default:

                break;
        }
    }

    /***
     * 扫描条形码
     */
    public void scan(){
        Intent openCameraIntent = new Intent(QMainActivity.this,
                CaptureActivity.class);
        startActivityForResult(openCameraIntent, SCANER_CODE);
    }

    /**com.demo.yige.fruits.saomiao
     * 扫描返回值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SCANER_CODE) {
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");
                tv.setText(scanResult);
                Intent i = new Intent(QMainActivity.this, ModelActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("data",info);
                i.putExtras(b);
                startActivity(i);
            }
        }
    }
}
