import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-01-21.
 */
public class CharEncoderTest {

    @Test
    public void testCharToInt() throws Exception {

        Assert.assertEquals(5, CharEncoder.charToInt('a'));
        Assert.assertEquals(5, CharEncoder.charToInt('A'));
        Assert.assertEquals(5, CharEncoder.charToInt('M'));
    }


    @Test
    public void convertWordToDigitString() throws Exception {
        Assert.assertEquals("562", CharEncoder.convertWordToDigitString("Mix"));
        Assert.assertEquals("4824", CharEncoder.convertWordToDigitString("Torf"));
    }




}