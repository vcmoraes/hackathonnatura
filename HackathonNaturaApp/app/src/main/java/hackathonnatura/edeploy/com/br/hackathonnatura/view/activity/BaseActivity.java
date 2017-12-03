package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import hackathonnatura.edeploy.com.br.hackathonnatura.enums.DialogType;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.NaturaDialogFragment;

import static hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants.RESULT_ANONYMOUS;

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

    void showMessage(String message, String code, DialogType dialogType, final boolean isFinish) {
        NaturaDialogFragment naturaDialog = new NaturaDialogFragment();
        if (dialogType == DialogType.SUCCESS) {
            naturaDialog.setCnCode(this, code);
            naturaDialog.setCnName(this, message);
        } else {
            naturaDialog.setMessage(this, message, dialogType);
        }
        naturaDialog.setClickListener(new NaturaDialogFragment.ClickListener() {
            @Override
            public void onClick() {
                if (isFinish) {
                    setResult(RESULT_ANONYMOUS);
                    finish();
                }
            }
        });
        naturaDialog.show(this.getFragmentManager(), "");
    }
}
