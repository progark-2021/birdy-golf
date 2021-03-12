package no.birdygolf.gruppe19.api;

public class ScoreDto {
    private final String player;
    private final long score;

    public ScoreDto(String player, long score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public long getScore() {
        return score;
    }
}
