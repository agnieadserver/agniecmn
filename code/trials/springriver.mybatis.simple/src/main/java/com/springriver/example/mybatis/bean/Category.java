package com.springriver.example.mybatis.bean;

public class Category {
    private int    categoryId;

    private String c_name;

    private int c_age;

    private int    c_salary;

    private String c_status;

    private String c_describe;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return c_name;
    }

    public void setName(String name) {
        this.c_name = name;
    }

    public int getAge() {
        return c_age;
    }

    public void setAge(int age) {
        this.c_age = age;
    }

    public int getSalary() {
        return c_salary;
    }

    public void setSalary(int salary) {
        this.c_salary = salary;
    }

    public String getStatus() {
        return c_status;
    }

    public void setStatus(String status) {
        this.c_status = status;
    }

    public String getDesc() {
        return c_describe;
    }

    public void setDesc(String desc) {
        this.c_describe = desc;
    }

}
