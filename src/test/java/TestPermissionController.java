import com.cyh.common.bo.UPermissionBo;
import com.cyh.common.dao.UPermissionMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by cai on 2017/7/9.
 */
public class TestPermissionController extends BaseTestController{
    @Autowired
    private UPermissionMapper uPermissionMapper;
    @Test
    public void testPermision(){
        List<UPermissionBo> uPermissionBos = this.uPermissionMapper.selectPermissionById(new Long(1));
        for (UPermissionBo permisionBo:uPermissionBos
             ) {
            System.out.println(permisionBo.getName() + ":  " + permisionBo.getUrl());
        }
    }
}
