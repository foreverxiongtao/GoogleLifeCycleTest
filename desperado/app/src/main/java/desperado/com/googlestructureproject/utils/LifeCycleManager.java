package desperado.com.googlestructureproject.utils;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import desperado.com.googlestructureproject.mvp.presenter.base.BasePresenter;

/**
 * Created by win 10 on 2017/9/5.
 */

public final class LifeCycleManager implements LifecycleObserver {

    private Lifecycle mLifeCycle;
    private BasePresenter mPresenter;

    public LifeCycleManager() {

    }

    /***
     * activity  or fragment onResume方法可见时回调管理回调
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e("onResume", "onResume");
    }


    /**
     * activity or fragment onDestroy 方法调用时的回调
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void unsubcrib() {
        mPresenter.detachView();
        if (mLifeCycle != null) {
            mLifeCycle.removeObserver(this);
            mLifeCycle = null;
        }
    }

    public void registerDelegate(BasePresenter presenter, Lifecycle mRegister) {
        if (presenter != null && mRegister != null) {
            mPresenter = presenter;
            mLifeCycle = mRegister;
            mLifeCycle.addObserver(this);
        }
    }

}
