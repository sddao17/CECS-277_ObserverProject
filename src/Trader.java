
import java.util.ArrayList;

/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Simulates a trader in a stock market using the following information:
 *          * traderName - the name of the trader
 *          * stockWatchList - the ArrayList of stocks to be watched; functions as the Observer List
 *          * stockShareholderList - the ArrayList of stocks that the trader currently owns
 *          * tradeList - the list of trades that the trader has made
 *          * portfolioAmount - the total equity of all dollar amounts from all shares of the trader
 *
 * Target Output: The relevant information of the trader of the stock.
 */
public class Trader implements Observer {
    private String traderName;
    private ArrayList<Stock> stockWatchList;
    private ArrayList<Stock> stockShareholderList;
    private ArrayList<Trade> tradeList;
    private double portfolioAmount;

    /**
     * Trader default constructor
     */
    public Trader() {
        traderName = "";
        stockWatchList = new ArrayList<>();
        stockShareholderList = new ArrayList<>();
        tradeList = new ArrayList<>();
        portfolioAmount = 0;
    }
    /**
     * Trader Overloaded constructor with updated name.
     *
     * @param newName the name of the Trader
     */
    public Trader(String newName) {
        traderName = newName;
        stockWatchList = new ArrayList<>();
        stockShareholderList = new ArrayList<>();
        tradeList = new ArrayList<>();
        portfolioAmount = 0;
    }

    /**
     * Returns the Trader name
     *
     * @return the Trader name
     */
    public String getTraderName() {
        return traderName;
    }

    /**
     * Returns the trader’s Watch List
     *
     * @return the trader’s stockWatchList
     */
    public ArrayList<Stock> getStockWatchList() {
        return stockWatchList;
    }

    /**
     * Returns the trader’s stocks owned list
     *
     * @return the stockShareholderList
     */
    public ArrayList<Stock> getStockShareholderList() {
        return stockShareholderList;
    }
    /**
     * Returns the trader’s trade history
     *
     * @return the tradeList of the trader
     */
    public ArrayList<Trade> getTradeList() {
        return tradeList;
    }

    /**
     * Returns the portfolio worth
     *
     * @return the portfolio amount
     */
    public double getPortfolioAmount() {
        return portfolioAmount;
    }

    /**
     * Sets Trader’s name
     *
     * @param newName set name of the trader
     */
    public void setTraderName(String newName) {
        traderName = newName;
    }

    /**
     * Sets Trader’s StockWatchList
     *
     * @param newStockList sets list of watchList for trader
     */
    public void setStockWatchList(ArrayList<Stock> newStockList) {
        stockWatchList = newStockList;
    }

    /**
     * Sets Trader’s list of stocks owned
     *
     * @param newStockList sets list of owned stocks for trader
     */
    public void setStockShareholderList(ArrayList<Stock> newStockList) {
        stockShareholderList = newStockList;
    }


    /**
     * Sets Trader’s lists of trades
     *
     * @param newTradeList sets the tradeList for the trader
     */
    public void setTradeList(ArrayList<Trade> newTradeList) {
        tradeList = newTradeList;
    }

    /**
     * Sets Trader’s portfolio amount
     *
     * @param newPortfolioAmount sets the portfolioAmount of the trader
     */
    public void setPortfolioAmount(double newPortfolioAmount) {
        portfolioAmount = newPortfolioAmount;
    }

    /**
     * Adds a trade to Trader’s tradeList
     *
     * @param trade is added to tradeList of trader
     */
    public void addTrade(Trade trade) {
        tradeList.add(trade);
    }

    /**
     * Adds a stock to watch list of trader & registers them to observer list of stock
     *
     * @param stock is added to the watchList for trader
     */
    public void addStockToWatchList(Stock stock) {
        // add the stock to the list of stocks that the trader will observe if not already added
        if (!stockWatchList.contains(stock)) {
            stockWatchList.add(stock);
        }
        // register this trader as an observer to the stock if not yet registered
        if (!stock.getObserverList().contains(this)) {
            stock.registerObserver(this);
        }
    }

    /**
     * Adds stock to owned stocks list for trader
     *
     * @param stock is added to stockShareholderList of trader
     */
    public void addStockToShareholderList(Stock stock) {
        // add the stock to the list of stocks that the trader owns if not yet registered
        if (!stockShareholderList.contains(stock)) {
            stockShareholderList.add(stock);
        }
    }

    /**
     * Removes stock from watch list of trader & unregisters trader as an observer from the stock
     *
     * @param stock is removed from stockWatchList of trader
     */
    public void removeStockFromWatchList(Stock stock) {
        // remove the stock from the list of stocks that the trader will observe
        stockWatchList.remove(stock);
        // unregister this trader as an observer to the stock
        stock.unregisterObserver(this);
    }

    /**
     * Removes stock from owned stocks list for trader
     *
     * @param stock is removed from stockShareholderList of trader
     */
    public void removeStockFromShareholderList(Stock stock) {
        // remove the stock from the list of stocks that the trader owns
        stockShareholderList.remove(stock);
    }

    /**
     * Notifies all registered observers of a stock trade
     *
     * @param latestTrade is shared to all other registered observers
     */
    @Override
    public void update(Trade latestTrade) {
        // notify all registered observers
        PrintMessage.observerHasBeenNotified(traderName);
    }

    /**
     * Prints every Trader, the stocks they hold, their portfolio worth, and their buy & sell trades
     *
     * @return Trader assets and trade data
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("\t");

        description.append(traderName).append(" (Trader):")
                .append("\n\t\t- Stock watchlist: [");

        int watchListLength = stockWatchList.size();

        for (int i = 0; i < watchListLength; ++i) {
            if (i < watchListLength - 1) {
                description.append(stockWatchList.get(i).getName()).append(" (")
                        .append(stockWatchList.get(i).getTicker()).append(")").append(", ");
            } else {
                description.append(stockWatchList.get(i).getName()).append(" (")
                        .append(stockWatchList.get(i).getTicker()).append(")");
            }
        }

        description.append("]\n\t\t- Stocks owned: [");

        int shareHolderListLength = stockShareholderList.size();

        for (int i = 0; i < shareHolderListLength; ++i) {
            if (i < shareHolderListLength - 1) {
                description.append(stockShareholderList.get(i).getName()).append(" (")
                        .append(stockShareholderList.get(i).getTicker()).append(")").append(", ");
            } else {
                description.append(stockShareholderList.get(i).getName()).append(" (")
                        .append(stockShareholderList.get(i).getTicker()).append(")");
            }
        }

        description.append("]\n\t\t- Portfolio Amount: ").append(String.format("$%.2f", Math.abs(portfolioAmount)))
                .append("\n\t\t- Trades made: [");

        int tradeListLength = tradeList.size();
        String action;

        for (int i = 0; i < tradeListLength; ++i) {
            if (tradeList.get(i).getTransaction().equalsIgnoreCase("buy")) {
                action = "bought";
            } else {
                action = "sold";
            }

            description.append("\n\t\t\t").append(tradeList.get(i).getTrader().getTraderName()).append(" ")
                    .append(action).append(" ").append(tradeList.get(i).getNumOfShares())
                    .append(" shares of ").append(tradeList.get(i).getStock().getTicker())
                    .append(" worth ").append(String.format("$%.2f", tradeList.get(i).getAmount()))
                    .append(".");

            if (i == tradeListLength - 1) {
                description.append("\n\t\t  ");
            }
        }
        description.append("]\n");

        return description.toString();
    }
}
