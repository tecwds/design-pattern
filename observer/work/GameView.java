package observer.work;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}

interface Observer {
    void update(String gameTime, int homeScore, int visitorScore);
}

class GameScore implements Subject {
    //比赛进行时间
    private String gameTime;

    //主队得分
    private int homeScore;

    //客队得分
    private int visitorScore;

    private final List<Observer> observers = new ArrayList<>();

    private GameScore() {}

    private static GameScore instance = null;
    public static GameScore getInstance() {
        if (null == instance) {
            synchronized (GameScore.class) {
                if (null == instance) {
                    instance = new GameScore();
                }
            }
        }
        return instance;
    }

    public void setScore(String gameTime, int homeScore, int visitorScore) {
        this.gameTime = gameTime;
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver() {
        observers.forEach(it -> {
            it.update(gameTime, homeScore, visitorScore);
        });
    }
}

class ScoreDB implements Observer {
    @Override
    public void update(String gameTime, int homeScore, int visitorScore) {
        System.out.printf("%s:%d-%dwriteToDB%n", gameTime, homeScore, visitorScore);
    }
}

class VoiceScore implements Observer {
    @Override
    public void update(String gameTime, int homeScore, int visitorScore) {
        System.out.printf("%s:%d-%dvoice%n", gameTime, homeScore, visitorScore);
    }
}

class DisplayScore implements Observer {
    @Override
    public void update(String gameTime, int homeScore, int visitorScore) {
        System.out.printf("%s:%d-%dscreenDisplay%n", gameTime, homeScore, visitorScore);
    }
}

public class GameView {
    public static void main(String[] args) {
        GameScore gameScore = GameScore.getInstance();
        ScoreDB scoreDB = new ScoreDB();
        VoiceScore voiceScore = new VoiceScore();
        DisplayScore displayScore = new DisplayScore();

        gameScore.registerObserver(scoreDB);
        gameScore.registerObserver(voiceScore);
        gameScore.registerObserver(displayScore);
        gameScore.setScore("Game10minute", 1, 0);
        gameScore.removeObserver(voiceScore);
        gameScore.setScore("Game19minute", 1, 1);
    }
}
