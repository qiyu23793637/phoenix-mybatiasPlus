package com.example.demo.vo;

import com.example.demo.mybatisext.Pagination;
import lombok.Data;

@Data
public class UserVO extends Pagination {
    Integer age;
}
