import com.cyh.common.utils.VerifyCodeUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by cai on 2017/8/1.
 */

public class VerifyCodeUtilsTest extends BaseTest{
    @Test
    public void test(){
        File dir = new File("d:/verifies");
        File file = new File(dir,"aaaa." + "jpg");
        VerifyCodeUtils.outputVerifyImage(200, 50, file, 5);
    }
}
