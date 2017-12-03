package hackathonnatura.edeploy.com.br.hackathonnatura.custom;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by vcmoraes on 03/12/17.
 */

public class Mask {

    public static String CPF_MASK = "###.###.###-##";
    public static String PHONE_MASK = "PHONE_MASK";
    public static String CEP_MASK = "#####-###";
    public static String DATE_MASK = "##/##/####";

    private static String PHONE_MASK_9 = "(##) #####-####";
    private static String PHONE_MASK_8 = "(##) ####-####";

    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll(" ", "")
                .replaceAll(",", "");
    }

    private static boolean isASign(char c) {
        return c == '.' || c == '-' || c == '/' || c == '(' || c == ')' || c == ',' || c == ' ';
    }

    public static String mask(String mask, String text) {
        int i = 0;
        String mascara = "";
        for (char m : mask.toCharArray()) {
            if (m != '#') {
                mascara += m;
                continue;
            }
            try {
                mascara += text.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }

        return mascara;
    }

    public static TextWatcher insert(final String maskSelection, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = Mask.unmask(s.toString());

                String mask;
                if (maskSelection.equalsIgnoreCase(PHONE_MASK)) {
                    mask = str.length() < 11 ? PHONE_MASK_8 : PHONE_MASK_9;
                } else {
                    mask = maskSelection;
                }

                String mascara = "";
                if (!str.isEmpty()) {
                    if (isUpdating) {
                        old = str;
                        isUpdating = false;
                        return;
                    }

                    int index = 0;
                    for (int i = 0; i < mask.length(); i++) {
                        char m = mask.charAt(i);
                        if (m != '#') {
                            if (index == str.length() && str.length() < old.length()) {
                                continue;
                            }
                            mascara += m;
                            continue;
                        }

                        try {
                            mascara += str.charAt(index);
                        } catch (Exception e) {
                            break;
                        }

                        index++;
                    }

                    if (mascara.length() > 0) {
                        char last_char = mascara.charAt(mascara.length() - 1);
                        boolean hadSign = false;
                        while (isASign(last_char) && str.length() == old.length()) {
                            mascara = mascara.substring(0, mascara.length() - 1);
                            last_char = mascara.charAt(mascara.length() - 1);
                            hadSign = true;
                        }

                        if (mascara.length() > 0 && hadSign) {
                            mascara = mascara.substring(0, mascara.length() - 1);
                        }
                    }
                    isUpdating = true;

                    ediTxt.setText(mascara);
                    ediTxt.setSelection(mascara.length());
                } else {
                    ediTxt.removeTextChangedListener(this);
                    ediTxt.setText(str);
                    ediTxt.addTextChangedListener(this);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        };
    }
}
