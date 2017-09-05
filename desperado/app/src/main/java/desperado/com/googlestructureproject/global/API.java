package desperado.com.googlestructureproject.global;


import desperado.com.googlestructureproject.entity.UserEntity;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Url;
/*
 *
 *
 * 版 权 :@Copyright desperado版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/12/20       13:13
 *
 * 描 述 :全局接口统一类
 *
 * 修订日期 :
 */

public interface API {

    /**
     * 测试
     */
    String DEBUG_BASE_URL = "http://192.168.6.167:8080";


    /**
     * 正式服务器地址
     */
    String PRODUCT_BASE_URL = "http://192.168.6.167:8080";


    /**
     * 根据点击类型为optional的类型去获取广告url
     *
     * @param url
     * @return
     */
    @GET("/android/user/list")
    Flowable<UserEntity> getUsers();
}
