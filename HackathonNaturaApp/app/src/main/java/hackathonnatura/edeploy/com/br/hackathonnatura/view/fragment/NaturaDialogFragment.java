package hackathonnatura.edeploy.com.br.hackathonnatura.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hackathonnatura.edeploy.com.br.hackathonnatura.R;
import hackathonnatura.edeploy.com.br.hackathonnatura.enums.DialogType;

/**
 * Created by sergio on 03/12/17.
 */
public class NaturaDialogFragment extends DialogFragment {

    private ImageView imageStatus;
    private TextView textViewSuccessMessage;
    private TextView textViewCodeValue;
    private LinearLayout linearLayoutCnCode;
    private ClickListener clickListener;

    private String message;
    private String cnCode;
    private DialogType dialogType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_natura_dialog, container,
                false);
        this.textViewSuccessMessage = view.findViewById(R.id.text_view_success_message);
        this.textViewCodeValue = view.findViewById(R.id.text_view_code_value);
        this.linearLayoutCnCode = view.findViewById(R.id.linear_layout_cn_code);
        this.imageStatus = view.findViewById(R.id.image_status);

        if (this.cnCode == null) {
            this.linearLayoutCnCode.setVisibility(View.GONE);
        } else {
            this.linearLayoutCnCode.setVisibility(View.VISIBLE);
            this.textViewCodeValue.setText(this.cnCode.trim());
        }

        if (dialogType == DialogType.SUCCESS) {
            this.imageStatus.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.circle_check));
        } else {
            this.imageStatus.setImageDrawable(this.getActivity().getResources().getDrawable(R.drawable.error));
        }

        this.textViewSuccessMessage.setText(message);

        view.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissAllowingStateLoss();
                if (clickListener != null) {
                    clickListener.onClick();
                }
            }
        });
        setCancelable(false);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void setCnCode(Context context, @NonNull String cnCode) {
        this.cnCode = cnCode;
    }

    public void setCnName(Context context, @NonNull String cnName) {
        this.message = context.getString(R.string.dialog_success_message);
        this.message = message.replace("[USER]", cnName);

        this.dialogType = DialogType.SUCCESS;
    }

    public void setMessage(Context context, @NonNull String message, DialogType dialogType) {
        this.message = message;
        this.dialogType = dialogType;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick();
    }
}