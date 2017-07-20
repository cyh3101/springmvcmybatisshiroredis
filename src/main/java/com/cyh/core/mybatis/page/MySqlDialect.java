package com.cyh.core.mybatis.page;

/**
 * Created by cai on 2017/7/20.
 */
public class MySqlDialect implements Dialect{
    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public boolean supportsLimit() {
        return true;
    }

    /**
     *
     * @param sql 原始的sql
     * @param offset 偏移量
     * @param limit 每页限定数量
     * @return
     */
    @Override
    public String getLimitSqlString(String sql, int offset, int limit) {
        sql = sql.trim();
        boolean isForUpdate = false;
        if (sql.toLowerCase().endsWith(" for update")) {
            sql = sql.substring(0, sql.length() - 11);
            isForUpdate = true;
        }
        if (offset < 0) {
            offset = 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(sql + " limit " + offset + "," + limit);
        if (isForUpdate) {
            sb.append(" for update");
        }
        return sb.toString();
    }

    @Override
    public String getCountSqlString(String sql) {
        sql = sql.trim();
        StringBuffer sb = new StringBuffer(sql.length() + 10);
        sb.append("select count(1) as " + RS_COLUMN + " from ( ");
        sb.append(sql);
        sb.append(" )a");
        return sb.toString();
    }

    private String trim(String sql){
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
        }
        return sql;
    }
}
