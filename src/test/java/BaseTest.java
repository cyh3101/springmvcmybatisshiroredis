/**
 * Created by cai on 2017/8/16.
 */
class Base{
    private void amethod(int iOver) {
        System.out.println("Base.amethod");
    }
}
public class BaseTest extends Base{
    public static void main(String args[]) {
        BaseTest o = new BaseTest();
        int iBase = 0 ;
        o.amethod(iBase) ;
    }
    public void amethod(int iOver) {
        System.out.println("Over.amethod");
    }

}


