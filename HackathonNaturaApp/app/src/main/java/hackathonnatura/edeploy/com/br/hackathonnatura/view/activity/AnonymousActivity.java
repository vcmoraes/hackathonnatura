package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.custom.Mask;
import hackathonnatura.edeploy.com.br.hackathonnatura.enums.DialogType;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.NaturaDialogFragment;

import static hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants.RESULT_ANONYMOUS;

@EActivity(R.layout.activity_anonymous)
public class AnonymousActivity extends BaseActivity {

    @ViewById
    EditText editTextName;

    @ViewById
    EditText editTextPhoneNumber;

    private ConsultoraDao consultoraDao;

    @AfterViews
    public void init() {
        consultoraDao = new ConsultoraDao(this);
        editTextPhoneNumber.addTextChangedListener(Mask.insert(Mask.PHONE_MASK, editTextPhoneNumber));
    }

    private boolean validate() {
        boolean result = true;

        if (editTextName.getText().toString().length() == 0) {
            result = false;
        }

        return result;
    }

    @Click(R.id.button_register)
    void onButtonRegister() {

        if (!this.validate()) {
            showMessage(getString(R.string.nome_cn_vazio), null, DialogType.ERROR);
            return;
        }

        Consultora consultora = new Consultora();

        consultora.setNome(editTextName.getText().toString());
        consultora.setTelefone(editTextPhoneNumber.getText().toString());
        consultora.setDateCheckin(Calendar.getInstance().getTime());
        consultoraDao.salvar(consultora);

        showMessage(editTextName.getText().toString(), consultora.getUuid(), DialogType.SUCCESS);
    }

    void showMessage(String message, String code, DialogType dialogType) {
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
                setResult(RESULT_ANONYMOUS);
                finish();
            }
        });
        naturaDialog.show(this.getFragmentManager(), "");
    }
}
