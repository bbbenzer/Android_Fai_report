package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Adapter.CustomerAdapter;
import com.pose.breakreport.android_fai_report.Adapter.List_NormalAdapter;
import com.pose.breakreport.android_fai_report.Properties.pNormal_list;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Normal_type_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Roomnametxt;
    ArrayList<pNormal_list> pNorm = new ArrayList<pNormal_list>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onBackPressed() {
        Normal_type_activity.super.onBackPressed();
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_type_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Roomnametxt = (TextView)findViewById(R.id.room_txt);

        Bundle extra = getIntent().getExtras();
        String roomtype = extra.getString("roomtype");
        getNormal_list(session.getDateSelect(),extra.getString("roomtype"));


    }

    public void getNormal_list(final String DueDate,String Roomtype) {
        class getNormal_list extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getNormallist();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObj = new JSONObject(s);
                    JSONArray setRs = jsonObj.getJSONArray(iFt.getTAG_RESULTS());

                    for(int i=0;i<setRs.length();i++) {
                        JSONObject c = setRs.getJSONObject(i);
                        pNormal_list xNorm = new pNormal_list();
                        Log.d("BBBB", c+"" );
                        xNorm.setNumber(c.getString("Number"));
                        xNorm.setNameth(c.getString("NameTH"));
                        xNorm.setPrice(c.getString("Price"));
                        xNorm.setProduce(c.getString("Qty"));
                        xNorm.setUnit(c.getString("Unit"));
                        if(i==0){
                            DueDatetxt.setText(c.getString("DueDate"));
                            Roomnametxt.setText(c.getString("Roomname"));
                        }
                        pNorm.add(xNorm);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_normal);
                    lv.setAdapter(new List_NormalAdapter(Normal_type_activity.this, pNorm));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //class connect php RegisterUserClass important !!!!!!!
            @Override
            protected String doInBackground(String... params) {
                RegisterUserClass ruc = new RegisterUserClass();
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("DueDate",params[0]);
                data.put("Roomtype",params[1]);
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                return result;
            }
        }
        getNormal_list ru = new getNormal_list();
        ru.execute(DueDate,Roomtype);
    }


}
