package com.example.demo.ctrl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import com.example.demo.service.HbaseService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @Autowired
    private HbaseService hbaseService;


    @GetMapping("page")
    public IPage<User> page(UserVO userVO) {
        return hbaseService.selectPage(userVO);
    }

    @GetMapping("insert")
    public int insert(User user){
        return hbaseService.insert(user);
    }

    @GetMapping("deleteById/{id}")
    public int deleteById(@PathVariable int id){
        return hbaseService.deleteById(id);
    }
}
