package lmusic.lmings22.com.Activity;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Map;

import base.lmings22.com.ui.Activity.BaseActivity;
import lmusic.lmings22.com.R;

@Route(path = "/activity/splash")
public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    private static final int REQUEST_OPEN_SETTINGS_CODE = 0x22;

    // 需要申请的动态权限
    String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private Map<String,Boolean> permissionMap = new HashMap();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() { }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);
        applyPermission();
    }

    /**
     * 跳转至主页面
     */
    public void runMainActivity() {
        new Handler().postDelayed(() -> {
            ARouter.getInstance()
                    .build("/activity/main")
                    .navigation();
                },3000);

    }

    /**
     * 设置全屏
     */
    private void setFullScreen(){
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 申请权限
     */
    private void applyPermission() {
        Log.d(TAG, "applyPermission: start apply");

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(permissions)
                .subscribe(permission -> {
                    if(permission.granted)
                        onPermissionGranted(permission);
                    else if(permission.shouldShowRequestPermissionRationale)
                        onShowPermissionRationale(permission);
                    else
                        onPermissionDeny(permission);
                });
    }

    /**
     * 权限申请通过
     * @param permission    通过的权限
     */
    public void onPermissionGranted(Permission permission) {
        permissionMap.put(permission.name,true);
        for(String key : permissions){
            Boolean flag = permissionMap.get(key);
            if(flag == null || flag == false)
                return;
        }
        runMainActivity();
    }

    /**
     * 申请的权限拒绝但可再申请
     * @param permission    被拒绝权限
     */
    public void onShowPermissionRationale(Permission permission) {
        permissionMap.put(permission.name,false);
        finish();
    }

    /**
     * 申请的权限不可再申请
     * @param permission    被决绝的权限
     */
    public void onPermissionDeny(Permission permission) {
        permissionMap.put(permission.name,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("权限申请")
                .setMessage("请在打开的窗口的权限中开启" + permission.name + "权限，以正常使用本应用")
                .setPositiveButton("设置", (dialog,which) -> openApplicationSettings(REQUEST_OPEN_SETTINGS_CODE))
                .setNegativeButton("取消", (dialog,which) -> finish());
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 跳转至权限设置页面
     * @param requestCode   回调码
     */
    private void openApplicationSettings(int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:"+getPackageName()));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent,requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_OPEN_SETTINGS_CODE){
            applyPermission();
        }
    }
}
