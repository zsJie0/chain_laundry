package com.laundry.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface LoginService {

    /**
     * 根据账号跟密码查询用户信息
     *
     * @param param
     * @return
     */
    Map<String, Object> queryUserByIdAndPwd(Map<String, Object> param);

    /**
     * 查询所有的洗衣店信息
     *
     * @param
     * @return
     */
    List<Map<String, Object>> queryAllLaundryInfo();

    /**
     * 注册用户信息
     *
     * @param param
     * @return
     */
    int addUserInfo(Map<String, Object> param);

    /**
     * 判断用户是否存在
     *
     * @param param
     * @return
     */
    Map<String, Object> isExist(Map<String, Object> param);

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    Map<String, Object> queryUserById(String userId);

    /**
     * 根据id查询洗衣店信息
     *
     * @param laundryId
     * @return
     */
    Map<String, Object> queryLaundryById(String laundryId);

    /**
     * 主页显示
     *
     * @return
     */
    List<Map<String, Object>> queryIndexShow();

    /**
     * 根据洗衣店模糊查询
     *
     * @return
     */
    List<Map<String, Object>> queryLaundryInfoByName(String laundryName);

    /**
     * 查询未入驻或审核中的
     *
     * @return
     */
    List<Map<String, Object>> queryNotSetInLaundryInfo();
}
