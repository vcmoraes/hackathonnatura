package hackathonnatura.edeploy.com.br.hackathonnatura.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.adapter.ViewHolderBind;
import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by vcmoraes on 02/12/17.
 */

public class ConsultoraAnonimoViewHolder extends ViewHolderBind<Consultora> {

    private TextView siglaNome, nome, telefone;
    private ImageView checkbox;

    public ConsultoraAnonimoViewHolder(RecyclerView parent) {
        super(parent, R.layout.item_list_consultora_anonimo);
        siglaNome = itemView.findViewById(R.id.sigla_nome);
        nome = itemView.findViewById(R.id.nome);
        telefone = itemView.findViewById(R.id.telefone);
        checkbox = itemView.findViewById(R.id.checkbox);
    }

    @Override
    public void onBindViewHolder(Consultora model, int position) {
        siglaNome.setText(getSiglasNome(model.getNome()));
        nome.setText(model.getNome());
        telefone.setText(model.getTelefone());
        checkbox.setVisibility(model.isServer() ? View.VISIBLE : View.GONE);
    }

    private String getSiglasNome(String nome) {
        if (!TextUtils.isEmpty(nome)) {
            String nomes[] = nome.trim().split(" ");
            if (nomes.length == 1) {
                return nome.substring(0, 1) + nome.substring(0, 1);
            } else {
                return nome.substring(0, 1) + nomes[nomes.length - 1].substring(0, 1);
            }
        } else {
            return "";
        }
    }
}
