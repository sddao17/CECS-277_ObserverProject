
/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Handles all instances of using PrintStream to produce output to the console.
 *
 * Target Output: Relevant information to the console depending on the method used.
 */
public class PrintMessage
{
    /**
     * Displays welcome message for Stock Market program.
     */
    public static void displayIntroduction() {
        System.out.println("-----------------------------------------------------\n" +
                "\tSimulated Stock Market using Observer Pattern\n" +
                "-----------------------------------------------------");
    }

    /**
     * Displays beginning of program and elements to expect.
     */
    public static void initializeStockTraderLists() {
        System.out.println("\nInitializing Stocks and Trader lists ...\n\n" +
                "- Expected: empty trades, lists, and portfolios\n\n");
    }

    /**
     * Prefacing Watch list additions.
     */
    public static void addingToWatchlist() {
        System.out.println("--------------------------------------------------\n\n" +
                "Adding 2 stocks to each Trader's watchlist ...\n\n" +
                "- Expected: lists should be updated with:\n" +
                "\t\t\t- 2 stocks in each Trader's watchlist\n" +
                "\t\t\t- the appropriate number of observers\n\n");
    }

    /**
     * Prefacing buy trades to each Trader.
     */
    public static void buyingWithUnregisteredObs() {
        System.out.println("-----------------------------------------------------------------------------\n\n" +
                "Submitting 3 \"buy\" trades per Trader with some unregistered observers ...\n\n" +
                "- Expected: only registered observers should receive notifications\n");
    }

    /**
     * Displays buy trade notification and update data.
     */
    public static void afterBuying() {
        System.out.println("\n-----------------------------------------------------------------------------\n\n" +
                "After submitting 3 \"buy\" trades per Trader ...\n\n" +
                "- Expected: lists should now have an:\n" +
                "\t\t\t- updated `latestTrade`\n" +
                "\t\t\t- updated list of stocks owned\n" +
                "\t\t\t- updated `portfolioAmount`\n" +
                "\t\t\t- extra 3 trades made for the Traders\n\n");
    }

    /**
     * Prefacing sell trades and some expected elements.
     */
    public static void sellingWithRegisteredObs() {
        System.out.println("-----------------------------------------------------------------------------\n\n" +
                "Selling the trades back ...\n\n" +
                "- Expected: similar output to `buy` trade, except now the shares are sold\n");
    }

    /**
     * Displays a sell trade notification and updated data.
     */
    public static void afterSelling() {
        System.out.println("\n-----------------------------------------------------------------------------\n\n" +
                "After selling the trades back ...\n\n" +
                "- Expected: lists should:\n" +
                "\t\t\t- be reverted to their states prior to the `buy` trade\n" +
                "\t\t\t- have an updated `latestTrade`\n" +
                "\t\t\t- have an extra 3 trades made for the Traders\n\n");
    }

    /**
     * Displays Watch list deletion confirmation.
     */
    public static void removingFromWatchlist() {
        System.out.println("--------------------------------------------------\n\n" +
                "After removing all stocks from all watch lists ...\n\n" +
                "- Expected: empty observer lists and watch lists\n\n");
    }

    /**
     * Prints heading to all the Traders.
     */
    public static void displayListOfTraders() {
        System.out.println("--------------------- Traders --------------------\n");
    }

    /**
     * Prints heading to all of the Stocks .
     */
    public static void displayListOfStocks() {
        System.out.println("--------------------- Stocks ---------------------\n");
    }


    /**
     * Prints trade data, stock, trader, shares, share price
     */
    public static void displayTrade(String name, String transaction,
                                    double numOfShares, double amount, String stockTicker) {
        String action;
        if (transaction.equalsIgnoreCase("buy")) {
            action = "bought";
        } else {
            action = "sold";
        }
        System.out.println("\n\tLatest trade: " + name + " " + action + " " + numOfShares +
                " shares of " + stockTicker + " worth " + String.format("$%.2f", amount) + ".");
    }

    /**
     * Prints the message that the observer has been notified.
     *
     * @param name the name of the observer
     */
    public static void observerHasBeenNotified(String name) {
        System.out.println("\t\t- " + name + " has been notified of this trade.");
    }

    /**
     * Displays an error message indicating that there are a negative number of shares.
     *
     * @param totalSharesLeft the total shares left that the trader currently owns
     */
    public static void negativeSharesLeftError(double totalSharesLeft) {
        System.out.println("Warning: A negative number of \"" + totalSharesLeft + "\" shares was found.");
    }

    /**
     * Displays the object using its toString() method.
     *
     * @param obj the object to display
     */
    public static void displayObject(Object obj) {
        System.out.println(obj);
    }

    /**
     * Displays the end of Stock Market program.
     */
    public static void displayTermination() {
        System.out.println("----------------------------------------\n" +
                "\tThe program has been terminated.\n" +
                "----------------------------------------");
    }
}
