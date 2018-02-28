package com.example.dailiwen.allkindstest.activity;

import android.Manifest;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dailiwen.allkindstest.R;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * 二维码识别的简单demo，只有识别功能
 * 用到 easypermissions 和 BGAQRCode-Android 组件
 * https://github.com/googlesamples/easypermissions
 * https://github.com/bingoogolapple/BGAQRCode-Android
 * @date 2018.01.24
 * @author dailiwen
 */
public class QRCodeActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private QRCodeView mQRCodeView;
    private final int RC_CAMERA_AND_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        mQRCodeView = (ZXingView) findViewById(R.id.QRview);
        mQRCodeView.setDelegate(this);

        //启动后摄像头
        mQRCodeView.startCamera();
        //展现二维码边框
        mQRCodeView.showScanRect();
        //开始扫描
        mQRCodeView.startSpot();
    }

    /**
     * 二维码获取成功后
     * @param result
     */
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i("dai", "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    /**
     * 让手机振动
     */
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //振动时间
        vibrator.vibrate(200);
    }
}