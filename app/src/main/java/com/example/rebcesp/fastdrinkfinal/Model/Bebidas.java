package com.example.rebcesp.fastdrinkfinal.Model;

/**
 * Created by Rebcesp on 09/03/2018.
 */

public class Bebidas {

    private String Name;
    private String Image;
    private String Descripcion;
    private String Precio;
    private String MenuId;

    public Bebidas(String name, String image, String descripcion, String precio, String menuId) {
        Name = name;
        Image = image;
        Descripcion = descripcion;
        Precio = precio;
        MenuId = menuId;
    }

    public Bebidas(){

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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
