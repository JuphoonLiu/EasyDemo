package com.juphoon.zeroflll.easydemo.bean;

/**
 * Created by Zeroflll on 2017/5/21.
 */

public class Repo {
    private int RESPONSE_STATUS;
    private String Tips;
    private ResponseInfo RESPONSE_INFO;

    public int getRESPONSE_STATUS() {
        return RESPONSE_STATUS;
    }

    public void setRESPONSE_STATUS(int RESPONSE_STATUS) {
        this.RESPONSE_STATUS = RESPONSE_STATUS;
    }

    public String getTips() {
        return Tips;
    }

    public void setTips(String tips) {
        Tips = tips;
    }

    public ResponseInfo getRESPONSE_INFO() {
        return RESPONSE_INFO;
    }

    public void setRESPONSE_INFO(ResponseInfo RESPONSE_INFO) {
        this.RESPONSE_INFO = RESPONSE_INFO;
    }
}
