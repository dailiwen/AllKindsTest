package com.example.dailiwen.allkindstest.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailiwen.allkindstest.R;
import com.example.dailiwen.allkindstest.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author dailiwen
 */
public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private final int RC_CAMERA_AND_LOCATION = 1;

    @BindView(R.id.QRCode)
    Button mQRCode;
    @BindView(R.id.UpData)
    Button mUpData;
    @BindView(R.id.Glide)
    Button mGlide;
    @BindView(R.id.EventBus)
    Button mEventBus;
    @BindView(R.id.StickyShow)
    TextView mStickyShow;
    @BindView(R.id.EventBusSticky)
    Button mEventBusSticky;
    @BindView(R.id.Mina)
    Button mMina;
    @BindView(R.id.Netty)
    Button mNetty;
    @BindView(R.id.Jackson)
    Button mJackson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //获取权限
        requestCodeQRCodePermissions();

        mQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCodeActivity.class);
                startActivity(intent);
            }
        });

        mUpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpDataActivity.class);
                startActivity(intent);
            }
        });

        mGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
            }
        });

        mEventBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        });

        mEventBusSticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册在按钮中，粘性事件实现
                EventBus.getDefault().register(MainActivity.this);
            }
        });
        //注册事件
        //EventBus.getDefault().register(this);

        mMina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MinaClientActivity.class);
                startActivity(intent);
            }
        });

        mNetty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, NettyClientActivity.class);
                startActivity(startIntent);
            }
        });

        mJackson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(MainActivity.this, JacksonActivity.class);
                startActivity(startIntent);
            }
        });
    }

    /**
     * ThreadMode设置为MAIN
     * 事件的处理会在UI线程中执行，用TextView来展示收到的事件消息
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        mEventBus.setText(messageEvent.getMessage());
    }

    /**
     * 粘性事件处理方法
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void ononMoonStickyEvent(MessageEvent messageEvent){
        mStickyShow.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }


    /**
     * 为了开始使用EasyPermissions，你需要在你的Activity (或者Fragment)中重写onRequestPermissionsResult方法
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 当权限已经被获取
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 当权限已经被拒绝
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 获取权限方法
     * AfterPermissionGranted 注解
     * 如果所有的请求权限都已经被授权，任何一个通过正确的requestCode注解的方法都将执行。在所有权限都被授权后，通过注解描述的该方法，将会自动执行。
     * 当然，你也可以不用AfterPermissionGranted 注解，你可以将该方法放入onPermissionsGranted 这个回调方法里面来执行。
     */
    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void requestCodeQRCodePermissions() {
        //获取相机权限
        //String perms = Manifest.permission.CAMERA;
        //获取相机和读写权限
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        //看是否获取到权限，未获取进行获取
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和闪光灯的权限", RC_CAMERA_AND_LOCATION, perms);
        }
    }
}

