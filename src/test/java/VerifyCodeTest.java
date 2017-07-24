import com.cyh.common.utils.VerifyCodeUtils;
import org.junit.Test;

/**
 * Created by cai on 2017/7/24.
 */
public class VerifyCodeTest extends BaseTest{
    @Test
    public void test(){
        String verifyCode = VerifyCodeUtils.generateVerifyCode(10);
        System.out.println(verifyCode);
    }
}
