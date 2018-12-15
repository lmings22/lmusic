package lmusic.lmings22.com.Contract;


import com.tbruyelle.rxpermissions2.Permission;

import base.lmings22.com.ui.View.BaseView;
import lmusic.lmings22.com.Activity.SplashActivity;

public interface SplashContract {
    interface View extends BaseView{

        /**
         * 权限申请
         */
        void applyPermission();

        /**
         * 权限通过
         */
        void onPermissionGranted(Permission permission);

        /**
         * 权限可再申请
         */
        void onShowPermissionRationale(Permission permission);

        /**
         * 权限永久拒绝
         */
        void onPermissionDeny(Permission permission);

        /**
         * 跳转到主界面
         */
        void runMainActivity();

    }

    interface Presenter{
    }
}
