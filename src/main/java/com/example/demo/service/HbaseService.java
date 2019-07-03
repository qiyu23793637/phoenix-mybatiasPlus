package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.hbase.HbaseTestMapper;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HbaseService {
    @Autowired
    private HbaseTestMapper hbaseTestMapper;


    public int insert(User user){
        return hbaseTestMapper.upsert(user);
    }

    public IPage selectPage(UserVO userVO){
        //by VO
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.lambda().eq(userVO.getAge() != null , User::getAge,userVO.getAge());
        //IPage<User> userIPage = hbaseTestMapper.selectPage(userVO.page(),queryWrapper);

        //不用VO用User或者? extends User
        User user = new User();
        user.setAge(userVO.getAge());
        IPage<User> userIPage = hbaseTestMapper.selectPage(userVO.page(),new QueryWrapper<>(user));

        return userIPage;
    }

    public int deleteById(int id){
        return hbaseTestMapper.deleteById(id);
    }
}
