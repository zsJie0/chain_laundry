package com.laundry.service.impl;

import com.laundry.mapper.LoginMapper;
import com.laundry.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class LoginMapperImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    /**
     * 根据id和pwd查询用户
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> queryUserByIdAndPwd(Map<String, Object> param) {

        return loginMapper.queryUserByIdAndPwd(param);
    }

    /**
     * 查询所有洗衣店信息
     *
     * @param
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAllLaundryInfo() {
        return loginMapper.queryAllLaundryInfo();
    }

    /**
     * 注册(添加)用户信息
     *
     * @param param
     * @return
     */
    @Override
    public int addUserInfo(Map<String, Object> param) {
        return loginMapper.addUserInfo(param);
    }

    /**
     * 判断用户是否存在
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, Object> isExist(Map<String, Object> param) {
        return loginMapper.isExist(param);
    }

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> queryUserById(String userId) {
        return loginMapper.queryUserById(userId);
    }

    /**
     * 根据id查询洗衣店信息
     *
     * @param laundryId
     * @return
     */
    @Override
    public Map<String, Object> queryLaundryById(String laundryId) {
        return loginMapper.queryLaundryById(laundryId);
    }

    /**
     * 主页显示
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryIndexShow() {
        return loginMapper.queryIndexShow();
    }

    /**
     * 模糊查询
     *
     * @param laundryName
     * @return
     */
    @Override
    public List<Map<String, Object>> queryLaundryInfoByName(String laundryName) {
        return loginMapper.queryLaundryInfoByName(laundryName);
    }

    @Override
    public List<Map<String, Object>> queryNotSetInLaundryInfo() {
        return loginMapper.queryNotSetInLaundryInfo();
    }
}
