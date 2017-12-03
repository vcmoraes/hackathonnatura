package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Calendar;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.PagerAdapterFragment;
import hackathonnatura.edeploy.com.br.hackathonnatura.contract.HomeContract;
import hackathonnatura.edeploy.com.br.hackathonnatura.custom.CustomViewPager;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.UpdateList;
import hackathonnatura.edeploy.com.br.hackathonnatura.presenter.HomePresenter;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.AnonimosFragment_;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.RegistradosFragment_;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity implements HomeContract.IHomeView {

    @Bean
    HomePresenter presenter;

    @ViewById
    TabLayout tabLayout;

    @ViewById
    CustomViewPager viewPager;

    private ConsultoraDao consultoraDao;

    private boolean updateList;

    @AfterViews
    public void init() {
        presenter.setView(this);
        consultoraDao = new ConsultoraDao(this);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.registrados)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.anonimos)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ArrayList<Fragment> listFragment = new ArrayList<>();
        listFragment.add(RegistradosFragment_.builder().build());
        listFragment.add(AnonimosFragment_.builder().build());

        viewPager.setAdapter(new PagerAdapterFragment(getSupportFragmentManager(), listFragment));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (updateList) {
            updateList = false;
            post(new UpdateList());
        }
    }

    @OnActivityResult(Constants.REQUEST_BARCODE)
    void onResultBarcode(int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                try {
                    String contentBarcode = data.getStringExtra(Constants.CONTENT_BARCODE);
                    String result[] = contentBarcode.split(";");
                    Consultora consultora = consultoraDao.recuperarPorID(result[0]);
                    if (consultora == null) {
                        consultora = new Consultora();
                    }
                    consultora.setId(result[0]);
                    consultora.setNome(result[1]);
                    if (consultora.getDateCheckin() == null) {
                        consultora.setDateCheckin(Calendar.getInstance().getTime());
                    } else {
                        consultora.setDateCheckout(Calendar.getInstance().getTime());
                    }
                    consultoraDao.salvar(consultora);
                    updateList = true;
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
                break;
        }
    }

    @OnActivityResult(Constants.REQUEST_ANONYMOUS)
    void onResultAnonymous(int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                try {
                    String teste;
                    teste = "";
                    updateList = true;
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onSucess() {

    }

    @Override
    public void onError(String men) {

    }
}
