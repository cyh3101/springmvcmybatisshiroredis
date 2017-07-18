import com.cyh.common.utils.MathUtil;
import org.junit.Test;

/**
 * Created by cai on 2017/7/15.
 */
public class UtilTest extends BaseTest{
    @Test
    public void test(){
        String md5Str = MathUtil.getMD5("123456");
        System.out.println(md5Str);
    }
}
