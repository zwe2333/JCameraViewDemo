package com.zwe.jcameraviewdemo;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cjt2325.cameralibrary.CheckPermissionsUtil;
import com.cjt2325.cameralibrary.JCameraView;

public class MainActivity extends AppCompatActivity {
    //依赖说明   https://github.com/CJT2325/CameraView
    private JCameraView mJCameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        CheckPermissionsUtil checkPermissionsUtil = new CheckPermissionsUtil(this);
        checkPermissionsUtil.requestAllPermission(this);

        mJCameraView = (JCameraView) findViewById(R.id.cameraview);
//(0.0.7+)设置视频保存路径（如果不设置默认为Environment.getExternalStorageDirectory().getPath()）
        mJCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath());
//(0.0.8+)设置手动/自动对焦，默认为自动对焦
        mJCameraView.setAutoFoucs(false);
        mJCameraView.setCameraViewListener(new JCameraView.CameraViewListener() {
            @Override
            public void quit() {
                //返回按钮的点击时间监听
                MainActivity.this.finish();
            }

            @Override
            public void captureSuccess(Bitmap bitmap) {
                //获取到拍照成功后返回的Bitmap
            }
            @Override
            public void recordSuccess(String url) {
                //获取成功录像后的视频路径
                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        mJCameraView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mJCameraView.onPause();
    }
}
