package pl.mogrodowski.zakazaneslowa;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Preferences extends PreferenceActivity {

    private EditTextPreference time;
    //TODO: pozostale opcje + poczatkowo powiazac z konfiguracja (defaultowe pola)

    private PortabooApp app;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (PortabooApp) getApplication();

        addPreferencesFromResource(R.layout.activity_preferences);

        time = (EditTextPreference) getPreferenceScreen().findPreference("time");

        //setCheckBoxSummary(showSplash);
        setEditTextSummary(time);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener(){
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                if(key.equals("time")){
                    //TODO: sprawdz czy jest intem
                    setEditTextSummary(time);
                }

                //TODO: pozostale pola
            }
        });
    }

    /*
    private void setCheckBoxSummary(CheckBoxPreference pref) {
        if (pref.isChecked()) {
            pref.setSummary("Enabled");
        } else {
            pref.setSummary("Disabled");
        }
    }
    */

    private void setEditTextSummary(EditTextPreference pref){
        pref.setSummary("Zmieniono na: " + pref.getText());
    }
}
