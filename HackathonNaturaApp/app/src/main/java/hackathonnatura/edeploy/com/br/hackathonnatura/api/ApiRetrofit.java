package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

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
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getBaseUrl())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            requestRetrofit = retrofit.create(RequestRetrofit.class);
        }
        return requestRetrofit;
    }
}
