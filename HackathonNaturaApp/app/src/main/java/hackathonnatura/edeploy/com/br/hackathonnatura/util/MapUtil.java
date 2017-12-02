package hackathonnatura.edeploy.com.br.hackathonnatura.util;

import android.location.Location;

/**
 * Created by vcmoraes on 29/09/17.
 */

public class MapUtil {

    private static final float DISTANCE_MINIMUM_METERS = 30f;
    private static final int TWO_MINUTES = 1000 * 60 * 2;

    public static boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }
        if (location.distanceTo(currentBestLocation) < DISTANCE_MINIMUM_METERS || location.getAccuracy() > 50) {
            return false;
        }
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        double bestLat = currentBestLocation.getLatitude();
        double bestLon = currentBestLocation.getLongitude();
        boolean isSameLat = lat == bestLat;
        boolean isSameLon = lon == bestLon;

        if (isSameLat && isSameLon) {
            return false;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 100;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private static boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
