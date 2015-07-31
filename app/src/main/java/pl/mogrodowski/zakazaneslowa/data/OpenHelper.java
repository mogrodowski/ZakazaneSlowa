package pl.mogrodowski.zakazaneslowa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import pl.mogrodowski.zakazaneslowa.R;

import pl.mogrodowski.zakazaneslowa.Constants;


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
      CardDao categoryDao = new CardDao(db);
      //TODO: if(database card is empty) then add cards from xml

      /*
      String[] categories = context.getResources().getStringArray(R.array.tmdb_categories);
      for (String cat : categories) {
         categoryDao.save(new Category(0, cat));
      }
      */
   }

   @Override
   public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
      Log.i(Constants.LOG_TAG, "SQLiteOpenHelper onUpgrade - oldVersion:" + oldVersion + " newVersion:"
                       + newVersion);

      CardTable.onUpgrade(db, oldVersion, newVersion);
   }
}
