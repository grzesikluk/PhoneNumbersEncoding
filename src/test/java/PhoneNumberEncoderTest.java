import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Lukasz on 2017-01-21.
 */
public class PhoneNumberEncoderTest {

    private static final String BIG_DICTIONARY = "src\\main\\resources\\dictionary.txt";
    private static final String SMALL_DICTIONARY = "src\\test\\resources\\dictionary.txt";
    private static final String RESULT_FILE_NAME = "src\\test\\resources\\result.txt";
    private static final String INPUT_FILE_NAME = "src\\test\\resources\\numbers.txt";
    private static final String BIG_INPUT_FILE_NAME = "src\\main\\resources\\input.txt";

    private static Dictionary dict;

    @BeforeClass
    public static void init() {
        dict = new Dictionary();
        dict.readFromFile(SMALL_DICTIONARY);
    }

    @Test
    public void testGetAllConversionsForSingleDigit() throws Exception {
        PhoneNumberEncoder searcher = new PhoneNumberEncoder(dict);
        Assert.assertTrue(searcher.getAllConversions("4/").isEmpty());
    }

    @Test
    public void testGetAllConversionsForUnconvertibleNumber() throws Exception {
        PhoneNumberEncoder searcher = new PhoneNumberEncoder(dict);
        Assert.assertTrue(searcher.getAllConversions("44444/").isEmpty());
    }

    @Test
    public void testGetAllConversionsFromInstruction() throws Exception {
        PhoneNumberEncoder searcher = new PhoneNumberEncoder(dict);
        List<String> resultFileContent = getFileContent(RESULT_FILE_NAME);

        Assert.assertEquals(resultFileContent, searcher.getAllConversionsForFile(INPUT_FILE_NAME));

    }

    @Test(timeout = 500)
    public void testGetAllConversionsForBigDictionary() throws Exception {
        Dictionary dictBig = new Dictionary();
        dictBig.readFromFile(BIG_DICTIONARY);

        PhoneNumberEncoder searcher = new PhoneNumberEncoder(dictBig);

        for (String s : searcher.getAllConversionsForFile(BIG_INPUT_FILE_NAME)) {
            System.out.println(s);

            /*Check some general conditions*/
            Assert.assertTrue("String" + s + " has wrong formatting", shouldNotHaveSpacesAtTheEnd(s));
            Assert.assertFalse("String" + s + " has two following digits", shouldNeverHaveTwoFollowingDigits(s));
            Assert.assertTrue("String" + s + " has wrong digit spacing", shouldHaveProperSpacingOnDigit(s));
            Assert.assertTrue("String" + s + " is empty", isNotEmptyString(s));

        }

    }

    private boolean isNotEmptyString(String s) {
        return !s.isEmpty();
    }

    private boolean shouldNotHaveSpacesAtTheEnd(String s) {
        return s.matches("^[\\d\\-\\/\\.]+:\\s[\\D\\d\\s]+\\S$");
    }

    private boolean shouldNeverHaveTwoFollowingDigits(String s) {
        return s.matches("^[\\d\\-\\/\\.]+:.*\\d\\d.*");
    }

    private boolean shouldHaveProperSpacingOnDigit(String s) {
        return !s.matches("^[\\d\\-\\/\\.]+:.*\\S\\d") && !s.matches("^[\\d\\-\\/\\.]+:.*\\d\\S");
    }

    private List<String> getFileContent(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.sorted().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}