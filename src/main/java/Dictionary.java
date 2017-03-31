import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Dictionary to hold all data in memory. It also contains retrieval operations.
 *
 */
public class Dictionary {
    Map<String, String> wordToNumber;
    Map<String, List<String>> numberToWord;


    Dictionary() {
        wordToNumber = new HashMap<>();
        numberToWord = new HashMap<>();
    }

    /**
     * Read dictionary file and create internal structures.
     *
     * @param fileName
     */
    public void readFromFile(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(s -> {
                String digits = CharEncoder.convertWordToDigitString(s);

                wordToNumber.put(s, digits);

                if (!numberToWord.containsKey(digits)) {
                    numberToWord.put(digits, new ArrayList<>());
                    numberToWord.get(digits).add(s);
                } else {
                    numberToWord.get(digits).add(s);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieve all words matching the number
     * @param number
     * @return
     */
    public List<String> getWordsForNumber(String number) {
        return numberToWord.get(number);
    }

    /**
     * Retrieve number matching the word.
     * @param word
     * @return
     */
    public String getNumberForWord(String word) {
        return wordToNumber.get(word);
    }

    public void clear() {
        wordToNumber.clear();
        numberToWord.clear();
    }

}
