package desperado.com.googlestructureproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import desperado.com.googlestructureproject.db.AppDataBase;
import desperado.com.googlestructureproject.entity.UserDataEntity;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by win 10 on 2017/9/7.
 */

public class SecondActivity extends AppCompatActivity {

    private List<UserDataEntity> userDataEntities;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        dbinit();
    }

    final static Migration MIGRATION_1_2 =new Migration(1,2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("alter table user add column gender TEXT");
        }
    };

    private void init() {
        Bundle bundle = getIntent().getExtras();
        final ArrayList<UserDataEntity> result = bundle.getParcelableArrayList("result");
        if (result != null && !result.isEmpty()) {
            final AppDataBase db = Room.databaseBuilder(this, AppDataBase.class, "db-name").addMigrations(MIGRATION_1_2).build();
            Flowable.create(new FlowableOnSubscribe<ArrayList<UserDataEntity>>() {
                @Override
                public void subscribe(FlowableEmitter<ArrayList<UserDataEntity>> e) throws Exception {
                    e.onNext(result);
                }
            }, BackpressureStrategy.ERROR).observeOn(Schedulers.io()).doOnNext(new Consumer<ArrayList<UserDataEntity>>() {
                @Override
                public void accept(ArrayList<UserDataEntity> list) throws Exception {
                    db.userDao().insertList(result);
                    userDataEntities = db.userDao().getAll();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArrayList<UserDataEntity>>() {
                @Override
                public void accept(ArrayList<UserDataEntity> list) throws Exception {
                    Toast.makeText(SecondActivity.this, JSON.toJSONString(userDataEntities) + "", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    private void dbinit() {

    }
}
