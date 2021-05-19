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
     * 普通管理员/用户主页显示
     * @return
     */
    Map<String,Object> queryIndexShowPT(@Param("laundryId") String laundryId);

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
     * 查询所有普通用户的信息
     */
    List<Map<String,Object>> queryAllUserInfoPT(@Param("uId") String uId,@Param("laundryId") String laundryId);


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

    /**
     * 根据id查询用户头像
     * @param userId
     * @return
     */
    Map<String,Object> queryImageById(@Param("userId") String userId);

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    int updateUserInfoById(Map<String,Object> param);

    /**
     * 修改密码
     */
    int  updatePwd(Map<String,Object> param);

    /**
     * 查询物资资料
     * @return
     */
    List<Map<String,Object>> queryMaterialInfo();

    /**
     * 入库列表
     * @return
     */
    List<Map<String,Object>> queryMaterialInfoRk();

    /**
     * 出库列表
     * @return
     */
    List<Map<String,Object>> queryMaterialInfoCk();

    /**
     * 添加物资入库(新物资)
     * @param param
     * @return
     */
    int addMaterial(Map<String,Object> param);

    /**
     * 添加物资入库(旧物资)
     * @param param
     * @return
     */
    int updateMaterial(Map<String,Object> param);

    /**
     * 未入库物资数量减少(旧物资)
     * @param param
     * @return
     */
    int updateNumber(Map<String,Object> param);


    /**
     * 查询物资码表信息
     * @return
     */
    List<Map<String,Object>> queryMaterialTypeInfo();

    /**
     * 判断在未入库物资中是否已经存在
     * @param param
     * @return
     */
    Map<String,Object> materialIsExist(Map<String,Object> param);

    /**
     * 判断在已入库物资中是否已经存在
     * @param param
     * @return
     */
    Map<String,Object> materialIsExistNow(Map<String,Object> param);

    /**
     * 根据物资类别模糊查询
     * @return
     */
    List<Map<String,Object>> queryMaterialByName(@Param("mName") String mName);

    /**
     * 根据id查询物资列表
     * @param mIds
     * @return
     */
    List<Map<String,Object>> queryMaterialById(@Param("mIds") String[] mIds);

    /**
     * 批量更新
     * @param mIds
     * @return
     */
    int updateSelectMaterialNumber(@Param("mIds") String[] mIds,@Param("selectNumber") String selectNumber);

    /**
     * 插入出库物资
     * @return
     */
    int addMaterialCk(Map<String,Object> param);

    /**
     * 根据id查询出库物资
     * @param mIds
     * @return
     */
    List<Map<String,Object>> queryCkMaterial(@Param("mIds") String[] mIds);

    /**
     * 更新出库物资的数量
     * @param mIds
     * @return
     */
    int updateMaterialById(@Param("mIds") String[] mIds,@Param("selectNumber") String selectNumber);

    /**
     * 查询公告
     * @return
     */
    List<Map<String,Object>> queryNoticeInfo();

    /**
     * 查询普通管理员/员工的公告
     * @return
     */
    List<Map<String,Object>> queryNoticeInfoPT(@Param("date") String date,@Param("laundryId") String laundryId);

    /**
     * 升级为店长
     * @return
     */
    Map<String,Object> upgradeUser(@Param("userId") String userId);

    /**
     * 更改用户类型
     * @param param
     * @return
     */
    int updateUserType(Map<String,Object> param);

    /**
     * 定位管理
     * @return
     */
    List<Map<String,Object>> queryPosition();

    /**
     * 定位下的连锁店列表信息
     * @param position
     * @return
     */
    List<Map<String,Object>> queryLaundryByPosition(@Param("position") String position);

    /**
     * 查询订单信息
     */
    List<Map<String,Object>> queryOrderInfo(@Param("laundryId") String laundryId);

    /**
     * 模糊查询订单信息
     * @return
     */
    List<Map<String,Object>> queryOrderInfoByState(@Param("state") String state,@Param("laundryId") String laundryId);

    /**
     * 更新订单状态
     * @param orderIds
     * @return
     */
    int updateOrderState(@Param("orderIds") String[] orderIds);

    /**
     * 查询超管电话
     */
    Map<String,Object> queryPhone();

    /**
     * 查询公告列表
     */
    List<Map<String,Object>> queryNoticeList(String spare);

    /**
     * 新增公告
     * @param param
     * @return
     */
    int addNotice(Map<String,Object> param);

    /**
     * 查询可发布的公告
     * @return
     */
    List<Map<String,Object>> queryNoticeY(String spare);

    /**
     * 发布公告
     * @param noticeIdList
     * @return
     */
    int updateNoticeById(@Param("noticeIdList") String[] noticeIdList);

    /**
     * 发布公告
     * @param noticeIdList
     * @return
     */
    int deleteNoticeById(@Param("noticeIdList") String[] noticeIdList);

    /**
     * 更新订单的数据 日期
     *
     */
    int updateOrderTime(String date);
}
