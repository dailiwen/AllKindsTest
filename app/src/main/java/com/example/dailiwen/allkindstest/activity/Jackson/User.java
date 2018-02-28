package com.example.dailiwen.allkindstest.activity.Jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author dailiwen
 * @date 2018/02/27
 */

public class User {
    private String name;

    /**
     * 生成json时,不生成age属性
     */
    @JsonIgnore
    private int age;

    /**
     * 设置日期格式
     */
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birthday;

    /**
     * 该属性的名称序列化为另外一个名称，如把trueName属性序列化为name
     */
    @JsonProperty("mail")
    private String email;

    public String getName() {
        return name;
    }

    /**
     * 在set方法中应该运用this.xxx = xxx;的格式，在后面的转换时json中就不会出现两个重复的数据
     * 具体出现重复数据的原因还不清楚
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}
