import com.cyh.common.dao.UUserMapper;
import com.cyh.common.model.URole;
import com.cyh.common.model.UUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cai on 2017/7/8.
 */
public class TestController extends BaseTestController{
    @Autowired
    private UUserMapper uUserMapper;

    @Test
    public void testUser(){
        System.out.println(uUserMapper);
        UUser user = this.uUserMapper.selectByPrimaryKey(new Long(1));

        List<URole> roles = this.uUserMapper.selectRoleByUserId(user.getId());
        for (URole role:roles
             ) {
            System.out.println(role.toString());
        }
        //System.out.println(roles);
    }
}
