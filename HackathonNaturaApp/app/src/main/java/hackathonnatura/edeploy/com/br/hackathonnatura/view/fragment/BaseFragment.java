package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by vcmoraes on 22/08/17.
 */
@EBean
public abstract class BaseFragment extends Fragment {

    public void post(Object event) {
        EventBus.getDefault().post(event);
    }
}
