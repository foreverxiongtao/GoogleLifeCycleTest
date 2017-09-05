package desperado.com.googlestructureproject.entity;

import java.util.List;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2017/9/5       17:49
 *
 * 描 述 :用户实体类
 *
 * 修订日期 :
 */

public class UserEntity {

    public List<UserDataEntity> result;

    public static class UserDataEntity {
        public String id;
        public int age;
        public String name;
    }
}
