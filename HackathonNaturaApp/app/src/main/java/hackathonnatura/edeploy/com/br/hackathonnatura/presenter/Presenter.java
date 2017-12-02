package hackathonnatura.edeploy.com.br.hackathonnatura.presenter;

/**
 * Created by vcmoraes on 21/08/17.
 */

public class Presenter<V> {

    private V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
