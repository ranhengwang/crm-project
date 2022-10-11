package com.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:MainController
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/17 21:35
 * @Author:wang ranheng
 */
@Controller
public class MainController {
    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }
}
