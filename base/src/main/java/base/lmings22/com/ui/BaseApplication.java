package base.lmings22.com.ui;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import base.lmings22.com.util.Util;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Util.isDebug(this)) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
