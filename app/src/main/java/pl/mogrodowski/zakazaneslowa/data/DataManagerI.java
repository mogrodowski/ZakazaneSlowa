package pl.mogrodowski.zakazaneslowa.data;

import android.database.Cursor;
import java.util.List;
import pl.mogrodowski.zakazaneslowa.model.Card;

public interface DataManagerI {

    public Card getCard(long cardId);

    public List<Card> getCards();

    public Card findCard(String name);

    public long saveCard(Card card);

    public void deleteCard(Card card);

    public Cursor getCardCursor();
}