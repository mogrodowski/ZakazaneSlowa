package pl.mogrodowski.zakazaneslowa;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.mogrodowski.zakazaneslowa.model.Team;

public class TeamsActivity extends Activity {

    private PortabooApp app;

    private Button button;
    private EditText team_1;
    private EditText team_2;
    private EditText team_3;
    private EditText team_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        app = (PortabooApp) getApplication();

        team_1 = (EditText) findViewById(R.id.editTextTeam1);
        team_2 = (EditText) findViewById(R.id.editTextTeam2);
        team_3 = (EditText) findViewById(R.id.editTextTeam3);
        team_4 = (EditText) findViewById(R.id.editTextTeam4);

        button = (Button) findViewById(R.id.buttonPlay);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                app.getGame().getTeams().clear();

                if(!isEditTextEmpty(team_1)){
                    Team team = new Team(team_1.getText().toString());
                    app.getGame().addTeam(team);
                }

                if(!isEditTextEmpty(team_2)){
                    Team team = new Team(team_2.getText().toString());
                    app.getGame().addTeam(team);
                }

                if(!isEditTextEmpty(team_3)){
                    Team team = new Team(team_3.getText().toString());
                    app.getGame().addTeam(team);
                }

                if(!isEditTextEmpty(team_4)){
                    Team team = new Team(team_4.getText().toString());
                    app.getGame().addTeam(team);
                }

                if(app.getGame().getTeams() != null && app.getGame().getTeams().size() >= 2){
                    Toast.makeText(TeamsActivity.this, "Wszystko ok. Zaczynam grę", Toast.LENGTH_SHORT).show();
                    //TODO: idz do kolejnego widoku
                }
                else{
                    Toast.makeText(TeamsActivity.this, "Za mało drużun", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isEditTextEmpty(final EditText editText) {
        return !((editText != null)
                && (editText.getText() != null)
                && (editText.getText().toString() != null)
                && !editText.getText().toString().equals(""));
    }
}
