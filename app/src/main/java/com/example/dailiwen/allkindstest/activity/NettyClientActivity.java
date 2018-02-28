package com.example.dailiwen.allkindstest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailiwen.allkindstest.R;
import com.example.dailiwen.allkindstest.activity.Netty.NettyClientBootstrap;
import com.example.dailiwen.allkindstest.activity.Netty.RequestInfo;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClientActivity extends AppCompatActivity {

    private Button mNettyBtn;
    private TextView mNettyText;
    NettyClientBootstrap bootstrap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netty_client);
        mNettyBtn = (Button) findViewById(R.id.nettyBtn);
        mNettyText = (TextView) findViewById(R.id.nettyText);
        try {
            bootstrap = new NettyClientBootstrap(1242, "192.168.155.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNettyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestInfo req = new RequestInfo();
                req.setSequence(123456);
                req.setType((byte) 1);
                req.setSequence(0);
                req.setBody(String.valueOf((new Date()).getTime()));
                bootstrap.getSocketChannel().writeAndFlush(req);
            }
        });
    }
}
