package com.cyh.core.mybatis.page;

/**
 * Created by cai on 2017/7/20.
 */
public interface Dialect {
    public static final String RS_COLUMN = "totalCount";

    public boolean supportsLimit();

    /**
     * 以传入的sql组装分页查询的sql
     * @param sql 原始的sql
     * @param offset 偏移量
     * @param limit 每页限定数量
     * @return 组装以后的sql
     */
    public String getLimitSqlString(String sql, int offset, int limit);

    /**
     * 以原始的sql组装查询总记录数的sql
     * @param sql 原始的sql
     * @return 拼装好的sql
     */
    public String getCountSqlString(String sql);
}
