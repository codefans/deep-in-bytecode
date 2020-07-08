/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ClassUtilTest
 * Author:   codefans
 * Date:     2020/4/17 18:52
 * Description: ceshi
 */


import com.codefans.bytecode.common.util.ClassUtil;
import com.codefans.javaagent.MyWebAppClassLoader;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 *
 * ceshi
 *
 * @author: codefans
 * @Date: 2020/04/17 18:52
 * @since: 1.0.0
 */
public class ClassUtilTest {

    @Test
    public void absolutePathToNameTest() {

        String root = "D:\\github\\springmvc-jsf\\springmvc-jsf-consumer\\target\\springmvc-jsf-consumer\\WEB-INF\\classes";
        String path = "D:\\github\\springmvc-jsf\\springmvc-jsf-consumer\\target\\springmvc-jsf-consumer\\WEB-INF\\classes\\com\\jd\\controller\\ConsumerController.class";
        System.out.println(ClassUtil.absolutePathToName(root, path));


    }
    @Test
    public void scanAllClassTest() {

//        String webPath = "D:\\github\\springmvc-jsf\\springmvc-jsf-consumer\\target\\springmvc-jsf-consumer";
//        List<String> allClassList = ClassUtil.scanAllClass(webPath);
//        CollectionUtil.print(allClassList);

        try {
            MyWebAppClassLoader myWebAppClassLoader = new MyWebAppClassLoader("D:\\github\\springmvc-jsf\\springmvc-jsf-consumer\\target\\springmvc-jsf-consumer\\");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


}