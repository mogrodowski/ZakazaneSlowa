package pl.mogrodowski.zakazaneslowa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;
import java.util.List;

import pl.mogrodowski.zakazaneslowa.data.CardTable.CardColumns;
import pl.mogrodowski.zakazaneslowa.Constants;
import pl.mogrodowski.zakazaneslowa.model.Card;


public class DataManager implements DataManagerI{

    private Context context;
    private SQLiteDatabase db;
    private CardDao cardDao;

    public DataManager(Context context){
        this.context = context;

        SQLiteOpenHelper openHelper = new OpenHelper(this.context);
        db = openHelper.getWritableDatabase();
        Log.i(Constants.LOG_TAG, "DataManagerImpl created, db open status: " + db.isOpen());

        cardDao = new CardDao(db);
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    private void openDb(){
        if(!db.isOpen()){
            db = SQLiteDatabase.openDatabase(DataConstants.DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
            cardDao = new CardDao(db);
        }
    }

    private void closeDb(){
        if(db.isOpen()){
            db.close();
        }
    }

    private void resetDb() {
        Log.i(Constants.LOG_TAG, "Resetting database connection (close and re-open).");
        closeDb();
        SystemClock.sleep(500);
        openDb();
    }

    @Override
    public Card getCard(long movieId) {
        Card card = cardDao.get(movieId);
        return card;
    }

    @Override
    public List<Card> getCards() {
        return cardDao.getAll();
    }

    @Override
    public Card findCard(String name) {
        Card card = cardDao.find(name);
        return card;
    }

    @Override
    public long saveCard(Card category) {
        return cardDao.save(category);
    }

    @Override
    public void deleteCard(Card card) {
        cardDao.delete(card);
    }

    @Override
    public Cursor getCardCursor() {
        return db.rawQuery("select " + CardColumns._ID + ", " + CardColumns.HEAD_WORD
                + " from " + CardTable.TABLE_NAME, null);
    }
}
