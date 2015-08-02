package pl.mogrodowski.zakazaneslowa.model;

public class Configuration {

    private int game_time = 5;
    private int games_number = 30;
    private int taboo_point = 1;
    private int point = 1;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getGameTime() {
        return game_time;
    }

    public void setGameTime(int game_time) {
        this.game_time = game_time;
    }

    public int getGamesNumber() {
        return games_number;
    }

    public void setGamesNumber(int games_number) {
        this.games_number = games_number;
    }

    public int getTabooPoint() {
        return taboo_point;
    }

    public void setTabooPoint(int taboo_point) {
        this.taboo_point = taboo_point;
    }
}
