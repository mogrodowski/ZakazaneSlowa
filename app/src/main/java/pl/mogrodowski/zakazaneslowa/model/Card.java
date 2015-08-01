package pl.mogrodowski.zakazaneslowa.model;

public class Card extends ModelBase implements Comparable<Card> {

    private String head_word;
    private String word_1;
    private String word_2;
    private String word_3;
    private String word_4;

    public String getHead_word() {
        return head_word;
    }

    public void setHead_word(String head_word) {
        this.head_word = head_word;
    }

    public String getWord_1() {
        return word_1;
    }

    public void setWord_1(String word_1) {
        this.word_1 = word_1;
    }

    public String getWord_2() {
        return word_2;
    }

    public void setWord_2(String word_2) {
        this.word_2 = word_2;
    }

    public String getWord_3() {
        return word_3;
    }

    public void setWord_3(String word_3) {
        this.word_3 = word_3;
    }

    public String getWord_4() {
        return word_4;
    }

    public void setWord_4(String word_4) {
        this.word_4 = word_4;
    }

    public String getWord_5() {
        return word_5;
    }

    public void setWord_5(String word_5) {
        this.word_5 = word_5;
    }

    private String word_5;

    public Card(){
    }

    public Card(long id){
        this.id = id;
    }

    public Card(String head_word){
        this.head_word = head_word;
    }

    public Card(String head_word, String word_1, String word_2, String word_3, String word_4, String word_5){
        this.head_word = head_word;

        this.word_1 = word_1;
        this.word_2 = word_2;
        this.word_3 = word_3;
        this.word_4 = word_4;
        this.word_5 = word_5;
    }

    @Override
    public String toString(){
        return this.head_word;
    }

    @Override
    public int compareTo(Card another){
        if(another == null){
            return -1;
        }
        if(this.head_word == null){
            return 1;
        }

        return this.head_word.compareTo(another.head_word);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.head_word == null) ? 0 : this.head_word.toUpperCase().hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(!super.equals(obj)){
            return false;
        }
        if(!(obj instanceof Card)){
            return false;
        }
        Card other = (Card) obj;
        if(this.head_word == null){
            if(other.head_word != null){
                return false;
            }
        }
        else if(!this.head_word.equalsIgnoreCase(other.head_word)){
            return false;
        }
        return true;
    }
}