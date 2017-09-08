package desperado.com.googlestructureproject.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import desperado.com.googlestructureproject.entity.UserDataEntity;
import desperado.com.googlestructureproject.entity.UserEntity;

/**
 * Created by win 10 on 2017/9/7.
 */

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<UserDataEntity> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<UserDataEntity> loadAllByIds(int[] userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserDataEntity... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertList(List<UserDataEntity> list);

    @Delete
    void delete(UserDataEntity user);
}