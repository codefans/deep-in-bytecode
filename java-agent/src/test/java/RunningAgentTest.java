/**
 * Copyright (C), 2015-2020, 京东
 * FileName: RunningAgentTest
 * Author:   caishengzhi
 * Date:     2020/7/7 10:56
 * Description: 运行时agent
 */

import com.codefans.bytecode.common.AsmBean;
import com.sun.tools.attach.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 *
 * 运行时agent
 *
 * @author caishengzhi
 * @date 2020/07/07 10:56
 * @since 1.0.0
 */
public class RunningAgentTest {

    @Test
    public void runningAgentTest() throws AgentInitializationException, AgentLoadException, AttachNotSupportedException, IOException {

        AsmBean asmBean = new AsmBean();
        String param = "张三zhangsan";
        String result = asmBean.doSomething(param);
        System.out.printf("param=%s, result=%s\n", param, result);

        runningAgent();

//        asmBean = new AsmBean();
//        param = "李四";
//        result = asmBean.doSomething(param);
//        System.out.printf("param=%s, result=%s\n", param, result);


    }

    private void runningAgent() throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {
        // 获取运行中的JVM列表
        List<VirtualMachineDescriptor> vmList = VirtualMachine.list();
        // 需要agent的jar包路径
        String agentJar = "G:\\github\\deep-in-bytecode\\java-agent\\target/java-agent.jar";
        for (VirtualMachineDescriptor vmd : vmList) {
            // 找到测试的JVM
//            if (vmd.displayName().endsWith("OriginStarter")) {
            if (vmd.displayName().equals("com.codefans.javaagent.OriginStarter")) {
                // attach到目标ID的JVM上
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                // agent指定jar包到已经attach的JVM上
                virtualMachine.loadAgent(agentJar);
                virtualMachine.detach();
            }
        }
    }

}