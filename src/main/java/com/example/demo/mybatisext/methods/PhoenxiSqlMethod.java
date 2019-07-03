package com.example.demo.mybatisext.methods;

public enum PhoenxiSqlMethod {

    UPSERT_ONE("upsert", "插入或覆盖一条数据（选择字段插入）", "<script>\nUPSERT INTO %s %s VALUES %s\n</script>");

    private final String method;
    private final String desc;
    private final String sql;

    PhoenxiSqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }
}
