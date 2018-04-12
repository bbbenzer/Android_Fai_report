package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Adapter.CustomerAdapter;
import com.pose.breakreport.android_fai_report.Properties.pCustomer;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;

public class Break_detail_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Customertxt;
    private TextView CusNametxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_detail_activity);

        session = new Session(getApplicationContext());

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Customertxt = (TextView)findViewById(R.id.customer_txt);
        CusNametxt = (TextView)findViewById(R.id.cusname_txt);


    }

    public void getDetail(String DueDate) {
        class getDetail extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getBreakDetail();

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

//                    for(int i=0;i<setRs.length();i++) {
//                        JSONObject c = setRs.getJSONObject(i);
//                        pCustomer xCus = new pCustomer();
//                        Log.d("BBBB", c+"" );
//                        xCus.setCus_Code(c.getString("Cus_Code"));
//                        xCus.setFullname(c.getString("Fullname"));
//                        if(i==0){
//                            session.setDueDate(c.getString("DueDate"));
//                        }
//                        pCus.add(xCus);
//                    }
//
//                    ListView lv = (ListView) findViewById(R.id.list_customer);
//                    lv.setAdapter(new CustomerAdapter(Summary_break_activity.this, pCus));
//                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            String Cus_Code = pCus.get(position).getCus_Code();
//                            session.setCusCode(Cus_Code);
//                            Intent intent = new Intent(Summary_break_activity.this, Break_detail_activity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });


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
        getDetail ru = new getDetail();
        ru.execute(DueDate);
    }
}
