package pl.mogrodowski.zakazaneslowa.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import pl.mogrodowski.zakazaneslowa.Constants;
import pl.mogrodowski.zakazaneslowa.data.DataManagerI;
import pl.mogrodowski.zakazaneslowa.model.Card;

public class CardUpdater extends AsyncTask<Void, Void, Boolean> {

    private final String CARDS_API = "http://portaboo.mogrodowski.pl/api/cards";
    private TextView update_cards_status;
    private DataManagerI dataManager;

    public CardUpdater(TextView textView, DataManagerI dataManager){
        this.update_cards_status = textView;
        this.dataManager = dataManager;
    }

    //TODO: zamiast TextView moze byc ImageView - dac jakis preloader
    @Override
    protected void onPreExecute(){
        update_cards_status.setText("Rozpoczynam pobieranie...");
    }

    @Override
    protected Boolean doInBackground(Void... params){
        Log.i(Constants.LOG_TAG, "start update cards");

        String cards_json = "";

        try{
            URL url = new URL(this.CARDS_API);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while((inputLine = in.readLine()) != null){
                cards_json += inputLine;
            }
            in.close();

            Log.i(Constants.LOG_TAG, "Pobrany json: " + cards_json);

            JSONArray cards = new JSONArray(cards_json);

            dataManager.deleteCards();
            for(int i = 0; i < cards.length(); i++){
                JSONObject c = cards.getJSONObject(i);

                Card card = new Card(c.getString("head_word"), c.getString("word_1"),
                        c.getString("word_2"), c.getString("word_3"), c.getString("word_4"), c.getString("word_5"));

                dataManager.saveCard(card);

                Log.i(Constants.LOG_TAG, "Card added");
            }

        }
        catch(Exception e){
            e.printStackTrace();

            return false;
        }

        Log.i(Constants.LOG_TAG, "end update cards");
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            update_cards_status.setText("Pobranie zakończone sukcesem. Liczba kart w bazie: " + dataManager.getCards().size());
        }
        else{
            update_cards_status.setText("Błąd podczas pobierania");
        }
    }
}
