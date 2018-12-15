package base.lmings22.com.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class Util {

    public static boolean isDebug(Context context){
        boolean isDebug = context.getApplicationInfo()!=null&&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        return isDebug;
    }

    public static void Sleep(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
