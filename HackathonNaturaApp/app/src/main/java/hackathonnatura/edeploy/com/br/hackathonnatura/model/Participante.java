package hackathonnatura.edeploy.com.br.hackathonnatura.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vcmoraes on 03/12/17.
 */

public class Participante {

    @SerializedName("cn_code")
    private String cnCode;

    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("check_in")
    private String checkIn;

    @SerializedName("check_out")
    private String checkOut;

    @SerializedName("confirmation_code")
    private String confirmationCode;

    @NonNull
    public String getCnCode() {
        return TextUtils.isEmpty(cnCode) ? "" : cnCode;
    }

    public void setCnCode(String cnCode) {
        this.cnCode = cnCode;
    }

    @NonNull
    public String getName() {
        return TextUtils.isEmpty(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getPhoneNumber() {
        return TextUtils.isEmpty(phoneNumber) ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    public String getUserId() {
        return TextUtils.isEmpty(userId) ? "" : userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getCheckIn() {
        return TextUtils.isEmpty(checkIn) ? "" : checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    @NonNull
    public String getCheckOut() {
        return TextUtils.isEmpty(checkOut) ? "" : checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @NonNull
    public String getConfirmationCode() {
        return TextUtils.isEmpty(confirmationCode) ? "" : confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
