package com.springriver.example.mybatis.bean;

public class Input {
    String  search;
    Integer age;
    String  type;

    public Input() {
    }

    public Input(String name, Integer age, String type) {
        this.search = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return search;
    }

    public void setName(String name) {
        this.search = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
