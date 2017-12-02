package hackathonnatura.edeploy.com.br.hackathonnatura.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Date;
import java.util.UUID;

import hackathonnatura.edeploy.com.br.hackathonnatura.sql.Entidade;

/**
 * Created by vcmoraes on 02/12/17.
 */

public class Consultora implements Entidade {

    private String id;
    private String uuid;

    private String nome;
    private String telefone;
    private boolean anonimo;

    private Date dateCheckin;
    private Date dateCheckout;

    @Override
    public String getId() {
        if (TextUtils.isEmpty(id)) {
            anonimo = true;
            id = getUuid();
        }
        return TextUtils.isEmpty(id) ? "" : id.trim();
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString().replace("-", "");
            if (uuid.length() > 6) {
                uuid = uuid.substring(0, 6);
            }
        }
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @NonNull
    public String getNome() {
        return TextUtils.isEmpty(nome) ? "" : nome.trim();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getTelefone() {
        return TextUtils.isEmpty(telefone) ? "" : telefone.trim();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    public void setAnonimo(boolean anonimo) {
        this.anonimo = anonimo;
    }

    public void setAnonimo(Integer anonimo) {
        this.anonimo = !(anonimo == null || anonimo == 0);
    }

    public Date getDateCheckin() {
        return dateCheckin;
    }

    public void setDateCheckin(Date dateCheckin) {
        this.dateCheckin = dateCheckin;
    }

    public Date getDateCheckout() {
        return dateCheckout;
    }

    public void setDateCheckout(Date dateCheckout) {
        this.dateCheckout = dateCheckout;
    }
}
