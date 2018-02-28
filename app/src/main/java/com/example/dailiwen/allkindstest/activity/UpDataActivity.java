package com.example.dailiwen.allkindstest.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dailiwen.allkindstest.R;
import com.example.dailiwen.allkindstest.util.DownloadUtils;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * DownloadManager应用：下载软件
 * @date 2018.01.24
 * @author dailiwen
 */
public class UpDataActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send) {
            DownloadUtils downloadUtils = new DownloadUtils(UpDataActivity.this);
            downloadUtils.downloadAPK("http://192.168.155.1:8080/ClashRoyale.apk", "ClashRoyale.apk");
        }
    }
}
