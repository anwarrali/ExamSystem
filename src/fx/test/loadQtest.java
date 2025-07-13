package fx.test;
import fx.classes.Question;
import fx.classes.utility;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class loadQtest {

    @Test
    void testLoadRandomQuestions() {
        int limit = 5;
        String category = "General exam";

        List<Question> questions = utility.loadRandomQuestions(limit, category);

        System.out.println("Questions returned: " + questions.size());
        for (Question q : questions) {
            System.out.println(q.getText());
        }

        assertNotNull(questions);
        assertTrue(questions.size() <= limit,
                "Returned " + questions.size() + " questions, expected at most " + limit);
    }

}
