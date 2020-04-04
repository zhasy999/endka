package kz.iitu.library.models;

public enum Type {
    NEWBIE, EXPERT, LIBRARIAN;

    public String getType(){
        return name();
    }
}
