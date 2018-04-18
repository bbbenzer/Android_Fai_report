package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pose.breakreport.android_fai_report.Adapter.RoomAdapter;
import com.pose.breakreport.android_fai_report.Properties.pRoom;
import com.pose.breakreport.android_fai_report.xFunction.Session;

import java.util.ArrayList;

public class Order_bill_activity extends AppCompatActivity {

    private Session session;
    ArrayList<pRoom> PArray = new ArrayList<pRoom>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(this,MainActivity_menu.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onBackPressed() {
        Order_bill_activity.super.onBackPressed();
        Intent back = new Intent(this,MainActivity_menu.class);
        startActivity(back);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_bill_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList menu = new ArrayList();
        menu.add("พาย-ครัวซอง");
        menu.add("ขนมปังไส้พี่ดาว");
        menu.add("ขนมปังพี่บี พี่ลัย");
        menu.add("ห้องแซนวิช");
        menu.add("คุ้กกี้-ของกรอบ-ของแห้ง");
        menu.add("ห้องขนมปังจืด");
        menu.add("ขนมปังไส้พี่ดาว");
        menu.add("ห้องพี่หอม");
        menu.add("ห้องซอฟเค้ก");
        menu.add("ห้องครัว-ไส้สังขยา");
        menu.add("ห้องพายถ้วย ป้าเครือ");
        menu.add("ห้องแต่งเค้ก");



        for (int i=0;i<menu.size();i++){
            pRoom Room = new pRoom();
            Room.setRoomname(menu.get(i).toString());
            PArray.add(Room);
            Log.d("ASD", menu.get(i).toString());
        }

        ListView lv = (ListView) findViewById(R.id.list_room);
        lv.setAdapter(new RoomAdapter(Order_bill_activity.this, PArray));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choice = PArray.get(position).getRoomname();
//                if(choice.equals("ใบสรุปขนมเบรค")){
//                    Intent intent = new Intent(MainActivity_menu.this, Summary_break_activity.class);
//                    startActivity(intent);
//                    finish();
//                }else if(choice.equals("ใบสั่งผลิต")){
////                    Intent intent = new Intent(MainActivity_menu.this, Activity_summary_break.class);
////                    startActivity(intent);
////                    finish();
//                }else{
//                    Log.d("Error", "Error enter list");
//                }

            }
        });

    }

}
