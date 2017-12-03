package hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao;

import android.content.ContentValues;
import android.content.Context;

import java.io.IOException;
import java.util.Date;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by vcmoraes on 02/12/17.
 */

public class ConsultoraDao extends Dao<Consultora> {

    public static final String NOME_TABELA = "CONSULTORA";
    public static final String SCRIPT_CRIACAO_TABELA = "CREATE TABLE IF NOT EXISTS CONSULTORA(id TEXT PRIMARY KEY, uuid TEXT, nome TEXT, telefone TEXT, anonimo INTEGER, date_in BLOB, date_out BLOB)";
    public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_UUID = "uuid";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_ANONIMO = "anonimo";
    public static final String COLUNA_DATE_IN = "date_in";
    public static final String COLUNA_DATE_OUT = "date_out";

    public ConsultoraDao(Context context) {
        super(context);
    }

    @Override
    public String getNomeColunaPrimaryKey() {
        return COLUNA_ID;
    }

    @Override
    public String getNomeTabela() {
        return NOME_TABELA;
    }

    @Override
    public ContentValues entidadeParacontentValues(Consultora entidade) {
        ContentValues contentValues = new ContentValues();
        try {
            contentValues.put(COLUNA_ID, entidade.getId());
            contentValues.put(COLUNA_UUID, entidade.getUuid());
            contentValues.put(COLUNA_NOME, entidade.getNome());
            contentValues.put(COLUNA_TELEFONE, entidade.getTelefone());
            contentValues.put(COLUNA_ANONIMO, entidade.isAnonimo());
            contentValues.put(COLUNA_DATE_IN, serialize(entidade.getDateCheckin()));
            contentValues.put(COLUNA_DATE_OUT, serialize(entidade.getDateCheckout()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    @Override
    public Consultora contentValuesParaEntidade(ContentValues contentValues) {
        Consultora consultora = new Consultora();
        consultora.setId(contentValues.getAsString(COLUNA_ID));
        consultora.setUuid(contentValues.getAsString(COLUNA_UUID));
        consultora.setNome(contentValues.getAsString(COLUNA_NOME));
        consultora.setTelefone(contentValues.getAsString(COLUNA_TELEFONE));
        consultora.setAnonimo(contentValues.getAsInteger(COLUNA_ANONIMO));
        try {
            consultora.setDateCheckin((Date) deserialize(contentValues.getAsByteArray(COLUNA_DATE_IN)));
            consultora.setDateCheckout((Date) deserialize(contentValues.getAsByteArray(COLUNA_DATE_OUT)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return consultora;
    }
}
