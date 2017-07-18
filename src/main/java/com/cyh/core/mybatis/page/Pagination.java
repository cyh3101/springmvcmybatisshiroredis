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

    public Pagination(){}

    public Pagination(int pageNo , int pageSize , int totalCount){
        super(pageNo , pageSize , totalCount);
    }

    public Pagination(int pageNo , int pageSize , int totalCount , List<T> list){
        super(pageNo , pageSize , totalCount);
        this.list = list;
    }

    public String getPageHtml(){
        StringBuffer pageHtml = new StringBuffer();

        pageHtml.append("<ul class='pagination'>");
        if(this.getPageNo() > 1){
            if(this.getPageNo() > 5){
                pageHtml.append("<li><a href='javascript:;' onclick='_submitform(1)'>首页</a></li>");
            }
            pageHtml.append("<li><a href='javascript:;' onclick='_submitform(" + (this.getPageNo() - 1) + ")'>上一页</a></li>");
        }
        for (int i = (this.getPageNo() - 2 <= 0 ? 1 : this.getPageNo()),no = 1;
             i <= this.getTotalPage() && no < 6 ; i++,no++){
            if(this.getPageNo() == i){
                pageHtml.append("<li><a href='javascript:;' class='active'>" + i + "</a></li>");
            }
            pageHtml.append("<li><a href='javascript:;' onclick='_submitform(" + i + ")'>" + i + "</a></li>");
        }
        if(this.getPageNo() < this.getTotalPage()){
            pageHtml.append("<li><a href='javascript:;' onclick='_submitform(" + (this.getPageNo() + 1) + ")'>下一页</a></li>");
        }
        pageHtml.append("</ul>");

        pageHtml.append("<script>");
        pageHtml.append("       function _submitform(pageNo){");
        pageHtml.append("           $(\"#formId\").append($(\"<input type='hidden' value='\" + pageNo +\"' name='pageNo'>\")).submit();");
        pageHtml.append("       }");
        pageHtml.append("</script>");


        return pageHtml.toString();
    }
}
