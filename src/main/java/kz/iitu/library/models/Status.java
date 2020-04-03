package kz.iitu.library.models;

public enum Status {
    AVAILABLE, REQUESTED, ISSUED, OVERDUE;

    public String getStatus(){
        return name();
    }
}
