package com.pose.breakreport.android_fai_report;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.pose.breakreport.android_fai_report.Adapter.CustomerAdapter;
import com.pose.breakreport.android_fai_report.Properties.pCustomer;
import com.pose.breakreport.android_fai_report.xFunction.RegisterUserClass;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class Summary_break_activity extends AppCompatActivity {

    private DatePickerDialog mDatePicker;
    private Calendar mCalendar;
    private Button btnDate;
    private TextView txtDate;
    private ArrayList<pCustomer> pCus = new ArrayList<pCustomer>();

    private DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
                    mCalendar.set(year, month, day);
                    Date date = mCalendar.getTime();
                    String textDate = dateFormat.format(date);

                    txtDate.setText(textDate);
                    getCustomerList(txtDate.getText().toString());
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_break_activity);

        btnDate = (Button) findViewById(R.id.button_date);
        txtDate = (TextView) findViewById(R.id.text_date);

        mCalendar = Calendar.getInstance();

        mDatePicker = DatePickerDialog.newInstance(onDateSetListener,
                mCalendar.get(Calendar.YEAR),        // ปี
                mCalendar.get(Calendar.MONTH),       // เดือน
                mCalendar.get(Calendar.DAY_OF_MONTH),// วัน (1-31)
                false);                        // ให้สั่นหรือไม่?

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePicker.setYearRange(2000, 2030);
                mDatePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });


        getCustomerList(txtDate.getText().toString());



    }

    public void getCustomerList(String DueDate) {
        class getCustomerList extends AsyncTask<String, Void, String> {

            iFunction iFt = new iFunction();
            String REGISTER_URL = iFt.getCustomer();

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
                        pCustomer xCus = new pCustomer();
                        Log.d("BBBB", c+"" );
                        xCus.setCus_Code(c.getString("Cus_Code"));
                        xCus.setFullname(c.getString("Fullname"));
                        pCus.add(xCus);
                    }

                    ListView lv = (ListView) findViewById(R.id.list_customer);
                    lv.setAdapter(new CustomerAdapter(Summary_break_activity.this, pCus));


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
        getCustomerList ru = new getCustomerList();
        ru.execute(DueDate);
    }
}
