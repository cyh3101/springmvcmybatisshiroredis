import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by cai on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/spring-mybatis.xml" , "classpath:spring-mvc.xml" ,
        "classpath:spring/spring-transaction.xml" , "classpath:spring/spring-shiro.xml"})
public class BaseTestController{
}
