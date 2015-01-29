package com.springriver.example.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Input;

public interface CategoryAllMapper {

    public List<Category> getAll(HashMap<String, Object> value, RowBounds rwobounds);

    public List<Category> getAll(HashMap<String, Object> value);

    public List<Category> getAllTest(Input input);

}
