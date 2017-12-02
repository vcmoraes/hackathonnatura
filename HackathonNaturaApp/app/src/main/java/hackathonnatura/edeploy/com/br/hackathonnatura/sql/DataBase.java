package hackathonnatura.edeploy.com.br.hackathonnatura.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao.ConsultoraDao;

/**
 * Created by VictorMoraes on 23/09/14.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String NOME_BANCO_DADOS = "hackathonnatura";
    private static final int VERSAO_BANCO_DADOS = 1;

    private static DataBase instance;

    public static DataBase getInstance(Context context) {
        if (instance == null) {
            instance = new DataBase(context);
        }
        return instance;
    }

    private DataBase(Context context) {
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConsultoraDao.SCRIPT_CRIACAO_TABELA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConsultoraDao.SCRIPT_DELECAO_TABELA);
        onCreate(db);
    }
}
