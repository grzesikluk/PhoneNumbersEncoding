import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Lukasz on 2017-01-21.
 */
public class DictionaryTest {


    private static final String dictionaryFileName = "src\\test\\resources\\dictionary.txt";
    private static final String dictionaryFileNameBig = "src\\main\\resources\\dictionary.txt";

    private static Dictionary dict;

    @BeforeClass
    public static void init() {
        dict = new Dictionary();
        dict.readFromFile(dictionaryFileName);
//        System.out.println(dict.getWordToNumber());
    }

    @Test
    public void testGetWordsForNumber() throws Exception {
        Assert.assertEquals(Arrays.asList("jemand"),dict.getWordsForNumber("105513"));
        Assert.assertEquals(Arrays.asList("o\"d"),dict.getWordsForNumber("83"));
    }

    @Test
    public void testGetNumberForWord() throws Exception {
        Assert.assertEquals("105513",dict.getNumberForWord("jemand"));
    }

    @Test(timeout = 1000)
    public void testReadFromFileBig() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.readFromFile(dictionaryFileNameBig);
        Assert.assertEquals(Arrays.asList("jemand"),dictionary.getWordsForNumber("105513"));
        Assert.assertEquals(Arrays.asList("o\"d"),dict.getWordsForNumber("83"));
    }


}