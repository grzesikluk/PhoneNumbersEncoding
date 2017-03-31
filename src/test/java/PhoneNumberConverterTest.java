import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lukasz on 2017-01-24.
 */
public class PhoneNumberConverterTest {
    @Test
    public void testRemoveUnnecessaryChars() throws Exception {
        Assert.assertEquals("1234", PhoneNumberConverter.removeUnnecessaryCharsFromPhoneNumber("1/2.3/4/"));
        Assert.assertEquals("42227103679862289959277180682170075257331", PhoneNumberConverter.removeUnnecessaryCharsFromPhoneNumber("4222710/-/-36798622899592771806--82-170075/257/331"));
    }

}