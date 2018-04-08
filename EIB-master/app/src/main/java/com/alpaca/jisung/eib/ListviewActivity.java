package com.alpaca.jisung.eib;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ListviewActivity extends AppCompatActivity {

    private Button settingBtn;
    private Button searchBtn;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingBtn = (Button) findViewById(R.id.settingBtn);
        searchBtn = (Button) findViewById(R.id.searchBtn);


        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class); // 다음 넘어갈 클래스 지정
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
        final Dialog dialog=new Dialog(ListviewActivity.this);
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

}
