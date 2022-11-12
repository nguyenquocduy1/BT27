package com.example.bt27;

public class Album {
    public String idAlbum;
    public String nameAlbum;

    public Album(String idAlbum, String nameAlbum) {
        this.idAlbum = idAlbum;
        this.nameAlbum = nameAlbum;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }
}
