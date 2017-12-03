package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import hackathonnatura.edeploy.com.br.hackathonnatura.listerner.ResponseListerner;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;

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
    public void atualizaListaServidor(ResponseListerner responseListerner) {
        ConsultoraDao consultoraDao = new ConsultoraDao(context);
        List<Consultora> consultoras = consultoraDao.recuperarTodos();
    }
}
