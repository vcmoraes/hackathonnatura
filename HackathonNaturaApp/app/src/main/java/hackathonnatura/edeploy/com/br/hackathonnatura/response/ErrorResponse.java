package hackathonnatura.edeploy.com.br.hackathonnatura.response;

public class ErrorResponse {

    private String men;
    private boolean serverError;

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public boolean isServerError() {
        return serverError;
    }

    public void setServerError(boolean serverError) {
        this.serverError = serverError;
    }
}
