package pl.mogrodowski.zakazaneslowa;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

public class PortabooApp extends Application {

    private ConnectivityManager cMgr;

    @Override
    public void onCreate() {
        super.onCreate();
        cMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public boolean connectionPresent() {
        NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
        if ((netInfo != null) && (netInfo.getState() != null)) {
            return netInfo.getState().equals(NetworkInfo.State.CONNECTED);
        }
        return false;
    }
}
