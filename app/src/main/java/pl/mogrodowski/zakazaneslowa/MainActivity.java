package pl.mogrodowski.zakazaneslowa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pl.mogrodowski.zakazaneslowa.model.Card;
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
            public void onClick(View arg){
                if(app.connectionPresent()){
                    TextView update_cards_status = (TextView) findViewById(R.id.update_cards_status);
                    new CardUpdater(update_cards_status, app.getDataManager()).execute();
                }
                else{
                    Toast.makeText(app.getApplicationContext(), "Internet connection error", Toast.LENGTH_LONG).show();
                }
            }
        });

        for(Card c: app.getDataManager().getCards()){
            Log.i(Constants.LOG_TAG, "Karta: " + c.getHead_word());
        }
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