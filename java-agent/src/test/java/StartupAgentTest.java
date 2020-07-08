import com.codefans.bytecode.common.AsmBean;
import com.codefans.bytecode.common.util.DateUtil;
import com.codefans.bytecode.common.util.FileUtil;
import com.codefans.javaagent.service.UserService;
import com.codefans.javaagent.service.impl.UserServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2020-07-08 0:18
 */

public class StartupAgentTest {

    /**
     *
     */
    private UserService userService;

    @Test
    public void startupAgentTest() {

//        this.asmBeanTest();
        this.userServiceTest();

    }

    public static void main(String[] args) {
        StartupAgentTest startupAgentTest = new StartupAgentTest();
        startupAgentTest.userServiceTest();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("shutdown....");
                    FileUtil.getInstance().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void asmBeanTest() {
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

    private void userServiceTest() {

        try {
            String param = "";
            String result = "";
            int count = 5;
            while(count > 0) {
                userService = new UserServiceImpl();
                param = "张三zhangsan";
                result = userService.getUserInfo(param);
                System.out.printf("userService.getUserInfo(), param=%s, result=%s, time=%s\n", param, result, DateUtil.format(new Date()));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
    }


}
