package hackathonnatura.edeploy.com.br.hackathonnatura.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import hackathonnatura.edeploy.com.br.hackathonnatura.BuildConfig;

/**
 * Created by vcmoraes on 24/08/17.
 */

public class Util {

    private static final float LDPI = 0.75f;
    private static final float MDPI = 1.0f;
    private static final float HDPI = 1.5f;
    private static final float XHDPI = 2.0f;
    private static final float XXHDPI_ = 2.625f;
    private static final float XXHDPI = 3.0f;
    private static final float DPI560 = 3.5f;
    private static final float XXXHDPI = 4.0f;

    public static Float convertDpToPixel(float dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        Float densidade = displayMetrics.density;
        return (dp * densidade);
    }

    public static float convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static void animateViewVisibility(final View view, final int visibility) {
        view.animate().cancel();
        view.animate().setListener(null);

        if (visibility == View.VISIBLE) {
            view.animate().translationX(1f).start();
            view.setVisibility(View.VISIBLE);
        } else {
            view.animate().setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(visibility);
                }

            }).translationX(view.getWidth()).start();
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void setMarginsInView(Context context, View view, int left, int top, int right, int bottom) {
        int marginLeftInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, left, context.getResources().getDisplayMetrics());
        int marginTopInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, top, context.getResources()
                        .getDisplayMetrics());
        int marginRightInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, right, context.getResources()
                        .getDisplayMetrics());
        int marginBottomInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, bottom, context.getResources()
                        .getDisplayMetrics());

        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(marginLeftInDp, marginTopInDp, marginRightInDp, marginBottomInDp);
            view.requestLayout();
        }
    }

    public static int resizeDialogOptionHeigth(float density) {
        int value = 0;
        if (density == LDPI) {
            value = 120;
        } else if (density == MDPI) {
            value = 250;
        } else if (density == HDPI) {
            value = 450;
        } else if (density == XHDPI) {
            value = 800;
        } else if (density == XXHDPI || density == XXHDPI_) {
            value = 1100;
        } else if (density == DPI560 || density == XXXHDPI) {
            value = 1500;
        }
        return value;
    }

    public static double distance(Double latitude, Double longitude, double e, double f) {
        double d2r = Math.PI / 180;
        double dlong = (longitude - f) * d2r;
        double dlat = (latitude - e) * d2r;
        double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(e * d2r) * Math.cos(latitude * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367 * c;
        return d;
    }

    private static void runSimpleAnimation(Context context, View view, int animationId) {
        view.startAnimation(AnimationUtils.loadAnimation(context, animationId
        ));
    }

    public static File takeScreenshot(View view) throws Exception {
        File file = null;

        View viewIgnore = view.findViewWithTag("ignore");
        if (viewIgnore != null) {
            viewIgnore.setVisibility(View.GONE);
        }

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            file = takeScreenshotScrollView(view, scrollView.getChildAt(0).getHeight(), scrollView.getChildAt(0).getWidth());
        }

        if (file == null) {
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            file = saveBitmapCacheDir(view.getContext(), bitmap);
        }

        view.setDrawingCacheEnabled(false);

        if (viewIgnore != null) {
            viewIgnore.setVisibility(View.VISIBLE);
        }
        return file;
    }

    private static File takeScreenshotScrollView(View view, int height, int width) throws Exception {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(ContextCompat.getColor(view.getContext(), android.R.color.white));
        }
        view.draw(canvas);
        return saveBitmapCacheDir(view.getContext(), bitmap);
    }

    public static void shareImage(@NonNull Context context, @NonNull File file, @Nullable String... aplication) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        if (aplication != null && aplication.length > 0) {
            String packageApp = aplication[0];
            if (isPackageInstalled(packageApp, context)) {
                share.setPackage(packageApp);
            } else {
                openPackage(context, packageApp);
            }
        }
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(share, "Share"));
    }

    private static boolean isPackageInstalled(String packagename, Context context) {
        try {
            context.getPackageManager().getPackageInfo(packagename, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void openPackage(@NonNull Context context, @NonNull String appPackageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException ignore2) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private static File saveBitmapCacheDir(Context context, Bitmap bitmap) throws IOException {
        File imagePath = new File(context.getCacheDir(), "images");
        File file = new File(imagePath, Calendar.getInstance().getTimeInMillis() + ".jpeg");
        if ((imagePath.exists() || imagePath.mkdir()) && (file.exists() || file.createNewFile())) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            fileOutputStream.write(bos.toByteArray());
        }
        return file;
    }

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static void setCursorDrawableColor(EditText editText, int color) {
        try {
            Field fCursorDrawableRes =
                    TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            Drawable[] drawables = new Drawable[2];
            Resources res = editText.getContext().getResources();
            drawables[0] = res.getDrawable(mCursorDrawableRes);
            drawables[1] = res.getDrawable(mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (final Throwable ignored) {
        }
    }

    public static boolean isEmailValido(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void setCursorEndEdittext(EditText... listEditText) {
        try {
            for (EditText editText : listEditText) {
                if (editText.getText() != null && editText.getText().length() > 0) {
                    editText.setSelection(editText.getText().length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeKeyboardEditText(Context context, EditText... editText) {
        try {
            for (EditText e : editText) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(e.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValueBR(Double value) {
        if (value != null) {
            return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Math.abs(value));
        }
        return "";
    }

    public static String getFormatedValue(Integer value) {
        if (value < 10) {
            return "0" + value;
        }
        return value.toString();
    }
}
