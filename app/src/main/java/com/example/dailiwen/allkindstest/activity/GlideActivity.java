package com.example.dailiwen.allkindstest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dailiwen.allkindstest.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        mImageView = (ImageView) findViewById(R.id.image);
    }

    public void loadImage(View view) {
        //静态图
        //String url = "http://192.168.155.1:8080/Glide/test1.jpg";
        //动图GIF
        String url = "http://p1.pstatp.com/large/166200019850062839d3";

        //Glide.with()方法，用于创建一个加载图片的实例。with()方法可以接收Context、Activity或者Fragment类型的参数
        //load()方法，这个方法用于指定待加载的图片资源。Glide支持加载各种各样的图片资源，包括网络图片、本地图片、应用资源、二进制流、Uri对象等等。
        //placeholder()方法，占位图，未加载之前的图片
        //diskCacheStrategy(DiskCacheStrategy.NONE)，禁用缓存功能
        //override(100, 100)，Glide只会将图片加载成100*100像素的尺寸
        //asBitmap(),asGif()，分别是只会加载静态图和动态图
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(100, 100)
                .into(mImageView);
    }
}
