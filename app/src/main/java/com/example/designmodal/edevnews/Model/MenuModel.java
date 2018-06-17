package com.example.designmodal.edevnews.Model;

/**
 * Created by compware on 6/15/2018.
 */

public class MenuModel
{
    private String category_id;
    private String category_title;

    public MenuModel(String category_id, String category_title)
    {
        this.category_id = category_id;
        this.category_title = category_title;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return category_title;
    }

    public void setCategory_title(String category_title) {
        this.category_title = category_title;
    }
}


