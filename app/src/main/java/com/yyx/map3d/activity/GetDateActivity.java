package com.yyx.map3d.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.yyx.map3d.R;

/**
 * Created by vimi8 on 2017/5/9.
 */

public class GetDateActivity extends AppCompatActivity implements AMap.OnCameraChangeListener {

    private MapView mapView;
    private AMap aMap;

    Marker marker;

    private TextView j,w;

    private LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map3d_get_date);
        j = (TextView) findViewById(R.id.j);
        w = (TextView) findViewById(R.id.w);
        ll = (LinearLayout) findViewById(R.id.ll);


        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        init();

        LatLng latLng = new LatLng(39.906901,116.397972);
        marker = aMap.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("经度：116.397972\n纬度：39.906901"));

        aMap.setOnCameraChangeListener(this);

        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll.getVisibility()==View.GONE){
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        LatLng target = cameraPosition.target;
        System.out.println(target.latitude + "jinjin------" + target.longitude);
        w.setText("纬度："+target.latitude);
        j.setText("经度："+target.longitude);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }
}
