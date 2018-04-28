package com.jzx.net.netsetting.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Function:
 * Created by TianMing.Xiong on 18-4-9.
 */

public class ToastUtil {

    private static Toast toast;

    public static void showToast(Context c, String s){
        if(toast==null){
            toast = Toast.makeText(c.getApplicationContext(),s+"",Toast.LENGTH_SHORT);
        }else{
            toast.setText(s+"");
        }
        toast.show();
    }
}
