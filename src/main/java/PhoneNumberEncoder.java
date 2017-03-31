import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for encoding phone numbers.
 */
public class PhoneNumberEncoder {

    private static Dictionary dict;

    PhoneNumberEncoder(Dictionary dict) {
        this.dict = dict;
    }

    /**
     * Get all conversons of given phone number.
     *
     * @param number
     * @return all conversions list.
     */
    public List<String> getAllConversions(String number) {
        return ConversionTreeBuilder.convertPhoneNumberToConversionTree(number, dict).toStringList().
                stream().filter(s -> !s.isEmpty()).map(s -> number + ": " + s).collect(Collectors.toList());
    }


    /**
     * Retrieve all conversions for numbers from file. Create big sorted list of results. Helpful for tests.
     *
     * @param fileName
     * @return
     */
    public List<String> getAllConversionsForFile(String fileName) {
        List<String> result = new ArrayList<>();


        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.map(s -> getAllConversions(s)).flatMap(l -> l.stream()).forEach(result::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.stream().sorted().collect(Collectors.toList());
    }

    /**
     * Print all conversions for numbers from file. It doesnt create list, thus is memory efficient.
     *
     * @param fileName
     * @return
     */
    public void printAllConversionsForFile(String fileName) {

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.map(s -> getAllConversions(s)).flatMap(l -> l.stream()).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
