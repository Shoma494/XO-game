import java.io.Serializable;

public class Person implements Serializable {
    String name;
    private int games;
    private int wins;
    private int defeats;
    private int drawResult;

    public Person(String name) {
        this.name = name;

    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getDrawResult() {
        return drawResult;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public void setDrawResult(int drawResult) {
        this.drawResult = drawResult;
    }
    public void wins() {
        games++;
        wins++;

    }

    public void defeats () {
        games++;
        defeats++;
    }

    public void drawResult() {
        games++;
        drawResult++;
    }

    @Override
    public String toString() {
        return "Имя - "+this.name;
    }
}
