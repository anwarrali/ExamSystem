package fx.classes;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class utility {
    private static final String RESOURCE_FILE_PATH = "src/fx/db/users.json";
    private static final String QUESTION_FILE = "src/fx/db/Questions.json";
    private static final String RESULT_FILE_PATH = "src/fx/db/scores.json";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void saveUsers(List<User> users) {
        List<User> allUsers = loadUsers();

        for (User newUser : users) {
            if (!allUsers.contains(newUser)) {
                allUsers.add(newUser);
            }
        }

        try {
            File file = new File(RESOURCE_FILE_PATH);

            if (!file.exists()) {
                System.out.println("Creating users.json...");
                Files.createFile(Paths.get(RESOURCE_FILE_PATH));
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, allUsers);
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        File file = new File(RESOURCE_FILE_PATH);
        if (!file.exists()) {
            System.out.println("users.json file not found. Returning empty list.");
            return new ArrayList<>();
        }

        try {
            List<User> users = objectMapper.readValue(file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
            System.out.println("Loaded users: " + users.size());
            return users;
        } catch (IOException e) {
            System.out.println("Error reading users.json: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    public static List<Question> loadRandomQuestions(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.err.println("Questions.json not found at: " + file.getAbsolutePath());
            return new ArrayList<>();
        }

        try {
            List<Question> allQuestions = objectMapper.readValue(
                    file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class)
            );
            System.out.println("Loaded questions: " + allQuestions.size());
            return allQuestions.size() > 70 ? allQuestions.subList(0, 70) : allQuestions;
        } catch (IOException e) {
            System.err.println("Error reading Questions.json: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static List<Score> loadResults() {
        File file = new File(RESULT_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Score.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void saveResults(List<Score> results) {
        try {
            File file = new File(RESULT_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveQuestions(List<Question> questions, String pathfile) {
        try {
            List<Question> allQuestions = loadQuestions(pathfile);

            for (Question newQuestion : questions) {
                if (!allQuestions.contains(newQuestion)) {
                    allQuestions.add(newQuestion);
                }
            }

            File file = new File(pathfile);
            if (!file.exists()) {
                System.out.println("Creating file: " + pathfile);
                Files.createFile(Paths.get(pathfile));
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, allQuestions);
            System.out.println("Questions saved successfully in " + pathfile);
        } catch (IOException e) {
            System.out.println("Error saving questions: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static List<Question> loadQuestions(String pathfile) {
        try {
            File file = new File(pathfile);
            if (file.exists()) {
                return objectMapper.readValue(file,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));
            }
        } catch (IOException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
        return new ArrayList<>();
    }



}