package com.example.demo.hbase;

import com.example.demo.entity.User;
import com.example.demo.mybatisext.PhoenixBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface HbaseTestMapper extends PhoenixBaseMapper<User> {
}
