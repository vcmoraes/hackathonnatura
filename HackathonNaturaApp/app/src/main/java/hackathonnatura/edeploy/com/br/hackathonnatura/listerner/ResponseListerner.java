package hackathonnatura.edeploy.com.br.hackathonnatura.listerner;

import hackathonnatura.edeploy.com.br.hackathonnatura.response.ErrorResponse;

/**
 * Created by vcmoraes on 05/09/17.
 */
public interface ResponseListerner {

    void success();

    void error(ErrorResponse errorResponse);
}
