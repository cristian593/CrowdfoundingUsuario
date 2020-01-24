package com.guilcapi.crowdfoundingusuario;

public class Products {

    //Para el adaptador de Recycler de firebase
    //Ponemos todos los  elementos que necesitemos  del Products de la RealTimeDatabase

    private String archivo,date, description, email, image, pid, pname, telefono, time;

    public Products() {
    }

    public Products(String archivo, String date, String description, String email, String image, String pid, String pname, String telefono, String time) {
        this.archivo = archivo;
        this.date = date;
        this.description = description;
        this.email = email;
        this.image = image;
        this.pid = pid;
        this.pname = pname;
        this.telefono = telefono;
        this.time = time;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
