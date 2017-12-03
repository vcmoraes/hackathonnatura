package hackathonnatura.edeploy.com.br.hackathonnatura.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hackathonnatura.edeploy.com.br.hackathonnatura.model.Participante;

/**
 * Created by vcmoraes on 03/12/17.
 */

public class UpdateRequest {

    @SerializedName("event_id")
    private int eventoID;

    @SerializedName("participants")
    private ArrayList<Participante> participantes;

    public int getEventoID() {
        return eventoID;
    }

    public void setEventoID(int eventoID) {
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
