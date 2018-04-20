package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Adapter.List_PieAdapter;
import com.pose.breakreport.android_fai_report.Adapter.List_softcakeAdapter;
import com.pose.breakreport.android_fai_report.Adapter.List_softcake_2Adapter;
import com.pose.breakreport.android_fai_report.Properties.pPie_list;
import com.pose.breakreport.android_fai_report.Properties.pSoftcake_list;
import com.pose.breakreport.android_fai_report.Properties.pSoftcake_list_sub;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Softcake_type_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Roomnametxt;
    ArrayList<pSoftcake_list> pSC = new ArrayList<pSoftcake_list>();
    ArrayList<pSoftcake_list_sub> pSC_sub = new ArrayList<pSoftcake_list_sub>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onBackPressed() {
        Softcake_type_activity.super.onBackPressed();
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softcake_type_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Roomnametxt = (TextView)findViewById(R.id.room_txt);

        getSoftcake_list(session.getDateSelect());
        getSoftcake_list_sub(session.getDateSelect());
    }

    public void getSoftcake_list(final String DueDate) {
        class getSoftcake_list extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getSoftcakelist();

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
                        pSoftcake_list xSc = new pSoftcake_list();
                        Log.d("BBBB", c+"" );
                        xSc.setNumber(c.getString("Number"));
                        xSc.setNameth(c.getString("NameTH"));
                        xSc.setPrice(c.getString("Price"));
                        xSc.setProduce(c.getString("Qty"));
                        xSc.setUnit(c.getString("Unit"));
                        if(i==0){
                            DueDatetxt.setText(c.getString("DueDate"));
                            Roomnametxt.setText(c.getString("Roomname"));
                        }
                        pSC.add(xSc);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_softcake_1);
                    lv.setAdapter(new List_softcakeAdapter(Softcake_type_activity.this, pSC));

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
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                return result;
            }
        }
        getSoftcake_list ru = new getSoftcake_list();
        ru.execute(DueDate);
    }

    public void getSoftcake_list_sub(final String DueDate) {
        class getSoftcake_list_sub extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getSoftcakelistsub();

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
                        pSoftcake_list_sub xSc_sub = new pSoftcake_list_sub();
                        Log.d("BBBB", c+"" );
                        xSc_sub.setNameTH(c.getString("NameTH"));
                        xSc_sub.setForm(c.getString("Form"));
                        pSC_sub.add(xSc_sub);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_softcake_2);
                    lv.setAdapter(new List_softcake_2Adapter(Softcake_type_activity.this, pSC_sub));

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
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                return result;
            }
        }
        getSoftcake_list_sub ru = new getSoftcake_list_sub();
        ru.execute(DueDate);
    }

}
