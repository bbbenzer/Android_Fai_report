package com.pose.breakreport.android_fai_report.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pose.breakreport.android_fai_report.Properties.pAddon_list;
import com.pose.breakreport.android_fai_report.Properties.pAddon_list;
import com.pose.breakreport.android_fai_report.R;
import com.pose.breakreport.android_fai_report.xFunction.iFunction;

import java.util.ArrayList;

/**
 * Created by User on 19/4/2561.
 */

public class List_AddonAdapter extends ArrayAdapter{
    private ArrayList<pAddon_list> listData;
    private AppCompatActivity aActivity;
    private iFunction iFt = new iFunction();

    private String REGISTER_URL;

    public List_AddonAdapter(AppCompatActivity aActivity, ArrayList<pAddon_list> listData) {
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
        final View v = inflater.inflate(R.layout.list_addon, parent, false);
        final pAddon_list pList = listData.get(position);

        TextView number = (TextView)v.findViewById(R.id.number_txt);
        number.setText(pList.getNumber());

        TextView nameth = (TextView)v.findViewById(R.id.nameth_txt);
        nameth.setText(pList.getNameth());

        TextView price = (TextView)v.findViewById(R.id.price_txt);
        price.setText(pList.getPrice());

        TextView stock = (TextView)v.findViewById(R.id.stock_txt);
        stock.setText(pList.getStock());

        TextView produce2 = (TextView)v.findViewById(R.id.produce2_txt);
        produce2.setText(pList.getProduce2());

        TextView produce = (TextView)v.findViewById(R.id.produce_txt);
        produce.setText(pList.getProduce());

        TextView unit = (TextView)v.findViewById(R.id.unit_txt);
        unit.setText(pList.getUnit());

        return v;
    }
}
