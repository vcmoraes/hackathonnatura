package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.mock;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Util;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.BaseActivity;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.HomeActivity_;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_mock_4_activity)
public class Mock4Activity extends BaseActivity {

    @ViewById
    ImageView imageMock;

    @AfterViews
    public void init() {
        Bitmap banner = Util.loadBitmap(R.drawable.mock_4, getResources());
        imageMock.setImageBitmap(banner);
    }

    @Click(R.id.image_mock)
    void onMock() {
        HomeActivity_.intent(this).start();
        finish();
    }
}
