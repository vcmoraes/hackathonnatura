package hackathonnatura.edeploy.com.br.hackathonnatura.sql.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hackathonnatura.edeploy.com.br.hackathonnatura.sql.DataBase;
import hackathonnatura.edeploy.com.br.hackathonnatura.sql.Entidade;

/**
 * Created by VictorMoraes on 23/09/14.
 */
public abstract class Dao<T extends Entidade> {

    private SQLiteDatabase dataBase = null;

    public Dao(Context context) {
        DataBase persistenceHelper = DataBase.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }

    public abstract String getNomeColunaPrimaryKey();

    public abstract String getNomeTabela();

    public abstract ContentValues entidadeParacontentValues(T entidade);

    public abstract T contentValuesParaEntidade(ContentValues contentValues);

    public void salvar(T entidade) {
        try {
            dataBase.beginTransaction();
            ContentValues values = entidadeParacontentValues(entidade);
            dataBase.insertWithOnConflict(getNomeTabela(), null, values, SQLiteDatabase.CONFLICT_REPLACE);
            dataBase.setTransactionSuccessful();
            dataBase.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(List<T> listEntidades) {
        try {
            dataBase.beginTransaction();
            for (T entidade : listEntidades) {
                ContentValues values = entidadeParacontentValues(entidade);
                dataBase.insertWithOnConflict(getNomeTabela(), null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            dataBase.setTransactionSuccessful();
            dataBase.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(HashMap<Integer, T> listEntidades) {
        try {
            dataBase.beginTransaction();
            for (T entidade : listEntidades.values()) {
                ContentValues values = entidadeParacontentValues(entidade);
                dataBase.insertWithOnConflict(getNomeTabela(), null, values, SQLiteDatabase.CONFLICT_REPLACE);
            }
            dataBase.setTransactionSuccessful();
            dataBase.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletar(T t) {
        try {
            String[] valoresParaSubstituir = {
                    String.valueOf(t.getId())
            };

            dataBase.delete(getNomeTabela(), getNomeColunaPrimaryKey() + " =  ?", valoresParaSubstituir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarTodos() {
        dataBase.execSQL("DELETE FROM " + getNomeTabela());
    }

    public void editar(T t) {
        try {
            ContentValues valores = entidadeParacontentValues(t);

            String[] valoresParaSubstituir = {
                    String.valueOf(t.getId())
            };
            dataBase.update(getNomeTabela(), valores, getNomeColunaPrimaryKey() + " = ?", valoresParaSubstituir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> recuperarTodos() {
        String queryReturnAll = "SELECT * FROM " + getNomeTabela();
        return recuperarPorQuery(queryReturnAll);
    }

    public T recuperarPorID(String id) {
        String queryOne = "SELECT * FROM " + getNomeTabela() + " where " + getNomeColunaPrimaryKey() + " = '" + id + "'";
        List<T> result = recuperarPorQuery(queryOne);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<T> recuperarPorQuery(String query) {
        List<T> result = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = dataBase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    ContentValues contentValues = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                    T t = contentValuesParaEntidade(contentValues);
                    result.add(t);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return result;
    }

    public T recuperarPorQueryUnico(String query) {
        List<T> result = recuperarPorQuery(query);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<T> recuperaPorParametro(String nomeColuna, String keyBusca) {
        String query = "SELECT * FROM " + getNomeTabela() + " WHERE " + nomeColuna.trim() + " = '" + keyBusca.trim() + "'";
        return recuperarPorQuery(query);
    }

    public List<T> recuperaPorParametroDezUltimos(String nomeColuna, String keyBusca, String orderBy) {
        String query = "SELECT * FROM " + getNomeTabela() + " WHERE " + nomeColuna.trim() + " = '" + keyBusca.trim() + "' ORDER BY " + orderBy + " DESC LIMIT 10";
        return recuperarPorQuery(query);
    }

    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public byte[] marshall(Parcelable parceable) {
        Parcel parcel = Parcel.obtain();
        parceable.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();
        parcel.recycle(); // not sure if needed or a good idea
        return bytes;
    }

    public Parcel unmarshall(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0); // this is extremely important!
        return parcel;
    }

    public <T> T unmarshall(byte[] bytes, Parcelable.Creator<T> creator) {
        Parcel parcel = unmarshall(bytes);
        return creator.createFromParcel(parcel);
    }

    public void fecharConexao() {
        if (dataBase != null && dataBase.isOpen())
            dataBase.close();
    }
}

