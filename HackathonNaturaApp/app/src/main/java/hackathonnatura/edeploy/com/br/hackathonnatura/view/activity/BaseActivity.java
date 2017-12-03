package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by vcmoraes on 22/08/17.
 */
@EBean
public class BaseActivity extends AppCompatActivity {

    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

    public void onClick(View view) {
    }
}
