package com.crm.workbench.Service;

import com.crm.workbench.mapper.ActivityMapper;
import com.crm.workbench.model.Activity;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ActivityService
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/18 17:18
 * @Author:wang ranheng
 */
public interface ActivityService {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String, Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);
}