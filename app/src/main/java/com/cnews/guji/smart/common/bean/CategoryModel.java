package com.cnews.guji.smart.common.bean;

import com.google.gson.Gson;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    public String title;


    public CategoryModel(String title){
        this.title = title;

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
