package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by sergio on 02/12/17.
 */

@EBean
class RequestImpl implements Request {

    @RootContext
    Context context;

    @Bean
    ApiRetrofit apiRetrofit;
}
