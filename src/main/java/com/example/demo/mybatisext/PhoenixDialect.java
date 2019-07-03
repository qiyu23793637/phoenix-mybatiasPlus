package com.example.demo.mybatisext;

import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect;

public class PhoenixDialect implements IDialect {

    @Override
    public DialectModel buildPaginationSql(String originalSql, long offset, long limit) {
        String sql = originalSql + " limit " + FIRST_MARK + " offset " + SECOND_MARK;
        return new DialectModel(sql, limit, offset).setConsumerChain();
    }
}
