package com.summit.base;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonResult {
    private boolean mysuccess = false;

    private boolean success = true;

    private String errorText;

    private String successText;

    private JSONArray jarray;

    private JSONObject jobject;

    public String getSuccessText() {
        return successText;
    }

    public void setSuccessText(String successText) {
        this.successText = successText;
    }

    public boolean isMysuccess() {
        return mysuccess;
    }

    public void setMysuccess(boolean mysuccess) {
        this.mysuccess = mysuccess;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
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
