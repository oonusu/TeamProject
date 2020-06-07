package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Camera;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Button btn_upload,btn_view1;

    private EditText inputAddr; //입력받은 주소값
    private Button btn_go; //마커이동 버튼
    //private TextView tv; //위도 경도 변환 결과 확인 임시 정보창


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


        //지도구현
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        inputAddr = (EditText) findViewById(R.id.inputAddr);
        btn_go = (Button) findViewById(R.id.btn_go);
        //tv = (TextView) findViewById(R.id.tv);


        final Geocoder geocoder = new Geocoder(this); //입력받은 주소 값 위도경도 값으로 변환해주는 거

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;
                String str = inputAddr.getText().toString();

                try {
                    list = geocoder.getFromLocationName(str, 10);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        //입력한 주소의 위도경도 값이 없다면
                        Toast.makeText(MainActivity.this,"해당되는 주소 정보는 없습니다",Toast.LENGTH_SHORT).show();

                    } else {
                        //tv.setText(list.get(0).toString());

                        Address address = list.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));

                    }
                }
            }
        });


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mMap = googleMap;

        LatLng Seoul = new LatLng(37.551036, 126.990899); //처음 지도 켰을때 위치
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Seoul);
        markerOptions.title("서울");
        markerOptions.snippet("남산공원");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Seoul));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
}
