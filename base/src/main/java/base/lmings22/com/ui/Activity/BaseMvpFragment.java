package base.lmings22.com.ui.Activity;

import base.lmings22.com.ui.Presenter.BasePresenter;
import base.lmings22.com.ui.View.BaseView;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView{
    protected T presenter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
