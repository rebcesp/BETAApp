package com.example.rebcesp.fastdrinkfinal.Model;

/**
 * Created by Rebcesp on 09/03/2018.
 */

public class Category {

    private String Name;
    private String Image;

    public Category(String name, String image) {
        this.Name = name;
        this.Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Category(){

    }


}
