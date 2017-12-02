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

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_mock_1_activity)
public class Mock1Activity extends BaseActivity {

    @ViewById
    ImageView imageMock;

    @AfterViews
    public void init() {
        Bitmap banner = Util.loadBitmap(R.drawable.mock_1, getResources());
        imageMock.setImageBitmap(banner);
    }

    @Click(R.id.image_mock)
    void onMock() {
        Mock2Activity_.intent(this).start();
        finish();
    }
}
