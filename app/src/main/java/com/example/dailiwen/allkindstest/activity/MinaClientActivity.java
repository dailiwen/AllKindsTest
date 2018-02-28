package com.example.dailiwen.allkindstest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dailiwen.allkindstest.R;
import com.example.dailiwen.allkindstest.activity.Mina.ClientConfig;
import com.example.dailiwen.allkindstest.activity.Mina.MinaClient;
import com.example.dailiwen.allkindstest.entity.SfkInfo;

import org.apache.mina.core.session.IoSession;

/**
 * @author dailiwen
 */
public class MinaClientActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private Button button;
    private TextView text;
    private MinaClient minaClient;
    private IoSession serverSeesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina_client);
        init();
    }

    private void init() {
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SfkInfo sfkInfo = new SfkInfo();
                sfkInfo.setStoreId("1");
                sfkInfo.setTerminalId("2");
                sfkInfo.setServiceSocket("danima");
                sfkInfo.setFireSize(3);
                sfkInfo.setWindSize(4);
                sfkInfo.setCondenseSize(5);
                sfkInfo.setBootTime("6");
                minaClient.sendMessage(sfkInfo);
            }
        });
        text = (TextView) findViewById(R.id.text);

        initClient();
    }

    private void initClient() {
        //客户端初始化
        ClientConfig clientConfig = new ClientConfig.Builder().setIp("192.168.155.1").setPort(3344).build();
        minaClient = new MinaClient(clientConfig);
        minaClient.setClientStateListener(new MinaClient.ClientStateListener() {
            @Override
            public void sessionCreated() {
                Log.d(TAG, "client sessionCreated ");
            }

            @Override
            public void sessionOpened() {
                Log.d(TAG, "client sessionOpened ");
            }

            @Override
            public void sessionClosed() {
                Log.d(TAG, "client sessionClosed ");
            }

            @Override
            public void messageReceived(String message) {
                Log.d(TAG, "client messageReceived " + message.toString());
                text.setText(message.toString());
            }

            @Override
            public void messageSent(String message) {
                Log.d(TAG, "client messageSent " + message);
            }
        });

    }
}
