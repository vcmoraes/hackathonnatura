package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by sergio on 02/12/17.
 */

@EBean
class RequestImpl implements Request {

    @RootContext
    Context context;

    @Bean
    ApiRetrofit apiRetrofit;

    @Override
    public void atualizaListaServidor(ArrayList<Consultora> lisUpdate) {

    }
}
