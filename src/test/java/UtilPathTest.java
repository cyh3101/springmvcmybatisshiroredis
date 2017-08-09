import com.cyh.common.utils.UtilPath;
import com.cyh.user.controller.HomeController;
import org.junit.Test;

/**
 * Created by cyh on 2017/8/8.
 */
public class UtilPathTest {
    //@Test
    public void test(){
        System.out.println(UtilPath.getClassPath());
    }

    //@Test
    public void test01(){
        System.out.println(UtilPath.getObjectPath(new HomeController()));
    }

    //@Test
    public void testgGetProjectPath(){
        System.out.println(UtilPath.getProjectPath());
    }

    @Test
    public void testGetWEB_INF(){
        System.out.println(UtilPath.getWEB_INF());
    }
}
