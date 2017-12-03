package hackathonnatura.edeploy.com.br.hackathonnatura.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Participante;

/**
 * Created by vcmoraes on 03/12/17.
 */

public class UpdateRequest implements Serializable {

    @SerializedName("event_id")
    private String eventoID;

    @SerializedName("participants")
    private ArrayList<Participante> participantes;

    public String getEventoID() {
        return eventoID;
    }

    public void setEventoID(String eventoID) {
        this.eventoID = eventoID;
    }

    @NonNull
    public ArrayList<Participante> getParticipantes() {
        return participantes == null ? new ArrayList<Participante>() : participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }
}
