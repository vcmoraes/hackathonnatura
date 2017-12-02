package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.PermissionsUtil;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_barcode)
public class BarcodeActivity extends BaseActivity {

    @ViewById
    DecoratedBarcodeView barcodeScanner;

    @AfterViews
    public void init() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeScanner.resume();
        if (PermissionsUtil.isHavePermissionCamera(this)) {
            initBarcodeScanner();
        } else {
            PermissionsUtil.requestPermissionCamera(this);
        }
    }

    private void initBarcodeScanner() {
        barcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                Intent intent = new Intent();
                intent.putExtra(Constants.CONTENT_BARCODE, result.getText());
                setResult(RESULT_OK, intent);
                BarcodeActivity.this.finish();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScanner.pause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScanner.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionsUtil.isHavePermissionCamera(this)) {
            initBarcodeScanner();
        } else {
            Toast.makeText(this, getResources().getText(R.string.aviso_qrcode), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
