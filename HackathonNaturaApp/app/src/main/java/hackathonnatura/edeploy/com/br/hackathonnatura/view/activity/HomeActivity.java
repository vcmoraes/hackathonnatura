package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.content.Intent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @AfterViews
    public void init() {
        //TODO: INIT
    }

    @Click(R.id.btn_qrcode)
    void onLogin() {
        BarcodeActivity_.intent(this).startForResult(Constants.REQUEST_BARCODE);
    }

    @OnActivityResult(Constants.REQUEST_BARCODE)
    void onResultBarcode(int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                String contentBarcode = data.getStringExtra(Constants.CONTENT_BARCODE);
                break;
        }
    }
}
