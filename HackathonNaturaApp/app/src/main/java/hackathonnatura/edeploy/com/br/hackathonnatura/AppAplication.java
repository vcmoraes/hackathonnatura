package hackathonnatura.edeploy.com.br.hackathonnatura;

import android.app.Application;

import hackathonnatura.edeploy.com.br.hackathonnatura.sql.DataBase;

/**
 * Created by vcmoraes on 02/12/17.
 */
public class AppAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBase.getInstance(this);
    }
}
