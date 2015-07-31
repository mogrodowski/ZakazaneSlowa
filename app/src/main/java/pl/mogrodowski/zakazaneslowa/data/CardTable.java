package pl.mogrodowski.zakazaneslowa.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import pl.mogrodowski.zakazaneslowa.Constants;


public class CardTable {
    public static final String TABLE_NAME = "cards";

    public static class CardColumns implements BaseColumns {
        public static final String HEAD_WORD = "head_word";
        public static final String WORD_1 = "word_1";
        public static final String WORD_2 = "word_2";
        public static final String WORD_3 = "word_3";
        public static final String WORD_4 = "word_4";
        public static final String WORD_5 = "word_5";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + CardTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(CardColumns.HEAD_WORD + " TEXT UNIQUE NOT NULL, ");
        sb.append(CardColumns.WORD_1 + " TEXT, ");
        sb.append(CardColumns.WORD_2 + " TEXT, ");
        sb.append(CardColumns.WORD_3 + " TEXT, ");
        sb.append(CardColumns.WORD_4 + " TEXT, ");
        sb.append(CardColumns.WORD_5 + " TEXT ");
        sb.append(");");

        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CardTable.TABLE_NAME);
        CardTable.onCreate(db);
    }
}