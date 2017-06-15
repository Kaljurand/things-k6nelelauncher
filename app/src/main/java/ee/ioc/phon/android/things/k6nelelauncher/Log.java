package ee.ioc.phon.android.things.k6nelelauncher;

public final class Log {

    private Log() {
    }

    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static final String LOG_TAG = "things-k6nelelauncher";

    public static void i(String msg) {
        if (DEBUG) android.util.Log.i(LOG_TAG, msg);
    }

    public static void e(String msg) {
        if (DEBUG) android.util.Log.e(LOG_TAG, msg);
    }

    public static void e(String msg, Throwable throwable) {
        if (DEBUG) android.util.Log.e(LOG_TAG, msg, throwable);
    }
}