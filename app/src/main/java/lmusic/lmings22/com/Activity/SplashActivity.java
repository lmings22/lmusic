package lmusic.lmings22.com.Activity;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;


import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import base.lmings22.com.ui.Activity.BaseMvpActivity;
import lmusic.lmings22.com.Contract.SplashContract;
import lmusic.lmings22.com.Presenter.SplashPresenter;
import lmusic.lmings22.com.R;

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    private static final String TAG = "SplashActivity";

    private static final int REQUEST_OPEN_SETTINGS_CODE = 0x22;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        applyPermission();

        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
            finish();
        }

        runMainActivity();
    }

    @Override
    public void runMainActivity() {

    }

    @Override
    public void applyPermission() {
        Log.d(TAG, "applyPermission: start apply");
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE)
                .subscribe(permission -> {
                    if(permission.granted)
                        onPermissionGranted(permission);
                    else if(permission.shouldShowRequestPermissionRationale)
                        onShowPermissionRationale(permission);
                    else
                        onPermissionDeny(permission);
                });
    }

    @Override
    public void onPermissionGranted(Permission permission) {
        Log.i(TAG,permission.name + "granted");
    }

    @Override
    public void onShowPermissionRationale(Permission permission) {
        finish();
    }

    @Override
    public void onPermissionDeny(Permission permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("权限申请")
                .setMessage("请在打开的窗口的权限中开启" + permission.name + "权限，以正常使用本应用")
                .setPositiveButton("设置", (dialog,which) -> openApplicationSettings(REQUEST_OPEN_SETTINGS_CODE))
                .setNegativeButton("取消", (dialog,which) -> finish());
        builder.setCancelable(false);
        builder.show();
    }


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
