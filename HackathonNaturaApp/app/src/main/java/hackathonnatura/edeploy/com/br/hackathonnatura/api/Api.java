package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by sergio on 02/12/17.
 */
@EBean(scope = EBean.Scope.Singleton)
public class Api {

    private String baseUrl;

    @Bean(RequestImpl.class)
    Request request;

    public Request getRequest() {
        return request;
    }

    public void setBaseURL(String url) {
        baseUrl = url;
    }

    public String getBaseUrl() {
        return "http://ubuntugroup4.brazilsouth.cloudapp.azure.com:3000/v1/";
    }
}
