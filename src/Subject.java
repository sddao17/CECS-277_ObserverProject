
/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Implements the Observer pattern by creating the interface for the Subject type.
 *          To be used with the Observer interface.
 *
 * Target Output: The relevant subject's information.
 */
public interface Subject {
    /**
     * Registers Observer for the stock
     *
     * @param obs the observer added to the list of Observers
     */
    void registerObserver(Observer obs);

    /**
     * Unregisters Observer for the stock
     *
     * @param obs the observer removed from the list of Observers
     */
    void unregisterObserver(Observer obs);

    /**
     * Notifies all observers of a particular stock and its details
     */
    void notifyObservers();
}
