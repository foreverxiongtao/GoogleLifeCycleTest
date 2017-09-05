package desperado.com.googlestructureproject.mvp.model.base;


import desperado.com.googlestructureproject.global.API;
import desperado.com.googlestructureproject.global.NetWorkManager;

/*
 *
 *
 * 版 权 :@Copyright desperado版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/12/20       13:12
 *
 * 描 述 :数据源抽象类
 *
 * 修订日期 :
 */
public class BaseModel {
    private static API mApi;

    protected BaseModel() {
        if (mApi == null) {
            mApi = NetWorkManager.getManager().getAPI();
        }

    }

    public API getAPIService() {
        return mApi;
    }
}
