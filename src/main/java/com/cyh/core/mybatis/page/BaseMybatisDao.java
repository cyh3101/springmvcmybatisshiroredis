package com.cyh.core.mybatis.page;

import com.cyh.common.utils.LoggerUtils;
import com.cyh.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Autowired
    @Qualifier("sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

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

    public List findList(String sqlId , Map<String ,Object> params ,
                            Integer pageNo , Integer pageSize){
        pageNo = (null == pageNo) ? 1 :pageNo;
        pageSize = (null == pageSize) ? 10 :pageSize;

        int offSize = (pageNo - 1) * pageSize;
        String page_sql = String.format("limit %s , %s" , offSize , pageSize);
        params.put("page_sql" , page_sql);
        sqlId = String.format("%s.%s" , NAMESPACE , page_sql);

        List resultList = this.getSqlSession().selectList(sqlId , params);
        return resultList;
    }

    public List findList(Map<String , Object> params , Integer pageNo , Integer pageSize){
        return findList(DEFAULT_SQL_ID , params , pageNo , pageSize);
    }

    /**
     * 分页
     * @param sqlId
     * @param countId
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination findPage(String sqlId , String countId ,
                               Map<String , Object> params , Integer pageNo , Integer pageSize){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Pagination page = new Pagination();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        Configuration config = this.getSqlSession().getConfiguration();
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        String page_sql = String.format("limit %s , %s" , offset , pageSize);
        params.put("page_sql" , page_sql);

        BoundSql boundSql = config.getMappedStatement(sqlId).getBoundSql(params);
        String sqlCode = boundSql.getSql();

        String countCode = "";
        BoundSql countSql = null;
        if(StringUtils.isBlank(countId)){
            countCode = sqlCode;
            countSql = boundSql;
        }else {
            countId = String.format("%s.%s", NAMESPACE, countId);
            countSql = config.getMappedStatement(countId).getBoundSql(params);
            countCode= countSql.getSql();
        }

        try {
            //处理list
            Connection conn = this.getSqlSession().getConnection();
            List resultList = this.getSqlSession().selectList(sqlId, params);
            page.setList(resultList);

            //处理count
            PreparedStatement ps = getPreparedStatment4Count(countCode,countSql.getParameterMappings(),
                    params,conn);
            ps.execute();
            ResultSet resultSet= ps.getResultSet();
            while(resultSet.next()){
                page.setTotalCount(resultSet.getInt(1));
            }
        } catch (Exception e){
            LoggerUtils.fmtError(getClass(), e, "jdbc.error.code.findByPageBySqlId");
        } finally {

        }
        return page;
    }

    /**
     * 重载findPage
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination findPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        return findPage(DEFAULT_SQL_ID, DEFAULT_COUNT_SQL_ID, params, pageNo, pageSize);
    }

    /**
     * 查询count
     * @param sql
     * @param parameterMappingList
     * @param params
     * @param conn
     * @return
     * @throws SQLException
     */
    private PreparedStatement getPreparedStatment4Count(String sql,
             List<ParameterMapping> parameterMappingList,
             Map<String, Object> params, Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(StringUtils.trim(sql));
        int index = 1;
        for (int i = 0;i<parameterMappingList.size();i++){
            ps.setObject(index++,params.get(parameterMappingList.get(i).getProperty()));
        }
        return ps;
    }
}
