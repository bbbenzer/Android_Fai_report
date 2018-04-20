package com.pose.breakreport.android_fai_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.pose.breakreport.android_fai_report.Adapter.RoomAdapter;
import com.pose.breakreport.android_fai_report.Properties.pRoom;
import com.pose.breakreport.android_fai_report.xFunction.Session;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Order_bill_activity extends AppCompatActivity {

    private Session session;
    private DatePickerDialog mDatePicker;
    private Calendar mCalendar;
    private Button btnDate;
    private TextView txtDate;
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

    private DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {

                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
                    mCalendar.set(year, month, day);
                    Date date = mCalendar.getTime();
                    String textDate = dateFormat.format(date);

                    txtDate.setText(textDate);
                    session.setDateSelect(textDate);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_bill_activity);

        session = new Session(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        txtDate.setText(session.getDateSelect());

        ArrayList menu = new ArrayList();
        menu.add("1.พาย-ครัวซอง");
        menu.add("2.ขนมปังไส้พี่ดาว");
        menu.add("3.ขนมปังพี่บี พี่ลัย");
        menu.add("4.ห้องแซนวิช");
        menu.add("5.ห้องพายถ้วย ป้าเครือ");
        menu.add("6.ห้องขนมปังจืด");
        menu.add("7.ห้องแต่งเค้ก");
        menu.add("8.ห้องพี่หอม");
        menu.add("9.ห้องซอฟเค้ก");
        menu.add("10.ห้องครัว-ไส้สังขยา");



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
                Intent intent;
                String datecheck = txtDate.getText().toString();
                if(!datecheck.equals("")) {
                    switch (position) {
                        case 0:
                            intent = new Intent(Order_bill_activity.this, Normal_type_activity.class);
                            intent.putExtra("roomtype", "1");
                            startActivity(intent);
                            finish();
                            break;
                        case 1:
                            intent = new Intent(Order_bill_activity.this, Normal_type_activity.class);
                            intent.putExtra("roomtype", "10");
                            startActivity(intent);
                            finish();
                            break;
                        case 2:
                            intent = new Intent(Order_bill_activity.this, Normal_type_activity.class);
                            intent.putExtra("roomtype", "17");
                            startActivity(intent);
                            finish();
                            break;
                        case 3:
                            intent = new Intent(Order_bill_activity.this, Addon_type_activity.class);
                            intent.putExtra("roomtype", "sandwish");
                            startActivity(intent);
                            finish();
                            break;

                        case 4:
                            intent = new Intent(Order_bill_activity.this, Pie_type_activity.class);
                            startActivity(intent);
                            finish();
                            break;

                        case 5:
                            intent = new Intent(Order_bill_activity.this, Addon_type_activity.class);
                            intent.putExtra("roomtype", "12");
                            startActivity(intent);
                            finish();
                            break;

                        case 6:
                            intent = new Intent(Order_bill_activity.this, Addon_type_activity.class);
                            intent.putExtra("roomtype", "cake");
                            startActivity(intent);
                            finish();
                            break;

                        case 7:
                            intent = new Intent(Order_bill_activity.this, Addon_type_activity.class);
                            intent.putExtra("roomtype", "20");
                            startActivity(intent);
                            finish();
                            break;

                        case 8:
                            intent = new Intent(Order_bill_activity.this, Softcake_type_activity.class);
                            startActivity(intent);
                            finish();
                            break;

                        case 9:
                            intent = new Intent(Order_bill_activity.this, Kitchen_type_activity.class);
                            startActivity(intent);
                            finish();
                            break;

                        default:
                            Log.d("Error", "Error enter list");
                            break;

                    }
                }else{
                Toast.makeText(Order_bill_activity.this, "กรุณาเลือกวันที่", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
