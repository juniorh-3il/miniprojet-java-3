import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Pendu {

    public static void main(String[] args) {
        final String WORDS_FILE_RELATIVE_PATH = "./ressources/mots.txt";
        Path WORDS_FILE_PATH = Paths.get(System.getProperty("user.dir"), WORDS_FILE_RELATIVE_PATH);

        ArrayList<String> words = new ArrayList<>();
        HashMap<String, String> dictionary = new HashMap<>();
        String newLine;
        String newWord;
        String newDefinition;

        try (BufferedReader br = Files.newBufferedReader(WORDS_FILE_PATH, StandardCharsets.UTF_8)) {
            while ((newLine = br.readLine()) != null) {
                String[] wordDefinitionPair = newLine.split(" ", 2);
                newWord = wordDefinitionPair[0];
                newDefinition = wordDefinitionPair[1];

                words.add(newWord);
                dictionary.put(newWord, newDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(WORDS_FILE_PATH);
        System.out.println(words);
        System.out.println(dictionary);
    }
}
