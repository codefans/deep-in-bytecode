package com.codefans.javaagent.service;

/**
 * 用户接口
 */
public interface UserService {
    /**
     * 根据用户姓名查用户信息
     * @param name
     * @return
     */
    String getUserInfo(String name);
}
