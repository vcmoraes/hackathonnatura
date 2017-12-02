package hackathonnatura.edeploy.com.br.hackathonnatura.view.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.PagerAdapterFragment;
import hackathonnatura.edeploy.com.br.hackathonnatura.custom.CustomViewPager;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.AnonimosFragment_;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment.RegistradosFragment_;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @ViewById
    TabLayout tabLayout;

    @ViewById
    CustomViewPager viewPager;

    @AfterViews
    public void init() {
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

    @Click(R.id.button_add)
    void onButtonAdd() {
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
