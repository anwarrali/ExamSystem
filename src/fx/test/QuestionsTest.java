package fx.test;


import fx.classes.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class QuestionsTest {

    @Test
    public void testConstructorAndGetters() {
        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        Question question = new Question(1, "What is Java?", options, "Option 2", "Programming");

        assertEquals(1, question.getId());
        assertEquals("What is Java?", question.getText());
        assertEquals(options, question.getOptions());
        assertEquals("Option 2", question.getCorrectAnswer());
        assertEquals("Programming", question.getMaterial());
    }

    @Test
    public void testSetters() {
        Question question = new Question();

        question.setId(2);
        question.setText("What is 2 + 2?");
        List<String> options = Arrays.asList("2", "3", "4", "5");
        question.setOptions(options);
        question.setCorrectAnswer("4");
        question.setMaterial("Math");

        assertEquals(2, question.getId());
        assertEquals("What is 2 + 2?", question.getText());
        assertEquals(options, question.getOptions());
        assertEquals("4", question.getCorrectAnswer());
        assertEquals("Math", question.getMaterial());
    }

    @Test
    public void testToString() {
        List<String> options = Arrays.asList("Java", "C++", "Python", "JavaScript");
        Question question = new Question(3, "What is your favorite programming language?", options, "Java", "Programming Languages");

        String expectedString = "Question{id=3, text='What is your favorite programming language?', options=[Java, C++, Python, JavaScript], correctAnswer='Java', material='Programming Languages'}";
        assertEquals(expectedString, question.toString());
    }
}
