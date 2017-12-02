package hackathonnatura.edeploy.com.br.hackathonnatura.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by rbsilva on 05/09/2017.
 */

public class PermissionsUtil {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 98;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 99;

    public static boolean checkLocationPermission(final Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isHavePermissionLocation(Context context) {
        return isHavePermissionAccessCoarseLocation(context) && isHavePermissionAccessFineLocation(context);
    }

    public static void callsPermissionLocation(Activity activity) {
        try {
            boolean isHavePermissionAccessFineLocation = isHavePermissionAccessFineLocation(activity);
            boolean isHavePermissionAccessCoarseLocation = isHavePermissionAccessCoarseLocation(activity);
            ArrayList<String> permissions = new ArrayList<>();
            if (!isHavePermissionAccessFineLocation) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (!isHavePermissionAccessCoarseLocation) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (permissions.size() > 0) {
                callsPermission(activity, PermissionsUtil.MY_PERMISSIONS_REQUEST_LOCATION, permissions.toArray(new String[permissions.size()]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isHavePermissionAccessFineLocation(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isHavePermissionCamera(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissionCamera(Activity activity) {
        callsPermission(activity, MY_PERMISSIONS_REQUEST_CAMERA, Manifest.permission.CAMERA);
    }

    private static boolean isHavePermissionAccessCoarseLocation(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private static void callsPermission(Activity activity, int requestCode, String... permissoes) {
        try {
            ActivityCompat.requestPermissions(activity, permissoes,
                    requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void failureAuthorizePermissions() {

    }
}
