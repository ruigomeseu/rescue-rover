package rescuerover.logic;

public interface Observer {

    //method to update the observer, used by subject
    public void update(Object object);
}