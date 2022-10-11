package com.crm.commons.domain;

/**
 * ClassName:ReturnObject
 * Package:
 * Project:crm-project
 * Description:
 *
 * @Date:2022/9/13 17:26
 * @Author:wang ranheng
 */
public class ReturnObject {
    private String code;//处理成功或失败的标记：1--成功，0--失败
    private String message;//提示信息
    private Object reData;//其他数据

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReData() {
        return reData;
    }

    public void setReData(Object reData) {
        this.reData = reData;
    }
}
