package com.crm.settings.web.controller;

import com.crm.commons.contants.Contants;
import com.crm.commons.domain.ReturnObject;
import com.crm.commons.utils.DateUtils;
import com.crm.settings.model.User;
import com.crm.settings.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

/**
 * ClassName:UserController
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/12 17:45
 * @Author:wang ranheng
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/setting/qx/user/toLogin.do")
    public String toLogin(){
        return "/setting/qx/user/login";
    }
    @RequestMapping("/settings/qx/user/login.do")
    public @ResponseBody Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法，查询用户
        User user=userService.queryUserByLoginActAndPwd(map);

        //根据查询结果，生成响应信息
        ReturnObject returnObject=new ReturnObject();
        if(user==null){
            //登录失败,用户名或者密码错误
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或者密码错误");
        }else{//进一步判断账号是否合法
            //user.getExpireTime()   //2019-10-20
            //        new Date()     //2020-09-10
            if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else{
                //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                //把user保存到session中
                session.setAttribute(Contants.SESSION_USER,user);

                //如果需要记住密码，则往外写cookie
                if("true".equals(isRemPwd)){
                    Cookie c1=new Cookie("loginAct",user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);
                    c1.setSecure(true);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    c2.setSecure(true);
                    response.addCookie(c2);
                }else{
                    //把没有过期cookie删除
                    Cookie c1=new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    c1.setSecure(true);
                    response.addCookie(c1);
                    Cookie c2=new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    c2.setSecure(true);
                    response.addCookie(c2);
                }
            }
        }

        return returnObject;
    }
    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        Cookie c1=new Cookie("loginAct","1");
        c1.setMaxAge(0);
        c1.setSecure(true);
        response.addCookie(c1);
        Cookie c2=new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        c2.setSecure(true);
        response.addCookie(c2);
//        销毁session
        session.invalidate();
//        跳转首页
        return "redirect:/";
    }
}
