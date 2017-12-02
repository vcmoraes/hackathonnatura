package hackathonnatura.edeploy.com.br.hackathonnatura.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by vcmoraes on 24/08/17.
 */

public abstract class ViewHolderBind<M> extends RecyclerView.ViewHolder {

    public ViewHolderBind(ViewGroup parent, @NonNull @LayoutRes Integer layout) {
        super(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    public abstract void onBindViewHolder(final M model, int position);
}
