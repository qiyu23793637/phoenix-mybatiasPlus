package com.example.demo.mybatisext;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.extension.injector.methods.*;
import com.example.demo.mybatisext.methods.PhoenixUpsert;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PhoenixSqlInjector extends AbstractSqlInjector{
    @Override
    public List<AbstractMethod> getMethodList() {
        return Stream.of(
                new PhoenixUpsert(),
                new Delete(),
                new DeleteByMap(),
                new DeleteById(),
                new DeleteBatchByIds(),
                new SelectById(),
                new SelectBatchByIds(),
                new SelectByMap(),
                new SelectOne(),
                new SelectCount(),
                new SelectMaps(),
                new SelectMapsPage(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage()
        ).collect(toList());
    }


}