package com.alpaca.jisung.eib;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView nameText;
    Button settingBtn,searchBtn;

    double lat,lng;

    Intent intent;

    private Button setting;
    private Button eib;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();//초기화


        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {//찾기버튼 클릭시 이벤트
            @Override
            public void onClick(View v) {
                getLocationData();//위치를 찾은 후

                intent = new Intent(MainActivity.this,MapsActivity.class);

                intent.putExtra("Lat",lat+0.0000001);
                intent.putExtra("Lng",lng+0.0000001);//값을 넘겨

                startActivity(intent);//엑티비티 실행
            }
        });


        final String[] name = {"지갑", "필통", "노트북"};
        final ListAdapter adapter = new Item(this, name);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        arrayList = new ArrayList<>(Arrays.asList(name));
        arrayAdapter=new ArrayAdapter<String>(this, R.layout.image_layout, R.id.itemName,arrayList);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(arrayList.get(position),position);
                return false;
            }
        });

    }
    public void showInputBox(String oldItem, final int index){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.editbox);
        TextView txtMessage=(TextView)dialog.findViewById(R.id.txtmsg);
        txtMessage.setText("수정하시겠습니까?");
        final EditText editText=(EditText)dialog.findViewById(R.id.txtinput);
        editText.setText(oldItem);
        Button bt = (Button)dialog.findViewById(R.id.save);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.set(index,editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();

    }



    public void init( ){
        nameText = (TextView)findViewById(R.id.nameText);
        settingBtn = (Button)findViewById(R.id.settingBtn);
        searchBtn = (Button)findViewById(R.id.searchBtn);//id 값 찾기 (xml)

        nameText.setText(getIntent().getStringExtra("name"));//로그인에서 이름 정보가져와서 텍스트로 설정

    }


    public void getLocationData(){
        lat = 37.555744;
        lng = 126.970431;// 임시 설정값  gps 위치 값 넣을 것
    }//기기 위치 정보 찾는 함수


}
