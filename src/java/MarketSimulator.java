public class MarketSimulator {

	private MarketDataSource ds;
	private BuyingStrategy bs;
	private SellingStrategy ss;
	private PortfolioManager pm;

	public MarketSimulator(PortfolioManager portfolioManager) {
		this.pm = portfolioManager;
	}

	public void addDataSource(MarketDataSource ds) {
		this.ds = ds;
	}

	public void addBuyingStrategy(BuyingStrategy bs) {
		this.bs = bs;

	}

	public void addSellingStrategy(SellingStrategy ss) {
		this.ss = ss;

	}

	public void simulate() {
		DayData dd = null;
		while ((dd = ds.getNextDayData()) != null) {

			Operation buyop = bs.computeOperation(dd);
			Operation sellop = ss.computeOperation(dd);
			PortfolioManager pm = getPortfolioManager();
			if (buyop != null)
				pm.performOp(buyop, dd);
			if (sellop != null)
				pm.performOp(sellop, dd);
		}
	}

	private PortfolioManager getPortfolioManager() {
		return this.pm;
	}

	public MarketReport getReport() {
		return getPortfolioManager().getReport();

	}

}
