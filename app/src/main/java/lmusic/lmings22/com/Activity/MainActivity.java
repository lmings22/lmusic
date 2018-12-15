package lmusic.lmings22.com.Activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import base.lmings22.com.ui.Activity.BaseMvpActivity;
import lmusic.lmings22.com.Contract.MainContract;
import lmusic.lmings22.com.Presenter.MainPresenter;
import lmusic.lmings22.com.R;

@Route(path = "/activity/main")
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
