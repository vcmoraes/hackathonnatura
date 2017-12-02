package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by vcmoraes on 02/12/17.
 */

public class BaseSubscriberFragment extends BaseFragment {

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
