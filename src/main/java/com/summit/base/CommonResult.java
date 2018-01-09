package com.summit.base;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonResult {

    //成功标志
    private boolean success = true;

    //返回的本文内容
    private String resultMessage;

    //成功或失败类型
    private String resultType;

    //返回结果包含的json数组信息
    private JSONArray jarray;

    //返回结果包含的json对象信息
    private JSONObject jobject;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JSONArray getJarray() {
        return jarray;
    }

    public void setJarray(JSONArray jarray) {
        this.jarray = jarray;
    }

    public JSONObject getJobject() {
        return jobject;
    }

    public void setJobject(JSONObject jobject) {
        this.jobject = jobject;
    }
}
