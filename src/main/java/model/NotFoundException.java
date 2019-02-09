package model;

/**
 * Custom Exception that will help when any class will throw a NullPointerException with it's respective class
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2670303903107543800L;

    private String object;

    /**
     *
     * @param object the String representing the type of Class that is throwing this exception
     */
    public NotFoundException(String object){
        this.setObject(object);
    }

    /**
     * @return the String that was the cause of the exception
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the object of the exception
     * @param object the other Class type
     */
    public void setObject(String object) {
        if(object == null){
            throw new IllegalArgumentException("Can't be null and/or incorrect input");
        }
        this.object = object;
    }
}
