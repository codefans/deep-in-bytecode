/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ClassUtilTest
 * Author:   caishengzhi
 * Date:     2020/4/17 18:52
 * Description: ceshi
 */


import com.javaagent.MyWebAppClassLoader;
import com.javaagent.util.ClassUtil;
import com.javaagent.util.CollectionUtil;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * ceshi
 *
 * @author: caishengzhi
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