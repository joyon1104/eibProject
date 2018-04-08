package com.alpaca.jisung.eib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    Button mainBtn,searchBtn;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mainBtn= (Button) findViewById(R.id.mainBtn);
        searchBtn = (Button) findViewById(R.id.searchBtn);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent);

            }
        });

    }
}
