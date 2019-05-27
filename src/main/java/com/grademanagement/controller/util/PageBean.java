package com.grademanagement.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean<E> {

    private int curPage;//当前的页码
    private int countPage;//总页数
    private int totalCount;//每一页应该查询出来的的记录条数
    private List<E> pageData=new ArrayList<E>();//承载数据\
    private Map<String,String> sqlProjection;

    public PageBean() {
    }

    public PageBean(int curPage, int countPage, int totalCount, List<E> pageData, Map<String, String> sqlProjection) {
        this.curPage = curPage;
        this.countPage = countPage;
        this.totalCount = totalCount;
        this.pageData = pageData;
        this.sqlProjection = sqlProjection;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        if(countPage==0) {
            countPage=1;
            return;
        }
        if(countPage%this.totalCount==0) {
            this.countPage=countPage/totalCount;
        }else {
            this.countPage = countPage/totalCount+1;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getPageData() {
        return pageData;
    }

    public void setPageData(List<E> pageData) {
        this.pageData = pageData;
    }

    public Map<String, String> getSqlProjection() {
        return sqlProjection;
    }

    public void setSqlProjection(Map<String, String> sqlProjection) {
        this.sqlProjection = sqlProjection;
    }
}
