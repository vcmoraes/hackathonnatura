package hackathonnatura.edeploy.com.br.hackathonnatura.api;

import android.annotation.SuppressLint;
import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import hackathonnatura.edeploy.com.br.hackathonnatura.listerner.ResponseListerner;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Participante;
import hackathonnatura.edeploy.com.br.hackathonnatura.request.UpdateRequest;
import hackathonnatura.edeploy.com.br.hackathonnatura.response.ErrorResponse;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;

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
    public void atualizaListaServidor(final ResponseListerner responseListerner) {
        ConsultoraDao consultoraDao = new ConsultoraDao(context);
        List<Consultora> consultoras = consultoraDao.recuperarTodos();
        ArrayList<Participante> participantes = new ArrayList<>();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        for (Consultora consultora : consultoras) {
            Participante participante = new Participante();
            participante.setCnCode(consultora.getId());
            participante.setName(consultora.getNome());
            participante.setPhoneNumber(consultora.getTelefone());
            participante.setUserId("");
            if (consultora.getDateCheckin() != null) {
                participante.setCheckIn(df.format(consultora.getDateCheckin()));
            }
            if (consultora.getDateCheckout() != null) {
                participante.setCheckOut(df.format(consultora.getDateCheckout()));
            }
            participante.setConfirmationCode(consultora.getUuid());
            participantes.add(participante);
        }
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setEventoID(1234);
        updateRequest.setParticipantes(participantes);
        apiRetrofit.getAPI().postUpdateUsers(updateRequest).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<Response>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response value) {
                responseListerner.success();
            }

            @Override
            public void onError(Throwable e) {
                try {
                    if (e instanceof HttpException) {
                        HttpException httpException = (HttpException) e;
                        if (httpException.code() == 503 || httpException.code() == 500 || httpException.code() == 404) {
                            ErrorResponse errorResponse = new ErrorResponse();
                            errorResponse.setMen("Verifique sua conexão e tente novamente!");
                            responseListerner.error(errorResponse);
                            return;
                        }
                    } else if (e instanceof SocketTimeoutException) {
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.setMen("Verifique sua conexão e tente novamente!");
                        responseListerner.error(errorResponse);
                        return;
                    }
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMen("Verifique sua conexão e tente novamente!");
                    responseListerner.error(errorResponse);
                } catch (Exception ee) {
                    try {
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.setMen("Verifique sua conexão e tente novamente!");
                        responseListerner.error(errorResponse);
                    } catch (Exception ignore) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
