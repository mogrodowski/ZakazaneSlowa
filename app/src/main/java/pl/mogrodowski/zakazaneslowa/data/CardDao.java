package pl.mogrodowski.zakazaneslowa.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.mogrodowski.zakazaneslowa.data.CardTable.CardColumns;

import pl.mogrodowski.zakazaneslowa.model.Card;

public class CardDao implements DaoI<Card>{

    private static final String INSERT =
            "insert into " + CardTable.TABLE_NAME + "(" + CardColumns.HEAD_WORD + ", " + CardColumns.WORD_1 + ", "
                    + CardColumns.WORD_2 + ", " + CardColumns.WORD_3 + ", " + CardColumns.WORD_4 + ", "
                    + CardColumns.WORD_5 + ") values (?, ?, ?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public CardDao(SQLiteDatabase db) {
        this.db = db;
        this.insertStatement = db.compileStatement(CardDao.INSERT);
    }

    @Override
    public long save(Card entity) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, entity.getHead_word());
        insertStatement.bindString(2, entity.getWord_1());
        insertStatement.bindString(3, entity.getWord_2());
        insertStatement.bindString(4, entity.getWord_3());
        insertStatement.bindString(5, entity.getWord_4());
        insertStatement.bindString(6, entity.getWord_5());

        return insertStatement.executeInsert();
    }

    @Override
    public void update(Card entity) {
        final ContentValues values = new ContentValues();
        values.put(CardColumns.HEAD_WORD, entity.getHead_word());
        values.put(CardColumns.WORD_1, entity.getWord_1());
        values.put(CardColumns.WORD_2, entity.getWord_1());
        values.put(CardColumns.WORD_3, entity.getWord_1());
        values.put(CardColumns.WORD_4, entity.getWord_1());
        values.put(CardColumns.WORD_5, entity.getWord_1());
        db.update(CardTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[] { String
                .valueOf(entity.getId()) });
    }

    @Override
    public void delete(Card entity) {
        if (entity.getId() > 0) {
            db.delete(CardTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[] { String.valueOf(entity.getId()) });
        }
    }

    @Override
    public Card get(long id) {
        Card card = null;
        Cursor c =
                db.query(CardTable.TABLE_NAME, new String[] { BaseColumns._ID, CardColumns.HEAD_WORD,
                                CardColumns.WORD_1, CardColumns.WORD_2, CardColumns.WORD_3, CardColumns.WORD_4,
                                CardColumns.WORD_5},
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            card = this.buildCardFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return card;
    }

    @Override
    public List<Card> getAll() {
        List<Card> list = new ArrayList<Card>();
        Cursor c =
                db.query(CardTable.TABLE_NAME, new String[] { BaseColumns._ID, CardColumns.HEAD_WORD,
                                CardColumns.WORD_1, CardColumns.WORD_2, CardColumns.WORD_3, CardColumns.WORD_4,
                                CardColumns.WORD_5, }, null,
                        null, null, null, CardColumns.HEAD_WORD, null);
        if (c.moveToFirst()) {
            do {
                Card card = this.buildCardFromCursor(c);
                if (card != null) {
                    list.add(card);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public Card find(String name) {
        long cardId = 0L;
        String sql = "select _id from " + CardTable.TABLE_NAME + " where upper(" + CardColumns.HEAD_WORD + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if (c.moveToFirst()) {
            cardId = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }

        return this.get(cardId);
    }

    private Card buildCardFromCursor(Cursor c) {
        Card card = null;
        if (c != null) {
            card = new Card();
            card.setId(c.getLong(0));
            card.setHead_word(c.getString(1));
            card.setWord_1(c.getString(2));
            card.setWord_2(c.getString(3));
            card.setWord_3(c.getString(4));
            card.setWord_4(c.getString(5));
            card.setWord_5(c.getString(6));
        }

        return card;
    }
}
