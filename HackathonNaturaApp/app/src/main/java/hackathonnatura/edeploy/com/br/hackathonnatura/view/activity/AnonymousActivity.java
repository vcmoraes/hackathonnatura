package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.UpdateList;
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
    }

    private boolean validate() {
        return true;
    }

    @Click(R.id.button_register)
    void onButtonRegister() {

        if (!this.validate()) {
            return;
        }

        Consultora consultora = new Consultora();
        consultora.setId("");
        consultora.setNome(editTextName.getText().toString());
        consultora.setTelefone(editTextPhoneNumber.getText().toString());
        consultora.setDateCheckin(Calendar.getInstance().getTime());
        consultoraDao.salvar(consultora);
        post(new UpdateList());
    }
}
