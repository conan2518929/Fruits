package com.demo.yige.fruits.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.yige.fruits.R;
import com.demo.yige.fruits.entity.AppleModelInfo;
import com.demo.yige.fruits.entity.UserInfo;
import com.demo.yige.fruits.tool.DBTools;
import com.demo.yige.fruits.tool.FileUtils;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.tsz.afinal.FinalDb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yige on 2016/12/23.
 */
public class ModelActivity extends Activity implements View.OnClickListener {

    private ImageView back;
    private ImageView img_one, img_two, img_three, img_four, img_five;
    private Button delete_one, delete_two, delete_three, delete_four, delete_five;
    private EditText et_input_one, et_input_two, et_input_three, et_input_four, et_input_five;
    private String mOne ="", mTwo="", mThree="", mFour="",mFive="";
    private ImageView photo_one, photo_two, photo_three, photo_four, photo_five;
    private String[] arr = new String[]{"拍照"};
    private Uri uri = null;
    private static int CAMERA_REQUEST_CODE = 1;
    private static int CROP_REQUEST_CODE = 3;
    private List<ImageView>imgList;
    private String mHead_img = "";

    private FinalDb db;

    private String[] imgArray = new String[5];
    private String[] nameArray = new String[5];

    private int typeNmu = 0;
    private Button btn_ok;
    private UserInfo userInfo;

    private File file = null;

    private  List<AppleModelInfo> modelList;
    private Uri uriPaiZhao = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_model);
        initView();
        initData();
    }

    private void initData() {
        modelList = new ArrayList<>();
        imgList  = new ArrayList<>();
        imgList.add(photo_one);
        imgList.add(photo_two);
        imgList.add(photo_three);
        imgList.add(photo_four);
        imgList.add(photo_five);
        db = DBTools.getFinalDB(ModelActivity.this);
        userInfo = (UserInfo) getIntent().getExtras().getSerializable("data");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modelList = db.findAllByWhere(AppleModelInfo.class,"name='"+userInfo.getUsername()+"'");
        if(modelList.size() != 0){
            et_input_one.setText(modelList.get(0).getDescribe());
            et_input_two.setText(modelList.get(1).getDescribe());
            et_input_three.setText(modelList.get(2).getDescribe());
            et_input_four.setText(modelList.get(3).getDescribe());
            et_input_five.setText(modelList.get(4).getDescribe());
            for(int j=0;j<5;j++){
                nameArray[j] = modelList.get(j).getDescribe();
                imgArray[j] = modelList.get(j).getImgUrl();

            }
            for(int n = 0; n<5; n++){
                Log.i("TAG",n+","+modelList.get(n).getImgUrl());
                ImageLoader.getInstance().displayImage(modelList.get(n).getImgUrl(), imgList.get(n));
            }
        }
    }

    private void initView(){
        back = (ImageView) findViewById(R.id.back);

        img_one = (ImageView) findViewById(R.id.img_one);
        img_two = (ImageView) findViewById(R.id.img_two);
        img_three = (ImageView) findViewById(R.id.img_three);
        img_four = (ImageView) findViewById(R.id.img_four);
        img_five = (ImageView) findViewById(R.id.img_five);

        img_one.setOnClickListener(this);
        img_two.setOnClickListener(this);
        img_three.setOnClickListener(this);
        img_four.setOnClickListener(this);
        img_five.setOnClickListener(this);

        delete_one = (Button) findViewById(R.id.delete_one);
        delete_two = (Button) findViewById(R.id.delete_two);
        delete_three = (Button) findViewById(R.id.delete_three);
        delete_four = (Button) findViewById(R.id.delete_four);
        delete_five = (Button) findViewById(R.id.delete_five);

        delete_one.setOnClickListener(this);
        delete_two.setOnClickListener(this);
        delete_three.setOnClickListener(this);
        delete_four.setOnClickListener(this);
        delete_five.setOnClickListener(this);

        et_input_one = (EditText) findViewById(R.id.et_input_one);
        et_input_two = (EditText) findViewById(R.id.et_input_two);
        et_input_three = (EditText) findViewById(R.id.et_input_three);
        et_input_four = (EditText) findViewById(R.id.et_input_four);
        et_input_five = (EditText) findViewById(R.id.et_input_five);

        photo_one = (ImageView) findViewById(R.id.photo_one);
        photo_two = (ImageView) findViewById(R.id.photo_two);
        photo_three = (ImageView) findViewById(R.id.photo_three);
        photo_four = (ImageView) findViewById(R.id.photo_four);
        photo_five = (ImageView) findViewById(R.id.photo_five);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_one:
                typeNmu = 0;
                addPic();
                break;
            case R.id.img_two:
                typeNmu = 1;
                addPic();
                break;
            case R.id.img_three:
                typeNmu = 2;
                addPic();
                break;
            case R.id.img_four:
                typeNmu = 3;
                addPic();
                break;
            case R.id.img_five:
                typeNmu = 4;
                addPic();
                break;
            case R.id.delete_one:

                break;
            case R.id.delete_two:

                break;
            case R.id.delete_three:

                break;
            case R.id.delete_four:

                break;
            case R.id.delete_five:

                break;
            case R.id.btn_ok:
                mOne = et_input_one.getText().toString().trim();
                mTwo = et_input_two.getText().toString().trim();
                mThree = et_input_three.getText().toString().trim();
                mFour = et_input_four.getText().toString().trim();
                mFive = et_input_five.getText().toString().trim();
                if(mOne.length() != 0 && mTwo.length() != 0 && mThree.length() != 0 && mFour.length() != 0 && mFive.length() != 0){
                    for(int i = 0; i < 5; i++){
                        if(imgArray.length == 0){
                            Toast.makeText(ModelActivity.this, "图片不能为空...", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    nameArray[0] = mOne;
                    nameArray[1] = mTwo;
                    nameArray[2] = mThree;
                    nameArray[3] = mFour;
                    nameArray[4] = mFive;

                    db.deleteByWhere(AppleModelInfo.class,"name='"+userInfo.getUsername()+"'");

                    for(int m = 0; m < 5; m++){
                        AppleModelInfo info = new AppleModelInfo();
                        info.setDescribe(nameArray[m]);
                        info.setImgUrl(imgArray[m]);
                        info.setName(userInfo.getUsername());
                        info.setType(1);//代表苹果
                        db.save(info);
                    }
                    System.out.println("个数="+db.findAllByWhere(AppleModelInfo.class,"name='"+userInfo.getUsername()+"'").size());
                    finish();
                }else{
                    Toast.makeText(ModelActivity.this, "模板信息不能为空...", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void addPic() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(ModelActivity.this);
        builder2.setTitle("采集图片");
        builder2.setItems(arr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int position) {
                // 照相
                if (position == 0) {
                    //6.0权限处理
                    Acp.getInstance(ModelActivity.this).request(new AcpOptions.Builder().setPermissions(
                            Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA).build(), new AcpListener() {
                        @Override
                        public void onGranted() {
                            getphoto();
                        }

                        @Override
                        public void onDenied(List<String> permissions) {
                            Log.i("TAG",permissions.toString() + "权限拒绝");
                        }
                    });
                }
            }
        });
        builder2.create().show();
    }

    private void getphoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        file = FileUtils.getImageFile(this);
        uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                return;
            }
            uriPaiZhao = uri;
            System.out.println("uri=" + getRealFilePath(ModelActivity.this,uri));
            startImageZoom(uriPaiZhao, 1, 1, 150, 150);
        } else if (requestCode == CROP_REQUEST_CODE) {
            if (data == null) {
                return;
            }
            Bundle extras = data.getExtras();
            if (extras == null) {
                return;
            }
            Bitmap bm = extras.getParcelable("data");
            imgList.get(typeNmu).setImageBitmap(bm);
            Log.i("TAG",uriPaiZhao.toString());
//            mHead_img = sendImage(bm);
//            Log.i("===TAG===",mHead_img);
//            imgArray[typeNmu] = getRealFilePath(ModelActivity.this,uri);
            imgArray[typeNmu] = uri.toString();
//            sendPic(mHead_img, bm);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startImageZoom(Uri uri, int x, int y, int xx, int yy) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", x);
        intent.putExtra("aspectY", y);
        intent.putExtra("outputX", xx);
        intent.putExtra("outputY", yy);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    private String sendImage(Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));
        System.out.println("ima==" + img);
        return img;
    }

    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
