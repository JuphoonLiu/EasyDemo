package com.juphoon.zeroflll.easydemo.bean;

/**
 * Created by Zeroflll on 2017/5/21.
 */

public class Page {

    private int pageCount;
    private String itemCount;
    private int currentPage;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
