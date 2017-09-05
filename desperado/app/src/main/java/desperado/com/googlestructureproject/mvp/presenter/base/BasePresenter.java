package desperado.com.googlestructureproject.mvp.presenter.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;


import org.reactivestreams.Subscription;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import desperado.com.googlestructureproject.mvp.model.base.BaseModel;
import desperado.com.googlestructureproject.mvp.view.base.BaseView;
import desperado.com.googlestructureproject.utils.LifeCycleManager;

/*
 *
 *
 * 版 权 :@Copyright desperado版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/12/20       13:21
 *
 * 描 述 :业务处理基类  P层
 *
 * 修订日期 :
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements PresenterHelper<V, M> {
    private V mView;
    private M mModel;
    private Subscription mSubcribtion;

    protected void bindDisposiable(Subscription disposable) {
        if (mSubcribtion != null)
            this.mSubcribtion = disposable;
    }

    public BasePresenter() {

    }

    /***
     * 对于activity，fragment的根据生命周期管理mode层，避免内存泄漏
     * @param recycle
     */
    public void autoManageLiefeCycle(Lifecycle recycle) {
        if (recycle != null) {
            LifeCycleManager manager = new LifeCycleManager();
            manager.registerDelegate(BasePresenter.this, recycle);
        }
    }

    @Override
    public void attachView(V _view) {
        this.mView = _view;
    }

    @Override
    public void attachModel(M _model) {
        this.mModel = _model;
    }


    @Override
    public void detachView() {
        try {
            if (mSubcribtion != null) {
                mSubcribtion.cancel();
                Log.i("basePresenter", "取消订阅成功....");
            }
        } catch (Exception e) {
            Log.i("basePresenter", "取消订阅失败....");
        }
        this.mView = null;
    }


    protected V getView() {
        return mView;
    }

    protected M getModel() {
        return mModel;
    }
}
