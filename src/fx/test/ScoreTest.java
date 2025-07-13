package fx.test;


import fx.classes.Score;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    @Test
    public void testScoreConstructorAndGetters() {
        Score score = new Score(85, 85.0, "Math", 1, 4, "Good performance", 50, 45);

        assertEquals(85, score.getScore());
        assertEquals(85.0, score.getPercentage());
        assertEquals("Math", score.getMostAnsweredMaterial());
        assertEquals(1, score.getStudentId());
        assertEquals(4, score.getExamId());
        assertEquals("Good performance", score.getFeedback());
        assertEquals(50, score.getTotalQuestions());
        assertEquals(45, score.getCorrectAnswers());
    }
}
