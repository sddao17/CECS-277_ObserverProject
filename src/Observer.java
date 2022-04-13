
/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Implements the Observer pattern by creating the interface for the Observer type.
 *          To be used with the Subject interface.
 *
 * Target Output: The relevant observer's information.
 */
public interface Observer {
    /**
     * Notifies all registered observers of a stock trade
     *
     * @param latestTrade is shared to all other registered observers
     */
    void update(Trade latestTrade);
}
