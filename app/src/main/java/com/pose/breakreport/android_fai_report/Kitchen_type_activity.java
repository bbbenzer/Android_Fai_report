package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Adapter.List_KitchenAdapter;
import com.pose.breakreport.android_fai_report.Properties.pKitchen_list;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Kitchen_type_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Roomnametxt;
    ArrayList<pKitchen_list> pKc = new ArrayList<pKitchen_list>();

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onBackPressed() {
        Kitchen_type_activity.super.onBackPressed();
        Intent back = new Intent(this,Order_bill_activity.class);
        startActivity(back);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_type_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Roomnametxt = (TextView)findViewById(R.id.room_txt);

        getKitchenlist(session.getDateSelect());
    }

    public void getKitchenlist(final String DueDate) {
        class getKitchenlist extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getKitchenlist();

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
                        pKitchen_list xKc = new pKitchen_list();
                        Log.d("BBBB", c+"" );
                        xKc.setNameTH(c.getString("NameTH"));
                        xKc.setProduce("["+c.getString("Qty")+"ชิ้น ]");
                        xKc.setForm(c.getString("Form"));
                        if(i==0){
                            DueDatetxt.setText(c.getString("DueDate"));
                            Roomnametxt.setText(c.getString("Roomname"));
                        }
                        pKc.add(xKc);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_kitchen);
                    lv.setAdapter(new List_KitchenAdapter(Kitchen_type_activity.this, pKc));

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
        getKitchenlist ru = new getKitchenlist();
        ru.execute(DueDate);
    }
}
