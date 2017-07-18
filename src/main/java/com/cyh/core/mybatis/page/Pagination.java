package com.cyh.core.mybatis.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cyh on 2017/7/18.
 */
public class Pagination<T> extends SimplePage implements Paginable , Serializable{

    /**
     * 当前页的数据
     */
    private List<T> list;

    public void setList(List<T> list){
        this.list = list;
    }

    public List<T> getList(){
        return this.list;
    }
}
