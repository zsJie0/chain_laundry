package com.laundry.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface LoginMapper {

    /**
     * 根据id查询用户信息
     * @param param
     * @return
     */
    Map<String,Object> queryUserByIdAndPwd(Map<String,Object> param);

    /**
     * 查询所有的洗衣店信息
     * @param
     * @return
     */
    List<Map<String,Object>> queryAllLaundryInfo();

    /**
     * 注册用户信息
     * @param param
     * @return
     */
    int addUserInfo(Map<String,Object> param);

    /**
     * 判断用户是否存在
     * @param param
     * @return
     */
    Map<String,Object> isExist(Map<String,Object> param);

    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    Map<String,Object> queryUserById(String userId);

    /**
     * 根据id查询洗衣店信息
     * @param laundryId
     * @return
     */
    Map<String,Object> queryLaundryById(String laundryId);

    /**
     * 主页显示
     * @return
     */
    List<Map<String,Object>> queryIndexShow();

    /**
     * 查询营业额
     * @return
     */
    Map<String,Object> queryTurnover();

    /**
     * 根据洗衣店模糊查询
     * @return
     */
    List<Map<String,Object>> queryLaundryInfoByName(@Param("laundryName") String laundryName);

    /**
     * 查询已入驻的连锁店
     * @return
     */
    List<Map<String,Object>> queryNotSetInLaundryInfo();

    /**
     * 查询状态码表
     *
     */
    List<Map<String,Object>> queryStateInfo();

    /**
     * 修改状态
     * @param laundryId
     * @return
     */
    int updateStateByLaundryId(String laundryId);

    /**
     * 查询所有用户的信息
     */
    List<Map<String,Object>> queryAllUserInfo(@Param("uId") String uId);

    /**
     * 根据name模糊查询用户信息
     * @param userName
     * @return
     */
    List<Map<String,Object>> queryUserInfoByName(@Param("userName") String userName);

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    int deleteUserById(@Param("userIds") String[] userIds);

    /**
     * 根据ID删除用户信息
     * @param userId
     * @return
     */
    int deleteUserInfoById(String userId);
}
