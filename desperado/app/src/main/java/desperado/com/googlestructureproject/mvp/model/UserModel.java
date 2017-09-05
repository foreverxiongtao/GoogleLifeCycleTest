package desperado.com.googlestructureproject.mvp.model;


import desperado.com.googlestructureproject.entity.UserEntity;
import desperado.com.googlestructureproject.mvp.model.base.BaseModel;
import io.reactivex.Flowable;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2017/9/5       17:38
 *
 * 描 述 :用户信息model类
 *
 * 修订日期 :
 */
public class UserModel extends BaseModel {

    public Flowable<UserEntity> getUsers() {
        return getAPIService().getUsers();
    }
}
