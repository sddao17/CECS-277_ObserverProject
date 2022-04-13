
/**
 * @author Alexander Loo, Phoenix Ngan, Steven Dao
 * @version 1.0
 * Due Date: April 21th, 2021, 2:00pm
 *
 * Purpose: Simulates a trade in a stock market using the following information:
 *          * stock - the stock to be traded
 *          * trader - the trader that will be involved with the trade
 *          * transaction - the type of transaction to occur, either a `buy` or `sell`
 *          * numOfShares - the number of shares to be traded
 *          * amount - the dollar amount of the total shares to be traded
 *
 * Target Output: The relevant trade information of the share of the stock.
 */
public class Trade {
    private Stock stock;
    private Trader trader;
    private String transaction;
    private double numOfShares;
    private double amount;

    /**
     * Trade default constructor
     */
    public Trade() {
        stock = null;
        trader = null;
        transaction = "";
        numOfShares = 0;
        amount = 0;
    }

    /**
     * Trade Overloaded constructor; constructs a Trade with stock and trader name,
     * trade(buy or sell), and the number of shares
     *
     * @param newStock the name of the Stock
     * @param newTrader the name of the Trader
     * @param newTransaction the transaction type
     * @param newNumOfShares number of shares for underlying stock
     */
    public Trade(Stock newStock, Trader newTrader, String newTransaction, double newNumOfShares) {
        stock = newStock;
        trader = newTrader;
        transaction = newTransaction;
        numOfShares = newNumOfShares;
        amount = stock.getCurrentPricePerShare() * newNumOfShares;

        if (transaction.equalsIgnoreCase("buy")) {
            // add the trader to the watch / observer lists for the stock if they are buying but not yet registered
            trader.addStockToShareholderList(stock);
        }

        // add this trade to the trader's list of trades made
        trader.addTrade(this);
    }

    /**
     * Returns the Stock name
     *
     * @return the stock name
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Returns the Trader name
     *
     * @return the trader name
     */
    public Trader getTrader() {
        return trader;
    }

    /**
     * Returns the transaction type (buy or sell)
     *
     * @return the transaction type
     */
    public String getTransaction() {
        return transaction;
    }

    /**
     * Returns the amount of share
     *
     * @return the number of shares
     */
    public double getNumOfShares() {
        return numOfShares;
    }

    /**
     * Returns the portfolio worth
     *
     * @return the amount of portfolio
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets stock name
     *
     * @param newStock sets name of stock
     */
    public void setStock(Stock newStock) {
        stock = newStock;
    }

    /**
     * Sets trader name
     *
     * @param newTrader sets name of trader
     */
    public void setTrader(Trader newTrader) {
        trader = newTrader;
    }

    /**
     * Sets transaction type
     *
     * @param newTransaction sets transaction as buy or sell
     */
    public void setTransactionType(String newTransaction) {
        transaction = newTransaction;
    }

    /**
     * Set’s number of shares
     *
     * @param newNumOfShares sets number of shares to buy/sell
     */
    public void setNumOfShares(double newNumOfShares) {
        numOfShares = newNumOfShares;
    }
    /**
     * Set’s the price of a stock
     *
     * @param newAmount sets the price of the stock
     */
    public void setAmount(double newAmount) {
        amount = newAmount;
    }

    /**
     * Executes trades (buy and sell) then adds or subtracts the number of shares
     * and portfolio amount; Method will give an error message if there are negative
     * shares.
     */
    public void executeTrade() {
        double totalSharesLeft = 0;

        // keep track of the total shares of this stock that the trader has
        for (Trade trade : trader.getTradeList()) {
            if (trade.getStock().getTicker().equals(this.getStock().getTicker())) {
                // if the trade was bought, add to the total
                if (trade.getTransaction().equalsIgnoreCase("buy")) {
                    totalSharesLeft += trade.getNumOfShares();
                } else {
                    // the trade was sold, so subtract from the total
                    totalSharesLeft -= trade.getNumOfShares();
                }
            }
        }

        // if the totalSharesLeft is negative, let the user know there is an error
        if (totalSharesLeft < 0) {
            PrintMessage.negativeSharesLeftError(totalSharesLeft);
        } else {
            // remove the trader from their shareholder list if they no longer have shares of the stock
            if (totalSharesLeft == 0) {
                trader.removeStockFromShareholderList(stock);
            }

            // complete the transaction by updating the trader's portfolio
            if (transaction.equalsIgnoreCase("buy")) {
                // shares have been bought; add to the portfolio
                trader.setPortfolioAmount(trader.getPortfolioAmount() + amount);
            } else {
                // shares have been sold; subtract from the portfolio
                trader.setPortfolioAmount(trader.getPortfolioAmount() - amount);
            }
        }
    }

    /**
     * Prints trade data, stock, trader, shares, share price, and portfolio amount.
     *
     * @return trade data
     */
    @Override
    public String toString() {
        return "\n\t\t\tStock traded: " + stock.getName() + " (" + stock.getTicker() + ")" +
                "\n\t\t\t\t- Trader name: " + trader.getTraderName() +
                "\n\t\t\t\t- Transaction: " + transaction +
                "\n\t\t\t\t- Shares: " + numOfShares +
                "\n\t\t\t\t- Price/Share: " + String.format("$%.2f", getStock().getCurrentPricePerShare()) +
                "\n\t\t\t\t- Amount: " + String.format("$%.2f", amount);
    }
}
