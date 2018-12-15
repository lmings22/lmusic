package base.lmings22.com.ui.Presenter;

import base.lmings22.com.ui.View.BaseView;

public class BasePresenter<V extends BaseView> {
    protected V view;

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }

    public boolean isAttach(){
        return view != null;
    }
}
