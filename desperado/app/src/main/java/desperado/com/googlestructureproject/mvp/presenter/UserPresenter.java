package desperado.com.googlestructureproject.mvp.presenter;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import desperado.com.googlestructureproject.entity.UserEntity;
import desperado.com.googlestructureproject.mvp.model.UserModel;
import desperado.com.googlestructureproject.mvp.presenter.base.BasePresenter;
import desperado.com.googlestructureproject.mvp.view.base.BaseView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/*
 *
 *
 * 版 权 :@Copyright desperado版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/12/20       13:42
 *
 * 描 述 :用户信息控制业务类
 *
 * 修订日期 :
 */

public class UserPresenter extends BasePresenter<BaseView, UserModel> {

    public UserPresenter() {
        attachModel(new UserModel());
    }

    public void getUsers() {
        Flowable<UserEntity> userObservable = getModel().getUsers();
        Subscriber subscriber = new Subscriber<UserEntity>() {
            @Override
            public void onSubscribe(Subscription s) {
                bindDisposiable(s);
                s.request(Integer.MAX_VALUE);
                getView().startToLoadData();
            }

            @Override
            public void onNext(UserEntity userEntity) {
                getView().handleData(userEntity);
            }

            @Override
            public void onError(Throwable t) {
                getView().laodDataError(t.getMessage() + "");
            }

            @Override
            public void onComplete() {
                getView().loadDataCompleted();
            }
        };
        userObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);
    }
}
