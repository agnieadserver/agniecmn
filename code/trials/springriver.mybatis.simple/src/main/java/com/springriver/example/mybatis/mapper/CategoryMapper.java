package com.springriver.example.mybatis.mapper;

import com.springriver.example.mybatis.bean.Category;

public interface CategoryMapper {
    public Category selectCategoryById(Integer id);

}
