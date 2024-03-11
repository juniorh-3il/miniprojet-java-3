import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HangmanModel {

    private final String wordListFilePath;
    private ArrayList<String> wordList;
    private HashMap<String, String> dictionary;
    private String chosenWord;

    private String displayWord;
    private ArrayList<Character> pickedLetters;
    private static final String DEFAULT_WORD_LIST_FILE_PATH = "./resources/mots.txt";

    public HangmanModel() {
        this(HangmanModel.DEFAULT_WORD_LIST_FILE_PATH);
    }

    public HangmanModel(String wordListFilePath) {
        this.wordListFilePath = wordListFilePath;
        this.wordList = new ArrayList<>();
        this.dictionary = new HashMap<>();
        this.pickedLetters = new ArrayList<>();
        this.parseWordListFile();
    }

    private void parseWordListFile() {

        Path WORDS_FILE_PATH = Paths.get(System.getProperty("user.dir"), this.wordListFilePath);

        String newLine;

        try (BufferedReader br = Files.newBufferedReader(WORDS_FILE_PATH, StandardCharsets.UTF_8)) {
            while ((newLine = br.readLine()) != null) {
                String[] wordDefinitionPair = newLine.split(" ", 2);
                this.wordList.add(wordDefinitionPair[0]);
                this.dictionary.put(wordDefinitionPair[0], wordDefinitionPair[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectRandomWord() {
        Random random = new Random();
        this.chosenWord = this.wordList.get(random.nextInt(this.wordList.size()));
    }
}
