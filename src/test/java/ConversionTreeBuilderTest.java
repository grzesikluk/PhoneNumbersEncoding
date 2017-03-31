import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-01-21.
 */
public class ConversionTreeBuilderTest {

    private static final String dictionaryFileName = "src\\test\\resources\\dictionary.txt";
    private static Dictionary dict;

    @BeforeClass
    public static void init() {
        dict = new Dictionary();
        dict.readFromFile(dictionaryFileName);
    }

    @Test
    public void testConvertPhoneToTree_Test1() throws Exception {
        Assert.assertEquals("Tor 4", ConversionTreeBuilder.convertPhoneNumberToConversionTree("4824" ,dict).toStringList().get(0));
        Assert.assertEquals("fort", ConversionTreeBuilder.convertPhoneNumberToConversionTree("4824" ,dict).toStringList().get(1));
        Assert.assertEquals("Torf", ConversionTreeBuilder.convertPhoneNumberToConversionTree("4824" ,dict).toStringList().get(2));
    }

    @Test
    public void testConvertPhoneToTree_Test2() throws Exception {
        Assert.assertEquals("Mix Tor", ConversionTreeBuilder.convertPhoneNumberToConversionTree("5624-82" ,dict).toStringList().get(1));
        Assert.assertEquals("mir Tor", ConversionTreeBuilder.convertPhoneNumberToConversionTree("5624-82" ,dict).toStringList().get(0));
    }

    @Test
    public void testConvertPhoneToTree_Test3() throws Exception {
        Assert.assertEquals("neu o\"d 5", ConversionTreeBuilder.convertPhoneNumberToConversionTree("10/783--5" ,dict).toStringList().get(2));
        Assert.assertEquals("je bo\"s 5", ConversionTreeBuilder.convertPhoneNumberToConversionTree("10/783--5" ,dict).toStringList().get(1));
        Assert.assertEquals("je Bo\" da", ConversionTreeBuilder.convertPhoneNumberToConversionTree("10/783--5" ,dict).toStringList().get(0));
    }


    @Test
    public void testConvertPhoneToTree_Test4() throws Exception {
        Assert.assertEquals("so 1 Tor", ConversionTreeBuilder.convertPhoneNumberToConversionTree("381482" ,dict).toStringList().get(0));
    }

    @Test
    public void testConvertPhoneToTree_Test5() throws Exception {
        Assert.assertEquals("0 Tor 4", ConversionTreeBuilder.convertPhoneNumberToConversionTree("04824" ,dict).toStringList().get(0));
        Assert.assertEquals("0 Torf", ConversionTreeBuilder.convertPhoneNumberToConversionTree("04824" ,dict).toStringList().get(2));
        Assert.assertEquals("0 fort", ConversionTreeBuilder.convertPhoneNumberToConversionTree("04824" ,dict).toStringList().get(1));

    }

    @Test
    public void testConvertPhoneToTree_Test6() throws Exception {
        Assert.assertEquals("", ConversionTreeBuilder.convertPhoneNumberToConversionTree("6" ,dict).toStringList().get(0));
    }


    @Test
    public void testToString() {
        Assert.assertEquals("562482: mir Tor\n" +
                "562482: Mix Tor", ConversionTreeBuilder.convertPhoneNumberToConversionTree("5624-82" ,dict).toString());
    }



}