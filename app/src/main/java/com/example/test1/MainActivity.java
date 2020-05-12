package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    private Button btn_upload,btn_view1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_upload = findViewById(R.id.btn_upload);//리뷰등록 버튼
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //메인->리뷰등록 UploadActivity로 이동
                Intent intent =new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });

        btn_view1 = findViewById(R.id.btn_view1);//리뷰보기 버튼
        btn_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //최근 영상 자동 재생을 위한 PlayActivity로 이동
                Intent intent =new Intent(MainActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });


    }

}
