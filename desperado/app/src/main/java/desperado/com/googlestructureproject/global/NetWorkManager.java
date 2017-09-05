package desperado.com.googlestructureproject.global;


import java.util.concurrent.TimeUnit;

import desperado.com.googlestructureproject.utils.conventer.FastJsonConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/*
 *
 *
 * 版 权 :@Copyright 中海互联版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2017/9/5       17:37
 *
 * 描 述 :网络管理类
 *
 * 修订日期 :
 */

public class NetWorkManager {
    private static volatile boolean mIsDebug = true;
    private static volatile NetWorkManager mInstance;
    private static volatile API mApi;
    private static volatile API mDownloadApi;

    public static NetWorkManager getManager() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 是否开启debug模式
     *
     * @param isDebug
     */
    public void setmIsDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }

    public API getAPI() {
        if (mApi == null) {
            OkHttpClient client = null;
            Retrofit retrofit = null;
            client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
            if (mIsDebug) {
                retrofit = new Retrofit.Builder().baseUrl(API.DEBUG_BASE_URL).client(client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            } else {
                retrofit = new Retrofit.Builder().baseUrl(API.PRODUCT_BASE_URL).client(client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
            }
            mApi = retrofit.create(API.class);
        }
        return mApi;
    }

    public API getDownloadAPI(OkHttpClient client) {
        Retrofit retrofit = null;
        if (mIsDebug) {
            retrofit = new Retrofit.Builder().baseUrl(API.DEBUG_BASE_URL).client(client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        } else {
            retrofit = new Retrofit.Builder().baseUrl(API.PRODUCT_BASE_URL).client(client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }
        mDownloadApi = retrofit.create(API.class);
        return mDownloadApi;
    }
}
