import com.cyh.user.service.UUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by cyh on 2017/7/10.
 */
public class TestUserController extends BaseTestController{
    @Autowired
    private UUserService uUserService;

    @Test
    public void testUser(){
        this.uUserService.login("8446666@qq.com" , "d57ffbe486910dd5b26d0167d034f9ad");

    }
}
