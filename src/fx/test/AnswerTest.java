package fx.test;

import fx.classes.Answer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {

    @Test
    public void testConstructorAndGetters() {
        Answer answer = new Answer(101, 2, "Mathematics");

        assertEquals(101, answer.getQuestionId());
        assertEquals(2, answer.getSelectedAnswer());
        assertEquals("Mathematics", answer.getMaterial());
    }

    @Test
    public void testSetSelectedAnswer() {
        Answer answer = new Answer(101, 2, "Physics");

        answer.setSelectedAnswer(3);
        assertEquals(3, answer.getSelectedAnswer());
    }
}
