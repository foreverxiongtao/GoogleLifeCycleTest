package desperado.com.googlestructureproject.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by win 10 on 2017/9/7.
 */

@Entity(tableName = "user")
public class UserDataEntity implements Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "uid")
    public String id;
    public int age;
    public String name;
    public String gender="aaa";

    public UserDataEntity() {

    }

    protected UserDataEntity(Parcel in) {
        id = in.readString();
        age = in.readInt();
        name = in.readString();
        gender = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(gender);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserDataEntity> CREATOR = new Creator<UserDataEntity>() {
        @Override
        public UserDataEntity createFromParcel(Parcel in) {
            return new UserDataEntity(in);
        }

        @Override
        public UserDataEntity[] newArray(int size) {
            return new UserDataEntity[size];
        }
    };
}
