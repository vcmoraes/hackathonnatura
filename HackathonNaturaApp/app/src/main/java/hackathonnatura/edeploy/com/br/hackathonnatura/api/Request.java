package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import hackathonnatura.edeploy.com.br.hackathonnatura.listerner.ResponseListerner;

/**
 * Created by sergio on 02/12/17.
 */

public interface Request {
    void atualizaListaServidor(ResponseListerner responseListerner);
}
