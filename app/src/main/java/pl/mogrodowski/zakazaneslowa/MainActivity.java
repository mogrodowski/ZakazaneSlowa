package pl.mogrodowski.zakazaneslowa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.mogrodowski.zakazaneslowa.util.CardUpdater;

public class MainActivity extends Activity {

    private Button preferences;
    private Button update_cards;
    private Button start_game;

    private static final int OPTIONS_MENU_PREFERENCES = 0;
    private PortabooApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (PortabooApp) getApplication();

        setContentView(R.layout.activity_main);

        preferences = (Button) findViewById(R.id.button_config);
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                startActivity(new Intent(MainActivity.this, Preferences.class));
            }
        });

        start_game = (Button) findViewById(R.id.button_start_game);
        start_game.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg){
                startActivity(new Intent(MainActivity.this, TeamsActivity.class));
            }
        });

        update_cards = (Button) findViewById(R.id.button_update_cards);
        update_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg) {
                //TODO: jakis loader i update cards

                app.getDataManager().updateCards();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0, MainActivity.OPTIONS_MENU_PREFERENCES, 0, "Preferences").setIcon(android.R.drawable.ic_menu_preferences);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case OPTIONS_MENU_PREFERENCES:
                startActivity(new Intent(this, Preferences.class));
                break;
        }

        return false;
    }
}