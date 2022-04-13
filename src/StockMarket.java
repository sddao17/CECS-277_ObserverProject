
import java.util.ArrayList;

/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Demonstrates the Observer Pattern.
 *          Simulates a stock market by being able to:
 *          * register stocks as Subjects
 *          * submit trades to a stock, keeping track of the trade and the updated information of the traders
 *          * keep a list of observers to notify when a trade has been made
 *          * add or delete from the list of observers, for our purposes, through handling of watch lists
 *
 * Target Output: Relevant trades, stock information, and trader information, along with the correct portrayal
 *                  the Observer Pattern by displaying the relationship between a Subject and an Observer.
 */
public class StockMarket {
    /**
     * Tests the classes needed for simulation of the Stock Market.
     *
     * @param args command-line arguments for the application
     */
    public static void main(String[] args) {
        PrintMessage.displayIntroduction();

        // begin testing

        PrintMessage.initializeStockTraderLists();

        // list of stocks for our simulated Stock Market
        ArrayList<Stock> stockList = new ArrayList<>() {
            {
                add(new Stock("Game Stop", "GME", 158.53));
                add(new Stock("Airbnb", "ABNB", 169.57));
                add(new Stock("General Electric", "GE", 13.08));
                add(new Stock("Tesla", "TSLA", 718.99));
                add(new Stock("Pfizer", "PFE", 39.03));
                add(new Stock("Netflix", "NFLX", 554.67));
            }
        };

        // list of testing traders
        ArrayList<Trader> traderList = new ArrayList<>() {
            {
                add(new Trader("Amy"));
                add(new Trader("Julie"));
                add(new Trader("Nick"));
                add(new Trader("Alex"));
                add(new Trader("Jennifer"));
                add(new Trader("Vy"));
            }
        };

        int traderListLength = traderList.size();
        int stockListLength = stockList.size();

        // print the initial list of stocks
        PrintMessage.displayListOfStocks();

        for (Stock stock : stockList) {
            PrintMessage.displayObject(stock);
        }

        // print the initial list of traders
        PrintMessage.displayListOfTraders();

        for (Trader trader : traderList) {
            PrintMessage.displayObject(trader);
        }

        PrintMessage.addingToWatchlist();

        // add stocks to each trader's watchlist in index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            traderList.get(i).addStockToWatchList(stockList.get(i % stockListLength));
        }

        // add stocks to each trader's watchlist in reverse index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            traderList.get(i).addStockToWatchList(stockList.get((stockListLength - 1 - i) % stockListLength));
        }

        // print the list of stocks after adding to their observer lists
        PrintMessage.displayListOfStocks();

        for (Stock stock : stockList) {
            PrintMessage.displayObject(stock);
        }

        // print the list of traders after adding to their watch lists
        PrintMessage.displayListOfTraders();

        for (Trader trader : traderList) {
            PrintMessage.displayObject(trader);
        }

        PrintMessage.buyingWithUnregisteredObs();

        // buying with unregistered observers in index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get(i % stockListLength)
                    .submitTrade(new Trade(stockList.get(i % stockListLength),
                            traderList.get(i),
                            "buy", (traderListLength - i) * 100));
        }

        // buying with unregistered observers in index + 1 matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get((i + 2) % stockListLength)
                    .submitTrade(new Trade(stockList.get((i + 2) % stockListLength),
                            traderList.get((i + 1) % traderListLength),
                            "buy", (traderListLength - i) * 100));
        }

        // buying with unregistered observers in index + 3 matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get((i + 5) % stockListLength)
                    .submitTrade(new Trade(stockList.get((i + 5) % stockListLength),
                            traderList.get((i + 2) % traderListLength),
                            "buy", (traderListLength - i) * 100));
        }

        PrintMessage.afterBuying();

        // print the list of stocks after buying stocks for each trader
        PrintMessage.displayListOfStocks();

        for (Stock stock : stockList) {
            PrintMessage.displayObject(stock);
        }

        // print the list of traders after buying stocks for each trader
        PrintMessage.displayListOfTraders();

        for (Trader trader : traderList) {
            PrintMessage.displayObject(trader);
        }

        PrintMessage.sellingWithRegisteredObs();

        // selling with registered observers in index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get(i % stockListLength)
                    .submitTrade(new Trade(stockList.get(i % stockListLength),
                            traderList.get(i),
                            "sell", (traderListLength - i) * 100));
        }

        // selling with unregistered observers in index + 1 matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get((i + 2) % stockListLength)
                    .submitTrade(new Trade(stockList.get((i + 2) % stockListLength),
                            traderList.get((i + 1) % traderListLength),
                            "sell", (traderListLength - i) * 100));
        }

        // selling with unregistered observers in index + 3 matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            stockList.get((i + 5) % stockListLength)
                    .submitTrade(new Trade(stockList.get((i + 5) % stockListLength),
                            traderList.get((i + 2) % traderListLength),
                            "sell", (traderListLength - i) * 100));
        }

        PrintMessage.afterSelling();

        // print the list of stocks after selling the trades back
        PrintMessage.displayListOfStocks();

        for (Stock stock : stockList) {
            PrintMessage.displayObject(stock);
        }

        // print the list of traders after selling the trades back
        PrintMessage.displayListOfTraders();

        for (Trader trader : traderList) {
            PrintMessage.displayObject(trader);
        }

        PrintMessage.removingFromWatchlist();

        // remove stocks from each trader's watchlist in index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            traderList.get(i).removeStockFromWatchList(stockList.get(i % stockListLength));
        }

        // remove stocks from each trader's watchlist in reverse index-matching order
        for (int i = 0; i < traderListLength; ++i) {
            // in case there are more traders than stocks, mod the index by the length of the stock list
            traderList.get(i).removeStockFromWatchList(stockList.get((stockListLength - 1 - i) % stockListLength));
        }

        // print the list of stocks after removing from the watch lists
        PrintMessage.displayListOfStocks();

        for (Stock stock : stockList) {
            PrintMessage.displayObject(stock);
        }

        // print the list of traders after removing from their watch lists
        PrintMessage.displayListOfTraders();

        for (Trader trader : traderList) {
            PrintMessage.displayObject(trader);
        }


        // testing is done
        PrintMessage.displayTermination();
    }
}
