package pl.mogrodowski.zakazaneslowa;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import pl.mogrodowski.zakazaneslowa.data.DataManager;
import pl.mogrodowski.zakazaneslowa.data.DataManagerI;

public class PortabooApp extends Application {

    private ConnectivityManager cMgr;
    private DataManagerI dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        cMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        dataManager = new DataManager(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public boolean connectionPresent() {
        NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
        if((netInfo != null) && (netInfo.getState() != null)){
            return netInfo.getState().equals(NetworkInfo.State.CONNECTED);
        }
        return false;
    }

    public DataManagerI getDataManager() {
        return this.dataManager;
    }
}
