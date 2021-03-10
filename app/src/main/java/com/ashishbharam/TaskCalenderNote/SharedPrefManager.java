package com.ashishbharam.TaskCalenderNote;
/*
Created by Ashish Bharam on 22-Feb-21 at 4:32 PM.
Copyright (c) 2021 Ashish Bharam. All rights reserved.
*/

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "MySharedPref";

    private static SharedPrefManager prefInstance;
    private Context context;

    private SharedPrefManager(Context ctx) {
        this.context = ctx;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {

        if (prefInstance == null) {
            prefInstance = new SharedPrefManager(context);
        }
        return prefInstance;
    }

    public void saveNoteInfo(String noteDate, String note) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(noteDate, note);

        editor.apply();
    }

    public String getNoteInfo(String noteDate) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return preferences.getString(noteDate,null);
    }

    public void clear() {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();
        editor.apply();
    }

}
