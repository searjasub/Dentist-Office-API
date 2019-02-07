package model;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2670303903107543800L;

    private String object;

    public NotFoundException(String object){
        this.setObject(object);
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        if(object == null){
            throw new IllegalArgumentException("Can't be null and/or incorrect input");
        }
        this.object = object;
    }
}
