package com.njusw.tourtool.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.njusw.tourtool.activity.BaseActivity;


public class ActivityUtil {

    public static void start(Context c, Class<? extends BaseActivity> clazz, boolean ifFinishSelf){

        Intent intent = new Intent(c,clazz);
        c.startActivity(intent);

        if(ifFinishSelf) {
            ((Activity) c).finish();
        }
    }
}
