package hackathonnatura.edeploy.com.br.hackathonnatura.contract;

/**
 * Created by vcmoraes on 03/12/17.
 */

public interface HomeContract {

    interface IHomeView {
        void onSucess();

        void onError(String men);
    }

    interface IHomePresenter {
        void atualizaListaServidor();
    }
}
