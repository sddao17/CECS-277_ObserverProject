
import java.util.ArrayList;

/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Simulates a stock in a stock market using the following information:
 *          * stockName - the name of the stock
 *          * stockTicker - the ticker or symbol of the stock
 *          * currentPricePerShare - the stock's current price per share
 *          * latestTrade - the latest trade made involving the stock
 *          * observerList - the ArrayList of observers to be notified when a trade has been made
 *
 * Target Output: The relevant information of the stock.
 */
public class Stock implements Subject {
    private String stockName;
    private String stockTicker;
    private double currentPricePerShare;
    private Trade latestTrade;
    private ArrayList<Observer> observerList;

    /**
     * Stock default constructor
     */
    public Stock() {
        stockName = "";
        stockTicker = "";
        currentPricePerShare = 0;
        latestTrade = null;
        observerList = new ArrayList<>();
    }

    /**
     * Overloaded constructor a stock the update name, ticker, and share price.
     *
     * @param newName the name of the Stock
     * @param newTicker the name of the stock ticker
     * @param newPricePerShare the updated share price
     */
    public Stock(String newName, String newTicker, double newPricePerShare) {
        stockName = newName;
        stockTicker = newTicker;
        currentPricePerShare = newPricePerShare;
        latestTrade = null;
        observerList = new ArrayList<>();
    }

    /**
     * Returns the Stock Name.
     *
     * @return the stock name
     */
    public String getName() {
        return stockName;
    }

    /**
     * Returns the ticker name.
     *
     * @return the ticker
     */
    public String getTicker() {
        return stockTicker;
    }

    /**
     * Returns the price of one share.
     *
     * @return the share price
     */
    public double getCurrentPricePerShare() {
        return currentPricePerShare;
    }

    /**
     * Returns the most recent trade
     *
     * @return the latest trade
     */
    public Trade getLatestTrade() {
        return latestTrade;
    }

    /**
     * Returns the ArrayList of observers
     *
     * @return the list of observers
     */
    public ArrayList<Observer> getObserverList() {
        return observerList;
    }

    /**
     * Set’s name of stock
     *
     * @param newName set’s name of stock
     */
    public void setName(String newName) {
        stockName = newName;
    }

    /**
     * Sets ticker symbol of stock
     *
     * @param newTicker sets ticker of stock
     */
    public void setTicker(String newTicker) {
        stockTicker = newTicker;
    }

    /**
     * Sets price of stock
     *
     * @param newPricePerShare sets price of stock
     */
    public void setCurrentPricePerShare(double newPricePerShare) {
        currentPricePerShare = newPricePerShare;
    }

    /**
     * Sets latestTrade of the stock
     *
     * @param newTrade sets latest trade of the stock
     */
    public void setLatestTrade(Trade newTrade) {
        latestTrade = newTrade;
    }

    /**
     * Sets ObserverList of the stock
     *
     * @param newObserverList sets observer list of the stock
     */
    public void setObserverList(ArrayList<Observer> newObserverList) {
        observerList = newObserverList;
    }

    /**
     * Submits a trade for the stock
     *
     * @param newTrade sets latest trade of the stock
     */
    public void submitTrade(Trade newTrade) {
        // set the latest trade
        latestTrade = newTrade;

        PrintMessage.displayTrade(latestTrade.getTrader().getTraderName(), latestTrade.getTransaction(),
                latestTrade.getNumOfShares(), latestTrade.getAmount(),
                latestTrade.getStock().getTicker());

        // notify all registered observers of the stock
        notifyObservers();

        // after notifying all observers, now execute the trade
        latestTrade.executeTrade();
    }

    /**
     * Registers Observer for the stock
     *
     * @param obs the observer added to the list of Observers
     */
    @Override
    public void registerObserver(Observer obs) {
        observerList.add(obs);
    }

    /**
     * Unregisters Observer for the stock
     *
     * @param obs the observer removed from the list of Observers
     */
    @Override
    public void unregisterObserver(Observer obs) {
        observerList.remove(obs);
    }

    /**
     * Notifies all observers of a particular stock and its details
     */
    @Override
    public void notifyObservers() {
        // notify all registered observers of the stock
        for (Observer obs : observerList) {
            obs.update(latestTrade);
        }
    }

    /**
     * Returns detailed description of a Stock (its name, price, and latest trade)
     *
     * @return the stock details
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder();

        description.append("\t").append(stockName).append(" (").append(stockTicker).append("):")
                .append("\n\t\t- Current share price: ")
                .append(String.format("$%.2f", currentPricePerShare))
                .append("\n\t\t- Latest trade: ");

        // print "none" instead of "null" if there was no latest trade
        if (latestTrade == null) {
            description.append("none");
        } else {
            // otherwise, print out the latest trade
            String action;

            if (latestTrade.getTransaction().equalsIgnoreCase("buy")) {
                action = "bought";
            } else {
                action = "sold";
            }

            description.append(latestTrade.getTrader().getTraderName()).append(" ")
                    .append(action).append(" ").append(latestTrade.getNumOfShares())
                    .append(" shares of ").append(latestTrade.getStock().getTicker())
                    .append(" worth ").append(String.format("$%.2f", latestTrade.getAmount()))
                    .append(".");
        }

        description.append("\n\t\t- Observers: [");

        int observerListLength = observerList.size();

        for (int i = 0; i < observerListLength; ++i) {
            // check if the observer is of the Trader class type
            if (observerList.get(i) instanceof Trader) {
                // if it is a Trader class type, we can invoke its methods
                Trader obsAsTrader = (Trader) observerList.get(i);

                if (i < observerListLength - 1) {
                    description.append(obsAsTrader.getTraderName()).append(", ");
                } else {
                    description.append(obsAsTrader.getTraderName());
                }
            } else {
                // if it isn't a Trader class type, just return the object's toString() method
                if (i < observerListLength - 1) {
                    description.append(observerList.get(i)).append(", ");
                } else {
                    description.append(observerList.get(i));
                }
            }
        }
        description.append("]\n");

        return description.toString();
    }
}
