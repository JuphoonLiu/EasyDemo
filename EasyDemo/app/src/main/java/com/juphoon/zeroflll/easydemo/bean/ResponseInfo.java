package com.juphoon.zeroflll.easydemo.bean;

import java.util.ArrayList;

/**
 * Created by Zeroflll on 2017/5/21.
 */

public class ResponseInfo {
    private Page page;
    private ArrayList<Hot> list;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<Hot> getList() {
        return list;
    }

    public void setList(ArrayList<Hot> list) {
        this.list = list;
    }
}
