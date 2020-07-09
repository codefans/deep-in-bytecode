/**
 * Copyright (C), 2015-2020, 京东
 * FileName: UserServiceImpl
 * Author:   caishengzhi
 * Date:     2020/7/8 11:07
 * Description: 用户服务
 */
package com.codefans.javaagent.service.impl;


import com.codefans.javaagent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 *
 * 用户服务
 *
 * @author codefans
 * @date 2020/07/08 11:07
 * @since 1.0.0
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     *
     * @param name
     * @return
     */
    @Override
    public String getUserInfo(String name) {
        int randomInt = new Random().nextInt(1000);
        LOG.info("randomInt={}", randomInt);
        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello name[" + name + "]";
    }
}