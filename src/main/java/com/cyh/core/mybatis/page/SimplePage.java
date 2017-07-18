package com.cyh.core.mybatis.page;

/**
 * Created by cyh on 2017/7/18.
 */
public class SimplePage implements Paginable{

    public static final int DEFAULT_COUNT = 20;
    private int totalCount = 0;

    private int pageNo = 1;

    private int pageSize = DEFAULT_COUNT;

    public SimplePage(){}

    public SimplePage(int pageNo , int pageSize , int totalCount){
        if(pageNo <= 0){
            this.pageNo = 1;
        }else {
            this.pageNo = pageNo;
        }
        if(pageSize <= 0){
            this.pageSize = DEFAULT_COUNT;
        }else {
            this.pageSize = pageSize;
        }
        if(totalCount <= 0){
            this.totalCount = 0;
        }else {
            this.totalCount = totalCount;
        }
        if((this.pageNo - 1) * pageSize >= totalCount){
            this.pageNo = totalCount / pageSize;
            if(this.pageNo == 0){
                this.pageNo = 1;
            }
        }
    }

    @Override
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    public int getTotalPage() {
        int totalPage = this.totalCount / pageSize;
        if(totalCount % pageSize != 0 || totalPage == 0){
            totalPage++;
        }
        return totalPage;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public int getPageNo() {
        return this.pageNo;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public void setPageNo(int pageNo){
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @Override
    public boolean isFirstPage() {
        return this.pageNo <= 1;
    }

    @Override
    public boolean isLastPage() {
        return this.pageNo >= getTotalPage();
    }

    @Override
    public int getNextPage() {
        if(isLastPage()){
            return this.pageNo;
        }else {
            return this.pageNo + 1;
        }
    }

    @Override
    public int getPrePage() {
        if(isFirstPage()){
            return this.pageNo;
        }else {
            return this.pageNo - 1;
        }
    }
}
