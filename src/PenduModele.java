import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class PenduModele {

    private final String wordListFilePath;
    private ArrayList<String> wordList;
    private HashMap<String, String> dictionary;

    public PenduModele() {
        this("./ressources/mots.txt");
    }

    public PenduModele(String wordListFilePath) {
        this.wordListFilePath = wordListFilePath;
        this.wordList = new ArrayList<>();
        this.dictionary = new HashMap<>();
        this.parseWordListFile();
    }


    private void parseWordListFile() {

        Path WORDS_FILE_PATH = Paths.get(System.getProperty("user.dir"), this.wordListFilePath);

        String newLine;
        String newWord;
        String newDefinition;

        try (BufferedReader br = Files.newBufferedReader(WORDS_FILE_PATH, StandardCharsets.UTF_8)) {
            while ((newLine = br.readLine()) != null) {
                String[] wordDefinitionPair = newLine.split(" ", 2);
                newWord = wordDefinitionPair[0];
                newDefinition = wordDefinitionPair[1];

                this.wordList.add(newWord);
                dictionary.put(newWord, newDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println(WORDS_FILE_PATH);
        System.err.println(this.wordList);
        System.err.println(dictionary);
    }
}
