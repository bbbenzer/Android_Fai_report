package com.pose.breakreport.android_fai_report.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Properties.pBreakDetail;
import com.pose.breakreport.android_fai_report.R;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import java.util.ArrayList;

/**
 * Created by User on 11/4/2561.
 */

public class BreakdetailAdapter extends ArrayAdapter {

    private ArrayList<pBreakDetail> listData;
    private AppCompatActivity aActivity;
    private iFunction iFt = new iFunction();

    private String REGISTER_URL;

    public BreakdetailAdapter(AppCompatActivity aActivity, ArrayList<pBreakDetail> listData) {
        super(aActivity, 0, listData);
        this.aActivity= aActivity;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) aActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.list_break_detail, parent, false);
        final pBreakDetail pList = listData.get(position);

        TextView cusname = (TextView)v.findViewById(R.id.cusname_txt);
        if(pList.getCusname().equals("BLANK_TRUE")){
            cusname.setVisibility(View.GONE);
        }else {
            cusname.setText(pList.getCusname());
        }

        TextView detail = (TextView)v.findViewById(R.id.detail_txt);
        detail.setText(pList.getDetail());

        TextView qty = (TextView)v.findViewById(R.id.qty_txt);
        qty.setText(pList.getQty()+" ชุด");

        return v;
    }

}