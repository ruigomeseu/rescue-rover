package rescuerover.logic;

/**
 * An interface that defines an observer
 * Used in the Observer Pattern
 */
public interface Observer {

    //method to update the observer, used by subject
    public void update(Object object);
}