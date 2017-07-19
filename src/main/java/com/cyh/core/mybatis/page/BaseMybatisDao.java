package com.cyh.core.mybatis.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by cyh on 2017/7/19.
 */
public class BaseMybatisDao<T> extends SqlSessionDaoSupport{
    private String NAMESPACE;
    final static Class<? extends Object> SELF = BaseMybatisDao.class;
    final static String DEFAULT_SQL_ID = "findAll";
    final static String DEFAULT_COUNT_SQL_ID = "findCount";
    protected final Log logger = LogFactory.getLog(BaseMybatisDao.class);

    public BaseMybatisDao(){
        Object genericClz = getClass().getGenericSuperclass();
        //System.out.println("aaaa" + getClass().toString());

        if(genericClz instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType)genericClz;
            //System.out.println("aaaa" + parameterizedType.toString());

            Class<T> entityClazz = (Class<T>)parameterizedType.getActualTypeArguments()[0];
            NAMESPACE = entityClazz.getPackage().getName() + "." + entityClazz.getSimpleName();
            //System.out.println("aaaa" + NAMESPACE);
        }
    }

    public List<T> findList(String sqlId , Map<String ,Object> params ,
                            Integer pageNo , Integer pageSize){
        pageNo = (null == pageNo) ? 1 :pageNo;
        pageSize = (null == pageSize) ? 10 :pageSize;

        int offSize = (pageNo - 1) * pageSize;
        String page_sql = String.format("limit %s , %s" , offSize , pageSize);
        params.put("page_sql" , page_sql);
        sqlId = String.format("%s.%s" , NAMESPACE , page_sql);

        List<T> resultList = this.getSqlSession().selectList(sqlId , params);
        return resultList;
    }

    public List<T> findList(Map<String , Object> params , Integer pageNo , Integer pageSize){
        return findList(DEFAULT_SQL_ID , params , pageNo , pageSize);
    }

    public Pagination findPage(String sqlId , String countId ,
                               Map<String , Object> params , Integer pageNo , Integer pageSize){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        Configuration configuration = this.getSqlSession().getConfiguration();
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        String page_sql = String.format("limit %s , %s" , offset , pageSize);
        params.put("page_sql" , page_sql);
        return page;
    }
}
