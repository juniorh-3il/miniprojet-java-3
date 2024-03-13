import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HangmanModel {

	private final String wordListFilePath;
	private ArrayList<String> wordList;
	private HashMap<String, String> dictionary;
	private String chosenWord;

	private String sanitizedChosenWord;
	private String displayedWord;
	private ArrayList<Character> pickedLetters;
	private HashMap<Character, ArrayList<Integer>> letterOccurrences;
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
		this.pickRandomWord();
		this.displayedWord = "_".repeat(this.chosenWord.length());
		this.letterOccurrences = new HashMap<>();
		this.parseLetterOccurrences();

		System.out.println(this.displayedWord);
		System.out.println(this.chosenWord);
		System.out.println(this.sanitizedChosenWord);
		System.out.println(this.letterOccurrences);
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

	public void pickLetter(Character pickedLetter) {
		this.pickedLetters.add(pickedLetter);
		this.refreshDisplayedWord();
		System.out.println(this.displayedWord);
	}

	private void pickRandomWord() {
		Random random = new Random();
		this.chosenWord = this.wordList.get(random.nextInt(this.wordList.size()));
		this.sanitizedChosenWord = Normalizer.normalize(this.chosenWord, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private void parseLetterOccurrences() {
		for (Character letter : HangmanController.ALPHABET) {
			for (int index = this.chosenWord.indexOf(letter); index >= 0; index = this.chosenWord.indexOf(letter, index + 1)) {
				ArrayList<Integer> indexes = this.letterOccurrences.get(letter);
				if (indexes == null) {
					indexes = new ArrayList<Integer>();
				}
				indexes.add(index);
				this.letterOccurrences.put(letter, indexes);
			}
		}
	}

	private void refreshDisplayedWord() {
		this.displayedWord = "_".repeat(this.chosenWord.length());
		for (Character letter : this.pickedLetters) {
			if (this.letterOccurrences.containsKey(letter)) {
				for (int index : this.letterOccurrences.get(letter)) {
					this.displayedWord = this.displayedWord.substring(0, index) + letter + this.displayedWord.substring(index + 1);
				}
			}
		}
	}
}
