package hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao;

import android.content.ContentValues;
import android.content.Context;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Consultora;

/**
 * Created by vcmoraes on 02/12/17.
 */

public class ConsultoraDao extends Dao<Consultora> {

    public static final String NOME_TABELA = "CONSULTORA";
    public static final String SCRIPT_CRIACAO_TABELA = "CREATE TABLE IF NOT EXISTS DIAS_SORTEIO(id INTEGER PRIMARY KEY)";
    public static final String SCRIPT_DELECAO_TABELA = "DROP TABLE IF EXISTS " + NOME_TABELA;

    public static final String COLUNA_ID = "id";

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
            if (entidade.getId() > 0) {
                contentValues.put(COLUNA_ID, entidade.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValues;
    }

    @Override
    public Consultora contentValuesParaEntidade(ContentValues contentValues) {
        Consultora consultora = new Consultora();
        consultora.setId(contentValues.getAsInteger(COLUNA_ID));
        return consultora;
    }
}
