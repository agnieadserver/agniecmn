package com.springriver.example.mybatis.util;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.springriver.example.mybatis.bean.Category;
import com.springriver.example.mybatis.bean.Input;
import com.springriver.example.mybatis.mapper.CategoryAllMapper;
import com.springriver.example.mybatis.mapper.CategoryMapper;

public class CatgegoryManager {
    public static Category selectCategoryById(int id) {
        SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();
        try {

            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);

            return categoryMapper.selectCategoryById(id);
        } catch (Throwable ex) {
            System.out.println("Error Found child1" + ex.getMessage());
            return null;
        } finally {
            sqlSession.close();
        }
    }

    public static enum SortOrder {
        ASC, DESC;
    }

    public static List<Category> getALLElementsWithoutPaging(String name, Integer age, String status, String column, SortOrder order) {
        SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();

        try {
            CategoryAllMapper allMapper = sqlSession.getMapper(CategoryAllMapper.class);

            HashMap<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("name", name);
            mapValue.put("age", age);
            mapValue.put("status", status);
            mapValue.put("column", column);
            mapValue.put("order", order);
            return allMapper.getAll(mapValue);
        } catch (Throwable ex) {
            System.out.println("Error Found " + ex.getMessage());
            return null;
        } finally {
            sqlSession.close();
        }
    }

    public static List<Category> getALLElements(String name, Integer age, String status, String column, int offset, int limit, SortOrder order) {
        SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();

        try {
            CategoryAllMapper allMapper = sqlSession.getMapper(CategoryAllMapper.class);

            HashMap<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("name", name);
            mapValue.put("age", age);
            mapValue.put("status", status);
            mapValue.put("column", column);
            mapValue.put("order", order);
            return allMapper.getAll(mapValue, new RowBounds(offset, limit));
        } catch (Throwable ex) {
            System.out.println("Error Found " + ex.getMessage());
            return null;
        } finally {
            sqlSession.close();
        }
    }

    public static List<Category> getALLTestElements(String name, Integer age, String type) {
        SqlSession sqlSession = ConnectionFactory.getSqlSessionFactory().openSession();

        try {
            CategoryAllMapper allMapper = sqlSession.getMapper(CategoryAllMapper.class);

            // There are other ways to do this e.g. putting '%'|| #{name} ||'%' inside mapper.xml but by this way
            // through sql injection someone can perform sql injection
            return allMapper.getAllTest(new Input(name, age, type));
        } catch (Throwable ex) {
            System.out.println("Error Found " + ex.getMessage());
            return null;
        } finally {
            sqlSession.close();
        }
    }

}
