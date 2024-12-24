package fx.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {

    private int score;
    private double percentage;
    private String mostAnsweredMaterial;

    @JsonCreator
    public Score(
            @JsonProperty("score") int score,
            @JsonProperty("percentage") double percent,
            @JsonProperty("mostAnsweredMaterial") String material
    ) {
        this.score = score;
        this.percentage = percent;
        this.mostAnsweredMaterial = material;
    }

    public int getScore() {
        return score;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getMostAnsweredMaterial() {
        return mostAnsweredMaterial;
    }
}
