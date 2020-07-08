import com.codefans.bytecode.common.AsmBean;
import com.codefans.bytecode.common.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2020-07-08 0:18
 */

public class StartupAgentTest {

    @Test
    public void startupAgentTest() {

        AsmBean asmBean = null;
        String param = "";
        String result = "";

        while(true) {
            asmBean = new AsmBean();
            param = "张三zhangsan";
            result = asmBean.doSomething(param);
            System.out.printf("param=%s, result=%s, time=%s\n", param, result, DateUtil.format(new Date()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
