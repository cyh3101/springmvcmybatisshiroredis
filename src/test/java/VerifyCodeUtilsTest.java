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


        for (int i = 0;i < 100; i++) {
            String verifyCode = VerifyCodeUtils.generateVerifyCode(5);
            File file = new File(dir,verifyCode  + ".jpg");
            VerifyCodeUtils.outputImage(200, 50, file, verifyCode);
        }
    }
}
