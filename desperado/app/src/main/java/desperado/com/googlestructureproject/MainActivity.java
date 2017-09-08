package desperado.com.googlestructureproject;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import desperado.com.googlestructureproject.entity.UserDataEntity;
import desperado.com.googlestructureproject.entity.UserEntity;
import desperado.com.googlestructureproject.mvp.model.UserModel;
import desperado.com.googlestructureproject.mvp.presenter.UserPresenter;
import desperado.com.googlestructureproject.mvp.presenter.base.BasePresenter;
import desperado.com.googlestructureproject.mvp.view.base.BaseView;


public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner, BaseView<UserEntity> {

    LifecycleRegistry registry = new LifecycleRegistry(this);
    private TextView tv_main_content;
    private UserPresenter mPresenter;
    private ArrayList<UserDataEntity> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_main_content = (TextView) findViewById(R.id.tv_main_content);
        mPresenter = new UserPresenter();
        mPresenter.attachView(this);
        mPresenter.autoManageLiefeCycle(registry);
//        MyObserver myObserver = new MyObserver();
//        registry.addObserver(new BasePresenter() {
//        });
//        registry.addObserver(myObserver);
    }


//    public class MyObserver implements LifecycleObserver {
//        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//        public void onResume() {
//            Log.e("onResume", "onResume");
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//        public void onPause() {
//            Log.e("onPause", "onPause");
//        }
//    }


    @Override
    public void handleData(UserEntity userEntity) {
        if (userEntity != null && userEntity.result != null && !userEntity.result.isEmpty()) {
            tv_main_content.setText(JSON.toJSONString(userEntity));
            result = userEntity.result;
        }
    }

    @Override
    public void loadDataCompleted() {
        Log.e("MainActivity", "loadDataCompleted");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("MainActivity", "onDestroy");
    }

    @Override
    public void startToLoadData() {
        Log.e("MainActivity", "startToLoadData");
    }

    @Override
    public void laodDataError(String errMsg) {
        Toast.makeText(this, errMsg + "", Toast.LENGTH_SHORT).show();
    }

    public void getUserInfo(View view) {
        mPresenter.getUsers();
    }


    public void goToSecondActivity(View view) {
        Intent localIntent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("result", result);
        localIntent.putExtras(bundle);
        startActivity(localIntent);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }
}
