package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.mock;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.BaseActivity;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_mock_4_activity)
public class Mock4Activity extends BaseActivity {

    @AfterViews
    public void init() {

    }

    @Click(R.id.image_mock)
    void onMock() {
        Mock5Activity_.intent(this).start();
        finish();
    }
}
