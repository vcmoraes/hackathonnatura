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

public class ConsultoraRegistradaViewHolder extends ViewHolderBind<Consultora> {

    private TextView siglaNome, nome, codigoConsultora;
    private ImageView checkbox;

    public ConsultoraRegistradaViewHolder(RecyclerView parent) {
        super(parent, R.layout.item_list_consultora_registrada);
        siglaNome = itemView.findViewById(R.id.sigla_nome);
        nome = itemView.findViewById(R.id.nome);
        codigoConsultora = itemView.findViewById(R.id.codigo_consultora);
        checkbox = itemView.findViewById(R.id.checkbox);
    }

    @Override
    public void onBindViewHolder(Consultora model, int position) {
        siglaNome.setText(getSiglasNome(model.getNome()));
        nome.setText(model.getNome());
        codigoConsultora.setText(String.format("Código: %s", model.getId()));
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
