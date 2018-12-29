package lmusic.lmings22.com.Widget;

import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import lmusic.lmings22.com.R;
import lmusic.lmings22.com.Util.ToastUtil;

public class HomeToolbar extends Toolbar {

    Context   context;
    ImageView barNet;
    ImageView barFriends;
    ImageView barMusic;
    ImageView barSearch;

    public HomeToolbar(Context context){
        this(context,null);

    }

    public HomeToolbar(Context context, AttributeSet attrs){
        this(context, attrs, R.attr.toolbarStyle);
    }

    public HomeToolbar(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    public void initView(){
        barNet     = (ImageView)findViewById(R.id.bar_net);
        barFriends = (ImageView)findViewById(R.id.bar_friends);
        barMusic   = (ImageView)findViewById(R.id.bar_music);
        barSearch  = (ImageView)findViewById(R.id.bar_search);

        barNet.setOnClickListener(v->{
            ToastUtil.showShortToast(context, "barNet");
        });

        barFriends.setOnClickListener(v->{
            ToastUtil.showShortToast(context, "barFriends");
        });

        barMusic.setOnClickListener(v->{
            ToastUtil.showShortToast(context, "barMusic");
        });

        barSearch.setOnClickListener(v->{
            ToastUtil.showShortToast(context, "barSearch");
        });

    }
}
