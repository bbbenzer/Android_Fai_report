package com.pose.breakreport.android_fai_report.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Properties.pKitchen_list;
import com.pose.breakreport.android_fai_report.R;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import java.util.ArrayList;

/**
 * Created by User on 20/4/2561.
 */

public class List_KitchenAdapter extends ArrayAdapter {

    private ArrayList<pKitchen_list> listData;
    private AppCompatActivity aActivity;
    private iFunction iFt = new iFunction();

    private String REGISTER_URL;

    public List_KitchenAdapter(AppCompatActivity aActivity, ArrayList<pKitchen_list> listData) {
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
        final View v = inflater.inflate(R.layout.list_kitchen, parent, false);
        final pKitchen_list pList = listData.get(position);


        TextView nameth = (TextView)v.findViewById(R.id.nameth_txt);
        nameth.setText(pList.getNameTH());

        TextView produce = (TextView)v.findViewById(R.id.produce_txt);
        produce.setText(pList.getProduce());

        TextView form = (TextView)v.findViewById(R.id.form_txt);
        form.setText(pList.getForm());

        return v;
    }
}
