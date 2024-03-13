import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * HangmanModel represents the model of the Hangman game. It manages the game state,
 * word selection, letter picking, and word display.
 */
public class HangmanModel {

	private static final String DEFAULT_WORD_LIST_FILE_PATH = "./resources/mots.txt";
	private final String wordListFilePath;
	private ArrayList<String> wordList;
	private HashMap<String, String> dictionary;
	private String chosenWord;
	private String sanitizedChosenWord;
	private String displayedWord;
	private ArrayList<Character> pickedLetters;
	private HashMap<Character, ArrayList<Integer>> letterOccurrences;
	private int nbErrors;

	/**
	 * Constructs a HangmanModel object with the default word list file path.
	 */
	public HangmanModel() {
		this(HangmanModel.DEFAULT_WORD_LIST_FILE_PATH);
	}

	/**
	 * Constructs a HangmanModel object with a specified word list file path.
	 *
	 * @param wordListFilePath the path to the word list file
	 */
	public HangmanModel(String wordListFilePath) {
		this.wordListFilePath = wordListFilePath;
		this.wordList = new ArrayList<>();
		this.dictionary = new HashMap<>();
		this.pickedLetters = new ArrayList<>();
		this.parseWordListFile();
		this.pickRandomWord();
		this.displayedWord = "_".repeat(this.chosenWord.length());
		this.letterOccurrences = new HashMap<>();
		this.nbErrors = 0;
		this.parseLetterOccurrences();
	}

	/**
	 * Parses the word list file and populates the word list and dictionary.
	 */
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

	/**
	 * Picks a letter in the Hangman game.
	 *
	 * @param pickedLetter the letter picked by the player
	 */
	public void pickLetter(Character pickedLetter) {
		this.pickedLetters.add(pickedLetter);
		if (!this.letterOccurrences.containsKey(pickedLetter)) {
			this.nbErrors++;
		}
		this.updateDisplayedWord();
		System.out.println(this.displayedWord);
		System.out.println(nbErrors);
	}

	/**
	 * Picks a random word from the word list.
	 */
	private void pickRandomWord() {
		Random random = new Random();
		this.chosenWord = this.wordList.get(random.nextInt(this.wordList.size()));
		this.sanitizedChosenWord = Normalizer.normalize(this.chosenWord, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Parses letter occurrences in the chosen word.
	 */
	private void parseLetterOccurrences() {
		for (Character letter : HangmanController.ALPHABET) {
			for (int index = this.sanitizedChosenWord.indexOf(letter); index >= 0; index = this.sanitizedChosenWord.indexOf(letter, index + 1)) {
				ArrayList<Integer> indexes = this.letterOccurrences.get(letter);
				if (indexes == null) {
					indexes = new ArrayList<Integer>();
				}
				indexes.add(index);
				this.letterOccurrences.put(letter, indexes);
			}
		}
	}

	/**
	 * Updates the displayed word based on the picked letters.
	 */
	private void updateDisplayedWord() {
		this.displayedWord = "_".repeat(this.chosenWord.length());
		for (Character letter : this.pickedLetters) {
			if (this.letterOccurrences.containsKey(letter)) {
				for (int index : this.letterOccurrences.get(letter)) {
					this.displayedWord = this.displayedWord.substring(0, index) + letter + this.displayedWord.substring(index + 1);
				}
			}
		}
	}

	/**
	 * Gets the displayed word with masked and revealed letters.
	 *
	 * @return the displayed word
	 */
	public String getDisplayedWord() {
		return this.displayedWord;
	}

	/**
	 * Gets the number of errors (incorrectly picked letters).
	 *
	 * @return the number of errors
	 */
	public int getNbErrors() {
		return nbErrors;
	}
}