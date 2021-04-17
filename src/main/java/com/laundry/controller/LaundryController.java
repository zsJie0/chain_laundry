package com.laundry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laundry.common.CommonUtils;
import com.laundry.mapper.LoginMapper;
import com.laundry.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.text.ParseException;
import java.util.*;

@Controller
public class LaundryController {
    String uId = "";

    @Autowired
    LoginService loginService;

    @Autowired
    LoginMapper loginMapper;

    /**
     * 登录
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    /**
     * 处理
     * @return
     */
    @RequestMapping("/handle/{laundryId}")
    public String handle(@PathVariable("laundryId") String laundryId,Model model){
        //查询状态码表
        List<Map<String, Object>> stateInfo = loginMapper.queryStateInfo();
        model.addAttribute("stateInfo",stateInfo);
        System.out.println(stateInfo);
        //根据id查询洗衣店
        Map<String, Object> laundryMap = loginService.queryLaundryById(laundryId);
        model.addAttribute("laundryMap",laundryMap);
        return "handle";
    }

    /**
     * 列表
     * @return
     */
    @RequestMapping("/laundryList")
    public String memberList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "5") int pageSize){
        getUserInfo(model);
        getUrlOrImage(model,uId);
        //分页
        PageHelper.startPage(page,pageSize);
        //查询所有洗衣店信息
        List<Map<String, Object>> laundryInfoList = loginService.queryAllLaundryInfo();
        if(CommonUtils.isEmpty(laundryInfoList)){
            laundryInfoList = new ArrayList<>();
        }
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(laundryInfoList);
        System.out.println(laundryInfoList);
        model.addAttribute("pageInfo",pageInfo);
        return "laundryList";
    }

    /**
     * 入驻管理
     * @return
     */
    @RequestMapping("/settleIn")
    public String memberDel(Model model,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "5") int pageSize){
        getUserInfo(model);
        getUrlOrImage(model,uId);
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> notSetInLaundryInfo = loginService.queryNotSetInLaundryInfo();
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(notSetInLaundryInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "settleIn";
    }


    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "4") int pageSize){
        System.out.println(uId);
        getUserInfo(model);
        getUrlOrImage(model,uId);
        //总营业额
        Map<String, Object> queryTurnoverMap = loginMapper.queryTurnover();
        model.addAttribute("turnoverMap",queryTurnoverMap);
        //分页
        PageHelper.startPage(page,pageSize);
        //主页显示
        List<Map<String, Object>> showList = loginService.queryIndexShow();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(showList);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

    /**
     * 注册/添加用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUserInfo")
    public String addUserInfo(@RequestParam Map<String,Object> param){
        String date = String.valueOf(param.get("time"));
        String formatDate = CommonUtils.dateTransformation2(date);
        param.put("date", formatDate);
        System.out.println("addUserInfo"+param);
        //判断该账号是否存在
        if(!CommonUtils.isEmpty(loginMapper.isExist(param))){
            return "exist";
        }
        //判断年龄是否为数字
        if(CommonUtils.isNumeric(param.get("userAge").toString())){
        //判断必填字段是否为空
        if(!(CommonUtils.isEmpty(param.get("userId")) || CommonUtils.isEmpty(param.get("userPwd")) || CommonUtils.isEmpty(param.get("userName")))){
            int i = loginMapper.addUserInfo(param);
            if(i>0){
                return "success";
            }else {
                    return "fail";
                }
            }


        }
        return "empty";
    }

    /**
     * 根据id和pwd查询用户
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUserByIdAndPwd")
    public String queryUserByIdAndPwd(@RequestParam Map<String,Object> map,HttpSession session ){
        if (!(CommonUtils.isEmpty(map.get("userId")) || CommonUtils.isEmpty(map.get("userPwd")))) {
            Map<String, Object> stringObjectMap = loginMapper.queryUserByIdAndPwd(map);
            if(CommonUtils.isEmpty(stringObjectMap)){
                return "fail";
            }else {
                String userId = map.get("userId").toString();
                session.setAttribute("userId", userId);
                return "success";
            }
        }
        return "empty";
    }

    /**
     * 查询所有洗衣店信息
     * @param model
     * @return
     */
    @RequestMapping("/queryAllLaundryInfo")
    public String queryAllLaundryInfo(Model model){
        List<Map<String, Object>> laundryInfoList = loginService.queryAllLaundryInfo();
//        for(Map<String,Object> laundryMap : laundryInfoList){
//            System.out.println(laundryMap);
//        }
        model.addAttribute("laundryInfoList",laundryInfoList);
        //年龄段
        Map<String,Integer> ageMap = new HashMap<>();
        for(int i =1;i<101;i++){
            ageMap.put(i+"",i);
        }
        model.addAttribute("ageList",ageMap);
        return "register";
    }

    /**
     * 主页显示接口
     * @param userId
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryUserById/{userId}")
    public String queryUserById(@PathVariable String userId, Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "4") int pageSize){
        uId = userId;
        getUrlOrImage(model,userId);
        Map<String, Object> userMap = loginMapper.queryUserById(userId);
        if(!CommonUtils.isEmpty(userMap)){
            model.addAttribute("userMap",userMap);
            //总营业额
            Map<String, Object> queryTurnoverMap = loginMapper.queryTurnover();
            model.addAttribute("turnoverMap",queryTurnoverMap);
            //分页
            PageHelper.startPage(page,pageSize);
            //主页显示
            List<Map<String, Object>> showList = loginService.queryIndexShow();
            if(CommonUtils.isEmpty(showList)){
                showList = new ArrayList<>();
            }
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(showList);
            model.addAttribute("pageInfo", pageInfo);
        }
        return "index";
    }

    /**
     * 模糊查询洗衣店信息
     * @param laundryName
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryLaundryInfoByName/{laundryName}")
    public String queryLaundryInfoByName(@PathVariable String laundryName, Model model,
                                         @RequestParam(required = false, defaultValue = "1") int page,
                                         @RequestParam(required = false, defaultValue = "3") int pageSize){
        getUserInfo(model);
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> laundryList = loginService.queryLaundryInfoByName(laundryName);
        if(CommonUtils.isEmpty(laundryList)){
            laundryList = new ArrayList<>();
        }
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(laundryList);
        model.addAttribute("pageInfo",pageInfo);
        return "laundryList";
    }

    /**
     * 模糊查询用户信息
     * @param userName
     * @param model
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/queryUserInfoByName/{userName}")
    public String queryUserInfoByName(@PathVariable String userName, Model model,
                                      @RequestParam(required = false, defaultValue = "1") int page,
                                      @RequestParam(required = false, defaultValue = "3") int pageSize){
        getUserInfo(model);
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> userInfo = loginMapper.queryUserInfoByName(userName);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(userInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "userList";
    }

    /**
     * 入驻申请/审核处理
     * @param laundryId
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateStateByLaundryId")
    public String updateStateByLaundryId(@RequestParam String laundryId){
        System.out.println(laundryId);
        int i = loginMapper.updateStateByLaundryId(laundryId);
        if(i>0){
            return "success";
        }
        return "fail";
    }

    /**
     * 用户列表
     */
    @RequestMapping("/queryUserList")
    public String queryUserList(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "5") int pageSize){
        getUserInfo(model);
        getUrlOrImage(model,uId);
        //分页
        PageHelper.startPage(page,pageSize);
        List<Map<String, Object>> userInfo = loginMapper.queryAllUserInfo(uId);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(userInfo);
        model.addAttribute("pageInfo",pageInfo);
        return "userList";
    }


    /**
     * 批量删除用户
     * @return
     */
//    @ResponseBody
    @RequestMapping("/deleteUserById")
    public String deleteUserById(String[] userIds) {
        //转换成数组
        Arrays.asList(userIds);
        System.out.println(userIds);
        loginMapper.deleteUserById(userIds);
        return "redirect:queryUserList";
    }

    /**
     * 根据ID删除用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/deleteUserInfoById")
    public String deleteUserInfoById(String userId){
        loginMapper.deleteUserInfoById(userId);
        return "redirect:queryUserList";
    }

    /**
     * 根据ID获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping("/queryUserInfoById/{userId}")
    public String queryUserInfoById(@PathVariable("userId")String userId,Model model){
        Map<String, Object> userList = loginMapper.queryUserById(userId);
        String time = CommonUtils.defaultValueOfObjects(userList.get("time"),"");
        String formatDate = CommonUtils.dateTransformation(String.valueOf(time));
        userList.put("time",formatDate);
        model.addAttribute("userList",userList);
        return "userInfo";
    }


    /**
     * 个人信息
     * @param model
     * @return
     */
    @RequestMapping("/userInfo")
    public String queryUserList(Model model){
        getUserInfo(model);
        getUrlOrImage(model,uId);
        return "userInfo";
    }

    /**
     * 修改个人信息
     * @param
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(@RequestParam Map<String,Object> map){
        System.out.println(map);
        String time = map.get("time").toString();
        String formatTime = CommonUtils.dateTransformation2(time);
        map.put("time",formatTime);
        int i = loginMapper.updateUserInfoById(map);
        if(i>0){
            return "success";
        }else {
            return "fail";
        }

    }

    /**
     * 修改密码(页面)
     * @param model
     * @return
     */
    @RequestMapping("/updatePwd")
    public String updatePwd(Model model,@RequestParam Map<String,Object> param) {
        getUserInfo(model);
        getUrlOrImage(model, uId);
        return "updatePwd";
    }

    /**
     * 修改密码
     * @param param
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@RequestParam Map<String,Object> param) {
        int i = loginMapper.updatePwd(param);
        if(i>0){
            return "success";
        }else {
            return "fail";
        }
    }



    /**
     * 根据登录id获取用户信息
     * @return
     */
    public void getUserInfo(Model model){
        if(!"".equals(uId)) {
            Map<String, Object> userMap = loginMapper.queryUserById(uId);
            String time = CommonUtils.defaultValueOfObjects(userMap.get("time"),"");
            String formatTime = CommonUtils.dateTransformation(time);
            userMap.put("time",formatTime);
            model.addAttribute("userMap", userMap);
        }
    }

    /**
     * 获取相应的洗衣店列表
     * @return
     */
    public List<Map<String,Object>> getLaundryList(String str){
        List<Map<String,Object>> newLaundryList = new ArrayList<>();
        //查询所有洗衣店信息
        List<Map<String, Object>> laundryList = loginService.queryAllLaundryInfo();
        //遍历传进来的list
        for(Map<String,Object> laundryMap : laundryList){
            if(str.equals(laundryMap.get("state_name"))){
                newLaundryList.add(laundryMap);
            }
        }
        return newLaundryList;
    }

    /**
     * 查询跳转连接/头像
     */

    public void getUrlOrImage(Model model,String userId){
        Map<String, Object> imageMap = loginMapper.queryImageById(userId);
        model.addAttribute("imageMap",imageMap);
    }
}
