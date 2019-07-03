package com.example.demo.mybatisext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface PhoenixBaseMapper<T> extends BaseMapper<T> {

    int upsert(T entity);

    @Override
    default int insert(T entity){
        return upsert(entity);
    }

    @Override
    default int update(T entity, Wrapper<T> updateWrapper){
        throw new MybatisPlusException("phoenix not Support update");
    }

    @Override
    default int updateById(T entity){
        throw new MybatisPlusException("phoenix not Support updateById");
    }
}
