package com.cyh.core.mybatis.page;

/**
 * Created by cyh on 2017/7/18.
 */
public interface Paginable {

    /**
     * 总记录数
     *
     */
    public int getTotalCount();

    /**
     * 总页数
     *
     */
    public int getTotalPage();

    /**
     * 每页记录数
     *
     */
    public int getPageSize();

    /**
     * 当前页
     *
     */
    public int getPageNo();

    /**
     * 是否第一页
     *
     */
    public boolean isFirstPage();

    /**
     * 是否最后一页
     *
     */
    public boolean isLastPage();

    /**
     *返回下一页的页号
     *
     */
    public int getNextPage();

    /**
     * 返回上一页的页号
     *
     */
    public int getPrePage();
}
