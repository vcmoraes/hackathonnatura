package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.RecyclerViewAdapter;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.viewholder.ConsultoraAnonimoViewHolder;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.UpdateList;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;
import hackathonnatura.edeploy.com.br.hackathonnatura.util.Constants;
import hackathonnatura.edeploy.com.br.hackathonnatura.view.activity.AnonymousActivity_;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EFragment(R.layout.fragment_anonimos)
public class AnonimosFragment extends BaseSubscriberFragment {

    @ViewById
    RecyclerView recyclerView;

    @ViewById
    LinearLayout llRegistrarAnonimo;

    @ViewById
    FloatingActionButton buttonAdd;

    private ArrayList<Consultora> listOptions = new ArrayList<>();
    private RecyclerViewAdapter<Consultora> adapter = new RecyclerViewAdapter<>(ConsultoraAnonimoViewHolder.class, listOptions);

    private ConsultoraDao consultoraDao;

    @AfterViews
    public void init() {
        consultoraDao = new ConsultoraDao(getContext());
        recyclerView.setAdapter(adapter);
        updateList();
    }

    private void updateList() {
        listOptions.clear();
        listOptions.addAll(consultoraDao.recuperaPorParametro(ConsultoraDao.COLUNA_ANONIMO, "1"));
        llRegistrarAnonimo.setVisibility(listOptions.isEmpty() ? View.VISIBLE : View.GONE);
        buttonAdd.setVisibility(listOptions.isEmpty() ? View.GONE : View.VISIBLE);
        recyclerView.setVisibility(listOptions.isEmpty() ? View.GONE : View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateList(UpdateList updateList) {
        updateList();
    }

    @Click(R.id.button_add)
    void onButtonAdd() {
        AnonymousActivity_.intent(getActivity()).startForResult(Constants.REQUEST_ANONYMOUS);
    }

    @Click(R.id.btn_registrar_anonimo)
    void onButtonRegistrarPresenca() {
        AnonymousActivity_.intent(getActivity()).startForResult(Constants.REQUEST_ANONYMOUS);
    }
}
