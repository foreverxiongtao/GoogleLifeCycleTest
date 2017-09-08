package desperado.com.googlestructureproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import desperado.com.googlestructureproject.dao.UserDao;
import desperado.com.googlestructureproject.entity.UserDataEntity;
import desperado.com.googlestructureproject.entity.UserEntity;

/**
 * Created by win 10 on 2017/9/7.
 */

@Database(entities = {UserDataEntity.class}, version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao userDao();
}