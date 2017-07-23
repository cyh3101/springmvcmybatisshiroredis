import com.cyh.common.controller.BaseController;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cai on 2017/7/23.
 */
public class BaseControllerTest extends BaseTest{
    @Test
    public void testBaseController(){
        BaseController baseController = new BaseController();
        Map<String , Object> map = new HashMap<>();
        map.put("aaaaa" , "aaaaa");
        map.put("bbbbb" , new String("bbbbb"));
        map.put("ccccc" , BaseController.class.toString());
        System.out.println(map);
        map = baseController.handleParams(map);
        System.out.println(map);
    }
}
