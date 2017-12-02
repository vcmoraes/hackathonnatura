package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.RecyclerViewAdapter;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.viewholder.ConsultoraAnonimoViewHolder;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by vcmoraes on 02/12/17.
 */
@EFragment(R.layout.fragment_anonimos)
public class AnonimosFragment extends BaseFragment {

    @ViewById
    RecyclerView recyclerView;

    private ArrayList<Consultora> listOptions = new ArrayList<>();
    private RecyclerViewAdapter<Consultora> adapter = new RecyclerViewAdapter<>(ConsultoraAnonimoViewHolder.class, listOptions);

    @AfterViews
    public void init() {
        recyclerView.setAdapter(adapter);
    }
}
