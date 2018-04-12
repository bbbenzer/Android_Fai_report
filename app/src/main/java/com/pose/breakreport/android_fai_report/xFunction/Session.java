package com.pose.breakreport.android_fai_report.xFunction;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by User on 12/2/2561.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setCusCode(String CusCode) {
        prefs.edit().putString("CusCode", CusCode).commit();
    }

    public String getCusCode() {
        String CusCode = prefs.getString("CusCode","");
        return CusCode;
    }

    public void setDueDate(String DueDate) {
        prefs.edit().putString("DueDate", DueDate).commit();
    }

    public String getDueDate() {
        String DueDate = prefs.getString("DueDate","");
        return DueDate;
    }




}