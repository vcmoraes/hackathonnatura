package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by sergio on 02/12/17.
 */

public interface Request {
    void atualizaListaServidor(ArrayList<Consultora> lisUpdate);

}
