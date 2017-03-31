import java.util.Arrays;
import java.util.List;

/**
 * Use this class to prepare number for processing.
 *
 */
public class PhoneNumberConverter {

    public static String removeUnnecessaryCharsFromPhoneNumber(String number) {

        /*The set of chars to remove*/
        List<Character> charsToBeRemoved = Arrays.asList('/', '\\', '-', '.');
        String result = number;

        for (Character c : charsToBeRemoved)
            result = result.replace(c.toString(), "");

        return result;
    }

}
