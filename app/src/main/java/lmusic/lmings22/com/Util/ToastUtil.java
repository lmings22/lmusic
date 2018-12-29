package lmusic.lmings22.com.Util;

import android.content.Context;
import android.widget.Toast;


public class ToastUtil {
    private static Toast toast;

    public static void showLongToast(Context context, String message){
        showToast(context, message, Toast.LENGTH_LONG);
    }

    public static void showShortToast(Context context, String message){
        showToast(context, message, Toast.LENGTH_SHORT);
    }
    private static void showToast(Context context, String message, int type){
        if(toast == null){
            toast = Toast.makeText(context, message, type);
        }
        else
            toast.setText(message);
        toast.show();
    }
}
