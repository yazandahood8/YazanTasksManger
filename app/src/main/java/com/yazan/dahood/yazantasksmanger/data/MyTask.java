package com.yazan.dahood.yazantasksmanger.data;

import java.util.Date;

/**
 * Created by user on 9/8/2016.
 */
public class MyTask
{
    /**
     * rakam almhama
     */
    private String id;
    /**
     * enwaan
     */
    private String title;
    private int prioroty;
    private Date when;
    private String address;
    private String phone;



    public MyTask(String title, int prioroty, Date when, String address, String phone, String id) {

        this.title = title;
        this.prioroty = prioroty;
        this.when = when;
        this.address = address;
        this.phone = phone;
        this.id = id;


        }

    public MyTask() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrioroty() {
        return prioroty;
    }

    public void setPrioroty(int prioroty) {
        this.prioroty = prioroty;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", prioroty=" + prioroty +
                ", when=" + when +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}

