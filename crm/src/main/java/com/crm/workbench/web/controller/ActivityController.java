package com.crm.workbench.web.controller;

import com.crm.commons.contants.Contants;
import com.crm.commons.domain.ReturnObject;
import com.crm.commons.utils.DateUtils;
import com.crm.commons.utils.UUIDUtils;
import com.crm.settings.model.User;
import com.crm.settings.service.UserService;
import com.crm.workbench.Service.ActivityService;
import com.crm.workbench.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ActivityController
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/17 22:58
 * @Author:wang ranheng
 */
@Controller
public class ActivityController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUsers();
        request.setAttribute("userList",userList);
//        请求转发到市场活动的主页面
        return "workbench/activity/index";
    }
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody
    Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            int i = activityService.saveCreateActivity(activity);
            if (i>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
            else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("添加失败");
            }
        }
        catch (Exception exception){
            exception.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("添加失败");
        }

        return returnObject;
    }
    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        Map<String,Object> map= new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Activity> activities = activityService.queryActivityByConditionForPage(map);

        int count = activityService.queryCountOfActivityByCondition(map);
//        根据查询结果，生成响应信息
        Map<String ,Object> retmap = new HashMap<>();
        retmap.put("activityList",activities);
        retmap.put("totalRows",count);
        return retmap;
    }
    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    public @ResponseBody Object deleteActivityByIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
             int ret = activityService.deleteActivityByIds(id);
             if (ret>0){
                 returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
             }else {
                 returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                 returnObject.setMessage("删除失败");
             }
        }catch (Exception exception){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("删除失败");
        }
        return returnObject;
    }
    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody Object queryActivityById(String id){
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }
    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity,HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
//        封装参数
        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityService.saveEditActivity(activity);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("修改失败");
            }
        }catch (Exception exception){
            exception.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("修改失败");
        }
        return returnObject;
    }
}
