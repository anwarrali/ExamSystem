package fx.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import fx.Main;

import java.sql.*;
import java.util.*;

public class utility {

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String DB_USER = "C##EXAMNEW";
    private static final String DB_PASSWORD = "EXAM123";
    //here : static String role = Main.getInstance().getRole();
    static String role = "student";
    static String sql = "";

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void saveUsers(User user) {
        String sql = "";

        try (Connection conn = getConnection()) {
            String role = user.getRole();
            //System.out.println("debug"+role);

            if (role.equalsIgnoreCase("admin")) {
                sql = "INSERT INTO admins (username, user_password, full_name, email,phone) VALUES (?, ?, ?, ?,?)";
            } else if (role.equalsIgnoreCase("student")) {
                sql = "INSERT INTO students (username, user_password, full_name, email,phone) VALUES (?, ?, ?, ?,?)";
            } else {
                throw new IllegalArgumentException("unknown role: " + role);
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFullName());
                stmt.setString(4, user.getEmailaddress());
                stmt.setString(5, user.getNumber());
                stmt.executeUpdate();
                System.out.println("User saved successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error saving users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Score> loadResults() {
        List<Score> results = new ArrayList<>();

        String query = "SELECT student_id, exam_id, score, feedback, total_questions, correct_answers FROM exam_attempts";

        try (Connection conn =getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int score = rs.getInt("score");
                int totalQuestions = rs.getInt("total_questions");
                int correctAnswers = rs.getInt("correct_answers");

                double percentage = totalQuestions > 0 ? ((double) correctAnswers / totalQuestions) * 100 : 0;

                String mostAnsweredMaterial = "N/A";

                Score s = new Score(
                        score,
                        percentage,
                        mostAnsweredMaterial,
                        rs.getInt("student_id"),
                        rs.getInt("exam_id"),
                        rs.getString("feedback"),
                        totalQuestions,
                        correctAnswers
                );

                results.add(s);
            }

            System.out.println("Loaded " + results.size() + " scores from database.");
        } catch (SQLException e) {
            System.out.println("Error loading results: " + e.getMessage());
            e.printStackTrace();
        }

        return results;
    }

    public static List<Question> loadRandomQuestions(int limit, String category) {
        List<Question> questions = new ArrayList<>();
        System.out.println("Category (title): " + category);
        System.out.println("Limit: " + limit);

        String questionSQL = """
            SELECT q.question_id, q.question_text, q.exam_id, a.correct_option_id FROM questions q
            JOIN exams e ON q.exam_id = e.exam_id
            LEFT JOIN answers a ON q.question_id = a.question_id
            WHERE e.title = ? ORDER BY DBMS_RANDOM.VALUE FETCH FIRST ? ROWS ONLY""";


        try (Connection conn = getConnection();

             PreparedStatement stmt = conn.prepareStatement(questionSQL)) {
            stmt.setString(1, category);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            Map<Integer, Question> questionMap = new HashMap<>();

            while (rs.next()) {
                int questionId = rs.getInt("question_id");
                String questionText = rs.getString("question_text");
                String examId = rs.getString("exam_id");
                String correctAnswerId = rs.getString("correct_option_id");

                Question question = new Question();
                question.setId(questionId);
                question.setText(questionText);
                question.setMaterial(examId);
                if (correctAnswerId != null) {
                    question.setCorrectAnswer(correctAnswerId);
                }
                questionMap.put(questionId, question);
            }

            String optionSQL = "SELECT option_text FROM options WHERE question_id = ?";
            PreparedStatement optionStmt = conn.prepareStatement(optionSQL);

            for (Question question : questionMap.values()) {
                optionStmt.setInt(1, question.getId());
                ResultSet optionRS = optionStmt.executeQuery();
                while (optionRS.next()) {
                    question.getOptions().add(optionRS.getString("option_text"));
                }
            }

            questions.addAll(questionMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }




    public static void saveResults(Score score) {
        String sql = "INSERT INTO exam_attempts (student_id, exam_id, score, feedback, total_questions, correct_answers) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, score.getStudentId());
            stmt.setInt(2, score.getExamId());
            stmt.setDouble(3, score.getScore());
            stmt.setString(4, score.getFeedback());
            stmt.setInt(5, score.getTotalQuestions());
            stmt.setInt(6, score.getCorrectAnswers());
            stmt.executeUpdate();
            System.out.println("Results saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}