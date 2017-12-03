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

    @Click(R.id.button_register)
    void onButtonRegister() {
        Consultora consultora = new Consultora();

        consultora.setNome(editTextName.getText().toString());
        consultora.setTelefone(editTextPhoneNumber.getText().toString());
        consultora.setDateCheckin(Calendar.getInstance().getTime());
        consultoraDao.salvar(consultora);

        showMessage(editTextName.getText().toString(), consultora.getUuid(), DialogType.SUCCESS, true);
    }
}
