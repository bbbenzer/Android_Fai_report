package com.pose.breakreport.android_fai_report.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Properties.pPie_list_sub;
import com.pose.breakreport.android_fai_report.R;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import java.util.ArrayList;

/**
 * Created by User on 19/4/2561.
 */

public class List_Pie_subAdapter extends ArrayAdapter {
    private ArrayList<pPie_list_sub> listData;
    private AppCompatActivity aActivity;
    private iFunction iFt = new iFunction();

    private String REGISTER_URL;

    public List_Pie_subAdapter(AppCompatActivity aActivity, ArrayList<pPie_list_sub> listData) {
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
        final View v = inflater.inflate(R.layout.list_pie_sub, parent, false);
        final pPie_list_sub pList = listData.get(position);

        TextView nameth = (TextView)v.findViewById(R.id.nameth_txt);
        nameth.setText(pList.getNameTH());

        TextView form = (TextView)v.findViewById(R.id.produce_txt);
        form.setText(pList.getForm());

        return v;
    }
}
