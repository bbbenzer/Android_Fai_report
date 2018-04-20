package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Adapter.List_AddonAdapter;
import com.pose.breakreport.android_fai_report.Adapter.List_PieAdapter;
import com.pose.breakreport.android_fai_report.Adapter.List_Pie_subAdapter;
import com.pose.breakreport.android_fai_report.Properties.pAddon_list;
import com.pose.breakreport.android_fai_report.Properties.pPie_list;
import com.pose.breakreport.android_fai_report.Properties.pPie_list_sub;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Pie_type_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Roomnametxt;
    ArrayList<pPie_list> pPie = new ArrayList<pPie_list>();
    ArrayList<pPie_list_sub> pPie_sub = new ArrayList<pPie_list_sub>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onBackPressed() {
        Pie_type_activity.super.onBackPressed();
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_type_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Roomnametxt = (TextView)findViewById(R.id.room_txt);

        getPie_list(session.getDateSelect());
        getPie_list_sub(session.getDateSelect());
    }

    public void getPie_list(final String DueDate) {
        class getPie_list extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getPielist();

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
                        pPie_list xPie = new pPie_list();
                        Log.d("BBBB", c+"" );
                        xPie.setNumber(c.getString("Number"));
                        xPie.setNameth(c.getString("NameTH"));
                        xPie.setPrice(c.getString("Price"));
                        xPie.setProduce(c.getString("Qty"));
                        xPie.setUnit(c.getString("Unit"));
                        if(i==0){
                            DueDatetxt.setText(c.getString("DueDate"));
                            Roomnametxt.setText(c.getString("Roomname"));
                        }
                        pPie.add(xPie);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_pie);
                    lv.setAdapter(new List_PieAdapter(Pie_type_activity.this, pPie));

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
        getPie_list ru = new getPie_list();
        ru.execute(DueDate);
    }

    public void getPie_list_sub(final String DueDate) {
        class getPie_list extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getPielistsub();

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
                        pPie_list_sub xPiesub = new pPie_list_sub();
                        Log.d("BBBB", c+"" );
                        xPiesub.setNameTH(c.getString("NameTH"));
                        xPiesub.setForm(c.getString("Form"));
                        pPie_sub.add(xPiesub);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_pie_sub);
                    lv.setAdapter(new List_Pie_subAdapter(Pie_type_activity.this, pPie_sub));

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
        getPie_list ru = new getPie_list();
        ru.execute(DueDate);
    }
}
