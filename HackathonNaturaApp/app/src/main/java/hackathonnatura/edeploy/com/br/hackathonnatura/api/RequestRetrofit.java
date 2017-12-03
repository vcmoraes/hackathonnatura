package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import hackathonnatura.edeploy.com.br.hackathonnatura.request.UpdateRequest;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sergio on 02/12/17.
 */

interface RequestRetrofit {

    @POST("events")
    Observable<Object> postUpdateUsers(@Body UpdateRequest updateRequest);
}
