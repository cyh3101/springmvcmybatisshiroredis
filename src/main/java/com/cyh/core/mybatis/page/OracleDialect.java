package com.cyh.core.mybatis.page;

/**
 * Created by cyh3101 on 2017/7/22.
 */
public class OracleDialect implements Dialect{
    protected static final String SQL_END_DELIMITER = ";";
    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitSqlString(String sql, int offset, int limit) {
        sql = trim(sql);
        boolean forUpdate = false;
        if(sql.endsWith(" for update")){
            sql = sql.substring(0,sql.length()-11);
            forUpdate = true;
        }
        if(offset < 0){
            offset = 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("select * from (select row_.*,rownum rownum_ from (");
        sb.append(" ) row_ ) where rownum <= " +
                (offset + limit) + " and rownum > " + (offset) + "");
        if(forUpdate){
            sb.append(" for update");
        }
        return sb.toString();
    }

    @Override
    public String getCountSqlString(String sql) {
        sql = trim(sql);
        StringBuffer sb = new StringBuffer();
        sb.append("select count(1) as " + RS_COLUMN + " from (");
        sb.append(sql);
        sb.append(" )a");
        return sb.toString();
    }

    public String trim(String sql){
        sql = sql.trim();
        if(sql.endsWith(SQL_END_DELIMITER)){
            sql = sql.substring(0,sql.length()-1-SQL_END_DELIMITER.length());
        }
        return sql;
    }
}
