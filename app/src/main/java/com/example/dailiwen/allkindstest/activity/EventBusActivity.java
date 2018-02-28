package com.example.dailiwen.allkindstest.activity;

import android.content.Intent;
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

public class EventBusActivity extends AppCompatActivity {
    private TextView tv_message;
    private Button bt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        tv_message=(TextView)this.findViewById(R.id.tv_message);
        tv_message.setText("消息发送回主页");
        bt_message=(Button)this.findViewById(R.id.bt_message);
        bt_message.setText("发送");
        bt_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("发送消息"));
                finish();
            }
        });

    }
}
