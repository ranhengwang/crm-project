package com.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:WorkbenchIndexController
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/13 18:03
 * @Author:wang ranheng
 */
@Controller
public class WorkbenchIndexController {
    @RequestMapping("/workbench/index.do")
    public String index(){
        return "workbench/index";
    }
}
