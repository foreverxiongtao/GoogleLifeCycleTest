package desperado.com.googlestructureproject.mvp.view.base;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2017/9/5       17:48
 *
 * 描 述 : v 层
 *
 * 修订日期 :
 */
public interface BaseView<T> {

    void handleData(T t);


    void loadDataCompleted();


    void startToLoadData();


    void laodDataError(String errMsg);
}
