package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import android.support.annotation.NonNull;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vcmoraes on 05/09/17.
 */
@EBean(scope = EBean.Scope.Singleton)
class ApiRetrofit {

    @Bean
    Api api;

    private RequestRetrofit requestRetrofit;

    RequestRetrofit getAPI() {
        if (requestRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
                                    okhttp3.Request original = chain.request();
                                    okhttp3.Request.Builder requestBuilder = original.newBuilder()
                                            .header("appId", "123")
                                            .method(original.method(), original.body());
                                    okhttp3.Request request = requestBuilder.build();
                                    return chain.proceed(request);
                                }
                            })
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getBaseUrl())
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            requestRetrofit = retrofit.create(RequestRetrofit.class);
        }
        return requestRetrofit;
    }
}
