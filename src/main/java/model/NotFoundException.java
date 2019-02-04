package model;

public class NotFoundException extends RuntimeException {
    private String object;

    public NotFoundException(String object){
        this.setObject(object);
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        if(object == null){
            throw new IllegalArgumentException("Can't be null");
        }
        this.object = object;
    }
}
