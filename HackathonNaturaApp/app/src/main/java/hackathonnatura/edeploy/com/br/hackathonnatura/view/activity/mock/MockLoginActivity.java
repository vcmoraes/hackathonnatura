package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.mock;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.BaseActivity;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.HomeActivity_;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_mock_login)
public class MockLoginActivity extends BaseActivity {

    @AfterViews
    public void init() {

    }

    @Click(R.id.btn_login)
    void onLogin() {
        HomeActivity_.intent(this).start();
        finish();
    }
}
