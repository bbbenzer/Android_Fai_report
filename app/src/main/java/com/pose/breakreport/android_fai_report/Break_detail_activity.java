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

import com.pose.breakreport.android_fai_report.Adapter.BreakdetailAdapter;
import com.pose.breakreport.android_fai_report.Adapter.CustomerAdapter;
import com.pose.breakreport.android_fai_report.Properties.pBreakDetail;
import com.pose.breakreport.android_fai_report.Properties.pCustomer;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.Session;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Break_detail_activity extends AppCompatActivity {

    private Session session;
    private TextView DueDatetxt;
    private TextView Customertxt;
    private TextView CusNametxt;
    ArrayList<pBreakDetail> pBD = new ArrayList<pBreakDetail>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_detail_activity);

        session = new Session(getApplicationContext());

        DueDatetxt = (TextView)findViewById(R.id.duedate_txt);
        Customertxt = (TextView)findViewById(R.id.customer_txt);
        CusNametxt = (TextView)findViewById(R.id.cusname_txt);

        getDetail(session.getDueDate(),session.getCusCode());

    }

    public void getDetail(String DueDate,String CusCode) {
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

                    for(int i=0;i<setRs.length();i++) {
                        JSONObject c = setRs.getJSONObject(i);
                        pBreakDetail xpBD = new pBreakDetail();
                        Log.d("BBBB", c+"" );
                        xpBD.setDetail(c.getString("NameTH"));
                        xpBD.setQty(c.getString("Qty"));
                        if(i==0){
                            DueDatetxt.setText(c.getString("DueDate"));
                            Customertxt.setText(c.getString("customer"));
                            CusNametxt.setText(c.getString("CusName"));
                            session.setDueDate(c.getString("DueDate"));
                            Log.d("BBBB",c.getString("DueDate"));
                            Log.d("BBBB",c.getString("customer"));
                            Log.d("BBBB",c.getString("CusName"));
                            Log.d("BBBB",c.getString("DueDate"));
                        }
                        pBD.add(xpBD);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_break_detail);
                    lv.setAdapter(new BreakdetailAdapter(Break_detail_activity.this, pBD));
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
                data.put("Cus_Code",params[1]);
                Log.d("DueDate = ", params[0]);
                Log.d("DueDate = ", params[1]);
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                return result;
            }
        }
        getDetail ru = new getDetail();
        ru.execute(DueDate,CusCode);
    }
}
