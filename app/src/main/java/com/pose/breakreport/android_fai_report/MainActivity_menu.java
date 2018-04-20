package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pose.breakreport.android_fai_report.Adapter.MenuAdapter;
import com.pose.breakreport.android_fai_report.Properties.pMenu;
import com.pose.breakreport.android_fai_report.xFunction.Session;

import java.util.ArrayList;

public class MainActivity_menu extends AppCompatActivity {

    private Session session;
    ArrayList<pMenu> PArray = new ArrayList<pMenu>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        session = new Session(getApplicationContext());
        session.setDateSelect("");
        session.setDueDate("");
        session.setCusCode("");

        ArrayList menu = new ArrayList();
        menu.add("ใบสรุปขนมเบรค");
        menu.add("ใบสั่งผลิต");

        for (int i=0;i<menu.size();i++){
            pMenu tp = new pMenu();
            tp.setChoice(menu.get(i).toString());
            PArray.add(tp);
            Log.d("ASD", menu.get(i).toString());
        }

        ListView lv = (ListView) findViewById(R.id.list_menu);
        lv.setAdapter(new MenuAdapter(MainActivity_menu.this, PArray));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choice = PArray.get(position).getChoice();
                if(choice.equals("ใบสรุปขนมเบรค")){
                    Intent intent = new Intent(MainActivity_menu.this, Summary_break_activity.class);
                    startActivity(intent);
                    finish();
                }else if(choice.equals("ใบสั่งผลิต")){
                    Intent intent = new Intent(MainActivity_menu.this, Order_bill_activity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Log.d("Error", "Error enter list");
                }

            }
        });

    }
}

