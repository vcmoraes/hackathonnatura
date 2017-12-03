package hackathonnatura.edeploy.com.br.hackathonnatura.presenter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import hackathonnatura.edeploy.com.br.hackathonnatura.api.Api;
import hackathonnatura.edeploy.com.br.hackathonnatura.contract.HomeContract;
import hackathonnatura.edeploy.com.br.hackathonnatura.listerner.ResponseListerner;
import hackathonnatura.edeploy.com.br.hackathonnatura.response.ErrorResponse;

/**
 * Created by vcmoraes on 03/12/17.
 */
@EBean
public class HomePresenter extends Presenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {

    @Bean
    Api api;

    @Override
    public void atualizaListaServidor() {
        api.getRequest().atualizaListaServidor(new ResponseListerner() {
            @Override
            public void success() {
                getView().onSucess();
            }

            @Override
            public void error(ErrorResponse errorResponse) {
                getView().onError(errorResponse.getMen());
            }
        });
    }
}
