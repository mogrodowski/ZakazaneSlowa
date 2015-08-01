package pl.mogrodowski.zakazaneslowa.data;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import pl.mogrodowski.zakazaneslowa.R;

import pl.mogrodowski.zakazaneslowa.Constants;
import pl.mogrodowski.zakazaneslowa.model.Card;


public class OpenHelper extends SQLiteOpenHelper {

   private static final int DATABASE_VERSION = 1;

   private Context context;

   OpenHelper(final Context context) {
      super(context, DataConstants.DATABASE_NAME, null, DATABASE_VERSION);
      this.context = context;
   }

   @Override
   public void onOpen(final SQLiteDatabase db){
      super.onOpen(db);
      if(!db.isReadOnly()){
         db.execSQL("PRAGMA foreign_keys=ON;");

         Cursor c = db.rawQuery("PRAGMA foreign_keys", null);
         if(c.moveToFirst()){
            int result = c.getInt(0);
            Log.i(Constants.LOG_TAG, "SQLite foreign key support (1 is on, 0 is off): " + result);
         }
         else{
            Log.i(Constants.LOG_TAG, "SQLite foreign key support NOT AVAILABLE");
         }
         if(!c.isClosed()){
            c.close();
         }
      }
   }

   @Override
   public void onCreate(final SQLiteDatabase db){
      Log.i(Constants.LOG_TAG, "DataHelper.OpenHelper onCreate creating database " + DataConstants.DATABASE_NAME);

      CardTable.onCreate(db);
      CardDao cardDao = new CardDao(db);

      TypedArray cards = context.getResources().obtainTypedArray(R.array.cards);
      int cards_length = cards.length();

      for(int i = 0; i < cards_length; i++){
         int id = cards.getResourceId(i, 0);
         if(id > 0){
            String[] card = context.getResources().getStringArray(id);

            try{
               Card c = new Card(card[0], card[1], card[2], card[3], card[4], card[5]);
               cardDao.save(c);
            }
            catch(Exception e){

            }
         }
      }
   }

   @Override
   public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
      Log.i(Constants.LOG_TAG, "SQLiteOpenHelper onUpgrade - oldVersion:" + oldVersion + " newVersion:"
                       + newVersion);

      CardTable.onUpgrade(db, oldVersion, newVersion);
   }
}
