import com.cyh.common.dao.URoleMapper;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by cai on 2017/7/9.
 */
public class TestRoleController extends BaseTestController{
    @Autowired
    private URoleMapper uRoleMapper;
    @Test
    public void testRole(){
        Set<String> roles = uRoleMapper.findRoleByUserId(new Long(1));
        for (String role:roles
             ) {
            System.out.println(role);
        }

    }
}
