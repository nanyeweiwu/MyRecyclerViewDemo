package com.example.dzn.myrxjava;

/**
 * Created by dzn on 2016/10/13.
 */
public class TestBean {
    String name;
    String sex;
    String school;

    public TestBean(String school, String sex, String name) {
        this.school = school;
        this.sex = sex;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
