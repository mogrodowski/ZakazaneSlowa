package pl.mogrodowski.zakazaneslowa.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Team> teams;
    private Deck deck;
    private Configuration config;

    private Team current_team;


    public Game(){
        this.teams = new ArrayList<>();
    }

    public List<Team> getTeams(){
        return this.teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public Team getCurrent_team() {
        return current_team;
    }

    public void setCurrent_team(Team current_team) {
        this.current_team = current_team;
    }

    public void addTeam(Team team){
        this.teams.add(team);
    }
}
