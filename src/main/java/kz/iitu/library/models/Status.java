package kz.iitu.library.models;

public enum Status {
    FIXING, READY;

    public String getStatus(){
        return name();
    }
}
