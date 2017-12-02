package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.RecyclerViewAdapter;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.viewholder.ConsultoraRegistradaViewHolder;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.UpdateList;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EFragment(R.layout.fragment_registrados)
public class RegistradosFragment extends BaseSubscriberFragment {

    @ViewById
    RecyclerView recyclerView;

    private ArrayList<Consultora> listOptions = new ArrayList<>();
    private RecyclerViewAdapter<Consultora> adapter = new RecyclerViewAdapter<>(ConsultoraRegistradaViewHolder.class, listOptions);

    private ConsultoraDao consultoraDao;

    @AfterViews
    public void init() {
        consultoraDao = new ConsultoraDao(getContext());
        recyclerView.setAdapter(adapter);
        updateList();
    }

    private void updateList() {
        listOptions.clear();
        listOptions.addAll(consultoraDao.recuperaPorParametro(ConsultoraDao.COLUNA_ANONIMO, "0"));
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateList(UpdateList updateList) {
        updateList();
    }
}
